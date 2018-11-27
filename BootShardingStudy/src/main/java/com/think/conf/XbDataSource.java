package com.think.conf;

import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/***
 * sharding-jdbc 配置数据源和分库分表规则
 *
 * @author donghuating
 *
 */
@Component
public class XbDataSource {

    @Autowired
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

    private DataSource shardingDataSource;

    private static final Logger logger = LoggerFactory.getLogger(XbDataSource.class);

    @PostConstruct
    public void init() {
        try {
            Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
            dataSourceMap.put("testdb0", primaryDataSource);
            dataSourceMap.put("testdb1", secondaryDataSource);


            TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
            orderTableRuleConfig.setLogicTable("t_order");
            orderTableRuleConfig.setActualDataNodes("testdb${0..1}.t_order_${0..1}");
            orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "testdb${order_id % 2}"));
            orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));
            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
            shardingDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
        } catch (Exception e) {
            logger.error(" XbDataSource init error", e);
        }
    }

    public DataSource getShardingDataSource() {
        return shardingDataSource;
    }

}
