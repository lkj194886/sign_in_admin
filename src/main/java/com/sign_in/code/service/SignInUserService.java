package com.sign_in.code.service;

import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.util.Result;

import java.util.Map;


/**
 * @Classname SignInUserService
 * @Description 用户逻辑层
 * @Date 2020/10/20 14:16
 * @Created by wgg
 */
public interface SignInUserService {
    //登陆
    SignInUser getUser(String phone);
    //根据ID查询用户
    Result<Map<String,Object>> getUserId(String phone, String phoneCode);
}
