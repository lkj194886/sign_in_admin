package com.sign_in.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sign_in.code.entity.SignInUserAdmin;
import com.sign_in.code.mapper.UserAdminMapper;
import com.sign_in.code.service.UserAdminService;
import com.sign_in.code.util.Md5Util;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserAdminServiceImpl
 * @Description TODO
 * @Date 2020/11/03 17:58
 * @Created by wgg
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {
    @Autowired
    Md5Util md5Util;
    @Autowired
    UserAdminMapper userAdminMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public Result<Map<String, Object>> addUserAdmin(String name, String pwd) {
        //密码加密
        String md5Pwd = md5Util.generate(pwd);
        if (userAdminMapper.addAdmin(name, md5Pwd) > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> loginAdmin(String name, String pwd) {
        SignInUserAdmin userAdmin = new SignInUserAdmin();
        userAdmin = userAdminMapper.getUserAdmin(name);
        if (userAdmin==null){
            return new Result<>(404,"管理员不存在",null);
        }
        if (md5Util.verify(pwd,userAdmin.getUserAdminPwd())) {

            Map<String, Object> user = new HashMap<>();
            user.put("userAdmin", userAdmin);
            String userJson = JSONObject.toJSONString(user);
            redisUtil.set(name,userJson,3000);
            return new Result<>(200, "处理成功", user);
        } else {
            return new Result<>(500, "密码错误", null);
        }

    }
}
