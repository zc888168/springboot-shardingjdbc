package com.demo.service;

import com.demo.dao.OrderMapper;
import com.demo.entity.TOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description:
 * Created by chaoz2 on 2018/10/26.
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public void serviceA(TOrder tOrder) {
        orderMapper.insert(tOrder);
    };
    public void serviceB(TOrder tOrder) {
        orderMapper.insert(tOrder);
    };
    @Transactional
    public void serviceC(TOrder tOrder) {
        serviceA(tOrder);
        tOrder.setOrderId(tOrder.getOrderId() + 1);
        System.out.println("execute transaction A success");
        try{
            Thread.sleep(1000 *30);
        }catch ( Exception e) {
            e.printStackTrace();
        }
        System.out.println("execute tranaction B :disconnected");
        tOrder.setUserId(tOrder.getUserId() + 1);
        serviceB(tOrder);
    }
}
