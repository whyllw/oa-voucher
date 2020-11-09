package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-05-12
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
 * @Type ThirdpartShopVo
 * @Desc
 * @date 2020-05-12 10:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "第三方适用门店信息")
public class ThirdpartShopVo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "门店名")
    private String shopName;
    @ApiModelProperty(value = "门店地址")
    private String shopAddress;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-05-12 zhangyunjiao create
 */