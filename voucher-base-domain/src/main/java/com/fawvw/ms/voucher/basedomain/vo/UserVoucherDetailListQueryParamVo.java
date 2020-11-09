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
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type UserVoucherDetailListQueryParamVo
 * @Desc
 * @date 2019-08-18 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵批次列表查询参数")
public class UserVoucherDetailListQueryParamVo {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int TEN = 10;

    @ApiModelProperty(value = "劵状态：1 未领取，2 已撤回，3 已领取，4 已冻结 5 已使用 6 已过期")
    @Range(min = ONE, max = SIX, message = "优惠劵劵状态值错误")
    private Byte voucherStatus = null;
    @ApiModelProperty(value = "手机号")
    private String mobile = null;
    @ApiModelProperty(value = "vin码")
    private String vin = null;
    @ApiModelProperty(value = "核销开始时间")
    private Long redeemStartTime = null;
    @ApiModelProperty(value = "核销截止时间")
    private Long redeemEndTime = null;
    @ApiModelProperty(value = "发放方, DLR: 经销商,OEM: 厂商")
    private String grantChannel = null;
    @ApiModelProperty(value = "发放方经销商服务代码")
    private String grantDealerCode = null;
    @ApiModelProperty(value = "工单号")
    private String workSheetNo = null;
    @ApiModelProperty(value = "过期开始时间")
    private Long expireStartTime = null;
    @ApiModelProperty(value = "过期截止时间")
    private Long expireEndTime = null;
    @ApiModelProperty(value = "核销方经销商服务代码")
    private String redeemDealerCode = null;
    @ApiModelProperty(value = "核销码")
    private String redeemCode = null;
    @ApiModelProperty(value = "领取开始时间")
    private Long drawStartTime = null;
    @ApiModelProperty(value = "领取截止时间")
    private Long drawEndTime = null;
    @ApiModelProperty(value = "发放大区代码")
    private String grantBigRegionCode = null;
    @ApiModelProperty(value = "核销大区代码")
    private String redeemBigRegionCode = null;
    @ApiModelProperty(value = "发放小区名称")
    private String grantSmallRegionName = null;
    @ApiModelProperty(value = "核销小区名字")
    private String redeemSmallRegionName = null;
    @ApiModelProperty(value = "活动名称")
    private String activityName = null;
    @ApiModelProperty(value = "批次batchId")
    @Min(value = ONE, message = "批次id不能小于1")
    private Integer batchId = null;
    @ApiModelProperty(value = "当前页码")
    @Min(value = ONE, message = "当前页码不能小于1")
    private Integer currentPage;
    @ApiModelProperty(value = "一页条数")
    @Min(value = ONE, message = "一页条数不能小于1")
    private Integer pageSize = TEN;
    @ApiModelProperty(value = "优惠劵名称")
    private String voucherName = null;
    @ApiModelProperty(value = "优惠劵种类: 1 代金券，2 套餐劵，3 实物劵，4 权益劵 5异业劵")
    @Range(min = ONE, max = FIVE, message = "优惠劵种类值错误")
    private Byte voucherType = null;
    @ApiModelProperty(value = "限定业务类型: 1 不限制，2 保养，3 取送车，4 维修保养")
    @Range(min = ONE, max = FOUR, message = "限定业务类型值错误")
    private Byte businessType = null;
    @ApiModelProperty(value = "发放类型")
    @Range(min = ONE, max = FIVE, message = "发放类型，1:自动发放,2:推送至我的福利, 3:推送至取送车福利，4:筛选发放, 5:指定发放")
    private Byte grantType;
    @ApiModelProperty(value = "发放场景")
    @Range(min = ZERO, max = THREE, message = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务")
    private Byte grantBusinessType;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-18 zhangyunjiao create
 */