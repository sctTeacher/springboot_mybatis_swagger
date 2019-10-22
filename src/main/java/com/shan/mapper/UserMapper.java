package com.shan.mapper;

import com.shan.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {

    public List<User> queryUserList();

    int insertBatch(List<User> users);
}
