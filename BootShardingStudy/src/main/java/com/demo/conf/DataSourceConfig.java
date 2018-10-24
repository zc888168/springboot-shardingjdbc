package com.demo.conf;

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
 * @author chaozhang17
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    // 配置文件中前缀
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }


}