package com.fawvw.ms.voucher.basedomain.vo.req;

import com.fawvw.ms.oa.core.constants.RequestConstants;
import lombok.Data;

/**
 * @program: one-app-server
 * @description: CDP消息发送请求参数
 * @author: liwu
 * @create: 2019-08-15 13:05
 **/

@Data
public class BaseMsgParam {
    private String templateType = RequestConstants.CDP_MSG_PARAM;
}
