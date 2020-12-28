package com.sign_in.code.service;

import com.sign_in.code.entity.SignInActive;
import com.sign_in.code.util.Result;

import java.util.Map;

/**
 * @Classname SignInActiveService
 * @Description TODO
 * @Date 2020/11/29 18:51
 * @Created by wgg
 */
public interface SignInActiveService {
    Result<Map<String,Object>> getActiveList(Long uid);
    int addActive(SignInActive signInActive);

}
