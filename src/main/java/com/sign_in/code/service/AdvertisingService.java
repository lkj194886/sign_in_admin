package com.sign_in.code.service;

import com.sign_in.code.entity.SignInAdvErTiSing;
import com.sign_in.code.util.Result;

import java.io.IOException;
import java.util.Map;

/**
 * @Classname AdvertisingService
 * @Description TODO
 * @Date 2020/11/10 23:04
 * @Created by wgg
 */
public interface AdvertisingService {

    Result<Map<String,Object>> getAdvertising(String phone) throws InterruptedException;

    //获取广告列表
    Result<Map<String,Object>> getAdvertisingList(String title,Integer pageNum);

//    int modifyAdvErTiSing(SignInAdvErTiSing signInAdvErTiSing);
    int modifyAdvErTiSing(SignInAdvErTiSing signInAdvErTiSing);

    int addAdvErTiSing(SignInAdvErTiSing signInAdvErTiSing);
}
