package com.fawvw.ms.voucher.oss.voucherkafka;


import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @Author: liwu
 * @mail: **
 * @Date: 2020/10/9 14:10
 * @Description:
 */
@Slf4j
@Component
public class KafkaVoucherConsumerRunner {

    @Autowired
    @Qualifier("voucherConsumerProperties")
    private Properties voucherConsumerProperties;//kafka 消费者配置文件

    @Autowired
    private KafkaVoucherConsumerProperties kafkaVoucherConsumerProperties;

    @Resource(name = "asyncExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @PostConstruct
    public void consumer() {
        int coreNum = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < kafkaVoucherConsumerProperties.getConcurrencyNum(); i++) {
            threadPoolTaskExecutor.submit(() -> {
                VoucherConsumerWorker voucherConsumerWorker = new VoucherConsumerWorker(voucherConsumerProperties,
                    kafkaVoucherConsumerProperties.getConsumerTopics(), coreNum);
                voucherConsumerWorker.startConsume();
            });
        }
    }
}
