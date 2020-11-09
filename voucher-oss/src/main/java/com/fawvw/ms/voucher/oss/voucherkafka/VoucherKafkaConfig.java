package com.fawvw.ms.voucher.oss.voucherkafka;

import java.util.Properties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @Author: liwu
 * @mail: **
 * @Date: 2020/10/9 14:20
 * @Description: kafka配置
 */
@Data
public class VoucherKafkaConfig {
    @Value("${kafka.key.serializer}")
    protected String keySerializer;
    @Value("${kafka.key.deserializer}")
    protected String keyDeserializer;
    @Value("${kafka.value.serializer}")
    protected String valueSerializer;
    @Value("${kafka.value.deserializer}")
    protected String valueDeserializer;
    @Value("${kafka.voucher-consumer.servers}")
    protected String consumerServers;
    @Value("${kafka.voucher-consumer.groupId}")
    protected String consumerGroupId;
    @Value("${kafka.voucher-consumer.autoCommit}")
    protected boolean consumerAutoCommit;
    @Value("${kafka.voucher-consumer.timeoutMs}")
    protected int consumerTimeoutMs;
    @Value("${kafka.voucher-consumer.pollRecords}")
    protected int pollRecords;
    @Value("${kafka.voucher-consumer.offset}")
    protected String consumerOffset;
    @Value("${kafka.voucher-consumer.topics}")
    protected String[] consumerTopics;

    @Bean(name = "voucherConsumerProperties")
    public Properties voucherConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", this.consumerServers);
        properties.put("group.id", this.consumerGroupId);
        properties.put("key.deserializer", this.keyDeserializer);
        properties.put("value.deserializer", this.valueDeserializer);
        properties.put("enable.auto.commit", this.consumerAutoCommit);
        properties.put("session.timeout.ms", this.consumerTimeoutMs);
        properties.put("max.poll.records", this.pollRecords);
        properties.put("auto.offset.reset", this.consumerOffset);
        return properties;
    }
}
