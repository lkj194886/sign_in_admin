package com.sign_in.code.entity;

/**
 * @Classname SignInConfig
 * @Description TODO
 * @Date 2020/10/30 15:36
 * @Created by wgg
 */
public class SignInConfig {
    private Long configId;
    //会员兑换率
    private Double configSignInProps;
    //首页背景
    private String indexBackImg;
    //公告
    private String noticeBackImg;
    //邀请
    private String invitationBackImg;
    //下载二维码
    private String download;
    //邀请二维码
    private String invitation;

    //app 下载链接
    private String download_url;
    //签到活跃奖励
    private Double configActiveSignIn;
    //福利活跃奖励
    private Double configActiveWelfare;
    //提现兑换率
    private Double configWithdrawalRate;
    private Double videoRate;
    //每天可观看的视频次数
    private int configVideoCount;
    //邀请一人添加多少观看视屏次数
    private int configAddVideoCount;

    public int getConfigAddVideoCount() {
        return configAddVideoCount;
    }

    public void setConfigAddVideoCount(int configAddVideoCount) {
        this.configAddVideoCount = configAddVideoCount;
    }

    public int getConfigVideoCount() {
        return configVideoCount;
    }

    public void setConfigVideoCount(int configVideoCount) {
        this.configVideoCount = configVideoCount;
    }

    public Double getVideoRate() {
        return videoRate;
    }

    public void setVideoRate(Double videoRate) {
        this.videoRate = videoRate;
    }

    public Double getConfigWithdrawalRate() {
        return configWithdrawalRate;
    }

    public void setConfigWithdrawalRate(Double configWithdrawalRate) {
        this.configWithdrawalRate = configWithdrawalRate;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Double getConfigSignInProps() {
        return configSignInProps;
    }

    public void setConfigSignInProps(Double configSignInProps) {
        this.configSignInProps = configSignInProps;
    }

    public String getIndexBackImg() {
        return indexBackImg;
    }

    public void setIndexBackImg(String indexBackImg) {
        this.indexBackImg = indexBackImg;
    }

    public String getNoticeBackImg() {
        return noticeBackImg;
    }

    public void setNoticeBackImg(String noticeBackImg) {
        this.noticeBackImg = noticeBackImg;
    }

    public String getInvitationBackImg() {
        return invitationBackImg;
    }

    public void setInvitationBackImg(String invitationBackImg) {
        this.invitationBackImg = invitationBackImg;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public Double getConfigActiveSignIn() {
        return configActiveSignIn;
    }

    public void setConfigActiveSignIn(Double configActiveSignIn) {
        this.configActiveSignIn = configActiveSignIn;
    }

    public Double getConfigActiveWelfare() {
        return configActiveWelfare;
    }

    public void setConfigActiveWelfare(Double configActiveWelfare) {
        this.configActiveWelfare = configActiveWelfare;
    }

    @Override
    public String toString() {
        return "SignInConfig{" +
                "configId=" + configId +
                ", configSignInProps=" + configSignInProps +
                ", indexBackImg='" + indexBackImg + '\'' +
                ", noticeBackImg='" + noticeBackImg + '\'' +
                ", invitationBackImg='" + invitationBackImg + '\'' +
                ", download='" + download + '\'' +
                ", invitation='" + invitation + '\'' +
                ", download_url='" + download_url + '\'' +
                ", configActiveSignIn=" + configActiveSignIn +
                ", configActiveWelfare=" + configActiveWelfare +
                ", configWithdrawalRate=" + configWithdrawalRate +
                ", videoRate=" + videoRate +
                ", configVideoCount=" + configVideoCount +
                ", configAddVideoCount=" + configAddVideoCount +
                '}';
    }
}
