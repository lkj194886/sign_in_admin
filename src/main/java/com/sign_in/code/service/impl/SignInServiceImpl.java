package com.sign_in.code.service.impl;

import com.sign_in.code.config.RedisConfig;
import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.entity.SignTable;
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
    DateUtil dateUtil;

    @Autowired
    SignInConfigService signInConfigService;

    @Autowired
    SignInUserService signInUserService;

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;

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
                                return new Result<>(200, "处理成功", null);
                            }
                        }
                    } else {
                        //签到天数满三十天重新记录天数
                        if (signTable.getSignInDaysCount() == 30) {

                            if (signInMapper.signIn(signTable(uid, datenew, 1l)) > 0) {
                                //用户道具累加
                                if (signInUserService.cumulativeProps(uid, BigDecimal.valueOf(signTable.getSignInDaysCount() * qiBi)) > 0) {
                                    return new Result<>(200, "处理成功", null);
                                }
                            }
                        } else {
                            //签到信息累加
                            if (signInMapper.signIn(signTable(uid, datenew, signTable.getSignInDaysCount() + 1)) > 0) {
                                //用户道具累加
                                if (signInUserService.cumulativeProps(uid, BigDecimal.valueOf((signTable.getSignInDaysCount() + 1) * qiBi)) > 0) {
                                    return new Result<>(200, "处理成功", null);
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
                        return new Result<>(200, "处理成功", null);
                    }
                }
            }
        } catch (Exception e) {
            //打印异常
            logger.error(LoggerUtil.getTrace(e));
        }
        return new Result<>(500, "处理失败,\n请联系管理员!", null);
    }


    //创建签到对象
    public static SignTable signTable(Long uid, Date date, Long count) {
        SignTable signTable = new SignTable();
        signTable.setSignInUserId(uid);
        signTable.setSignInDaysCount(count);
        signTable.setSignInDate(date);
        return signTable;
    }
}
