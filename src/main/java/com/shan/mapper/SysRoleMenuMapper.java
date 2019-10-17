package com.shan.mapper;

import com.shan.domain.SysRoleMenu;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRoleMenu record);
    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);


}