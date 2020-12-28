package com.sign_in.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname SignInActive
 * @Description TODO
 * @Date 2020/11/29 18:41
 * @Created by wgg
 */
public class SignInActive {
    private Long activeId;
    private BigDecimal activeSum;
    private String activeType;
    private Long activeUserId;
    private Long activeJuniorUserId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date activeTime;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getActiveId() {
        return activeId;
    }

    public void setActiveId(Long activeId) {
        this.activeId = activeId;
    }

    public BigDecimal getActiveSum() {
        return activeSum;
    }

    public void setActiveSum(BigDecimal activeSum) {
        this.activeSum = activeSum;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public Long getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(Long activeUserId) {
        this.activeUserId = activeUserId;
    }

    public Long getActiveJuniorUserId() {
        return activeJuniorUserId;
    }

    public void setActiveJuniorUserId(Long activeJuniorUserId) {
        this.activeJuniorUserId = activeJuniorUserId;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    public String toString() {
        return "SignInActive{" +
                "activeId=" + activeId +
                ", activeSum=" + activeSum +
                ", activeType='" + activeType + '\'' +
                ", activeUserId=" + activeUserId +
                ", activeJuniorUserId=" + activeJuniorUserId +
                ", activeTime=" + activeTime +
                '}';
    }
}
