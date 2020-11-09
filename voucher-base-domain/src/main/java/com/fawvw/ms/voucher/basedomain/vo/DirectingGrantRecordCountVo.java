package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-11-13
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
 * @Type DirectingGrantRecordCountVo
 * @Desc
 * @date 2019-11-13 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "指定发放结果查询条件")
public class DirectingGrantRecordCountVo {
    @ApiModelProperty(value = "记录数量")
    private Integer recordCount;
    @ApiModelProperty(value = "记录id")
    private Integer grantTypeId;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-11-13 zhangyunjiao create
 */