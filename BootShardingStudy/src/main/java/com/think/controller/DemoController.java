package com.think.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.think.dao.OrderMapper;
import com.think.entity.TOrder;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @GetMapping("/listin")
    public Object listin(){

        orderMapper.findIn();
        return " success" ;
    }

    @GetMapping("/order")
    public Object testOrder(){
       List<TOrder> list =  orderMapper.testOrder();
       return JSONArray.toJSON(list);
    }
    @GetMapping("/testPage")
    public Object testPage(){
        List<TOrder> list =  orderMapper.testPage();
        return JSONArray.toJSON(list);
    }

    @GetMapping("/listbetween")
    public Object listbetween(){

        orderMapper.findbetween();
        return " success" ;
    }


}