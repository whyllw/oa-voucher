package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherDealerGrantRecordVo
 * @Desc
 * @date 2019-08-20 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "套餐券领取信息")
public class VoucherDealerGrantRecordVo {
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("车型")
    private String vehicleMode;
    @NotNull(message = "vin码不能为空")
    @ApiModelProperty("vin码")
    private String vin;
    @NotNull(message = "卡券批次ID不能为空")
    @ApiModelProperty("卡券批次ID")
    private Integer batchId;
    @NotNull(message = "发放数量不能为空")
    @ApiModelProperty("发放数量")
    private Integer grantNum;
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty("用户ID")
    private Integer aid;
    @ApiModelProperty("车型id")
    private Integer carModelId;
    @ApiModelProperty("车辆销售日期")
    private Date vehicleSaleDate;
    @ApiModelProperty("客户名称")
    private String clientName;
    @ApiModelProperty("车牌号")
    private String vehicleNumber;
    @ApiModelProperty("车龄")
    private String carAge;
}
