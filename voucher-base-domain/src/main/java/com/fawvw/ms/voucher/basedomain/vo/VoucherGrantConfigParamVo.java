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
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type VoucherGrantConfigParamVo
 * @Desc
 * @date 2019-08-20 21:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "筛选发放查询参数")
public class VoucherGrantConfigParamVo {
    @ApiModelProperty(value = "发放数量")
    @Min(value = 0, message = "发放数量不能小于0")
    private Integer provideNum;
    @ApiModelProperty(value = "应用消息提醒，0:不选择 ，1:选择")
    @Range(min = 0, max = 1, message = "应用消息提醒值为：0或1")
    private Byte appMessageNotice;
    @ApiModelProperty(value = "弹屏提醒： 0 不选择，1 选择")
    @Range(min = 0, max = 1, message = "弹屏提醒值为：0或1")
    private Byte popUpMessageNotice;
    @ApiModelProperty(value = "短信提醒：0 不选择，1 选择")
    @Range(min = 0, max = 1, message = "短信提醒值为：0或1")
    private Byte smsNotice;
    @ApiModelProperty(value = "消息提醒的文案")
    private String messageContent;
    @ApiModelProperty(value = "批次id列表")
    private List<Integer> batchIds;
    @ApiModelProperty(value = "批次id列表")
    private VoucherGrantBySelectParamVo voucherGrantBySelectParamVo;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-20 zhangyunjiao create
 */