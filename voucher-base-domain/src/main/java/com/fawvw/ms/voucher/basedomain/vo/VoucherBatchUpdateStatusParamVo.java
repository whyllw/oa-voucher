package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-08-21
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
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type VoucherBatchUpdateStatusParamVo
 * @Desc
 * @date 2019-08-21 15:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新批次状态")
public class VoucherBatchUpdateStatusParamVo {

    private static final int FOUR = 4;
    @ApiModelProperty(value = "批次batchId")
    @Min(value = 1, message = "批次id不能小于1")
    private Integer batchId;
    @ApiModelProperty(value = "批次状态: 1 已保存，2 已生成，3 已过期，4 已终止")
    @Range(min = 1, max = FOUR, message = "优惠劵劵状态值错误")
    private Byte status;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-21 zhangyunjiao create
 */