package com.itheima.service.impl;

import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import com.itheima.dao.IUserDao;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserSreviceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userinfo = iUserDao.findByUsername(username);

        User user = new User(userinfo.getUsername(),userinfo.getPassword(),userinfo.getStatus()==0?false:true,true,true,true,getAuthority(userinfo.getRoles()) );
        System.out.println(userinfo);
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list =new ArrayList<>();
        for (Role role : roles) {

            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        //对密码加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return iUserDao.findById(id);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String userId) {
        return iUserDao.findUserByIdAndAllRole(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            iUserDao.addRoleToUser(userId,roleId);
        }
    }
}
