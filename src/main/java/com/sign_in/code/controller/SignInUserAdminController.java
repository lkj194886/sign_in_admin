package com.sign_in.code.controller;

import com.sign_in.code.service.UserAdminService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Classname SignInUserAdminController
 * @Description TODO
 * @Date 2020/10/19 13:19
 * @Created by wgg
 */
@RestController
@RequestMapping("/admin")
public class SignInUserAdminController {

    @Autowired
    UserAdminService userAdminService;

    //新增管理员
    @RequestMapping("/addAdmin")
    Result<Map<String,Object>> addAdmin(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd){
        return userAdminService.addUserAdmin(userName, pwd);
    }

    //管理员登陆
    @RequestMapping("/loginAdmin")
    Result<Map<String,Object>> loginAdmin(@RequestParam("name") String name ,@RequestParam("pwd") String pwd){
        return  userAdminService.loginAdmin(name, pwd);
    }
}
