package com.myth.mapper;

import com.myth.entity.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    List<SysUserRole> selectAll();
}