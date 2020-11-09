package com.fawvw.ms.voucher.oss.voucherkafka;


import com.fawvw.ms.common.utils.kafka.MsMultiThreadKafkaConsumer;
import com.fawvw.ms.oa.core.utils.MapUtil;
import com.fawvw.ms.voucher.oss.vo.VoucherSendTextVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

/**
 * @Author: liwu
 * @mail: **
 * @Date: 2020/10/9 14:10
 * @Description: OneApp优惠券消费者
 */
@Slf4j
public class VoucherConsumerWorker extends MsMultiThreadKafkaConsumer<Object> {

    /**
     * 勋章墙构造类，可以通过构造参数引入服务、配置，如UserService
     * @param properties 消费配置
     * @param consumerTopics 消费主题
     * @param threadCpuRatio 线程CPU核心数
     */

    public VoucherConsumerWorker(Properties properties, String[] consumerTopics, int threadCpuRatio) {
        super(properties, consumerTopics, threadCpuRatio);
    }

    @Override
    public void preConsume(Object object) {
        log.info("VoucherConsumerWorker.preConsume >>> Object:{}",object);
        log.info("VoucherConsumerWorker.preConsume >>> startTime:{}",System.currentTimeMillis());
    }

    @Override
    public Object consume(Object object) {
        log.info("VoucherConsumerWorker.consume >>> medalSendText:{}",object);
        List<VoucherSendTextVo> voucherSendList = MapUtil.map2Java(new VoucherSendTextVo(), (ArrayList) object);
        if (CollectionUtils.isNotEmpty(voucherSendList)) {
            voucherSendList.forEach(voucherSendTextVo -> {
                //拿到kafka推送的消息更新到本地数据库

            });
        }
        return object;
    }

    @Override
    public void postConsume(Object object) {
        log.info("VoucherConsumerWorker.postConsume >>> medalSendText:{}, messageSendText:{}",
            object, object.toString());
        log.info("VoucherConsumerWorker.postConsume >>> endTime:{}", System.currentTimeMillis());
    }
}
