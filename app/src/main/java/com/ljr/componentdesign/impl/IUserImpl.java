package com.ljr.componentdesign.impl;


import com.ljr.arouter_annotation.ARouter;
import com.ljr.common.user.BaseUser;
import com.ljr.common.user.IUser;
import com.ljr.componentdesign.model.UserInfo;

/**
 * personal模块实现的内容
 */
@ARouter(path = "/app/getUserInfo")
public class IUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("ljr");
        userInfo.setAccount("main_simon");
        userInfo.setPassword("666666");
        userInfo.setVipLevel(9);
        return userInfo;
    }
}
