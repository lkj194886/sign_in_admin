package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInConfig;

/**
 * @Classname SignInConfigMapper
 * @Description TODO
 * @Date 2020/10/30 15:37
 * @Created by wgg
 */
public interface SignInConfigMapper {
    SignInConfig getSignInConfig();
    //获取公告以及邀请页背景
    SignInConfig getBackImg();
}
