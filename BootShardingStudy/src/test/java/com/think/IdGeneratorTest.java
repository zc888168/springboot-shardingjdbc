package com.think;

import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.IPIdGenerator;

/**
 * Description:
 * Created by chaoz2 on 2018/10/25.
 */
public class IdGeneratorTest
{
    public static void main(String[] args) {
        CommonSelfIdGenerator ipIdGenerator = new CommonSelfIdGenerator();
        System.out.println(ipIdGenerator.generateId());
        System.out.println(ipIdGenerator.generateId());
        System.out.println(ipIdGenerator.generateId());
    }
}
