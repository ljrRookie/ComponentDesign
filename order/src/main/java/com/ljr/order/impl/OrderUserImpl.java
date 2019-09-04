package com.ljr.order.impl;


import com.ljr.arouter_annotation.ARouter;
import com.ljr.common.user.BaseUser;
import com.ljr.common.user.IUser;
import com.ljr.order.model.UserInfo;

/**
 * personal模块实现的内容
 */
@ARouter(path = "/order/getUserInfo")
public class OrderUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("ljr");
        userInfo.setAccount("order_user");
        userInfo.setPassword("33333333");
        userInfo.setVipLevel(8);
        return userInfo;
    }
}
