package com.shan.controller;

import com.shan.domain.User;
import com.shan.mapper.UserMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @ApiOperation(value = "用戶列表", notes = "用户查询")
    @GetMapping("/findAll")
    @ResponseBody
    public List<User> finAll() {
        List<User> users = userMapper.queryUserList();
        System.out.println(users);
    /* List<User> users=new ArrayList<>();
     User user=new User();
      user.setName("tom");"123");
      users
      user.setPassword(.add(user)*/
        return users;
    }


    @ApiOperation(value = "根据id查用户", notes = "根据id查指定用户")
    @GetMapping("/finById/{id}")
    @ResponseBody
    public User finById(@PathVariable("id") Long id) {
        System.out.println(id);
        User user = new User();
        user.setId(id);
        user.setName("tom");
        user.setPassword("123");
        return user;
    }

    /**
     * (@ApiParam  注解必须配合 @RequestParam才能接受到参数
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用戶名查用户", notes = "根据用戶名查指定用户")
    @GetMapping("/finByName")
    @ResponseBody
    public User finByName(@ApiParam(name="username",value = "用户名",required = true) @RequestParam("username") String username) {
        System.out.println(username);
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");
        return user;
    }


    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PostMapping("/update")
    @ResponseBody
    public User update(@RequestBody(required = true) User user) {
        System.out.println(user.toString());
        User u = new User();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        return u;
    }
    /**
     * 作者:   shanc
     * 时间:   2021/4/12 10:51
     * 描述:   git请求 实体接受参数
     */
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @GetMapping("/update2")
    @ResponseBody
    public User update2(User user) {
        System.out.println(user.toString());
        User u = new User();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        return u;
    }


    /**
     * (@ApiParam  注解必须配合 @RequestParam才能接受到参数
     * @param username
     * @return
     */
    @ApiOperation(value = "根据参数查用户", notes = "根据参数查指定用户")
    @GetMapping("/finByParam")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户名",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")})
    public User finByParam( String username ,Long id) {
        System.out.println(username);
        User user = new User();
        user.setUsername(username);
        user.setId(id);
        return user;
    }


}
