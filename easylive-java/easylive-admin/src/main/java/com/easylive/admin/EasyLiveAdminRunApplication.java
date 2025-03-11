package com.easylive.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.easylive"})
@MapperScan(basePackages = {"com.easylive.mappers"})
@EnableTransactionManagement
@EnableScheduling
public class EasyLiveAdminRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyLiveAdminRunApplication.class, args);
    }
}
