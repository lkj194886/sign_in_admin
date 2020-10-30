package com.sign_in.code.controller;

import com.sign_in.code.service.SignInService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Classname SignInTableController
 * @Description TODO
 * @Date 2020/10/19 14:11
 * @Created by wgg
 */
@RequestMapping("/sign")
@RestController
public class SignInTableController {
    @Autowired
    SignInService signInService;

    @RequestMapping("/signIn")
    public Result<Map<String, Object>> signIn(@RequestParam("uid") Long uid) {
        return signInService.sign(uid);
    }
}
