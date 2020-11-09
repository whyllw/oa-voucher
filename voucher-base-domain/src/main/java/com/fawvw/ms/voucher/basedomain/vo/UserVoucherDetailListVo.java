package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-08-18
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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type UserVoucherDetailListVo
 * @Desc
 * @date 2019-08-18 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户优惠劵明细列表")
public class UserVoucherDetailListVo {
    @ApiModelProperty(value = "用户优惠劵明细列表")
    private List<UserVoucherDetailVo> userVoucherDetailList;
    @ApiModelProperty(value = "记录总数")
    private long totalCount;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-18 zhangyunjiao create
 */