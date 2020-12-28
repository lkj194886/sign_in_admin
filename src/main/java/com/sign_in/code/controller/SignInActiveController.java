package com.sign_in.code.controller;

import com.sign_in.code.service.SignInActiveService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Classname SignInActiveController
 * @Description TODO
 * @Date 2020/11/29 20:33
 * @Created by wgg
 */
@RestController
@RequestMapping("/active")
public class SignInActiveController {
    @Autowired
    SignInActiveService signInActiveService;
    @RequestMapping("/getActiveList")
    Result<Map<String,Object>> getActiveList(@RequestParam("uid") Long uid){
        return signInActiveService.getActiveList(uid);
    }
}
