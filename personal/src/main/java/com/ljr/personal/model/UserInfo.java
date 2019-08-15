package com.ljr.personal.model;


import com.ljr.common.user.BaseUser;

public class UserInfo extends BaseUser {

    private String token;
    private int vipLevel;

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token == null ? "" : token;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }
}
