package com.feri.openapi.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 15:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class MsgApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgApiApplication.class,args);
    }
}
