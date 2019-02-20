package com.itheima.ssm.controller;


import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService iUserService;

    /**
     * 添加角色(建立角色与用户的关系)
     * @return
     */
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        iUserService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 添加角色 (查询所有可添加角色)
     * @return
     */
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        List<Role> users = iUserService.findUserByIdAndAllRole(userId);
        UserInfo byId = iUserService.findById(userId);
        mv.addObject("user",byId);
        mv.addObject("roleList",users);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 用户详情
     * @param id
     * @return
     */
    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        UserInfo byId = iUserService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",byId);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 用户管理 查询所有用户
     * @return
     */
    @RequestMapping("findAll.do")
    public ModelAndView fingAll(){
        ModelAndView mv = new ModelAndView();
       List<UserInfo> list= iUserService.findAll();
       mv.addObject("userList",list);
       mv.setViewName("user-list");
        return mv;
    }

    /**
     * 新建用户
     * @param UserInfo
     * @return
     */
    @RequestMapping("save.do")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo UserInfo){

        iUserService.save(UserInfo);

        return "redirect:findAll.do";
    }
}
