package com.sign_in.code.service;

import com.sign_in.code.entity.SignInVip;
import com.sign_in.code.util.Result;

import java.util.List;
import java.util.Map;

/**
 * @Classname SignVipService
 * @Description TODO
 * @Date 2020/10/25 1:57
 * @Created by wgg
 */
public interface SignVipService {
    //查询会员
    Result<Map<String,Object>> getVipList();
    //修改会员
    Result<Map<String, Object>> modifyVip(SignInVip signInVip);
    //添加会员
    Result<Map<String,Object>> addVip(SignInVip signInVip);
    //删除会员
    Result<Map<String,Object>> removeVip(Long vipId);
    //加入合伙人
    Result<Map<String,Object>> joinAPartner(Map<String,Object> mapJoin);
    //修改会员七币兑换率
    Result<Map<String,Object>> modifyChangeRate(Long vid,Double changeRate);
}
