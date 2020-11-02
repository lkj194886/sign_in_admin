package com.sign_in.code.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Classname WithdrawalMapper
 * @Description TODO 提现Mapper
 * @Date 2020/10/20 14:20
 * @Created by wgg
 */
public interface WithdrawalMapper {
    //余额提现
    int balanceWithdrawal(@Param("uid") Long uid, @Param("balance") BigDecimal balance,@Param("status") String status);
}
