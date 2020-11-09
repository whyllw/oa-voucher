package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-12-02
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
 * @Type VoucherSmsParam
 * @Desc
 * @date 2019-12-02 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵发劵短信")
public class VoucherSmsParam {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "业务请求携带的appkey")
    private String channelAppKey;
    @ApiModelProperty(value = "短信模版编号")
    private String tplId;
    @ApiModelProperty(value = "动态参数")
    private List<String> params;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-12-02 zhangyunjiao create
 */