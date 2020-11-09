package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherGrantResultVo
 * @Desc
 * @date 2019-08-14 14:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "发送CDP获取优惠券详情信息参数实体")
public class SendVoucherInfoVo {
    @ApiModelProperty(value = "合法身份验证key")
    private String cardTicketKey;
    @ApiModelProperty(value = "卡券模板ID")
    private String generateId;
    @ApiModelProperty(value = "卡券ID")
    private String changecodeId;
}
