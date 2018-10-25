package com.demo.algorithm;

import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGeneratorFactory;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by chaoz2 on 2018/10/25.
 */
@Service
public class IdGenerator {
    private KeyGenerator keyGenerator = KeyGeneratorFactory.createKeyGenerator(DefaultKeyGenerator.class);

    public KeyGenerator getIdGenerator() {
        //可以自定义算法设置worker_id
        //DefaultKeyGenerator.setWorkerId();
        return keyGenerator;
    }
}
