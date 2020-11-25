package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInAdvErTiSing;

/**
 * @Classname AdvertisingMapper
 * @Description TODO 广告Mapper
 * @Date 2020/11/10 22:32
 * @Created by wgg
 */
public interface AdvertisingMapper {
    //随机取出一条广告
    SignInAdvErTiSing getAdvertising();
}
