package com.myth.mapper;

import com.myth.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    List<String> selectAll();

    int updateByPrimaryKey(SysUser record);
}