package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-05-06
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
 * @Type ThirdpartShopExcelVo
 * @Desc
 * @date 2020-05-06 10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "异业第三方商家")
public class ThirdpartShopExcelVo {

    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    @ExcelProperty(index = INDEX_ZERO)
    private Integer index;
    @ExcelProperty(index = INDEX_ONE)
    private String city;
    @ExcelProperty(index = INDEX_TWO)
    private String shopName;
    @ExcelProperty(index = INDEX_THREE)
    private String shopAddress;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-05-06 zhangyunjiao create
 */