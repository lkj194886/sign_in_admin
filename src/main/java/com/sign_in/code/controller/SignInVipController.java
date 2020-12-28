package com.sign_in.code.controller;

import com.sign_in.code.entity.SignInVip;
import com.sign_in.code.service.SignVipService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    Result<Map<String,Object>> modifyVip(@RequestBody SignInVip signInVip)
    {
//        SignInVip signInVip1 = new SignInVip();
        return signVipService.modifyVip(signInVip);
    }

    /**
     * 添加新的会员
     * @param signInVip 会员实体类
     * @return
     */
    @RequestMapping("/addVip")
    Result<Map<String,Object>> addVip(@RequestBody SignInVip signInVip){
        return signVipService.addVip(signInVip);
    }

    /**
     * 删除会员
     * @param vipId 会员ID
     * @return
     */
    @RequestMapping("/removeVip/{vipId}")
    Result<Map<String,Object>> removeVip(@PathVariable("vipId") Long vipId){
        return  signVipService.removeVip(vipId);
    }


    /**
     * 加入合伙人
     * @param userId    用户id
     * @param userParentId  是否加入会员1/0
     * @return
     */
    @RequestMapping("/joinAPartner")
    Result<Map<String,Object>> joinAPartner(@RequestParam("userId") Long userId,
                                            @RequestParam("userPartnerId") Long userPartnerId){
        Map<String,Object> joinMap = new HashMap();
        joinMap.put("userId",userId);
        joinMap.put("userPartnerId",userPartnerId);
        return signVipService.joinAPartner(joinMap);
    }
}
