package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-08-18
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type UserVoucherDetailVo
 * @Desc
 * @date 2019-08-18 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户优惠劵明细")
public class UserVoucherDetailVo {

    private static final int ONE = 1;
    private static final int FOUR = 4;

    @ApiModelProperty(value = "发放大区名称")
    private String grantBigRegionName;
    @ApiModelProperty(value = "发放小区名称")
    private String grantSmallRegionName;
    @ApiModelProperty(value = "发放方经销商名称")
    private String grantDealerName;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "发放方经销商服务代码")
    private String grantDealerCode;
    @ApiModelProperty(value = "客户")
    private String userName;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "车型")
    private String carMake;
    @ApiModelProperty(value = "车辆销售日期")
    private Date vehicleSaleDate;
    @ApiModelProperty(value = "vin码")
    private String vin;
    @ApiModelProperty(value = "领取时间")
    private Date drawTime;
    @ApiModelProperty(value = "核销时间")
    private Date redeemTime;
    @ApiModelProperty(value = "过期时间")
    private Date expireTime;
    @ApiModelProperty(value = "工单号")
    private String workSheetNo;
    @ApiModelProperty(value = "核销码")
    private String redeemCode;
    @ApiModelProperty(value = "劵状态：1 未领取，2 已撤回，3 已领取，4 已冻结 5 已使用 6 已过期")
    private Byte voucherStatus;
    @ApiModelProperty(value = "劵状态名字")
    private String voucherStatusName;
    @ApiModelProperty(value = "核销大区名字")
    private String redeemBigRegionName;
    @ApiModelProperty(value = "核销小区名字")
    private String redeemSmallRegionName;
    @ApiModelProperty(value = "核销方经销商名称")
    private String redeemDealerName;
    @ApiModelProperty(value = "核销方经销商服务代码")
    private String redeemDealerCode;
    @ApiModelProperty(value = "发放方")
    private String grantChannel;
    @ApiModelProperty(value = "单次支持金额")
    private String singleTimeLimitDeductionAmount;
    @ApiModelProperty(value = "批次号")
    private String batchCode = "";
    @ApiModelProperty(value = "优惠劵名称")
    private String voucherName;
    @ApiModelProperty(value = "优惠劵种类: 1 代金券，2 套餐劵，3 实物劵，4 权益劵")
    @Range(min = ONE, max = FOUR, message = "优惠劵种类值错误")
    private Byte voucherType;
    @ApiModelProperty(value = "限定业务类型: 1 不限制，2 保养，3 取送车，4 维修保养")
    @Range(min = ONE, max = FOUR, message = "限定业务类型值错误")
    private Byte businessType;
    @ApiModelProperty(value = "劵面金额")
    private String redeemValue;
    @ApiModelProperty(value = "限制使用金额（仅代金劵需要）")
    private String minimumSpendAmount;
    @ApiModelProperty(value = "发放类型，1:自动发放,2:推送至我的福利, 3:推送至取送车福利，4:筛选发放, 5:指定发放")
    private Byte grantType;
    @ApiModelProperty(value = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务")
    private Byte grantBusinessType;
    @ApiModelProperty(value = "批次id")
    private Integer batchId;
    @ApiModelProperty(value = "异业劵类型,0:无,1:口令, 2:异业码")
    private Byte codeType;
    @ApiModelProperty(value = "异业劵口令")
    private String voucherCodeSignal;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-18 zhangyunjiao create
 */