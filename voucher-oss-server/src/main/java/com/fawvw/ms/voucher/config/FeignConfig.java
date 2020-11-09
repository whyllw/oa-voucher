package com.fawvw.ms.voucher.config;

import feign.Logger.Level;
import feign.Request;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/04/09 09:47
 * @Description:
 */
@Import(FeignAutoConfiguration.class)
@EnableFeignClients(basePackages = {"com.fawvw.ms.voucher.*.client"})
//
// 使用Feign微服务调用时请启用
@Configuration
public class FeignConfig {


    private static final int CONNECT_TIMEOUT_MILLIS = 100000;
    private static final int READ_TIMEOUT_MILLIS = 100000;


    @Bean
    public Request.Options requestOptions(ConfigurableEnvironment env) {
        return new Request.Options(CONNECT_TIMEOUT_MILLIS, READ_TIMEOUT_MILLIS);
    }

    @Bean
    Level feignLoggerLevel() {
        //NONE, 不记录 BASIC, 仅记录请求方式和URL及响应的状态代码与执行时间.
        //BASIC, 仅记录请求方式和URL及响应的状态代码与执行时间.
        //HEADERS, 日志的基本信息与请求及响应的头.
        //FULL, 记录请求与响应的头和正文及元数据.
        return Level.FULL;
    }
}
