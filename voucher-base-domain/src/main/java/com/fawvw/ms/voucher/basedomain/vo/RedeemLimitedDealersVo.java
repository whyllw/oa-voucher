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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type RedeemLimitedDealersVo
 * @Desc
 * @date 2019-08-18 12:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "可以核销的经销商列表")
public class RedeemLimitedDealersVo {
    @ApiModelProperty(value = "序号")
    private String number;
    @ApiModelProperty(value = "经销商销售代码")
    private String saleCode;
    @ApiModelProperty(value = "经销商服务代码")
    private String dealerCode;
    @ApiModelProperty(value = "经销商名称")
    private String dealerName;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-18 zhangyunjiao create
 */