package com.ljr.personal.impl;

import com.ljr.arouter_annotation.ARouter;
import com.ljr.common.user.BaseUser;
import com.ljr.common.user.IUser;
import com.ljr.personal.model.UserInfo;

/**
 * personal模块实现的内容
 */
//@ARouter(path = "/personal/getUserInfo")
public class PersonalUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("ljr");
        userInfo.setAccount("15622732935");
        userInfo.setPassword("666666");
        userInfo.setVipLevel(9);
        return userInfo;
    }
}
