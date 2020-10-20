package com.sign_in.code.entity;

import java.math.BigDecimal;

/**
 * @Classname SignInUser
 * @Description TODO
 * @Date 2020/10/18 16:54
 * @Created by wgg
 */
public class SignInUser {
    //用户ID
    private Long userId;
    //用户昵称
    private String userName;
    //用户vipID
    private Long userVipId;
    //用户余额
    private BigDecimal userRemainingSum;
    //用户七币
    private BigDecimal userQiBi;
    //用户被邀请人ID
    private Long userInvitationId;
    //用户邀请码
    private String userInvitationCode;

    private SignInVip signInVip;

    public SignInUser(){

    }
    public SignInVip getSignInVip() {
        return signInVip;
    }

    public void setSignInVip(SignInVip signInVip) {
        this.signInVip = signInVip;
    }


    @Override
    public String toString() {
        return "SignInUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userVipId=" + userVipId +
                ", userRemainingSum=" + userRemainingSum +
                ", userQiBi=" + userQiBi +
                ", userInvitationId=" + userInvitationId +
                ", userInvitationCode='" + userInvitationCode + '\'' +
                '}';
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserVipId() {
        return userVipId;
    }

    public void setUserVipId(Long userVipId) {
        this.userVipId = userVipId;
    }

    public BigDecimal getUserRemainingSum() {
        return userRemainingSum;
    }

    public void setUserRemainingSum(BigDecimal userRemainingSum) {
        this.userRemainingSum = userRemainingSum;
    }

    public BigDecimal getUserQiBi() {
        return userQiBi;
    }

    public void setUserQiBi(BigDecimal userQiBi) {
        this.userQiBi = userQiBi;
    }

    public Long getUserInvitationId() {
        return userInvitationId;
    }

    public void setUserInvitationId(Long userInvitationId) {
        this.userInvitationId = userInvitationId;
    }

    public String getUserInvitationCode() {
        return userInvitationCode;
    }

    public void setUserInvitationCode(String userInvitationCode) {
        this.userInvitationCode = userInvitationCode;
    }


}
