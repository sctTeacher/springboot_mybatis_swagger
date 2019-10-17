package com.shan.mapper;

import com.shan.domain.SysMenu;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysMenuMapper {

    int deleteByPrimaryKey(Long menuId);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);


}