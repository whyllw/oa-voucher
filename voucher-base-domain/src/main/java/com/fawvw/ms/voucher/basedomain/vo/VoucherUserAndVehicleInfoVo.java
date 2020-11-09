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
 * @Type VoucherUserAndVehicleInfoVo
 * @Desc
 * @date 2019-08-20 21:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户和车辆信息封装")
public class VoucherUserAndVehicleInfoVo {
    @ApiModelProperty(value = "vin码")
    private String vin;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "车型")
    private String makeName;
    @ApiModelProperty(value = "CDP用户id")
    private String aidRef;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-20 zhangyunjiao create
 */