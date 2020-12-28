package com.sign_in.code.service;

import com.sign_in.code.util.Result;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Classname WithdrawalService
 * @Description TODO
 * @Date 2020/11/02 13:36
 * @Created by wgg
 */
public interface WithdrawalService {
    //余额提现
    Result<Map<String,Object>> balanceWithdrawal(Long uid , BigDecimal balance,String account_type);

    //查询提现数据
    Result<Map<String,Object>> getWithdrawalList(Long uid);

    Result<Map<String,Object>> getListAll(Long uid,Integer pageNum);

    //修改提现状态
    Result<Map<String,Object>> modifyWithdrawal(Long wid,String status);
}
