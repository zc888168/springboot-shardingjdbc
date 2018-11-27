package com.think.service;

import com.google.common.collect.Lists;
import com.think.dao.OrderMapper;
import com.think.dao.TorderMapper;
import com.think.entity.TOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Created by chaoz2 on 2018/11/27.
 */
@Service
public class TOrderService {
    @Resource
    private TorderMapper torderMapper;

    public void batchInsert(Integer id) {
        torderMapper.selectByPrimaryKey(1L);
        List<TOrder> list = Lists.newArrayList();
        TOrder tOrder = new TOrder();
        tOrder.setOrderId(id);
        tOrder.setUserId(id);
        list.add(tOrder);
        TOrder tOrder2 = new TOrder();
        tOrder2.setUserId(id + 1);
        tOrder2.setOrderId(id + 1);
        list.add(tOrder2);
        torderMapper.insertBatch(list);
    }
}
