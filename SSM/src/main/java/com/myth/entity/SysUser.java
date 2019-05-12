package com.myth.entity;

import java.util.Date;

public class SysUser {
    private Long id;

    private String userName;

    private String userPassword;

    private String userEmail;

    private Date createTime;

    private String userInfo;

    private String headImg;

    public SysUser(Long id, String userName, String userPassword, String userEmail, Date createTime, String userInfo,
                   String headImg) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.createTime = createTime;
        this.userInfo = userInfo;
        this.headImg = headImg;
    }

    public SysUser() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}