package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherGrantCarInfoVo
 * @Desc
 * @date 2019-08-16 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "待发放优惠券车辆列表")
public class VoucherGrantCarInfoVo {
    @ApiModelProperty("客户名称")
    private String clientName;
    @ApiModelProperty("客户ID")
    private String aid;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("车型")
    private String vehicleMode;
    @ApiModelProperty("车牌号")
    private String vehicleNumber;
    @ApiModelProperty("vin码")
    private String vin;
    @ApiModelProperty("销售日期")
    private Date purchaseDate;
    @ApiModelProperty("车龄")
    private String carAge;
    @ApiModelProperty("车型id")
    private Integer carModelId;
}
