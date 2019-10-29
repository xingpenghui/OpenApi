package com.feri.openapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:02
 */
@SpringBootApplication
@EnableDiscoveryClient //注册服务
@MapperScan("com.feri.openapi.dao") //扫描Mybatis的持久层
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class,args);
    }
}
