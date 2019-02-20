package com.itheima.service.impl;

import com.itcast.domain.Permission;
import com.itcast.domain.Role;
import com.itheima.dao.IRoleDao;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired

    IRoleDao iRoleDao;
    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
       return iRoleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return iRoleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionids) {
        for (String permissionId : permissionids) {
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
