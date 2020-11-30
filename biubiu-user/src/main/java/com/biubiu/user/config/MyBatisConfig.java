package com.biubiu.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisConfig
 *
 * @author wbbaijq
 */
@Configuration
@MapperScan("com.biubiu.user.mapper")
public class MyBatisConfig {

}
