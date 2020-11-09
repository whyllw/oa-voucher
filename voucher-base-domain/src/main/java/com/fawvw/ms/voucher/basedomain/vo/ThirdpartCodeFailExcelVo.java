package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-05-11
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
 * @Type ThirdpartCodeFailExcelVo
 * @Desc
 * @date 2020-05-11 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "导入失败的异业码")
public class ThirdpartCodeFailExcelVo {
    @ExcelProperty("序号")
    private Integer index;
    @ExcelProperty("核销码")
    private String redeemCode;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-05-11 zhangyunjiao create
 */