package com.shan.mapper;

import com.shan.domain.SysDept;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysDeptMapper {

    int deleteByPrimaryKey(Long deptId);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(SysDept record);


}