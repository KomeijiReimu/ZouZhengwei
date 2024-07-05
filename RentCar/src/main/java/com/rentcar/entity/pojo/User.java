package com.rentcar.entity.pojo;

public class User {
    private int UserId;
    private String UserPwd;

    private String UserName;
    private int UserKind;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getUserKind() {
        return UserKind;
    }

    public void setUserKind(int userKind) {
        UserKind = userKind;
    }
}
