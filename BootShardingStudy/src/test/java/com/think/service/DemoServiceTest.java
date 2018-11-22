package com.think.service;

import com.think.AbstractTestBase;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Description:
 * Created by chaoz2 on 2018/11/22.
 */
public class DemoServiceTest  extends AbstractTestBase {

    @Resource
    private DemoService demoService;

    @Test
    public void testMethod(){
        demoService.test();
    }
}
