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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type DealerInfoVo
 * @Desc
 * @date 2019-07-29 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "经销商信息")
public class DealerInfoVo {
    @ApiModelProperty(value = "经销商服务代码")
    private String dealerCode;
    @ApiModelProperty(value = "经销商名称")
    private String dealerName;
    @ApiModelProperty(value = "经销商销售代码")
    private String saleCode;
    @ApiModelProperty(value = "大区代码")
    private String bigRegionCode;
    @ApiModelProperty(value = "小区代码")
    private String smallRegionCode;
    @ApiModelProperty(value = "大区名字")
    private String bigRegionName;
    @ApiModelProperty(value = "小区名字")
    private String smallRegionName;
    @ApiModelProperty(value = "商家名称")
    private String shopName;
    @ApiModelProperty(value = "商家地址")
    private String shopAddress;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-07-29 zhangyunjiao create
 */