package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-10-28
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
 * @Type DealersTreeVo
 * @Desc
 * @date 2019-10-28 15:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "可以经销商树形筛选列表")
public class DealersTreeVo {
    @ApiModelProperty(value = "经销商销售代码")
    private String saleCode;
    @ApiModelProperty(value = "经销商服务代码")
    private String dealerCode;
    @ApiModelProperty(value = "经销商大区名称（根据前端渲染格式设计）")
    private String label;
    @ApiModelProperty(value = "清算账户ID）")
    private String accountId;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-10-28 zhangyunjiao create
 */