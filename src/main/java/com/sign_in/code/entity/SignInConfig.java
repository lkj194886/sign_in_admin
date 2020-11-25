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
                '}';
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
}
