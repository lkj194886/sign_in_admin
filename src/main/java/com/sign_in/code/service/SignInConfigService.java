package com.sign_in.code.service;

import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.util.Result;

import java.util.Map;

/**
 * @Classname SignInConfigService
 * @Description TODO
 * @Date 2020/10/30 15:42
 * @Created by wgg
 */
public interface SignInConfigService {
    SignInConfig getSignInConfig();
    Result<Map<String,Object>> getBackImg();
    Result<Map<String,Object>> getAdminConfig();
    Result<Map<String,Object>> getIndexBack(Integer pageNum);
    int modifyConfig(SignInConfig signInConfig);
    Result<Map<String,Object>> modifyIndexBack(Long cid ,String indexBack);
}
