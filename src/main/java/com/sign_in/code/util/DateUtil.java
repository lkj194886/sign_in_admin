package com.sign_in.code.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DateUtil
 * @Description TODO
 * @Date 2020/10/29 23:48
 * @Created by wgg
 */
@Component
public class DateUtil {
    //返回年份.月份.天数
    public Map<String,Object> getDateString(Date data){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        //获取年份
        int year = c.get(Calendar.YEAR);
        //获取月份
        int month = c.get(Calendar.MONTH);
        //获取天数
        int date = c.get(Calendar.DATE);
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        map.put("month",month+1);
        map.put("date",date);
        return map;
    }

    //返回年份.月份.天数
    public Map<String,Object> getTheCurrentDate(){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //获取年份
        int year = c.get(Calendar.YEAR);
        //获取月份
        int month = c.get(Calendar.MONTH);
        //获取天数
        int date = c.get(Calendar.DATE);
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        map.put("month",month+1);
        map.put("date",date);
        return map;
    }
}
