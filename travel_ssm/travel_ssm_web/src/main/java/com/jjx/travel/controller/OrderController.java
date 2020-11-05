package com.jjx.travel.controller;

import com.github.pagehelper.PageInfo;
import com.jjx.travel.Order;
import com.jjx.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/29 15:44
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page,@RequestParam(value = "pageSize",required = true,defaultValue = "1") Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        //通过pagehelper进行分页操作
        List<Order> list = orderService.findAll(page, pageSize);
        PageInfo<Order> orderInfo = new PageInfo<>(list);
        //通过mv进行数据传递和页面跳转
        modelAndView.addObject("orderInfo",orderInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id") String id){
        Order orders = orderService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }

    @RequestMapping("/update4q")
    public ModelAndView update4q(@RequestParam(value = "id") String id){
        Order orders = orderService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-update");
        return modelAndView;
    }

}
