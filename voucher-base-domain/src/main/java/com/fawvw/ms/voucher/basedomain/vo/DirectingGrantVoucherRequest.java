package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-11-22
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
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type DirectingGrantVoucherRequest
 * @Desc
 * @date 2019-11-22 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "对指定用户发放优惠劵请求参数")
public class DirectingGrantVoucherRequest {
    @ApiModelProperty(value = "发放id")
    private Integer id;
    @ApiModelProperty(value = "应用消息提醒，0:不选择 ，1:选择")
    @Range(min = 0, max = 1, message = "应用消息提醒值错误")
    private Byte appMessageNotice;
    @ApiModelProperty(value = "短信提醒：0 不选择，1 选择")
    @Range(min = 0, max = 1, message = "短信提醒值错误")
    private Byte smsNotice;
    @ApiModelProperty(value = "弹屏提醒： 0 不选择，1 选择")
    @Range(min = 0, max = 1, message = "弹屏提醒值错误")
    private Byte popUpMessageNotice;
    @ApiModelProperty(value = "消息内容")
    private String messageContent;
    @ApiModelProperty(value = "短信模版id")
    private String smsTemplateId;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-11-22 zhangyunjiao create
 */