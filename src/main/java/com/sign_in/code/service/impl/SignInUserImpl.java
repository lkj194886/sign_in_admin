package com.sign_in.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SignInUserImpl
 * @Description TODO
 * @Date 2020/10/20 15:53
 * @Created by wgg
 */
@Service
public class SignInUserImpl implements SignInUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

//    @Override
//    public SignInUser getUser(String phone) {
//        //获取用户信息
//        return userMapper.getUser(phone);
//    }

    /**
     * 登陆接口
     * @param phone     电话号码
     * @param phoneCode 验证码
     * @return 返回用户信息
     */
    @Override
    public Result<Map<String, Object>> getUser(String phone, String phoneCode) {
        //判断是否存在该手机号的验证码
        if (redisUtil.get(phone) == null) {
            return new Result<>(404, "验证码已失效,请重新获取", null);
        } else {
            //判断验证码是否一致
            if (phoneCode.equals(redisUtil.get(phone))) {
                //验证成功,删除redis中的验证码
                redisUtil.remove(phone);
                //获取登陆用户信息
                SignInUser signInUser = userMapper.getUser(phone);
                //获取邀请人邀请码
                SignInUser userInvitationId = userMapper.getUserId(signInUser.getUserInvitationId());
                //将信息存入map中返回前端app
                Map<String, Object> map = new HashMap<>();
                map.put("user", signInUser);
                map.put("userInvitation", userInvitationId.getUserInvitationCode());
                String userJson = JSONObject.toJSONString(map);
                //将数据库信息存入redis当中,并设置三十分钟过期时间
                redisUtil.set(phone, userJson, 1800);
                return new Result<>(200, "处理成功", map);
            }
            //验证码错误返回信息
            return new Result<>(500, "验证码错误", null);
        }

    }
}
