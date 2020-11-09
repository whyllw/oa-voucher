package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-08-13
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
 * @Type VoucherBatchListQueryParamVo
 * @Desc
 * @date 2019-08-13 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵批次列表查询参数")
public class VoucherBatchListQueryParamVo {

    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int TEN = 10;
    @ApiModelProperty(value = "优惠劵种类: 1 代金券，2 套餐劵，3 实物劵，4 权益劵 5异业劵")
    @Range(min = 1, max = FIVE, message = "优惠劵种类值错误")
    private Byte voucherType;
    @ApiModelProperty(value = "优惠劵名称")
    private String voucherName;
    @ApiModelProperty(value = "批次号")
    private String batchCode;
    @ApiModelProperty(value = "批次状态: 1 已保存，2 已生成，3 已过期，4 已终止")
    @Range(min = 1, max = FOUR, message = "限定批次状态值错误")
    private Byte status;
    @ApiModelProperty(value = "限定业务类型: 1 不限制，2 保养，3 取送车，4 维修保养")
    @Range(min = 1, max = FOUR, message = "限定业务类型值错误")
    private Byte businessType;
    @ApiModelProperty(value = "是否可叠加，0 不可叠加，1 可以叠加")
    @Range(min = 0, max = 1, message = "是否可叠加值错误")
    private Byte isOverlapable;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "批次有效期开始时间")
    private Long expireDateStartTime;
    @ApiModelProperty(value = "批次有效期截止时间")
    private Long expireDateEndTime;
    @ApiModelProperty(value = "核销时间(开始值)")
    private Long redeemTimeBegin;
    @ApiModelProperty(value = "核销时间(截止值)")
    private Long redeemTimeEnd;
    @ApiModelProperty(value = "当前页码")
    @Min(value = 1, message = "当前页码不能小于1")
    private Integer currentPage;
    @ApiModelProperty(value = "一页条数")
    @Min(value = 1, message = "一页条数不能小于1")
    private Integer pageSize = TEN;
    @ApiModelProperty(value = "是否发放查询")
    @Range(min = 0, max = 1, message = "是否发放查询, 0 否, 1 是")
    private Byte isGrantSelect = 0;
    @ApiModelProperty(value = "发放类型")
    @Range(min = 1, max = FIVE, message = "发放类型，1:自动发放,2:推送至我的福利, 3:推送至取送车福利，4:筛选发放, 5:指定发放")
    private Byte grantType;
    @ApiModelProperty(value = "发放场景")
    @Range(min = 0, max = THREE, message = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务")
    private Byte grantBusinessType;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-13 zhangyunjiao create
 */