package com.fawvw.ms.voucher.basedomain.vo;
/*
 * Project: com.fawvw.ms.oneappserver.vo.voucher
 *
 * File Created at 2019-10-28
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author zhangyunjiao
 * @Type CdpDealerInfoVo
 * @Desc
 * @date 2019-10-28 17:17
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "cdp返回的经销商信息")
public class CdpDealerInfoVo {

    private List<DealerInfoVo> list;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DealerInfoVo {

        @ApiModelProperty(value = "经销商服务代码")
        private String dealerCode;
        @ApiModelProperty(value = "经销商名称")
        private String dealerName;
        @ApiModelProperty(value = "小区代码")
        private String saleSmallRegionCode;
        @ApiModelProperty(value = "小区名称")
        private String saleSmallRegion;
        @ApiModelProperty(value = "大区代码")
        private String saleBigRegionCode;
        @ApiModelProperty(value = "大区名称")
        private String saleBigRegion;
        @ApiModelProperty(value = "经销商销售代码")
        private String salesCode;
    }
}
/**
 * Revision history
 *
 *
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note -------------------------------------------------------------------------
 * 2019-10-28 zhangyunjiao create
 */