package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-02-04
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type VoucherPopUpMessageInfo
 * @Desc
 * @date 2020-02-04 12:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠券弹屏信息")
public class VoucherPopUpMessageInfo {
    @ApiModelProperty(value = "优惠券名称")
    private String voucherName;

    @ApiModelProperty(value = "优惠券种类 1 代金券，2 套餐劵，3 实物劵，4 权益劵")
    private Byte voucherType;

    @ApiModelProperty(value = "固定核销有效期开始时间")
    private String redeemStartTime;

    @ApiModelProperty(value = "固定核销有效期截止时间")
    private String redeemEndTime;

    @ApiModelProperty(value = "限定业务类型:  1 不限制，2 保养，3 取送车，4 维修保养")
    private Byte businessType;

    @ApiModelProperty(value = "劵面金额(元)（仅代金劵需要）")
    private String redeemValue;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-02-04 zhangyunjiao create
 */