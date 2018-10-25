package com.demo;

import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;

/**
 * Description:
 * Created by chaoz2 on 2018/10/25.
 */
public class IdGeneratorTest {
    public static void main(String[] args) {
        //1 id generator
//        IPIdGenerator ipIdGenerator = new IPIdGenerator();
//        System.out.println(ipIdGenerator.generateId());
//
//        //2 CommonSelfIdGenerator
//        CommonSelfIdGenerator commonSelfIdGenerator = new CommonSelfIdGenerator();
//        System.out.println(commonSelfIdGenerator.generateId());

        //HostNameIdGenerator
//        HostNameIdGenerator hostNameIdGenerator = new HostNameIdGenerator();
//        System.out.println(hostNameIdGenerator.generateId());

        DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();
        System.out.println(defaultKeyGenerator.generateKey());
    }
}
