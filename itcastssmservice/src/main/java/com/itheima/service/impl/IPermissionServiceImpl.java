package com.itheima.service.impl;

import com.itcast.domain.Permission;
import com.itheima.dao.IpermissionsDao;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    IpermissionsDao ipermissionsDao;
    @Override
    public List<Permission> findAll() {
        return ipermissionsDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        ipermissionsDao.save(permission);
    }


}
