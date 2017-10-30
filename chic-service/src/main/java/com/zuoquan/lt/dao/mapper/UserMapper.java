package com.zuoquan.lt.dao.mapper;

import com.zuoquan.lt.entity.UserInfo;

import java.util.List;

/**
 * Created by xinyi on 2017/10/11.
 */
public interface UserMapper {

    List<UserInfo> findAll();

}
