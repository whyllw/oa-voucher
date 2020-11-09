package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-07-29
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type TicketTemplateInfoVo
 * @Desc
 * @date 2019-07-29 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵批次信息")
public class VoucherBatchInfoVo {

    private static final int ONE = 1;
    private static final int FIVE = 5;
    private static final int TWO_THOUSAND = 2000;
    private static final int FOUR = 4;
    private static final int THREE = 3;
    private static final int TWELVE = 12;
    private static final int TWENTY_EIGHT = 28;
    @ApiModelProperty(value = "批次id，编辑、查询列表时使用")
    private Integer id;
    @ApiModelProperty(value = "优惠劵种类: 1 代金券，2 套餐劵，3 实物劵，4 权益劵, 5异业劵")
    @Range(min = ONE, max = FIVE, message = "优惠劵种类值错误")
    private Byte voucherType;
    @ApiModelProperty(value = "优惠劵名称")
    private String voucherName;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "批次号")
    private String batchCode = "";
    @ApiModelProperty(value = "批次有效期")
    private Date drawExpireDate;
    @ApiModelProperty(value = "发放数量")
    @Min(value = 0, message = "发放数量不能小于0")
    private Integer voucherCount;
    @ApiModelProperty(value = "劵面金额（仅代金劵需要）")
    private String redeemValue;
    @ApiModelProperty(value = "限制使用金额（仅代金劵需要）")
    private String minimumSpendAmount;
    @ApiModelProperty(value = "是否到期提醒，0 否，1 是")
    @Range(min = 0, max = 1, message = "是否到期提醒值错误")
    private Byte expirationReminder;
    @ApiModelProperty(value = "提醒到期前天数")
    @Min(value = 0, message = "提醒到期前天数不能小于0")
    private Integer remindBeforeExpireDay;
    @ApiModelProperty(value = "有效期类型，0 固定有效期，1 动态有效期")
    @Range(min = 0, max = 1, message = "有效期类型范围值错误")
    private Byte expiryDateType;
    @ApiModelProperty(value = "固定核销有效期开始时间")
    private Date redeemStartTime;
    @ApiModelProperty(value = "固定核销有效期截止时间")
    private Date redeemEndTime;
    @ApiModelProperty(value = "动态核销有效天数")
    @Min(value = 0, message = "动态核销有效天数不能小于0")
    private Integer validForNumberOfDays;
    @ApiModelProperty(value = "使用说明")
    @Size(min = 0, max = TWO_THOUSAND, message = "备注文字长度为0-2000")
    private String usageRule;
    @ApiModelProperty(value = "用户车龄开始时间(单位：月)（仅套餐劵需要）")
    @Min(value = 0, message = "用户车龄开始时间不能小于0")
    private Integer vehicleMileageMin;
    @ApiModelProperty(value = "用户车龄截止时间用户车龄截止时间(单位：月)（仅套餐劵需要）")
    @Min(value = 0, message = "不能小于0")
    private Integer vehicleMileageMax;
    @ApiModelProperty(value = "单次支持金额（仅套餐劵需要）")
    private String singleTimeLimitDeductionAmount;
    @ApiModelProperty(value = "最小购买数量（仅套餐劵需要）")
    @Min(value = 0, message = "最小购买数量不能小于0")
    private Integer minPurchaseNumber;
    @ApiModelProperty(value = "限定业务类型: 1 不限制，2 保养，3 取送车，4 维修保养")
    @Range(min = 1, max = FOUR, message = "限定业务类型值错误")
    private Byte businessType;
    @ApiModelProperty(value = "限定业务值，以逗号隔开")
    private String businessTypeOption;
    @ApiModelProperty(value = "限定业务文案")
    private String businessTypeOptionText;
    @ApiModelProperty(value = "vin码，0 不限制，1 必须绑定vin码")
    @Range(min = 0, max = 1, message = "vin码值错误")
    private Byte isVinLimited;
    @ApiModelProperty(value = "是否可叠加，0 不可叠加，1 可以叠加")
    @Range(min = 0, max = 1, message = "是否可叠加值错误")
    private Byte isOverlapable;
    @ApiModelProperty(value = "限制工单包含备件，0 不限制，1 限制")
    @Range(min = 0, max = 1, message = "限制工单包含备件值错误")
    private Byte isPartCodeLimited;
    @ApiModelProperty(value = "适用商家，0 全部经销商，1 指定经销商")
    @Range(min = 0, max = 1, message = "适用商家值错误")
    private Byte isDealerLimited;
    @ApiModelProperty(value = "库存数量")
    private Integer stockCount = 0;
    @ApiModelProperty(value = "已发放数量")
    private Integer distributionCount = 0;
    @ApiModelProperty(value = "已领取数量")
    private Integer receivedCount = 0;
    @ApiModelProperty(value = "已使用数量")
    private Integer usedCount = 0;
    @ApiModelProperty(value = "已过期数量")
    private Integer expiredCount = 0;
    @ApiModelProperty(value = "批次状态: 1 已保存，2 已生成，3 已过期，4 已终止")
    private Byte status;
    @ApiModelProperty(value = "是否生成卡劵模版: 0 否，1 是")
    private Byte isGenerateTemplate = 0;
    @ApiModelProperty(value = "抵扣类型：1材料费抵扣，2工时费抵扣，3材料+工时")
    @Range(min = 0, max = THREE, message = "抵扣类型值错误")
    private Byte redeemType = 1;
    @ApiModelProperty(value = "核销渠道: 1:非DMS 2:DMS")
    @Range(min = 1, max = 2, message = "核销渠道值错误")
    private Byte redeemChannel = 2;
    @ApiModelProperty(value = "实物代码")
    private String entityCode = null;
    @ApiModelProperty(value = "发放类型，1:自动发放,2:推送至我的福利, 3:推送至取送车福利，4:筛选发放, 5:指定发放")
    private Byte grantType;
    @ApiModelProperty(value = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务")
    private Byte grantBusinessType;
    @ApiModelProperty(value = "单人发放数量")
    private Integer grantLimitPerTime;
    @ApiModelProperty(value = "单人发放次数")
    private Integer grantTimesLimit;
    @ApiModelProperty(value = "卡劵模版id")
    private String voucherTemplateId;
    @ApiModelProperty(value = "异业劵口令")
    @Length(min = 0, max = TWELVE, message = "异业劵口令长度超出范围")
    private String voucherCodeSignal;
    @ApiModelProperty(value = "异业劵类型,0:无,1:口令, 2:异业码")
    @Range(min = 0, max = 2, message = "异业劵类型值错误")
    private Byte codeType;
    @ApiModelProperty(value = "异业劵logo链接")
    private String logoUrl;
    @ApiModelProperty(value = "异业劵商店跳转链接")
    private String shopUrl;
    @ApiModelProperty(value = "商家名称")
    @Length(min = 0, max = TWENTY_EIGHT, message = "商家名称长度超出范围")
    private String shopName;
    @ApiModelProperty(value = "导入异业码成功数量")
    private Integer successImportCount = 0;
    @ApiModelProperty(value = "导入异业码失败数量")
    private Integer failImportCount = 0;
    @ApiModelProperty(value = "核销模版信息")
    private RedeemTemplateInfoVo redeemTemplateInfo;
    @ApiModelProperty(value = "发放时间类型：0全部 1工作日 2周末")
    private Byte grantTimeType;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-07-29 zhangyunjiao create
 */