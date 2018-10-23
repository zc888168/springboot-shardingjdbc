package com.think.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.think.bean.DruidDataSourceProperties;
import com.think.bean.DruidDataSourceSecProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;

/***
 * 配置多个数据源
 * 
 * @author thinkstop
 *
 */
@Configuration
public class DataSourceConfig {
    @Resource
    private DruidDataSourceProperties druidDataSourceProperties;

    @Resource
    private DruidDataSourceSecProperties druidDataSourceSecProperties;

    @Bean(name = "primaryDataSource")
    @Primary
    // 配置文件中前缀
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
       // return DataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(druidDataSourceProperties.getUrl());
        dataSource.setUsername(druidDataSourceProperties.getUsername());// 用户名
        dataSource.setPassword(druidDataSourceProperties.getPassword());// 密码
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        // return DataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(druidDataSourceSecProperties.getUrl());
        dataSource.setUsername(druidDataSourceSecProperties.getUsername());// 用户名
        dataSource.setPassword(druidDataSourceSecProperties.getPassword());// 密码
        return dataSource;
    }



}
