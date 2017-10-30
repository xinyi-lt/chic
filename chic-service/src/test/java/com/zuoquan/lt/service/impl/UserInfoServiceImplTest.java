package com.zuoquan.lt.service.impl;

import com.zuoquan.lt.ChicServiceApplicationTests;
import com.zuoquan.lt.api.UserInfoService;
import com.zuoquan.lt.entity.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xinyi on 2017/10/23.
 */
public class UserInfoServiceImplTest extends ChicServiceApplicationTests{

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testFindAllUser() throws Exception {
        List<UserInfo> userInfoList = userInfoService.findAllUser();
        System.out.println("userInfoList size = " + userInfoList.size());
        Assert.assertTrue(userInfoList.size() > 0);
    }
}