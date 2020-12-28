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
    Result<Map<String,Object>> balanceWithdrawal(@RequestParam("uid") Long uid, @RequestParam("balance") BigDecimal balance,@RequestParam("account_type") String account_type){
        return withdrawalService.balanceWithdrawal(uid,balance,account_type);
    }

    //查询提现数据
    @RequestMapping("/getWthdrawalList")
    Result<Map<String,Object>> getWthdrawalList(@RequestParam("uid") Long uid ){
        return withdrawalService.getWithdrawalList(uid);
    }

    //获取提现集合
    @RequestMapping("/getListAll")
    Result<Map<String,Object>> getListAll(@RequestParam("uid") Long uid,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        return withdrawalService.getListAll(uid, pageNum);
    }

    //更改提现状态
    @RequestMapping("/modifyWithdrawal")
    Result<Map<String,Object>> modifyWithdrawal(@RequestParam("wid") Long wid,@RequestParam("status")String status){
        return withdrawalService.modifyWithdrawal(wid, status);
    }
}
