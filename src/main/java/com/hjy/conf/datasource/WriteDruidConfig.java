package com.hjy.conf.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by hjy on 17-9-25.
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.write")
public class WriteDruidConfig {

    public DataSource writeDruidConfig() throws Exception{
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        druidDataSource.setDriverClassName(this.driverClassName);
        druidDataSource.setInitialSize(this.initialSize);
        druidDataSource.setMaxActive(this.maxActive);
        druidDataSource.setMinIdle(this.minIdle);
        druidDataSource.setFilters(this.filters);
        druidDataSource.setMaxWait(this.maxWait);
        druidDataSource.setTestOnBorrow(this.testOnBorrow);
        druidDataSource.setTestOnReturn(this.testOnReturn);
        druidDataSource.setTestWhileIdle(this.testWhileIdle);
        druidDataSource.setValidationQuery(this.validationQuery);
        druidDataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        druidDataSource.init();
        return druidDataSource;
    }

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int maxActive;

    private int minIdle;

    private String filters;

    private long maxWait;
    
    private boolean testOnBorrow;
    
    private boolean testOnReturn;
    
    private boolean testWhileIdle;
    
    private String validationQuery;
    
    private long timeBetweenEvictionRunsMillis;
}
