package com.think.dao;

import com.think.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TorderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    int insertBatch(List<TOrder> list);
}