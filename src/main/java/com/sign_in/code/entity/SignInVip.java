package com.sign_in.code.entity;

/**
 * @Classname SignInVip
 * @Description TODO
 * @Date 2020/10/19 13:03
 * @Created by wgg
 */
public class SignInVip {
    //会员等级
    private Long vipId;
    //会员等级名称
    private String vipName;
    //会员提现汇率
    private Integer vipExchangeRate;

    public SignInVip(Long vipId, String vipName, Integer vipExchangeRate) {
        this.vipId = vipId;
        this.vipName = vipName;
        this.vipExchangeRate = vipExchangeRate;
    }
    public SignInVip(){

    }
    public Long getVipId() {
        return vipId;
    }

    public void setVipId(Long vipId) {
        this.vipId = vipId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public Integer getVipExchangeRate() {
        return vipExchangeRate;
    }

    public void setVipExchangeRate(Integer vipExchangeRate) {
        this.vipExchangeRate = vipExchangeRate;
    }

    @Override
    public String toString() {
        return "SignInVip{" +
                "vipId=" + vipId +
                ", vipName='" + vipName + '\'' +
                ", vipExchangeRate=" + vipExchangeRate +
                '}';
    }
}
