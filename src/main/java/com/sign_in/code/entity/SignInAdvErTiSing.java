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
    private String advertisingTitle;
    private String advertisingContent;
    private String advertisingHref;

    public String getAdvertisingTitle() {
        return advertisingTitle;
    }

    public void setAdvertisingTitle(String advertisingTitle) {
        this.advertisingTitle = advertisingTitle;
    }

    public String getAdvertisingContent() {
        return advertisingContent;
    }

    public void setAdvertisingContent(String advertisingContent) {
        this.advertisingContent = advertisingContent;
    }

    public String getAdvertisingHref() {
        return advertisingHref;
    }

    public void setAdvertisingHref(String advertisingHref) {
        this.advertisingHref = advertisingHref;
    }

    public SignInAdvErTiSing(Long advertisingId, String advertisingSrc, Long advertisingStatus) {
        this.advertisingId = advertisingId;
        this.advertisingSrc = advertisingSrc;
        this.advertisingStatus = advertisingStatus;
    }
    public SignInAdvErTiSing(){

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

    @Override
    public String toString() {
        return "SignInAdvErTiSing{" +
                "advertisingId=" + advertisingId +
                ", advertisingSrc='" + advertisingSrc + '\'' +
                ", advertisingStatus=" + advertisingStatus +
                ", advertisingTitle='" + advertisingTitle + '\'' +
                ", advertisingContent='" + advertisingContent + '\'' +
                ", advertisingHref='" + advertisingHref + '\'' +
                '}';
    }
}
