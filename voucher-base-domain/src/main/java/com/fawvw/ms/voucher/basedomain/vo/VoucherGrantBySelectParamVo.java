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
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhangyunjiao
 * @Type VoucherGrantBySelectParamVo
 * @Desc
 * @date 2019-08-20 17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "筛选发放查询参数")
public class VoucherGrantBySelectParamVo {

    private static final int ZORE = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int TEN = 10;

    @ApiModelProperty(value = "劵状态：0 潜客 ,1 车主 ")
    @Range(min = ZORE, max = TWO, message = "用户类型")
    private Byte userType;
    @ApiModelProperty(value = "车型id")
    private String makeId;
    @ApiModelProperty(value = "车型名称")
    private String makeName;
    @ApiModelProperty(value = "车牌")
    private String vehicleNo;
    @ApiModelProperty(value = "销售开始时间")
    private String saleStartTime;
    @ApiModelProperty(value = "销售截止时间")
    private String saleEndTime;
    @ApiModelProperty(value = "最后入库开始时间")
    private String lastBackStartTime;
    @ApiModelProperty(value = "最后入库截止时间")
    private String lastBackEndTime;
    @ApiModelProperty(value = "最后入库开始里程")
    private Integer mileageMin;
    @ApiModelProperty(value = "最后入库截止里程")
    private Integer mileageMax;
    @ApiModelProperty(value = "当前页码")
    @Min(value = ONE, message = "当前页码不能小于1")
    private Integer currentPage;
    @ApiModelProperty(value = "一页条数")
    @Min(value = ONE, message = "一页条数不能小于1")
    private Integer pageSize = TEN;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-20 zhangyunjiao create
 */