package com.demo;

import com.demo.dao.OrderMapper;
import com.demo.entity.TOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test() {
        TOrder order = new TOrder();
        order.setUserId(888);
        order.setOrderId(77);
        orderMapper.insert(order);
    }

    @Test
    public void test2() {
        TOrder order = orderMapper.findById(31);
        System.out.println(order.getOrderId() + "" + order.getUserId());
    }

}
