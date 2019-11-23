package com.brandon.harukutsu.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author bconnes
 * @since 11/9/2019
 */

@Configuration
public class PostgresDatasource
{
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource hikariDataSource()
    {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource.class)
            .build();
    }
}
