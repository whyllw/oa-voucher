package com.fawvw.ms.voucher.baseservice.client.message.core;

import com.fawvw.ms.voucher.basedomain.vo.req.SendVoucherMessageVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/04/09 10:05
 * @Description: 用户服务
 */
@Slf4j
@Component
public class MessageCoreFeignClientFallBack implements FallbackFactory<MessageCoreFeignClient> {

    @Override
    public MessageCoreFeignClient create(Throwable throwable) {
        return new MessageCoreFeignClient() {

            @Override
            public String sendVoucherMessage(SendVoucherMessageVo sendVoucherMessageVo) {
                log.error("MessageCoreFeignClient.sendVoucherMessage remote fallback", throwable);
                return null;
            }
        };
    }


}
