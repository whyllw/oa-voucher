package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type PackageVoucherInfoVo
 * @Desc
 * @date 2019-08-20 10:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "套餐券信息")
public class PackageVoucherInfoVo {
    @ApiModelProperty("卡券批次ID")
    private Integer batchIds;
    @ApiModelProperty("卡券批次名称")
    private String voucherName;
    @ApiModelProperty("库存")
    private String notReceivedNum;
    @ApiModelProperty("卡券模版ID")
    private String voucherTemplateId;
    @ApiModelProperty("最小购买数量")
    private Integer minPurchaseNumber;
}
