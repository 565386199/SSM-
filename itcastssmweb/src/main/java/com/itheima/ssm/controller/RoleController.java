package com.itheima.ssm.controller;

import com.itcast.domain.Permission;
import com.itcast.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    IRoleService iRoleService;
    //给角色添加权限
    @RequestMapping("addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionids){
        iRoleService.addPermissionToRole(roleId,permissionids);
        return "redirect:findAll.do";
    }

    //根据roleID查询role，并查询出出可以添加的权限
    @RequestMapping("findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv =new ModelAndView();
        //根据roleId查询role
        Role byId = iRoleService.findById(roleId);
        //查询出可以添加的权限
       List<Permission> permissionList=iRoleService.findOtherPermissions(roleId);
       mv.addObject("role",byId);
       mv.addObject("permissionList",permissionList);
       mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Role role){
        iRoleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> all = iRoleService.findAll();
        mv.addObject("roleList",all);
        mv.setViewName("role-list");
        return mv;
    }
}
