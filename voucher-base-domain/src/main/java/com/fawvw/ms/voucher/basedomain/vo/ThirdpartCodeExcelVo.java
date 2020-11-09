package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-04-29
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type ThirdpartCodeExcelVo
 * @Desc
 * @date 2020-04-29 16:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "异业码")
public class ThirdpartCodeExcelVo {
    @ExcelProperty(index = 0)
    private Integer index;
    @ExcelProperty(index = 1)
    private String thirdpartRedeemCode;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-04-29 zhangyunjiao create
 */