package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInAdvErTiSing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname AdvertisingMapper
 * @Description TODO 广告Mapper
 * @Date 2020/11/10 22:32
 * @Created by wgg
 */
public interface AdvertisingMapper {
    //随机取出一条广告
    SignInAdvErTiSing getAdvertising();

    //获取广告列表
    List<SignInAdvErTiSing> getAdvertisingList(@Param("title") String title);

    //添加广告
    int addAdvErTiSing(SignInAdvErTiSing signInAdvErTiSing);

    //修改广告
    int modifyAdvErTiSing(SignInAdvErTiSing signInAdvErTiSing);
}
