package com.sign_in.code.service.impl;

import com.sign_in.code.service.CityService;
import com.sign_in.code.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Classname CityServiceImpl
 * @Description TODO
 * @Date 2020/10/10 16:25
 * @Created by wgg
 */
@Service
public class CityServiceImpl implements CityService {
    //请求地址
    final static String SOJSON_WEATHER_URL = "http://t.weather.itboy.net/api/weather/city/%s";

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    RedisUtil redisUtil;

    @Override
    public String getCity(String cid) throws IOException {
        //先在redis中查询当前天气数据是否存在,如果不存在.重新请求数据并存入在redis中
        if (redisUtil.get(cid) != null) {
            System.out.println("在redis中查询="+cid);
            return redisUtil.get(cid);
        } else {
            InputStream input = null;
            try {
                String city = java.net.URLEncoder.encode(cid, "utf-8");
                //拼接地址
                String apiUrl = String.format(SOJSON_WEATHER_URL, city);
                //开始请求
                URL url = new URL(apiUrl);
                URLConnection open = url.openConnection();
                input = open.getInputStream();
                String result = org.apache.commons.io.IOUtils.toString(input, "utf-8");
                //将数据存入redis,并设置过期时间
                redisUtil.set(cid, result, 14400);
                System.out.println("重新请求的数据=" + cid);
                return result;
            } catch (Exception e) {
                log.info(String.valueOf(e));
            } finally {
                input.close();
            }
            return null;
        }
    }
}