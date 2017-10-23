package com.zuoquan.lt.service.impl;

import com.zuoquan.lt.api.UserInfoService;
import com.zuoquan.lt.dao.mapper.UserMapper;
import com.zuoquan.lt.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xinyi on 2017/10/11.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> findAllUser() {
        return userMapper.findAll();
    }
}
