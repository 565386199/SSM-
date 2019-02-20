package com.itheima.ssm.controller;

import com.itcast.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    IPermissionService iPermissionService;
    @RequestMapping("findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(){
        ModelAndView mv =new ModelAndView();
        List<Permission> all = iPermissionService.findAll();
        mv.addObject("permission",all);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Permission permission){
        iPermissionService.save(permission);
        return "redirect:findAll.do";
    }
}
