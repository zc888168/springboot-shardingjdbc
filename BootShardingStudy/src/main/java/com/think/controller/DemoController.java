package com.think.controller;

import com.think.dao.OrderMapper;
import com.think.entity.TOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * Created by chaoz2 on 2018/10/22.
 */
@RequestMapping(path = "/demo")
@RestController
public class DemoController {
    @Resource
    private OrderMapper orderMapper;

    @GetMapping("/index")
    public Object index(){
        return "SUCCESS";
    }
    @GetMapping("/get")
    public Object get(){
        //insert
        TOrder order = new TOrder();
//        order.setUserId(888);
//        order.setOrderId(Rando);
//        orderMapper.insert(order);

        //get
        order = orderMapper.findById(31);
//        TOrder order = new TOrder();
//        order.setUserId(888);
//        order.setOrderId(32);
//        orderMapper.insert(order);
        //System.out.println(order.getOrderId() + "" + order.getUserId());
        return " order" ;
    }

}