package com.fawvw.ms.voucher.basedao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: voucher
 * @description: 通用访问层配置
 * @author: yiyang.xu
 * @create: 2019-11-22 16:53
 **/
@Configuration
@ComponentScan(basePackages = {"com.fawvw.ms.voucher.basedao"})
@MapperScan({"com.fawvw.ms.voucher.basedao.mapper"})
public class BaseDaoConfig {

}
