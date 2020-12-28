package com.sign_in.code.service.impl;

import com.sign_in.code.config.RedisConfig;
import com.sign_in.code.entity.SignInActive;
import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.entity.SignTable;
import com.sign_in.code.mapper.SignInActiveMapper;
import com.sign_in.code.mapper.SignInConfigMapper;
import com.sign_in.code.mapper.SignInMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.service.SignInConfigService;
import com.sign_in.code.service.SignInService;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.DateUtil;
import com.sign_in.code.util.LoggerUtil;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SignInServiceImpl
 * @Description TODO
 * @Date 2020/10/29 23:06
 * @Created by wgg
 */
@Service
public class SignInServiceImpl implements SignInService {
    private Logger logger = LoggerFactory.getLogger(SignInServiceImpl.class);
    @Autowired
    SignInMapper signInMapper;

    @Autowired
    SignInActiveMapper signInActiveMapper;
    @Autowired
    DateUtil dateUtil;

    @Autowired
    SignInConfigService signInConfigService;

    @Autowired
    SignInUserService signInUserService;

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SignInConfigMapper signInConfigMapper;
    @Override
    public Result<Map<String, Object>> sign(Long uid) {
        try {
            SignTable signTable = signInMapper.getSignIn(uid);
            SignInUser signInUser = new SignInUser();
            signInUser = userMapper.getUserId(uid);
            if (redisUtil.get(signInUser.getUserName()) == null) {
                return new Result<>(401, "登陆已过期,请重新登陆!", null);
            }
            SignInConfig signInConfig = signInConfigService.getSignInConfig();
            Date newdate = new Date();
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp datenew = Timestamp.valueOf(simpleDate.format(newdate));
            //七币数量
            Double qiBi = signInConfig.getConfigSignInProps();

            //查询是否有签到记录
            if (signTable != null) {
                //获取签到时间
                Date date = signTable.getSignInDate();
                //格式化时间
                Map<String, Object> dateMap = dateUtil.getDateString(date);
                Map<String, Object> newdateMap = dateUtil.getTheCurrentDate();
                //判断上次签到时间是否和今日一致
                if (dateMap.get("year").equals(newdateMap.get("year")) && dateMap.get("month").equals(newdateMap.get("month")) && dateMap.get("date").equals(newdateMap.get("date"))) {
                    return new Result<>(403, "今日已签到", null);
                } else {
                    //昨天签到时间
                    int yesterday = Integer.parseInt(dateMap.get("date").toString());
                    //获取今日签到时间
                    int today = Integer.parseInt(newdateMap.get("date").toString());
                    //今日减去昨日.大于一天则签到中断
                    int countday = today - yesterday;
                    //判断昨日是否签到,如果有天数和奖励累加,如果昨天没有签到,则重新累加天数和奖励.
//                System.out.println("countday = " + countday);
                    if (countday > 1) {
                        //签到中断,重新累加签到天数
                        SignTable signTable1 = signTable(uid, datenew, 1l);
                        if (signInMapper.signIn(signTable1) > 0) {

                            if (signInUserService.cumulativeProps(uid, BigDecimal.valueOf(signTable1.getSignInDaysCount() * qiBi)) > 0) {
                                if (signInActive(BigDecimal.valueOf(signTable1.getSignInDaysCount() * qiBi), uid, "签到", signInUser.getUserInvitationId())) {
                                    return new Result<>(200, "处理成功", getUserContent(uid));
                                } else {
                                    return new Result<>(500, "处理失败", null);
                                }
                            }
                        }
                    } else {
                        //签到天数满三十天重新记录天数
                        if (signTable.getSignInDaysCount() == 30) {

                            if (signInMapper.signIn(signTable(uid, datenew, 1l)) > 0) {
                                //用户道具累加
                                SignTable signTable1 = signTable(uid, datenew, 1l);
                                if (signInUserService.cumulativeProps(uid, BigDecimal.valueOf(signTable1.getSignInDaysCount())) > 0) {
                                    if (signInActive(BigDecimal.valueOf(signTable.getSignInDaysCount() * qiBi), uid, "签到", signInUser.getUserInvitationId())) {
                                        return new Result<>(200, "处理成功", getUserContent(uid));
                                    } else {
                                        return new Result<>(500, "处理失败", null);
                                    }
                                }
                            }
                        } else {
                            //签到信息累加
                            if (signInMapper.signIn(signTable(uid, datenew, signTable.getSignInDaysCount() + 1)) > 0) {
                                //用户道具累加
                                SignTable signTable1 = signTable(uid, datenew, signTable.getSignInDaysCount() + 1);
                                if (signInUserService.cumulativeProps(uid, BigDecimal.valueOf(signTable1.getSignInDaysCount())) > 0) {
                                    if (signInActive(BigDecimal.valueOf(signTable.getSignInDaysCount() * qiBi), uid, "签到", signInUser.getUserInvitationId())) {
                                        return new Result<>(200, "处理成功", getUserContent(uid));
                                    } else {
                                        return new Result<>(500, "处理失败", null);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //没有签到过则添加新的签到记录
            else {
                SignTable signTable1 = signTable(uid, datenew, 1l);
//            System.out.println("signTable1.toString() = " + signTable1.toString());
                if (signInMapper.newSingIn(signTable1) > 0) {
                    //增加道具
                    if (signInUserService.cumulativeProps(uid, BigDecimal.valueOf(signTable1.getSignInDaysCount() * qiBi)) > 0) {
                        if (signInActive(BigDecimal.valueOf(signTable1.getSignInDaysCount() * qiBi), uid, "签到", signInUser.getUserInvitationId())) {
                            return new Result<>(200, "处理成功", getUserContent(uid));
                        } else {
                            return new Result<>(500, "处理失败", null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            //打印异常
            logger.error(LoggerUtil.getTrace(e));
        }
        return new Result<>(500, "处理失败,\n请联系管理员!", null);
    }


    public Map<String, Object> getUserContent(Long uid) {

        SignInUser signInUser = new SignInUser();
        Map<String, Object> map = new HashMap<>();
        signInUser = userMapper.getUserId(uid);
        map.put("user", signInUser);
        SignInUser userInvitationId = userMapper.getUserId(signInUser.getUserInvitationId());
//        System.out.println("userInvitationId = " + userInvitationId);
        if (userInvitationId!=null) {
            map.put("userInvitation", userInvitationId.getUserInvitationCode());
        }
        SignTable signTable = signInMapper.getSignIn(signInUser.getUserId());
        String indexBack = "";
        if (signTable.getSignInDaysCount() == 0 || signTable==null) {
            indexBack = signInConfigMapper.getIndexBackImg(1l);
        } else {
            indexBack = signInConfigMapper.getIndexBackImg(signTable.getSignInDaysCount());
        }
        Double withdrawalRate = signInConfigMapper.getWithdrawalRate();
        Double videoRate = signInConfigMapper.getSignInConfig().getVideoRate();
        map.put("withdrawalRate",withdrawalRate);
        map.put("videoRate",videoRate);
        map.put("indexBack", indexBack);
        map.put("videCount",signInConfigMapper.getSignInConfig().getConfigVideoCount());
        map.put("signExist", false);
        return map;
    }

    //创建签到对象
    public static SignTable signTable(Long uid, Date date, Long count) {
        SignTable signTable = new SignTable();
        signTable.setSignInUserId(uid);
        signTable.setSignInDaysCount(count);
        signTable.setSignInDate(date);
        return signTable;
    }

    //签到完成判断上级是否是合伙人,如果是则添加活跃奖励如果不是则不添加

    /**
     * @param sum        活跃奖励
     * @param juniorUid  下级ID
     * @param activeType 活跃奖励类型
     * @param superiorId 上级ID
     * @return
     */
    public boolean signInActive(BigDecimal sum, Long juniorUid, String activeType, Long superiorId) {
        SignInConfig signInConfig = signInConfigService.getSignInConfig();
        Double active_sign_in = signInConfig.getConfigActiveSignIn();
        SignInUser signInUser = userMapper.getUserId(superiorId);
        BigDecimal newSum = new BigDecimal(active_sign_in);
        BigDecimal nnsum = sum.multiply(newSum);
//        System.out.println("上级获取签到的百分之 = " + active_sign_in);
//        System.out.println("当前用户签到获取的道具数量 = " + sum);
//        System.out.println("newSum = " + newSum);
//        System.out.println("合伙人添加的道具数量 = " + nnsum);
        if (signInUser != null) {
            if (signInUser.getUserPartnerId() == 1) {
                SignInActive signInActive = new SignInActive();
                signInActive.setActiveSum(nnsum);
                signInActive.setActiveUserId(superiorId);
                signInActive.setActiveJuniorUserId(juniorUid);
                signInActive.setActiveType(activeType);
                if (signInActiveMapper.addActive(signInActive) > 0) {
                    if (signInUserService.cumulativeProps(superiorId, nnsum) > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }


        return true;
    }
}
