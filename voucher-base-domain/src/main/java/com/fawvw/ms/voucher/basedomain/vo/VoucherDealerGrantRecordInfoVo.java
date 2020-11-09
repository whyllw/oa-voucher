package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherDealerGrantRecordInfoVo
 * @Desc
 * @date 2019-08-20 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "经销商发放记录")
public class VoucherDealerGrantRecordInfoVo {
    @ApiModelProperty("发放记录ID")
    private Integer id;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("vin码")
    private String vin;
    @ApiModelProperty("套餐名字")
    private String voucherName;
    @ApiModelProperty("发放数量")
    private Integer grantCount;
    @ApiModelProperty("批次id")
    private Integer fkBatchId;
    @ApiModelProperty("发放状态 0:已发放，1:已撤回")
    private Byte status;
    @ApiModelProperty("车型名称")
    private String carModelName;
    @ApiModelProperty("单次保养套餐原价(")
    private String redeemValue;

}
