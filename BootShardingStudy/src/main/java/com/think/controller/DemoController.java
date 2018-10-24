package com.think.controller;

import com.alibaba.fastjson.JSONObject;
import com.think.dao.OrderMapper;
import com.think.entity.TOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Object index() {
        return "SUCCESS";
    }

    @GetMapping("/get")
    public Object get(@RequestParam("orderId") Integer orderId) {
        TOrder order = orderMapper.findById(orderId);
        JSONObject.toJSONString(order);
        return JSONObject.toJSONString(order);
    }

    @GetMapping("/add")
    public Object add(@RequestParam("orderId") Integer orderId, @RequestParam("userId") Integer userId) {
        TOrder order = new TOrder();
        order.setUserId(userId);
        order.setOrderId(orderId);
        orderMapper.insert(order);
        JSONObject.toJSONString(order);
        return JSONObject.toJSONString(order);
    }

}
