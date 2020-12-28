package com.sign_in.code.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.mapper.SignInConfigMapper;
import com.sign_in.code.service.SignInConfigService;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SignInConfigServiceImpl
 * @Description TODO 配置实体类
 * @Date 2020/10/30 15:44
 * @Created by wgg
 */
@Service
public class SignInConfigServiceImpl implements SignInConfigService {
    @Autowired
    SignInConfigMapper signInConfigMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public SignInConfig getSignInConfig() {
        return signInConfigMapper.getSignInConfig();
    }

    @Override
    public Result<Map<String, Object>> getBackImg() {
        if (redisUtil.get("signInConfig") == null) {
            SignInConfig signInConfig = signInConfigMapper.getBackImg();
            if (signInConfig != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("signInConfig", signInConfig);
                String signJson = JSONObject.toJSONString(signInConfig);
                redisUtil.set("signInConfig", signJson, 30000);
                return new Result<>(200, "处理成功", map);
            }
        } else {
            Map<String, Object> map = new HashMap<>();
            String signInConfig = redisUtil.get("signInConfig");
            SignInConfig sign = (SignInConfig) JSONObject.parseObject(signInConfig, SignInConfig.class);
            map.put("signInConfig", sign);
            return new Result<>(200, "处理成功", map);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> getAdminConfig() {
        SignInConfig config = signInConfigMapper.getAdminConfig();
        if (config!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("config",config);
            return new Result<>(200,"处理成功",map);
        }
        return new Result<>(500,"处理失败",null);
    }

    @Override
    public Result<Map<String, Object>> getIndexBack(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<SignInConfig> list = signInConfigMapper.getIndexBack();
        if (list!=null){
            PageInfo<SignInConfig> pageInfo = new PageInfo<>(list);
            Map<String,Object> map = new HashMap<>();
            map.put("pageInfo",pageInfo);
            return new Result<>(200,"处理成功",map);
        }
        return new Result<>(500,"处理失败",null);
    }

    @Override
    public int modifyConfig(SignInConfig signInConfig) {
        return signInConfigMapper.modifyConfig(signInConfig);
    }

    @Override
    public Result<Map<String, Object>> modifyIndexBack(Long cid, String indexBack) {
        if (signInConfigMapper.modifyIndexBack(cid,indexBack)>0){
            return new Result<>(200,"处理成功",null);
        }
        return new Result<>(500,"处理失败",null);
    }
}
