package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2020-05-12
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
 * @Type ThirdpartShopResultVo
 * @Desc
 * @date 2020-05-12 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "第三方适用门店信息")
public class ThirdpartShopResultVo {
    @ApiModelProperty(value = "门店信息列表")
    List<ThirdpartShopVo> shops;
    @ApiModelProperty(value = "是否有更多数据")
    Boolean hasMore;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-05-12 zhangyunjiao create
 */