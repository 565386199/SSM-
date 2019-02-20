package com.itheima.service;

import com.itcast.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();


    void save(Permission permission);
}
