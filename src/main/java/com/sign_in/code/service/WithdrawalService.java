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
    Result<Map<String,Object>> balanceWithdrawal(Long uid , BigDecimal balance);
}
