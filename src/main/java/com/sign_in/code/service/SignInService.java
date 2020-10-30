package com.sign_in.code.service;

import com.sign_in.code.entity.SignTable;
import com.sign_in.code.util.Result;

import java.util.Map;

/**
 * @Classname SignInService
 * @Description TODO
 * @Date 2020/10/29 23:05
 * @Created by wgg
 */
public interface SignInService {
    //签到
    Result<Map<String,Object>> sign(Long uid);
}
