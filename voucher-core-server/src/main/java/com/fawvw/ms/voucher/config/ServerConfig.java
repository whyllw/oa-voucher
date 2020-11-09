package com.fawvw.ms.voucher.config;

import com.fawvw.ms.voucher.basedao.config.BaseDaoConfig;
import com.fawvw.ms.voucher.baseservice.config.BaseServiceConfig;
import com.fawvw.ms.voucher.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/04/08 14:26
 * @Description: 订单服务总配置
 */
@Configuration
@Import({CoreConfig.class, BaseDaoConfig.class, BaseServiceConfig.class})
public class ServerConfig {

}
