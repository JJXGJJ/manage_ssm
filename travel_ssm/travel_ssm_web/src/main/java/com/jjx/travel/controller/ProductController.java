package com.jjx.travel.controller;

import com.github.pagehelper.PageInfo;
import com.jjx.travel.Product;
import com.jjx.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/28 16:43
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page, @RequestParam(value = "pageSize",required = true,defaultValue = "1") Integer pageSize){
        List<Product> list = productService.findAll(page,pageSize);
        PageInfo<Product> productList = new PageInfo<>(list);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }

    @RequestMapping("/update4q")
    public ModelAndView update(String id){
        Product product = productService.update4q(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product-update");
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:findAll.do";
    }

}
