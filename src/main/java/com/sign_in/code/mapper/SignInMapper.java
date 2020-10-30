package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignTable;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname SignInMapper
 * @Description TODO 签到mapper
 * @Date 2020/10/20 14:20
 * @Created by wgg
 */
public interface SignInMapper {
    //未签到过(添加新的签到)
    int newSingIn(SignTable signTable);
    //签到
    int signIn(SignTable signTable);
    //查询签到记录
    SignTable getSignIn(@Param("uid") Long uid);
}
