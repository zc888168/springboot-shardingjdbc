package com.think.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/***
 * 配置多个数据源
 * 
 * @author thinkstop
 *
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    // 配置文件中前缀
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
       // return DataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test0");
        dataSource.setUsername("root");// 用户名
        dataSource.setPassword("root");// 密码
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        // return DataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test1");
        dataSource.setUsername("root");// 用户名
        dataSource.setPassword("root");// 密码
        return dataSource;
    }



}
