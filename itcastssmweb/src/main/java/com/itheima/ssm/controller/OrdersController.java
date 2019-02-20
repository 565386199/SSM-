package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;
    //查询订单
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv =new ModelAndView();
        List<Orders> all = iOrdersService.findAll(page,size);
        //pageinfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("ordersList",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv =new ModelAndView();
       Orders orders= iOrdersService.findById(id);
       mv.addObject("orders",orders);
       mv.setViewName("orders-show");
        return mv;
    }

}
