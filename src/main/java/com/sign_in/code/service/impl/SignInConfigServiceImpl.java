package com.sign_in.code.service.impl;

import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.mapper.SignInConfigMapper;
import com.sign_in.code.service.SignInConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname SignInConfigServiceImpl
 * @Description TODO 配置实体类
 * @Date 2020/10/30 15:44
 * @Created by wgg
 */
@Service
public class SignInConfigServiceImpl  implements SignInConfigService {
    @Autowired
    SignInConfigMapper signInConfigMapper;
    @Override
    public SignInConfig getSignInConfig() {
        return signInConfigMapper.getSignInConfig();
    }
}
