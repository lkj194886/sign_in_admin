package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInUser;
import org.apache.ibatis.annotations.Param;

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
}
