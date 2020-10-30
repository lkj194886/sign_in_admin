package com.sign_in.code.entity;

/**
 * @Classname SignInConfig
 * @Description TODO
 * @Date 2020/10/30 15:36
 * @Created by wgg
 */
public class SignInConfig {
    private Long configId;
    private Double configSignInProps;

    @Override
    public String toString() {
        return "SignInConfig{" +
                "configId=" + configId +
                ", configSignInProps=" + configSignInProps +
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
