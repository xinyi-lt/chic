package com.zuoquan.lt.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zuoquan.lt.api.UserInfoService;
import com.zuoquan.lt.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinyi on 2017/10/9.
 */

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Reference(version = "1.0.0")
    private UserInfoService userInfoService;

    // 创建线程安全的Map
    static Map<Long, UserInfo> users = Collections.synchronizedMap(new HashMap<Long, UserInfo>());

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value="/list", method= RequestMethod.GET)
    public List<UserInfo> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
//        List<UserInfo> r = new ArrayList<UserInfo>(users.values());
        return userInfoService.findAllUser();
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String postUser(@RequestBody UserInfo user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public UserInfo getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }


    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody UserInfo user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        UserInfo u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }
}
