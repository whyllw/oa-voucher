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
 * @Type PartInfoVo
 * @Desc
 * @date 2019-07-29 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "工单备件信息")
public class PartInfoVo {
    @ApiModelProperty(value = "备件名")
    private String partName;
    @ApiModelProperty(value = "备件号")
    private String partCode;
    @ApiModelProperty(value = "适配车型")
    private String carModel;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-07-29 zhangyunjiao create
 */