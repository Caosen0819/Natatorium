package com.cc.natatorium.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author caosen
 * @Date 2022/10/18 11:06
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.cc.natatorium.mapper","com.cc.natatorium.dao"})
public class MyBatisConfig {
}
