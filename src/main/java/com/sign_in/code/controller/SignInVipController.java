package com.sign_in.code.controller;

import com.sign_in.code.entity.SignInVip;
import com.sign_in.code.service.SignVipService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Classname SignInVipController
 * @Description TODO vip视图层
 * @Date 2020/10/19 13:21
 * @Created by wgg
 */
@RestController
@RequestMapping("/vip")
public class SignInVipController {

    @Autowired
    SignVipService signVipService;

    /**
     * 获取vip信息
     * @return
     */
    @RequestMapping("/getVipList")
    Result<Map<String, Object>> getVipList() {
        return signVipService.getVipList();
    }

    /**
     * 修改会员信息
     * @param signInVip 会员实体类
     * @return
     */
    @RequestMapping("/modifyVip")
    Result<Map<String,Object>> modifyVip(SignInVip signInVip)
    {
        return signVipService.modifyVip(signInVip);
    }

    /**
     * 添加新的会员
     * @param signInVip 会员实体类
     * @return
     */
    @RequestMapping("/addVip")
    Result<Map<String,Object>> addVip(SignInVip signInVip){
        return signVipService.addVip(signInVip);
    }

    /**
     * 删除会员
     * @param vipId 会员ID
     * @return
     */
    @RequestMapping("/removeVip")
    Result<Map<String,Object>> removeVip(@RequestParam("vipId") Long vipId){
        return  signVipService.removeVip(vipId);
    }
}
