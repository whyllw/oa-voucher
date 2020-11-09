package com.fawvw.ms.voucher.baseservice.client.message.core;

import com.fawvw.ms.voucher.basedomain.vo.req.SendVoucherMessageVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/05/13 15:35
 * @Description: 消息服务
 */
@FeignClient(name = "server-message-v1", decode404 = true, fallbackFactory = MessageCoreFeignClientFallBack.class)
public interface MessageCoreFeignClient {

    @RequestMapping(value = "/message/send_voucher_msg", method = RequestMethod.POST)
    String sendVoucherMessage(SendVoucherMessageVo sendVoucherMessageVo);

}
