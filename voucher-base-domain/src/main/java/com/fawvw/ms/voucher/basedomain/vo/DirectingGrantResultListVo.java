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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type DirectingGrantResultListVo
 * @Desc
 * @date 2019-11-13 13:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "指定发放结果查询列表")
public class DirectingGrantResultListVo {
    @ApiModelProperty(value = "指定发放结果列表")
    private List<DirectingGrantResultVo> directingGrantResultVos;
    @ApiModelProperty(value = "记录总数")
    private long totalCount;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-11-13 zhangyunjiao create
 */