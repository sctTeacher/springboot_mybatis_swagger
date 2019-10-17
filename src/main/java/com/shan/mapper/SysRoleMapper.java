package com.shan.mapper;

import com.shan.domain.SysRole;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleMapper {


    int deleteByPrimaryKey(Long roleId);



    int insertSelective(SysRole record);


    SysRole selectByPrimaryKey(Long roleId);



    int updateByPrimaryKeySelective(SysRole record);


}