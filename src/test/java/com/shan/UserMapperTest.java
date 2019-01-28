package com.shan;

import com.shan.domain.User;
import com.shan.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day02springbootMybatisApplication.class)
public class UserMapperTest {

    @Autowired
  private UserMapper mapper;

    @Test
    public void test(){
      //  List<User> users = mapper.queryUserList();
      //System.out.println(users);
    }
}
