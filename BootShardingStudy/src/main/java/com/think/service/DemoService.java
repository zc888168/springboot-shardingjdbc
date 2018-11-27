package com.think.service;

import com.google.common.collect.Lists;
import com.think.dao.OrderMapper;
import com.think.entity.TOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Created by chaoz2 on 2018/11/22.
 */
@Service
public class DemoService {

    @Resource
    private OrderMapper orderMapper;


    public TOrder findById(int id) {
        return orderMapper.findById(31);
    }

    public void listIn() {
        orderMapper.findIn();
    }

    public List<TOrder> testOrder() {
        List<TOrder> list = orderMapper.testOrder();
        return list;
    }

    public List<TOrder> testPage() {
        return orderMapper.testPage();
    }

    public void findbetween() {
        orderMapper.findbetween();
    }

    public void batchInsert(Integer id) {
        List<TOrder> list = Lists.newArrayList();
        TOrder tOrder = new TOrder();
        tOrder.setOrderId(id);
        tOrder.setUserId(id);
        TOrder tOrder2 = new TOrder();
        tOrder2.setUserId(id + 1);
        tOrder2.setOrderId(id + 1);
        list.add(tOrder2);
        orderMapper.batchInsert(list);

    }
}
