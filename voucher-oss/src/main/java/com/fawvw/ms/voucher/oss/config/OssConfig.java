package com.fawvw.ms.voucher.oss.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description: Oss模块配置
 * @author: yiyang.xu
 * @create: 2019-11-26 13:27
 **/
@Configuration
@ComponentScan(basePackages = {"com.fawvw.ms.voucher.oss"})
@MapperScan({"com.fawvw.ms.voucher.oss.mapper","com.fawvw.ms.voucher.basedao.mapper"})
public class OssConfig {

}
