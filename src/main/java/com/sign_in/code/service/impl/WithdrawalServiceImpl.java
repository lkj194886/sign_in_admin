package com.sign_in.code.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.entity.SignWithdrawal;
import com.sign_in.code.mapper.SignInConfigMapper;
import com.sign_in.code.mapper.SignInMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.mapper.WithdrawalMapper;
import com.sign_in.code.service.WithdrawalService;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
    private static final String status_refuse = "不通过";
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    WithdrawalMapper withdrawalMapper;
    @Autowired
    SignInConfigMapper signInConfigMapper;

    /**
     * 余额提现
     * @param uid 用户ID
     * @param balance 提现余额
     * @return
     */
    @Override
    public Result<Map<String, Object>> balanceWithdrawal(Long uid, BigDecimal balance,String account_type) {
        Double balanceb =  signInConfigMapper.getWithdrawalRate();
        BigDecimal b = new BigDecimal(0.3);
        if (balance.compareTo(b)==-1){
            SignWithdrawal withdrawal = new SignWithdrawal();
            withdrawal = withdrawalMapper.getWithdrawal2(uid);
            if (withdrawal!=null){
                return new Result<>(500,"当前有提现未审核",null);
            }
        }
        if (balance.compareTo(b)==1){
            BigDecimal balanceBig = BigDecimal.valueOf(balanceb);
            balance = balance.multiply(balanceBig);
        }

        System.out.println("balance = " + balance);
        SignInUser signInUser = userMapper.getUserId(uid);
        //查询数据库是否存在该用户
        if (signInUser != null) {
            //查询该用户是否有登录
            if (redisUtil.get(signInUser.getUserName()) != null) {
                //重新设置过期时间
                redisUtil.expric(signInUser.getUserName(),1800);
                if (balance != null) {
                    //判断余额是否充足
                    if (account_type == null || account_type == "") {
                        return  new Result<>(500,"提现账户不能为空",null);
                    }
                    if (signInUser.getUserRemainingSum().compareTo(balance) == -1) {
                        return new Result<>(500, "余额不足", null);
                    }
                    //将信息添加到提现表
                    if (withdrawalMapper.balanceWithdrawal(uid, balance, status_review,account_type) > 0) {
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

    @Override
    public Result<Map<String, Object>> getWithdrawalList(Long uid) {
        List<SignWithdrawal> getList = withdrawalMapper.getList(uid);
        Map<String,Object> map  = new HashMap<>();
        if (getList!=null){
            map.put("list",getList);
            return new Result<>(200,"处理成功",map);
        }
        return new Result<>(500,"处理失败",null);
    }

    @Override
    public Result<Map<String, Object>> getListAll(Long uid,Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<SignWithdrawal> list= withdrawalMapper.getListAll(uid);
        Map<String,Object> map = new HashMap<>();
        if (list!=null){
            PageInfo<SignWithdrawal> pageInfo = new PageInfo<SignWithdrawal>(list);
            map.put("pageInfo",pageInfo);
            return new Result<>(200,"处理成功",map);
        }
        return new Result<>(500,"处理失败",null);
    }

    @Override
    public Result<Map<String,Object>> modifyWithdrawal(Long wid, String status) {
        if (status.equals("通过")){
            if (withdrawalMapper.modifyWithdrawal(wid,status_carry_out)>0){
                return new Result<>(200,"处理成功",null);
            }
        }
        if (status.equals("拒绝")){
            if (withdrawalMapper.modifyWithdrawal(wid,status_refuse)>0){

                SignWithdrawal signWithdrawal =new SignWithdrawal();
                signWithdrawal = withdrawalMapper.getWithdrawal(wid);
                SignInUser signInUser = userMapper.getUserId(signWithdrawal.getWithdrawalUserId());
                if (userMapper.balanceWithdrawal(signWithdrawal.getWithdrawalUserId(),signInUser.getUserRemainingSum().add(signWithdrawal.getWithdrawalSum()))>0) {
                    return new Result<>(200, "处理成功", null);
                }
            }
        }
        return new Result<>(500,"处理失败",null);
    }
}
