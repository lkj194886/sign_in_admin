package com.sign_in.code.service;

import com.sign_in.code.util.Result;

import java.util.Map;

/**
 * @Classname AdvertisingService
 * @Description TODO
 * @Date 2020/11/10 23:04
 * @Created by wgg
 */
public interface AdvertisingService {

    Result<Map<String,Object>> getAdvertising(String phone) throws InterruptedException;
}
