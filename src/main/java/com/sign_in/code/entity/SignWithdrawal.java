package com.sign_in.code.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname SignWithdrawal
 * @Description TODO
 * @Date 2020/10/19 13:07
 * @Created by wgg
 */
public class SignWithdrawal {
    //提现ID
    private Long withdrawalId;
    //提现金额
    private BigDecimal withdrawalSum;
    //提现时间
    private Date withdrawalDate;
    //提现人ID
    private Long withdrawalUserId;
    //提现状态
    private String withdrawalStatus;

    public Long getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(Long withdrawalId) {
        this.withdrawalId = withdrawalId;
    }

    public BigDecimal getWithdrawalSum() {
        return withdrawalSum;
    }

    public void setWithdrawalSum(BigDecimal withdrawalSum) {
        this.withdrawalSum = withdrawalSum;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public Long getWithdrawalUserId() {
        return withdrawalUserId;
    }

    public void setWithdrawalUserId(Long withdrawalUserId) {
        this.withdrawalUserId = withdrawalUserId;
    }

    public String getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    @Override
    public String toString() {
        return "SignWithdrawal{" +
                "withdrawalId=" + withdrawalId +
                ", withdrawalSum=" + withdrawalSum +
                ", withdrawalDate=" + withdrawalDate +
                ", withdrawalUserId=" + withdrawalUserId +
                ", withdrawalStatus='" + withdrawalStatus + '\'' +
                '}';
    }
}
