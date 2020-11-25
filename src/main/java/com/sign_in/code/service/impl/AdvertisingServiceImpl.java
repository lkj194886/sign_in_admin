package com.sign_in.code.service.impl;

import com.sign_in.code.entity.SignInAdvErTiSing;
import com.sign_in.code.mapper.AdvertisingMapper;
import com.sign_in.code.service.AdvertisingService;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname AdvertisingServiceImpl
 * @Description TODO
 * @Date 2020/11/10 23:05
 * @Created by wgg
 */
@Service
public class AdvertisingServiceImpl implements AdvertisingService {
    @Autowired
    AdvertisingMapper advertisingMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result<Map<String, Object>> getAdvertising(String phone) throws InterruptedException {
        Thread.sleep(2000);
        SignInAdvErTiSing signInAdvErTiSing = new SignInAdvErTiSing();
        signInAdvErTiSing = advertisingMapper.getAdvertising();
        if (redisUtil.get(phone) != null) {
            //重新设置用户过期时间
            redisUtil.expric(phone, 1800);
            if (signInAdvErTiSing != null) {
                Map<String, Object> stringObjectMap = new HashMap<>();
                stringObjectMap.put("Advertising", signInAdvErTiSing);
                return new Result<>(200, "处理成功", stringObjectMap);
            }
        } else {
            return new Result<>(403, "登陆已过期,请重新登陆", null);
        }

        return new Result<>(500, "处理失败", null);
    }
}
