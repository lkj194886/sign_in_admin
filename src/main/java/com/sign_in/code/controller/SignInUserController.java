package com.sign_in.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.AliYunUtil;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SignInUserController
 * @Description TODO
 * @Date 2020/10/19 13:18
 * @Created by wgg
 */
@RequestMapping("/user")
@Controller
public class SignInUserController {

    @Autowired
    SignInUserService signInUserService;

    @Autowired
    AliYunUtil aliYunUtil;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 短信接口
     *
     * @param phone 手机号
     * @return 返回处理成功信息
     */
    @RequestMapping("/getPhoneCode")
    @ResponseBody
    public Result<Map<String, Object>> getPhoneCode(@RequestParam("phone") String phone) {
        int code = aliYunUtil.aliYunCode(phone);
        //获取验证码后存入redis中,设置五分钟的过期时间
        redisUtil.set(phone, String.valueOf(code), 300);
        return new Result<>(200, "处理成功", null);
    }

    /**
     * 登陆接口
     * @param phone     电话号码
     * @param phoneCode 验证码
     * @return 返回用户信息
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result<Map<String, Object>> Login(@RequestParam("phone") String phone, @RequestParam("phoneCode") String phoneCode) {
        return signInUserService.getUser(phone, phoneCode);
    }

    @RequestMapping("/getInvitationP")
    @ResponseBody
    public Result<Map<String, Object>> getInvitationProgress(@RequestParam("invitationCodePhone") String invitationCodePhone){
        return signInUserService.getInvitationProgress(invitationCodePhone);
    }
}
