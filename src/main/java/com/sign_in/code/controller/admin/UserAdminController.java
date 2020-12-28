package com.sign_in.code.controller.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Classname UserAdminController
 * @Description TODO
 * @Date 2020/12/05 16:36
 * @Created by wgg
 */
@RequestMapping("/admin/user")
@RestController
public class UserAdminController {
    @Autowired
    SignInUserService signInUserService;

    //获取用户集合
    @RequestMapping("/getUserList")
    Result<Map<String, Object>> getUserList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam("userName") String userName, @RequestParam("conditions") String conditions) {
        return signInUserService.getUserList(pageNum, userName, conditions);
    }

    //修改用户
    @RequestMapping("/updateUser")
    Result<Map<String, Object>> updateUser(
            @RequestParam("userId") Long userId,
            @RequestParam("userRemainingSum") BigDecimal userRemainingSum,
            @RequestParam("userQiBi") BigDecimal userQiBi,
            @RequestParam("userWeiXinName") String userWeiXinName,
            @RequestParam("userWeiXin") String userWeiXin,
            @RequestParam("userZhiFuBaoName") String userZhiFuBaoName,
            @RequestParam("userZhiFuBao") String userZhiFuBao,
            @RequestParam("userVipId") Long userVipId
    ) {
//        System.out.println("signInUser = " + signInUser.toString());
        System.out.println("userId = " + userId);
        SignInUser signInUser = new SignInUser();
        signInUser.setUserId(userId);
        signInUser.setUserRemainingSum(userRemainingSum);
        signInUser.setUserQiBi(userQiBi);
        signInUser.setUserWeiXinName(userWeiXinName);
        signInUser.setUserWeiXin(userWeiXin);
        signInUser.setUserZhiFUBaoName(userZhiFuBaoName);
        signInUser.setUserZhiFUBao(userZhiFuBao);
        signInUser.setUserVipId(userVipId);
        System.out.println("signInUser = " + signInUser.toString());
        return signInUserService.updateUser(signInUser);
    }
}
