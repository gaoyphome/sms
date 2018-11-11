package com.bmac.ffan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.bmac.ffan.common.constant.DSEnum;
import com.bmac.ffan.core.datascope.DataScopeInterceptor;
import com.bmac.ffan.core.datasource.DruidProperties;
import com.bmac.ffan.core.mutidatasource.DynamicDataSource;
import com.bmac.ffan.core.mutidatasource.config.MutiDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * MybatisPlus配置
 *
 * @author xuzhanfu
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = {"com.bmac.ffan.modular.*.dao", "com.bmac.ffan.common.persistence.dao"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;


    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSourceGuns(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "ffan", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceGuns();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "ffan", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {

        DruidDataSource dataSourceGuns = dataSourceGuns();
        DruidDataSource bizDataSource = bizDataSource();

        try {
            dataSourceGuns.init();
            bizDataSource.init();
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(DSEnum.DATA_SOURCE_GUNS, dataSourceGuns);
        hashMap.put(DSEnum.DATA_SOURCE_BIZ, bizDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
