package com.sign_in.code.service.impl;

import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.mapper.SignInMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.mapper.WithdrawalMapper;
import com.sign_in.code.service.WithdrawalService;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Classname WithdrawalServiceImpl
 * @Description TODO
 * @Date 2020/11/02 13:37
 * @Created by wgg
 */
@Service
public class WithdrawalServiceImpl implements WithdrawalService {
    private static final String status_review = "审核中";
    private static final String status_carry_out = "已到账";
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    WithdrawalMapper withdrawalMapper;

    /**
     * 余额提现
     * @param uid 用户ID
     * @param balance 提现余额
     * @return
     */
    @Override
    public Result<Map<String, Object>> balanceWithdrawal(Long uid, BigDecimal balance) {
        SignInUser signInUser = userMapper.getUserId(uid);
        if (signInUser != null) {
            if (redisUtil.get(signInUser.getUserName()) != null) {
                if (balance != null) {
                    //判断余额是否充足
                    if (signInUser.getUserRemainingSum().compareTo(balance) == -1) {
                        return new Result<>(500, "余额不足", null);
                    }
                    //将信息添加到提现表
                    if (withdrawalMapper.balanceWithdrawal(uid, balance, status_review) > 0) {
                        //添加成功后,修改账户余额
                        if (userMapper.balanceWithdrawal(uid, signInUser.getUserRemainingSum().subtract(balance)) > 0) {
                            return new Result<>(200, "处理成功", null);
                        }
                    }
                } else {
                    return new Result<>(500, "提现金额不能为空", null);
                }
            } else {
                return new Result<>(401, "登陆已过期,\n请重新登陆", null);
            }
        }
        return new Result<>(500, "处理失败,\n请联系管理员", null);
    }
}
