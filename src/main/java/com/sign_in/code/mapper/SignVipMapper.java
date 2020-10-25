package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInVip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname SignVipMapper
 * @Description TODO vipMapper
 * @Date 2020/10/20 14:19
 * @Created by wgg
 */
public interface SignVipMapper {
    //查询vip及信息
    List<SignInVip> getVipList();
    //修改会员信息
    int modifyVip(SignInVip signInVip);
    //添加新的会员信息
    int addVip(SignInVip signInVip);
    //删除会员
    int removeVip(@Param("vipID") Long vipID);

}
