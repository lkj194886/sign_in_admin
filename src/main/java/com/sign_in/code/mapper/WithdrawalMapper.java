package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignWithdrawal;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname WithdrawalMapper
 * @Description TODO 提现Mapper
 * @Date 2020/10/20 14:20
 * @Created by wgg
 */
public interface WithdrawalMapper {
    //余额提现
    int balanceWithdrawal(@Param("uid") Long uid, @Param("balance") BigDecimal balance,@Param("status") String status ,@Param("account_type") String account_type);
    //查询余额提现账单
    List<SignWithdrawal> getList(@Param("uid") Long uid);

    //查询所有提现
    List<SignWithdrawal> getListAll(@Param("uid") Long uid);
    //提现状态修改
    int modifyWithdrawal(@Param("wid") Long wid,@Param("status") String status);

    SignWithdrawal getWithdrawal(@Param("wid") Long wid);

    SignWithdrawal getWithdrawal2(@Param("uid") Long uid);
}
