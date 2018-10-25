package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.algorithm.IdGenerator;
import com.demo.dao.OrderMapper;
import com.demo.entity.TOrder;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private IdGenerator idGenerator;

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
    public Object add(@RequestParam("userId") Integer userId) {
        TOrder order = new TOrder();
        order.setUserId(userId);
        order.setOrderId(idGenerator.getIdGenerator().generateKey().intValue());
        orderMapper.insert(order);
        return JSONObject.toJSONString(order);
    }

    @GetMapping("/queryIn")
    public Object in(@RequestParam("orderIds") String orderIds) {
        String[] ids = orderIds.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for (String id : ids) {
            idList.add(Integer.valueOf(id));
        }
        int[] array = new int[2];
        array[0] =31;
        array[1] =32;
        List<TOrder> lists = orderMapper.queryIn(idList);
        return JSONObject.toJSONString(lists);
    }

    @GetMapping("/queryBetween")
    public Object queryBetween(@RequestParam("start") Integer start, @RequestParam("start") Integer end) {
        List<TOrder> lists = orderMapper.queryBetween(start, end);
        return JSONObject.toJSONString(lists);
    }


}
