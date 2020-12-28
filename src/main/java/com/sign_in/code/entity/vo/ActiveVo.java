package com.sign_in.code.entity.vo;

import java.math.BigDecimal;

/**
 * @Classname ActiveVo
 * @Description TODO
 * @Date 2020/11/29 23:55
 * @Created by wgg
 */
public class ActiveVo {
    private Long uid;
    private BigDecimal activeReward;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getActiveReward() {
        return activeReward;
    }

    public void setActiveReward(BigDecimal activeReward) {
        this.activeReward = activeReward;
    }

    @Override
    public String toString() {
        return "ActiveVo{" +
                "uid=" + uid +
                ", activeReward=" + activeReward +
                '}';
    }
}
