package com.sign_in.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname SignTable
 * @Description TODO 签到实体类
 * @Date 2020/10/19 13:11
 * @Created by wgg
 */
public class SignTable {
    //签到ID
    private Long signInId;
    //签到天数
    private Long signInDaysCount;
    //签到时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signInDate;
    //签到人ID
    private Long signInUserId;

    public SignTable(Long signInId, Long signInDaysCount, Date signInDate, Long signInUserId) {
        this.signInId = signInId;
        this.signInDaysCount = signInDaysCount;
        this.signInDate = signInDate;
        this.signInUserId = signInUserId;
    }

    public SignTable() {

    }

    public Long getSignInId() {
        return signInId;
    }

    public void setSignInId(Long signInId) {
        this.signInId = signInId;
    }

    public Long getSignInDaysCount() {
        return signInDaysCount;
    }

    public void setSignInDaysCount(Long signInDaysCount) {
        this.signInDaysCount = signInDaysCount;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    public Long getSignInUserId() {
        return signInUserId;
    }

    public void setSignInUserId(Long signInUserId) {
        this.signInUserId = signInUserId;
    }

    @Override
    public String toString() {
        return "SignTable{" +
                "signInId=" + signInId +
                ", signInDaysCount=" + signInDaysCount +
                ", signInDate=" + signInDate +
                ", signInUserId=" + signInUserId +
                '}';
    }
}
