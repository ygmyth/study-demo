package com.myth.service.impl;

import com.myth.common.Response;
import com.myth.mapper.SysUserMapper;
import com.myth.entity.SysUser;
import com.myth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserMapper userMapper;
    @Override
    public SysUser getUserById(Long id) {
        return userMapper.selectByPrimaryKey(1L);
    }

    @Override
    public List<SysUser> selectAll() {
        return null;
    }

    @Override
    public Response login(String username, String password) {
        return null;
    }


}
