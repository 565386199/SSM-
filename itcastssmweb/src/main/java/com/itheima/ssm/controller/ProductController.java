package com.itheima.ssm.controller;

import com.itcast.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    /**
     * 查询全部产品
     * @return
     */
    @RequestMapping("findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv =new ModelAndView();
        List<Product> all = iProductService.findAll();
        mv.addObject("productList",all);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("save.do")
    public String save(Product product){
        iProductService.save(product);
        return "redirect:findAll.do";
    }


}
