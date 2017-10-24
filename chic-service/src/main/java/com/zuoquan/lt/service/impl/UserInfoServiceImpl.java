package com.zuoquan.lt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zuoquan.lt.api.UserInfoService;
import com.zuoquan.lt.dao.mapper.UserMapper;
import com.zuoquan.lt.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xinyi on 2017/10/11.
 */

@Service(version = "1.0.0")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> findAllUser() {
        return userMapper.findAll();
    }
}
