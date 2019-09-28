package com.example.ssm.controller;

import com.example.ssm.domain.Orders;
import com.example.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询所有订单信息(未分页)
     * @return 返回指定页面的订单信息
     * @throws Exception 抛出异常
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Orders> ordersList = ordersService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-list");
        mv.addObject("ordersList", ordersList);
        return mv;
    }

    /**
     * 查询订单详情
     * @param ordersId 订单id
     * @return 返回订单详情
     * @throws Exception 抛出异常
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
