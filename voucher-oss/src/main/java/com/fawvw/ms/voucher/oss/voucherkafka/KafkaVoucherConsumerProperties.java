package com.fawvw.ms.voucher.oss.voucherkafka;

import java.util.Properties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: liwu
 * @mail: **
 * @Date: 2020/10/9 14:10
 * @Description: OneApp优惠券消费者配置
 */
@Data
@Component
public class KafkaVoucherConsumerProperties extends VoucherKafkaConfig {

    @Value("${kafka.consumer.concurrency-num}")
    protected Integer concurrencyNum;

    @Bean(name = "voucherConsumerProperties")
    public Properties consumerProperties() {
        return super.voucherConsumerProperties();
    }
}
