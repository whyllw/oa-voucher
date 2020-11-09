package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-12-13
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
 * @Type VoucherCountInfo
 * @Desc
 * @date 2019-12-13 14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵异步导出信息")
public class VoucherCountInfo {
    @ApiModelProperty("页面数")
    private Integer pageCount;
    @ApiModelProperty("总记录数")
    private Integer totalCount;
    @ApiModelProperty("是否压缩")
    private Boolean isZip;
    @ApiModelProperty("文件名字")
    private String fileName;
    @ApiModelProperty("导出文件id")
    private Integer reportFileId;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-12-13 zhangyunjiao create
 */