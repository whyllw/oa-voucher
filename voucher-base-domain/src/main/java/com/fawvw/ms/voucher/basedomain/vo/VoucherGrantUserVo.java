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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type VoucherGrantUserVo
 * @Desc
 * @date 2019-08-20 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "筛选发放查询参数")
public class VoucherGrantUserVo {
    @ApiModelProperty(value = "用户和车辆信息列表")
    private List<VoucherUserAndVehicleInfoVo> userAndVehicleInfos;
    @ApiModelProperty(value = "符合条件数")
    private Long totalCount;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-20 zhangyunjiao create
 */