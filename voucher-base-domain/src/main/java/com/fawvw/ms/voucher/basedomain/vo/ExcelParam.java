package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-12-12
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
 * @Type ExcelParam
 * @Desc
 * @date 2019-12-12 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "excel参数")
public class ExcelParam {
    @ApiModelProperty("数据偏移量")
    private Integer offset;

    @ApiModelProperty("excel页面索引")
    private Integer sheetIndex;

    @ApiModelProperty("是否需要创建新sheet")
    private Boolean isNewSheet = true;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-12-12 zhangyunjiao create
 */