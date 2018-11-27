package com.think.controller;

import com.think.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * Created by chaoz2 on 2018/11/27.
 */
@Api(tags = "order")
@RequestMapping(path = "/order")
@RestController
public class TOrderController {
    @Resource
    private TOrderService orderService;

    @PutMapping("/batch/{id}")
    @ApiOperation("batch")
    public String batchInsert(@PathVariable Integer id){
        orderService.batchInsert(id);
        return "success";
    }
}
