package com.demo.dao;

import com.demo.conf.SimpleSelectInExtendedLanguageDriver;
import org.apache.ibatis.annotations.*;

import com.demo.entity.TOrder;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO t_order (order_id,user_id) VALUES (#{orderId},#{userId})")
    public void insert(TOrder order);

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Select("SELECT * FROM t_order WHERE order_id=#{id}")
    public TOrder findById(int id);

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Lang(SimpleSelectInExtendedLanguageDriver.class)
    @Select("SELECT * FROM t_order WHERE order_id in (#{idList})")
    public List<TOrder> queryIn(@Param("idList")List<Integer> idList);

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Select("SELECT * FROM t_order WHERE order_id between #{start} and #{end}")
    public List<TOrder> queryBetween(@Param("start")Integer start, @Param("end")Integer end);

}
