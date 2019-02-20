package com.itheima.dao;

import com.itcast.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IpermissionsDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findById(String id);

    @Select("select * from Permission")
    List<Permission> findAll();

    @Insert("insert into Permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
