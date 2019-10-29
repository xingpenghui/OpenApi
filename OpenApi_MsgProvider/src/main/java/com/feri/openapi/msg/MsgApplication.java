package com.feri.openapi.msg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.feri.openapi.msg.dao")
public class MsgApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgApplication.class,args);
    }
}
