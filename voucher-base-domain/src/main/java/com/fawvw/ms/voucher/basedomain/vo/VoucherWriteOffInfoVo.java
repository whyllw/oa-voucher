package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherWriteOffInfoVo
 * @Desc
 * @date 2019-08-23 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "撤回套餐券的信息")
public class VoucherWriteOffInfoVo {
    @ApiModelProperty("发放方名称")
    private String grantName;
    @ApiModelProperty("服务代码")
    private String dealerCode;
    @ApiModelProperty("优惠券批次")
    private String batchCode;
    @ApiModelProperty("券号")
    private String redeemCode;
    @ApiModelProperty("优惠券名称")
    private String voucherName;
    @ApiModelProperty("优惠券类型")
    private String voucherType;
    @ApiModelProperty("限定业务类型")
    private String businessType;
    @ApiModelProperty("券面金额")
    private String redeemValue;
    @ApiModelProperty("消费使用额度")
    private String minimumSpendAmount;
    @ApiModelProperty("过期时间")
    private String expiredTime;
    @ApiModelProperty("工单号")
    @NotNull(message = "工单号不能为空")
    private String orderNumber;
    @ApiModelProperty("是否包含附件 1：包含，2：不包含")
    private Byte annex;
    @ApiModelProperty("备附件集合")
    private List<VoucherLimitedPartsMapping> limitedPartsMappings;
    @ApiModelProperty("vin码")
    private String vin;
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("身份证")
    private String idCard;

}
