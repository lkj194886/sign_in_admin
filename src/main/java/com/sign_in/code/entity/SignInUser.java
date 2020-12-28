package com.sign_in.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sign_in.code.entity.vo.ActiveVo;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

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
    private SignInVip signInVip;
    private Long userPartnerId;
    private ActiveVo activeVo;
    //注册时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date userTime;

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
                ", userPartnerId=" + userPartnerId +
                ", activeVo=" + activeVo +
                ", userTime=" + userTime +
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

    public Long getUserPartnerId() {
        return userPartnerId;
    }

    public void setUserPartnerId(Long userPartnerId) {
        this.userPartnerId = userPartnerId;
    }

    public ActiveVo getActiveVo() {
        return activeVo;
    }

    public void setActiveVo(ActiveVo activeVo) {
        this.activeVo = activeVo;
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }
}
