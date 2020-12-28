package com.sign_in.code.service;


import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.util.Result;


import java.io.IOException;
import java.math.BigDecimal;

import java.util.Map;


/**
 * @Classname SignInUserService
 * @Description 用户逻辑层
 * @Date 2020/10/20 14:16
 * @Created by wgg
 */
public interface SignInUserService {

    //登陆
    Result<Map<String,Object>> getUser(String phone, String phoneCode);
    //邀请人的id
    long getUserInvitationId(String phone);
    //邀请进度
    Result<Map<String,Object>> getInvitationProgress(String invitationCodePhone);

    //绑定微信
    Result<Map<String,Object>> boundWeiXin(Long uid, String weiXin,String weiXinName);

    //绑定支付宝
    Result<Map<String,Object>> boundZhiFuBao(Long uid, String zhiFUBao,String zhiFUBaoName);

    //签到道具累加
    int cumulativeProps( Long uid, BigDecimal qiBi);

    //七币提现到余额
    Result<Map<String,Object>> qiBiWithdrawal(String userPhone, BigDecimal qiBi) throws InterruptedException;
    //获取邀请图片
    Result<Map<String,Object>> getInvitationImg(String code) throws IOException;

    //生成邀请码
    String generateRandomStr();

    //注册用户
    Result<Map<String,Object>> addUser(String phone,String yqmCode,String phoneCode);

    //查询剩余观看视屏次数
    Result<Map<String,Object>> getVideoCount(String phone);
    //邀请成功添加观看视屏次数
    Result<Map<String,Object>> addVideoCount(String phone);
    //观看视屏减少剩余次数
    Result<Map<String,Object>> lessenVideoCount(String phone,int videoCount);

    //获取用户集合
    Result<Map<String,Object>> getUserList(Integer pageNum,String userAdminName,String conditions);

    //用户修改
    Result<Map<String,Object>> updateUser(SignInUser signInUser);

    //观看视频完成道具增加
    Result<Map<String,Object>> addSum(Long uid);
}
