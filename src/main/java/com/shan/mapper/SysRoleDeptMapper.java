package com.shan.mapper;

import com.shan.domain.SysRoleDept;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleDeptMapper {


    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRoleDept record);


    SysRoleDept selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(SysRoleDept record);


}