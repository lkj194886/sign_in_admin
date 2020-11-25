package com.sign_in.code.controller;

import com.sign_in.code.service.SignInConfigService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Classname SignInConfigController
 * @Description TODO
 * @Date 2020/11/13 22:19
 * @Created by wgg
 */
@RequestMapping("/config")
@RestController
public class SignInConfigController {
    @Autowired
    SignInConfigService signInConfigService;

    //获取首页,邀请,公告图片
    @RequestMapping("/getBackImg")
    Result<Map<String,Object>> getBackImg(){
        return signInConfigService.getBackImg();
    }
}
