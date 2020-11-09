package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type SendVoucherBatchGrantVo
 * @Desc
 * @date 2019-08-14 15:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "自动发放请求信息")
public class SendVoucherBatchGrantVo {
    /**
     * 合法身份验证key（鉴权）
     */
    private String cardTicketKey;
    /**
     * 卡券模版ID
     */
    private String generateId;
    /**
     * 发放渠道名称
     */
    private String channelName;

    /**
     * 保证幂等性的流水号
     */
    private String serialNumber;

    private List<TicketUserVo> ticketUserList;

}
