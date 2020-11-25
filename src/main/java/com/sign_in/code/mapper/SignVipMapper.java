package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInVip;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

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
    //加入合伙人
    int joinAPartner(Map<String,Object> mapJoin);
    //根据vipID查询七币提现兑换率
    Double getExchangeRate(@Param("vid") Long vid);
    //修改七币兑换率
    int modifyChangeRate(@Param("vid") Long vid,@Param("changeRate") Double changeRate);
}
