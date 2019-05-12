package com.myth.service;


import com.myth.common.Response;
import com.myth.entity.SysUser;

import java.util.List;

public interface UserService {
    SysUser getUserById(Long id);

    List<SysUser> selectAll();


    Response login(String username, String password);
}
