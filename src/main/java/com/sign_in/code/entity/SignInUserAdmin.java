package com.sign_in.code.entity;

/**
 * @Classname SignInUserAdmin
 * @Description TODO
 * @Date 2020/10/19 12:58
 * @Created by wgg
 */
public class SignInUserAdmin {
    //管理员账号ID
    private Long userAdminId;
    //管理员昵称
    private String userAdminName;
    //管理员密码
    private String userAdminPwd;

    public Long getUserAdminId() {
        return userAdminId;
    }

    public void setUserAdminId(Long userAdminId) {
        this.userAdminId = userAdminId;
    }

    public String getUserAdminName() {
        return userAdminName;
    }

    public void setUserAdminName(String userAdminName) {
        this.userAdminName = userAdminName;
    }

    public String getUserAdminPwd() {
        return userAdminPwd;
    }

    public void setUserAdminPwd(String userAdminPwd) {
        this.userAdminPwd = userAdminPwd;
    }

    @Override
    public String toString() {
        return "SignInUserAdmin{" +
                "userAdminId=" + userAdminId +
                ", userAdminName='" + userAdminName + '\'' +
                ", userAdminPwd='" + userAdminPwd + '\'' +
                '}';
    }


}
