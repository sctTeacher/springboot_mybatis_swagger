package com.shan.controller;

import com.shan.domain.User;
import com.shan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

   @Autowired
    private UserMapper userMapper;
 @RequestMapping("/findAll")
 @ResponseBody
    public List<User> finAll(){
     //List<User> users = userMapper.queryUserList();
    // System.out.println(users);
     List<User> users=new ArrayList<>();
     User user=new User();
      user.setName("tom");
      user.setPassword("123");
      users.add(user);
     return users;
 }
}
