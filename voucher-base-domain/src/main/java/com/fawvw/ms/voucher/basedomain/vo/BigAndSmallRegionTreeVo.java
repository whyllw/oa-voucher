package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-10-25
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type BigAndSmallRegionTreeVo
 * @Desc
 * @date 2019-10-25 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigAndSmallRegionTreeVo {
    @ApiModelProperty(value = "经销商大区代码")
    private String bigRegionCode;
    @ApiModelProperty(value = "经销商大区名称（根据前端渲染格式设计）")
    private String label;
    @ApiModelProperty(value = "经销商小区和经销商代码")
    private List<SmallRegionAndDealersTreeVo> children;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-10-25 zhangyunjiao create
 */