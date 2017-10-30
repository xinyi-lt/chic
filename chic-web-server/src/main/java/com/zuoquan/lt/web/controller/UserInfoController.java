package com.zuoquan.lt.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zuoquan.lt.api.UserInfoService;
import com.zuoquan.lt.entity.UserInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value="获取用户列表", notes="222")
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public List<UserInfo> getUserList() {
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
//        List<UserInfo> r = new ArrayList<UserInfo>(users.values());
        return userInfoService.findAllUser();
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体UserInfo", required = true, dataType = "UserInfo")
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String postUser(@RequestBody UserInfo user) {
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public UserInfo getUser(@PathVariable Long id) {
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody UserInfo user) {
        UserInfo u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
