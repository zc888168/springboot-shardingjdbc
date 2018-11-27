package com.think.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.think.entity.TOrder;
import com.think.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author Created by chaoz2 on 2018/10/22.
 */
@Api(tags = "Demo")
@RequestMapping(path = "/demo")
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;


    @GetMapping("/index")
    public Object index() {
        return "SUCCESS";
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable Integer id) {
        TOrder order = demoService.findById(id);
        return JSON.toJSONString(order);
    }

    @GetMapping("/in")
    @ApiOperation(value = "in测试")
    public Object in() {
        demoService.listIn();
        return " success";
    }

    @GetMapping("/order")
    @ApiOperation("order")
    public Object testOrder() {


        return JSONArray.toJSON(demoService.testOrder());
    }

    @GetMapping("/page")
    @ApiOperation("page")
    public Object page() {

        return JSONArray.toJSON(demoService.testPage());
    }

    @GetMapping("/between")
    @ApiOperation("between")
    public Object between() {
        demoService.findbetween();
        return " success";
    }
    @PutMapping("/batch/{id}")
    @ApiOperation("batch")
    public String batchInsert(@PathVariable Integer id){
        demoService.batchInsert(id);
                return "success";
    }


}