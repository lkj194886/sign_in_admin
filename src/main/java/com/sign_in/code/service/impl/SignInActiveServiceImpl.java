package com.sign_in.code.service.impl;

import com.sign_in.code.entity.SignInActive;
import com.sign_in.code.mapper.SignInActiveMapper;
import com.sign_in.code.service.SignInActiveService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SignInActiveServiceImpl
 * @Description TODO
 * @Date 2020/11/29 18:52
 * @Created by wgg
 */
@Service
public class SignInActiveServiceImpl implements SignInActiveService {

    @Autowired
    SignInActiveMapper signInActiveMapper;

    @Override
    public Result<Map<String, Object>> getActiveList(Long uid) {
        List<SignInActive> signInActiveList = signInActiveMapper.getActiveList(uid);
        Map<String, Object> map = new HashMap<>();
        if (signInActiveList!=null){
            map.put("list",signInActiveList);

            return new Result<>(200,"处理成功",map);
        }
        map.put("signInActiveList",signInActiveList);
       return new Result<>(500,"处理失败",null);
    }

    @Override
    public int addActive(SignInActive signInActive) {
        return signInActiveMapper.addActive(signInActive);
    }
}
