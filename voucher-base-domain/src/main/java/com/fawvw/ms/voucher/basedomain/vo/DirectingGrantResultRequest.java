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
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type DirectingGrantResultRequest
 * @Desc
 * @date 2019-11-13 11:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "指定发放结果查询条件")
public class DirectingGrantResultRequest {

    private static final int PAGESIZE = 5;

    @ApiModelProperty(value = "导入类型, 1:手工导入, 2:批量导入")
    @Range(min = 1, max = 2, message = "导入类型错误")
    private Byte importType;
    @ApiModelProperty(value = "发放状态, 0:未发放, 1:已发放")
    @Range(min = 0, max = 1, message = "导入类型错误")
    private Byte isGrant;
    @ApiModelProperty(value = "导入开始时间")
    private Long importStartTime;
    @ApiModelProperty(value = "导入截止时间")
    private Long importEndTime;
    @ApiModelProperty(value = "当前页码")
    @Min(value = 1, message = "当前页码不能小于1")
    private Integer currentPage;
    @ApiModelProperty(value = "一页条数")
    @Min(value = 1, message = "一页条数不能小于1")
    private Integer pageSize = PAGESIZE;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-11-13 zhangyunjiao create
 */