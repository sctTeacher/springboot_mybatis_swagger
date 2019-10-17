package com.shan.mapper;

import com.shan.domain.SysUser;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long userId);


    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 根据参数查用户
     * @param user
     * @return
     */
    SysUser selectByParam(SysUser user);
}