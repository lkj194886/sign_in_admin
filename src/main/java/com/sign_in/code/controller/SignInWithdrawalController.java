package com.sign_in.code.controller;

import com.sign_in.code.service.WithdrawalService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Classname SignInWithdrawalController
 * @Description TODO
 * @Date 2020/10/19 14:12
 * @Created by wgg
 */
@RestController
@RequestMapping("/withdrawal")
public class SignInWithdrawalController {

    @Autowired
    WithdrawalService withdrawalService;

    /**
     * 余额提现
     * @param uid 账户ID
     * @param balance 提现余额
     * @return
     */
    @RequestMapping("/balanceWithdrawal")
    Result<Map<String,Object>> balanceWithdrawal(@RequestParam("uid") Long uid, @RequestParam("balance") BigDecimal balance){
        return withdrawalService.balanceWithdrawal(uid,balance);
    }
}
