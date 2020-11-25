package com.sign_in.code.entity;

/**
 * @Classname SignInAdvErTiSing
 * @Description TODO 广告表
 * @Date 2020/11/10 22:29
 * @Created by wgg
 */
public class SignInAdvErTiSing {
    private Long advertisingId;
    private String advertisingSrc;
    private Long advertisingStatus;

    public SignInAdvErTiSing(Long advertisingId, String advertisingSrc, Long advertisingStatus) {
        this.advertisingId = advertisingId;
        this.advertisingSrc = advertisingSrc;
        this.advertisingStatus = advertisingStatus;
    }
    public SignInAdvErTiSing(){

    }

    @Override
    public String toString() {
        return "SignInAdvErTiSing{" +
                "advertisingId=" + advertisingId +
                ", advertisingSrc='" + advertisingSrc + '\'' +
                ", advertisingStatus=" + advertisingStatus +
                '}';
    }

    public Long getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(Long advertisingId) {
        this.advertisingId = advertisingId;
    }

    public String getAdvertisingSrc() {
        return advertisingSrc;
    }

    public void setAdvertisingSrc(String advertisingSrc) {
        this.advertisingSrc = advertisingSrc;
    }

    public Long getAdvertisingStatus() {
        return advertisingStatus;
    }

    public void setAdvertisingStatus(Long advertisingStatus) {
        this.advertisingStatus = advertisingStatus;
    }
}
