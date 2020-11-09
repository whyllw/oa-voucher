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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type DirectingGrantResultVo
 * @Desc
 * @date 2019-11-13 11:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "指定发放结果")
public class DirectingGrantResultVo {
    @ApiModelProperty(value = "发放id")
    private Integer id;
    @ApiModelProperty(value = "导入类型, 1:手工导入, 2:批量导入")
    @Range(min = 1, max = 2, message = "导入类型错误")
    private Byte importType;
    @ApiModelProperty(value = "导入时间")
    private Date importTime;
    @ApiModelProperty(value = "导入数量")
    private Integer importNum;
    @ApiModelProperty(value = "是否发放状态, 0:未发放, 1:已发放")
    @Range(min = 0, max = 1, message = "是否发放状态错误")
    private Byte isGrant;
    @ApiModelProperty(value = "应用消息提醒，0:不选择 ，1:选择")
    private Byte appMessageNotice;
    @ApiModelProperty(value = "短信提醒：0 不选择，1 选择")
    private Byte smsNotice;
    @ApiModelProperty(value = "弹屏提醒： 0 不选择，1 选择")
    private Byte popUpMessageNotice;
    @ApiModelProperty(value = "导入进度")
    private Integer importProgress;
    @ApiModelProperty(value = "导入检测成功数量")
    private Integer checkSucessNum;
    @ApiModelProperty(value = "导入检测失败数量")
    private Integer checkFailNum;
    @ApiModelProperty(value = "发放sms成功数量")
    private Integer sendSmsSucessNum;
    @ApiModelProperty(value = "发放sms失败数量")
    private Integer sendSmsFailNum;
    @ApiModelProperty(value = "未发放sms数量")
    private Integer noSendSmsNum;
    @ApiModelProperty(value = "批次码")
    private String batchCode;
    @ApiModelProperty(value = "sms发放状态, 0:未发放, 1:发放成功, 2:发放失败")
    @Range(min = 0, max = 2, message = "sms发放状态错误")
    private Byte smsGrantStatus;
    @ApiModelProperty(value = "批次id")
    private Integer batchId;
    @ApiModelProperty(value = "优惠劵批次名次")
    private String voucherName;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-11-13 zhangyunjiao create
 */