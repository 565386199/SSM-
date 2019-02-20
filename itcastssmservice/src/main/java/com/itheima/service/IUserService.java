package com.itheima.service;


import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
