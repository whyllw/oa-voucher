package com.fawvw.ms.voucher.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: voucher
 * @description: 核心功能层
 * @author: yiyang.xu
 * @create: 2019-11-26 11:35
 **/
@Configuration
@ComponentScan(basePackages = {"com.fawvw.ms.oa.base.server","com.fawvw.ms.voucher.core"})
@MapperScan("com.fawvw.ms.voucher.core.mapper")
public class CoreConfig {

}
