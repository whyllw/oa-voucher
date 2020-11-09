package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherGrantResultVo
 * @Desc
 * @date 2019-08-14 14:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠券发放卡券详情信息")
public class VoucherGrantResultVo {
    @ApiModelProperty(value = "卡券名称")
    private String cardTicketName;
    @ApiModelProperty(value = "卡券生效开始日期")
    private String starttime;
    @ApiModelProperty(value = "卡券有效截止日期 ")
    private String endtime;
    @ApiModelProperty(value = "卡券类型 （1-满减券、2-代金券、3-折扣券、4-实物券、5-套餐券、6-异业券）")
    private String cardTicketType;
    @ApiModelProperty(value = "业务类型代码（1-维修保养  2-保养  3-不限制  4-取送车）")
    private String businessCode;
    @ApiModelProperty(value = "支持金额")
    private String supportAmount;
    @ApiModelProperty(value = "价值")
    private String valueTxt;
    @ApiModelProperty(value = "使用规则描述")
    private String usageRule;
    @ApiModelProperty(value = "卡券状态")
    private String cardTicketStatus;
    @ApiModelProperty(value = "经销商代码列表")
    private String dealercodeList;

}
