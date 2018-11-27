package com.think.dao;

import org.apache.ibatis.annotations.*;

import com.think.entity.TOrder;

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
    @Select("SELECT * from t_order where order_id in (31,32,33,34,35)")
    public List<TOrder> findIn();

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Select("select * from t_order order by order_id asc")
    public List<TOrder> testOrder();

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Select("select * from t_order order by order_id asc limit 2,2")
    public List<TOrder> testPage();

    @Results(value = { @Result(property = "userId", column = "user_id"),
            @Result(property = "orderId", column = "order_id"), })
    @Select("SELECT * from t_order where order_id between 31 and 36")
    public List<TOrder> findbetween();

    @Insert("INSERT INTO `t_order`( " +
            "          <include refid=\"Base_Column_List\"/> " +
            "          ) " +
            "          VALUES " +
            "         <foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" " +
            "                   close=\");\" separator=\"),(\"> " +
            "              #{item.orderId,jdbcType=INTEGER}, " +
            "              #{item.userId,jdbcType=INTEGER} " +
            "          </foreach>")
    public void batchInsert(@Param("list") List<TOrder> list);

}
