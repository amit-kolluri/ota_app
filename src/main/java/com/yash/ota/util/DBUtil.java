package com.yash.ota.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;

@Component
public class DBUtil {

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate JdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }
}
