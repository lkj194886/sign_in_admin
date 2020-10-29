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
import org.springframework.web.bind.annotation.RestController;

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
@RestController
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
    public Result<Map<String, Object>> getPhoneCode(@RequestParam("phone") String phone) {
        int code = aliYunUtil.aliYunCode(phone);
        //获取验证码后存入redis中,设置五分钟的过期时间
        redisUtil.set(phone, String.valueOf(code), 300);
        return new Result<>(200, "处理成功", null);
    }

    /**
     * 登陆接口
     *
     * @param phone     电话号码
     * @param phoneCode 验证码
     * @return 返回用户信息
     */
    @RequestMapping("/login")
    public Result<Map<String, Object>> Login(@RequestParam("phone") String phone, @RequestParam("phoneCode") String phoneCode) {
        return signInUserService.getUser(phone, phoneCode);
    }

    /**
     * 邀请进度
     * @param invitationCodePhone 用户手机号
     * @return 进度List
     */
    @RequestMapping("/getInvitationP")
    public Result<Map<String, Object>> getInvitationProgress(@RequestParam("invitationCodePhone") String invitationCodePhone) {
        return signInUserService.getInvitationProgress(invitationCodePhone);
    }

    /**
     * 绑定微信
     *
     * @param uid          用户ID
     * @param weiXinNumber 用户微信号
     * @param weiXinName   用户微信姓名
     * @return
     */
    @RequestMapping("/boundWeixin")
    public Result<Map<String, Object>> boundWeixin(@RequestParam("uid") Long uid, @RequestParam("weiXinNumber") String weiXinNumber, @RequestParam("weiXinName") String weiXinName) {
        return signInUserService.boundWeiXin(uid, weiXinNumber, weiXinName);
    }

    /**
     * 绑定支付宝
     *
     * @param uid            用户id
     * @param zhiFuBaoNumber 用户支付宝账号
     * @param zhiFuBaoName   支付宝姓名
     * @return
     */
    @RequestMapping("/boundZhiFuBao")
    public Result<Map<String, Object>> boundZhiFuBao(@RequestParam("uid") Long uid,@RequestParam("zhiFuBaoNumber") String zhiFuBaoNumber, @RequestParam("zhiFuBaoName")String zhiFuBaoName) {
        return signInUserService.boundZhiFuBao(uid, zhiFuBaoNumber, zhiFuBaoName);
    }
}
