package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description TODO 用户mapper
 * @Date 2020/10/20 14:19
 * @Created by wgg
 */
public interface UserMapper {
    //登陆
    SignInUser getUser(@Param("phone") String phone);
    //根据邀请人ID查询用户
    SignInUser getUserId(@Param("userInvitationId") Long userInvitationId);
    //添加用户
    int  addUser(SignInUser signInUser);
    //邀请人的id
    long getUserInvitationId(@Param("phone") String phone);
    //邀请进度
    List<SignInUser> getInvitationProgress(@Param("invitationCodeId")long invitationCodeId);

    //绑定微信账号
    int boundWeiXin(@Param("uid") Long uid,@Param("userWeiXin") String userWeiXin,@Param("userWeiXinName") String userWeiXinName);

    //绑定支付宝账号
    int boundZhiFuBao(@Param("uid") Long uid,@Param("userZhiFUBao") String userZhiFUBao,@Param("userZhiFUBaoName") String userZhiFUBaoName);
}
