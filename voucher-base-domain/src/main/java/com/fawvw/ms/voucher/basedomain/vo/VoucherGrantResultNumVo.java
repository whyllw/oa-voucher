package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-08-20
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
 * @Type VoucherGrantResultNumVo
 * @Desc
 * @date 2019-08-20 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵筛选发放结果数")
public class VoucherGrantResultNumVo {
    @ApiModelProperty(value = "发放成功数")
    private Integer sucessCount;
    @ApiModelProperty(value = "发放失败数")
    private Integer failCount;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-20 zhangyunjiao create
 */