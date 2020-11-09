package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-11-01
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
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type DirectingGrantRequest
 * @Desc
 * @date 2019-11-01 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "指定发放手机号或vin")
public class DirectingGrantRequest {
    @ApiModelProperty(value = "数据类型, 1:手机号 2:vin")
    @Range(min = 1, max = 2, message = "数据类型错误")
    private Byte dataType;
    @ApiModelProperty(value = "单人发放数量")
    private Integer grantLimitPerTime;
    @ApiModelProperty(value = "数据,手机号或者vin数组")
    private List<String> dataList;
    @ApiModelProperty(value = "批次id列表")
    private List<Integer> batchIds;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-11-01 zhangyunjiao create
 */