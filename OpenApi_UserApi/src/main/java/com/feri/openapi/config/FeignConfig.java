package com.feri.openapi.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 20:06
 */
@Configuration
public class FeignConfig {
    private int connecttimeout=10000;//10秒
    private int readtimeout=10000;//10秒
    @Bean
    public Request.Options createOp(){
        return new Request.Options(connecttimeout,readtimeout);
    }
}
