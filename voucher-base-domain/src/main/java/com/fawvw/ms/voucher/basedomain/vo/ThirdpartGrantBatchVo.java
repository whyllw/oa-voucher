package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-05-07
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
 * @Type ThirdpartGrantBatchVo
 * @Desc
 * @date 2020-05-07 15:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "可以发放的第三方批次信息")
public class ThirdpartGrantBatchVo {
    @ApiModelProperty(value = "批次id")
    private Integer batchId;
    @ApiModelProperty(value = "当前导入成功码数")
    private Integer currentImportCount;
    @ApiModelProperty(value = "批次发放总数")
    private Integer voucherCount;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-05-07 zhangyunjiao create
 */