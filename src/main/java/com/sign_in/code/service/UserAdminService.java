package com.sign_in.code.service;

import com.sign_in.code.util.Result;

import java.util.Map;

/**
 * @Classname UserAdminService
 * @Description TODO
 * @Date 2020/11/03 17:57
 * @Created by wgg
 */
public interface UserAdminService {
    //添加新的管理员
    Result<Map<String,Object>> addUserAdmin(String name, String pwd);

    //管理员登陆
    Result<Map<String,Object>> loginAdmin(String name,String pwd);
}
