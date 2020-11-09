package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-12-16
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyunjiao
 * @Type VoucherReportFileVo
 * @Desc
 * @date 2019-12-16 09:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵明细报表")
public class VoucherReportFileVo {
    @ApiModelProperty("记录id")
    private Integer id;
    @ApiModelProperty("报表名称")
    private String reportName;
    @ApiModelProperty("报表扩展名")
    private String extention;
    @ApiModelProperty("用户名")
    private String fkOperationUserRefId;
    @ApiModelProperty("文件条数")
    private Integer fileRecordNum;
    @ApiModelProperty("文件大小")
    private String fileSize;
    @ApiModelProperty("文件下载地址")
    private String url;
    @ApiModelProperty("下载进度")
    private String exportProgress;
    @ApiModelProperty("下载时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-12-16 zhangyunjiao create
 */