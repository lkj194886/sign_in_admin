package com.sign_in.code.service.impl;

import com.sign_in.code.entity.SignInVip;
import com.sign_in.code.mapper.SignVipMapper;
import com.sign_in.code.service.SignVipService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SignVipServiceImpl
 * @Description TODO
 * @Date 2020/10/25 1:58
 * @Created by wgg
 */
@Service
public class SignVipServiceImpl implements SignVipService {

    @Autowired
    SignVipMapper signVipMapper;

    @Override
    public Result<Map<String, Object>> getVipList() {
        Map<String, Object> vipMap = new HashMap<>();
        vipMap.put("vipList", signVipMapper.getVipList());
        return new Result<>(200, "处理成功", vipMap);
    }

    @Override
    public Result<Map<String, Object>> modifyVip(SignInVip signInVip) {

        if (signInVip.getVipName() == null) {
            return new Result<>(500, "vipName不能为空", null);
        }
        if (signInVip.getVipExchangeRate() == null) {
            return new Result<>(500, "vipExchangeRate不能为空", null);
        }
        int code = signVipMapper.modifyVip(signInVip);
        if (code > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> addVip(SignInVip signInVip) {

        if (signInVip.getVipName() == null) {
            return new Result<>(500, "vipName不能为空", null);
        }
        if (signInVip.getVipExchangeRate() == null) {
            return new Result<>(500, "vipExchangeRate不能为空", null);
        }

        int code = signVipMapper.addVip(signInVip);
        if (code > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> removeVip(Long vipId) {
        int code  = signVipMapper.removeVip(vipId);
        if (code>0){
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }


    @Override
    public Result<Map<String, Object>> joinAPartner(Map<String,Object> mapJoin) {
        int change = signVipMapper.joinAPartner(mapJoin);
        if (change > 0){
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }
}
