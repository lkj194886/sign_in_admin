package com.sign_in.code.entity;

import java.math.BigDecimal;

/**
 * @Classname SignInUser
 * @Description TODO 用户实体类
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
    //用户支付宝账号
    private String userZhiFUBao;
    //用户支付宝姓名
    private String userZhiFUBaoName;
    //用户微信账号
    private String userWeiXin;
    //用户微信姓名
    private String userWeiXinName;
    //是否是合伙人5/0


    private SignInVip signInVip;

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
                ", userZhiFUBao='" + userZhiFUBao + '\'' +
                ", userZhiFUBaoName='" + userZhiFUBaoName + '\'' +
                ", userWeiXin='" + userWeiXin + '\'' +
                ", userWeiXinName='" + userWeiXinName + '\'' +
                ", signInVip=" + signInVip +
                '}';
    }

    public SignInUser(){

    }


    public String getUserZhiFUBao() {
        return userZhiFUBao;
    }

    public void setUserZhiFUBao(String userZhiFUBao) {
        this.userZhiFUBao = userZhiFUBao;
    }

    public String getUserZhiFUBaoName() {
        return userZhiFUBaoName;
    }

    public void setUserZhiFUBaoName(String userZhiFUBaoName) {
        this.userZhiFUBaoName = userZhiFUBaoName;
    }

    public String getUserWeiXin() {
        return userWeiXin;
    }

    public void setUserWeiXin(String userWeiXin) {
        this.userWeiXin = userWeiXin;
    }

    public String getUserWeiXinName() {
        return userWeiXinName;
    }

    public void setUserWeiXinName(String userWeiXinName) {
        this.userWeiXinName = userWeiXinName;
    }

    public SignInVip getSignInVip() {
        return signInVip;
    }

    public void setSignInVip(SignInVip signInVip) {
        this.signInVip = signInVip;
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
