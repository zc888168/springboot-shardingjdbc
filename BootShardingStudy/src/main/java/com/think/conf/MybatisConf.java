package com.think.conf;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis的配置
 *
 * @author donghuating
 */
@Configuration
@EnableTransactionManagement
public class MybatisConf {

    @Autowired
    private XbDataSource xbDataSource;

    /**
     * 获取sqlFactory
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        /**
         * sharding-jdbc 产生的DataSource
         */
        DataSource dataSource = xbDataSource.getShardingDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(
                environment);
        // Dao层包路径
        configuration.addMappers("com.think.dao.*");
        configuration.addMappers("classpath:mapper/*.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Resource resource = new FileSystemResource("/Users/macbook/develop/learn/sharding-jdbc/mavenversion/bootshrdingdemo/springboot-shardingjdbc/BootShardingStudy/src/main/resources/mapper/TorderMapper.xml");

        Resource[] resources = new Resource[]{resource};
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {

        return new DataSourceTransactionManager(xbDataSource.getShardingDataSource());
    }

}
