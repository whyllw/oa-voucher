package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-08-28
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
 * @Type VoucherRedPacketVo
 * @Desc
 * @date 2020-08-28 13:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵红包信息封装")
public class VoucherRedPacketVo {

    @ApiModelProperty(value = "有效期类型 0 固定有效期，1 动态有效期")
    private Byte expiryDateType;

    @ApiModelProperty(value = "发放时间类型：0全部 1工作日 2周末")
    private Byte grantTimeType;

    @ApiModelProperty(value = "红包开始时间")
    private String startTime;

    @ApiModelProperty(value = "红包截止时间")
    private String endTime;
}
/**
 * Revision history -------------------------------------------------------------------------
 *
 * Date Author Note ------------------------------------------------------------------------- 2020-08-28 zhangyunjiao
 * create
 */