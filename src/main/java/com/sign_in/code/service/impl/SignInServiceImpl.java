package com.sign_in.code.service.impl;

import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.entity.SignTable;
import com.sign_in.code.mapper.SignInMapper;
import com.sign_in.code.service.SignInConfigService;
import com.sign_in.code.service.SignInService;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.DateUtil;
import com.sign_in.code.util.Result;
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

    @Autowired
    SignInMapper signInMapper;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    SignInConfigService signInConfigService;

    @Autowired
    SignInUserService signInUserService;
    @Override
    public Result<Map<String, Object>> sign(Long uid) {
        SignTable signTable = signInMapper.getSignIn(uid);
//        System.out.println("signTable = " + signTable.toString());
        SignInConfig signInConfig = signInConfigService.getSignInConfig();
        Date newdate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp datenew = Timestamp.valueOf(simpleDate.format(newdate));
        //七币数量
        Double qiBi= signInConfig.getConfigSignInProps();
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
            }
            else {
                int yesterday=Integer.parseInt( dateMap.get("date").toString());
                int today=Integer.parseInt(newdateMap.get("date").toString());
                int countday = today-yesterday;
                //判断昨日是否签到,如果有天数和奖励累加,如果昨天没有签到,则重新累加天数和奖励.
                System.out.println("countday = " + countday);
                if (countday > 1) {
                    //签到中断,重新累加签到天数
                    SignTable signTable1 = signTable(uid,datenew,1l);
                    if (signInMapper.signIn(signTable1) > 0) {
                        if (signInUserService.cumulativeProps(uid,BigDecimal.valueOf(signTable1.getSignInDaysCount()*qiBi))>0){
                            return new Result<>(200, "处理成功", null);
                        }
                    }
                } else {
                    //签到天数满三十天重新记录天数
                    if (signTable.getSignInDaysCount() == 30) {

                        if (signInMapper.signIn(signTable(uid, datenew, 1l)) > 0) {
                            //用户道具累加
                            if (signInUserService.cumulativeProps(uid,BigDecimal.valueOf(signTable.getSignInDaysCount()*qiBi)) > 0) {
                                return new Result<>(200, "处理成功", null);
                            }
                        }
                    } else {
                        //签到信息累加
                        if (signInMapper.signIn(signTable(uid, datenew, signTable.getSignInDaysCount() + 1)) > 0) {
                            //用户道具累加
                            if (signInUserService.cumulativeProps(uid,BigDecimal.valueOf((signTable.getSignInDaysCount() + 1)*qiBi)) > 0) {
                                return new Result<>(200, "处理成功", null);
                            }
                        }
                    }
                }
            }
        }
        //没有签到过则添加新的签到记录
        else {
            SignTable signTable1 = signTable(uid,datenew,1l);
//            System.out.println("signTable1.toString() = " + signTable1.toString());
            if (signInMapper.newSingIn(signTable1 )>0) {
                //增加道具
                if (signInUserService.cumulativeProps(uid,BigDecimal.valueOf(signTable1.getSignInDaysCount()*qiBi)) > 0) {
                    return new Result<>(200, "处理成功", null);
                }
            }
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
