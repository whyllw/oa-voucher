package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author zhubiyuan
 * @Type VoucherBatchMyWelfareInfoVo
 * @Desc
 * @date 2019-08-14 14:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "我的福利优惠券批次信息")
public class VoucherBatchMyWelfareInfoVo implements Cloneable {

    private static final int TWENTY_EIGHT = 28;

    @ApiModelProperty(value = "发放类型ID")
    private Integer grantTypeId = 0;
    @ApiModelProperty(value = "卡券批次ID")
    private Integer batchId = 0;
    @ApiModelProperty(value = "卡券模版ID")
    private String voucherTemplateId;
    @ApiModelProperty(value = "优惠券名称")
    private String voucherName;
    @ApiModelProperty(value = "优惠券种类 1 代金券，2 套餐劵，3 实物劵，4 权益劵 5 异业劵")
    private Byte voucherType;
    @ApiModelProperty(value = "批次有效期")
    private String drawExpireDate;
    @ApiModelProperty(value = "发放数量")
    private Integer voucherCount = 0;
    @ApiModelProperty(value = "固定核销有效期开始时间")
    private String redeemStartTime;
    @ApiModelProperty(value = "固定核销有效期截止时间")
    private String redeemEndTime;
    @ApiModelProperty(value = "动态核销有效天数")
    private Integer validForNumberOfDays = 0;
    @ApiModelProperty(value = "使用说明")
    private String usageRule;
    @ApiModelProperty(value = "适用商家，0 全部经销商，1 指定经销商")
    private Byte isDealerLimited;
    @ApiModelProperty(value = "有效期类型，0 固定有效期，1 动态有效期")
    private Byte expiryDateType;
    @ApiModelProperty(value = "限定业务类型:  1 不限制，2 保养，3 取送车，4 维修保养")
    private Byte businessType;
    @ApiModelProperty(value = "业务类型值")
    private String businessTypeOption;
    @ApiModelProperty(value = "弹屏提醒： 0 不选择，1 选择")
    private Byte popUpMessageNotice;
    @ApiModelProperty(value = "劵面金额(元)（仅代金劵需要）")
    private String redeemValue;
    @ApiModelProperty(value = "是否限制vin码，0 不限制，1 必须绑定vin码")
    private Byte isVinLimited;
    @ApiModelProperty(value = "经销商code列表")
    private List<DealerInfoVo> dealerList;
    @ApiModelProperty(value = "vin码")
    private String vin;
    @ApiModelProperty(value = "车系")
    private String carMake;
    @ApiModelProperty(value = "CDP卡券状态（10-待激活，20-可用，30-已使用，40-无效）")
    private String voucherStatus;
    @ApiModelProperty(value = "卡券ID")
    private String changeCodeId;
    @ApiModelProperty(value = "兑换码")
    private String redeemCode;
    @ApiModelProperty(value = "限制发放数量")
    private Integer granLimitPerTime = 0;
    @ApiModelProperty(value = "限制使用金额(元)（仅代金劵需要）")
    private String minimumSpendAmount;
    @ApiModelProperty(value = "工单备件信息")
    private List<PartInfoVo> partInfos;
    @ApiModelProperty(value = "限制工单包含备件，0 不限制，1 限制")
    private Byte isPartCodeLimited;
    @ApiModelProperty(value = "抵扣类型：1材料费抵扣，2工时费抵扣，3材料+工时")
    private Byte redeemType;
    @ApiModelProperty(value = "剩余可领劵天数")
    private long lastDrawVoucherDays = 0;
    @ApiModelProperty(value = "适用商家名称")
    @Length(min = 0, max = TWENTY_EIGHT, message = "商家名称长度超出范围")
    private String shopName;
    @ApiModelProperty(value = "异业劵logo链接")
    private String logoUrl;
    @ApiModelProperty(value = "异业劵商店跳转链接")
    private String shopUrl;
    @ApiModelProperty(value = "批次状态: 1 已保存，2 已生成，3 已结束，4 已终止, 5 已领光")
    private Byte batchStatus;
//    @ApiModelProperty(value = "当前经销商是否在劵经销商范围内")
//    private Boolean isInDealersLimited;
}
