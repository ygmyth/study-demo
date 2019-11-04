package com.myth.mapper;

import com.myth.entity.SysRolePrivilege;
import java.util.List;

public interface SysRolePrivilegeMapper {
    int insert(SysRolePrivilege record);

    List<SysRolePrivilege> selectAll();
}