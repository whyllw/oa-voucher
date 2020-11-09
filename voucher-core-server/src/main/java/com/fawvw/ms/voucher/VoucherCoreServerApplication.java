package com.fawvw.ms.voucher;

import com.tencent.tsf.monitor.annotation.EnableTsfMonitor;
import com.tencent.tsf.sleuth.annotation.EnableTsfSleuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.tsf.route.annotation.EnableTsfRoute;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.tsf.auth.annotation.EnableTsfAuth;
import org.springframework.tsf.ratelimit.annotation.EnableTsfRateLimit;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient // 使用服务注册发现时请启用
@EnableConfigurationProperties // 使用分布式配置时请启用
@EnableTsfAuth
@EnableTsfRoute
@EnableTsfRateLimit
@EnableTsfSleuth
@EnableTsfMonitor
@EnableWebMvc
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.fawvw.ms.voucher.config",
    "com.fawvw.ms.oa.base.server.config", "com.fawvw.ms.oa.core", "com.fawvw.ms.oa.base.services"})
@Slf4j
public class VoucherCoreServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            SpringApplication.run(VoucherCoreServerApplication.class, args);
        } catch (Exception e) {
            log.error("voucher-core-server start error {}", e.getMessage(), e);
        }
    }


    @Override
    //为了打包springboot项目
    protected SpringApplicationBuilder configure(
        SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}