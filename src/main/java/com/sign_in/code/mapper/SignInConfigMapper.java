package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname SignInConfigMapper
 * @Description TODO
 * @Date 2020/10/30 15:37
 * @Created by wgg
 */
public interface SignInConfigMapper {
    SignInConfig getSignInConfig();
    //获取公告以及邀请页背景
    SignInConfig getBackImg();

    //获取首页背景
    String getIndexBackImg(Long sid);

    //获取提现率
    Double getWithdrawalRate();
    SignInConfig getAdminConfig();

    List<SignInConfig> getIndexBack();

    int modifyConfig(SignInConfig signInConfig);

    int modifyIndexBack(@Param("cid") Long cid, @Param("indexBack") String indexBack);
}
