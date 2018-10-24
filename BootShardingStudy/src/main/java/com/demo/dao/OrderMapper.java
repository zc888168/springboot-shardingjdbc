package com.demo.dao;

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
    //@Select("SELECT * FROM t_order WHERE user_id in (<foreach item=\"item\" index=\"index\" collection=\"idList\" open=\"(\" separator=\",\" close=\")\"> #{item}</foreach>)")
    @Select("SELECT * FROM t_order WHERE order_id in (31,32）")
    public List<TOrder> queryIn(@Param("idList")List<Integer> idList);

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Select("SELECT * FROM t_order WHERE order_id between #{start} and #{end}")
    public List<TOrder> queryBetween(@Param("start")Integer start, @Param("end")Integer end);

}
