package com.sign_in.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname SignWithdrawal
 * @Description TODO 提现实体类
 * @Date 2020/10/19 13:07
 * @Created by wgg
 */
public class SignWithdrawal {
    //提现ID
    private Long withdrawalId;
    //提现金额
    private BigDecimal withdrawalSum;
    //提现时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date withdrawalDate;
    //提现人ID
    private Long withdrawalUserId;
    //提现状态
    private String withdrawalStatus;

    private String account_type;

    @Override
    public String toString() {
        return "SignWithdrawal{" +
                "withdrawalId=" + withdrawalId +
                ", withdrawalSum=" + withdrawalSum +
                ", withdrawalDate=" + withdrawalDate +
                ", withdrawalUserId=" + withdrawalUserId +
                ", withdrawalStatus='" + withdrawalStatus + '\'' +
                ", account_type='" + account_type + '\'' +
                '}';
    }

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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
