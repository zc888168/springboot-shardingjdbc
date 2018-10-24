package com.think.conf;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.think.algorithm.ProgramShardingAlgorithm;
import com.think.algorithm.SingleKeyModuloDatabaseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
           Map<String, DataSource> map = new HashMap<String, DataSource>();
           map.put("testdb0", primaryDataSource);
           map.put("testdb1", secondaryDataSource);
           DataSourceRule dataSourceRule = new DataSourceRule(map);
           List<TableRule> tableRuleList = new ArrayList<TableRule>();
           List<String> pList = new ArrayList<String>();
           for (int i = 0; i < 2; i++) {
               pList.add("t_order_" + i);
           }
           tableRuleList.add(new TableRule.TableRuleBuilder("t_order").actualTables(pList).dataSourceRule(dataSourceRule)
                   .tableShardingStrategy(new TableShardingStrategy("order_id", new ProgramShardingAlgorithm())).build());
           ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule)
                   .databaseShardingStrategy(
                           new DatabaseShardingStrategy("order_id", new SingleKeyModuloDatabaseShardingAlgorithm()))
                   .tableRules(tableRuleList).build();
           shardingDataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
       }catch (Exception e) {
           logger.error("XbDataSource init error :", e);
           
       }
    }

    public DataSource getShardingDataSource() {
        return shardingDataSource;
    }
}
