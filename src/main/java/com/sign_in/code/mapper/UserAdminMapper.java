package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInUserAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname UserAdminMapper
 * @Description TODO 管理员mapper
 * @Date 2020/10/20 14:19
 * @Created by wgg
 */
public interface UserAdminMapper {
    //管理员登陆
    public SignInUserAdmin getUserAdmin(@Param("name") String name);

    //新增管理员
    int addAdmin(@Param("name") String name, @Param("pwd") String pwd);
}
