package com.fawvw.ms.voucher.oss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: one-app-server
 * @Package: com.fawvw.ms.oneappserver.vo.kafka
 * @ClassName: VoucherSendTextVo
 * @Author: liwu
 * @Description: ${description}
 * @Date: 2020/10/10 09:55
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠券推送封装类")
public class VoucherSendTextVo {

    @ApiModelProperty("大区代码")
    private String bigRegionCode;

    @ApiModelProperty("核销经销商")
    private String dealerCode;

    @ApiModelProperty("卡券状态")
    private String cardTicketChildStatus;

    @ApiModelProperty("卡券名称")
    private String cardTicketName;

    @ApiModelProperty("卡券类型")
    private Integer cardTicketType;

    @ApiModelProperty("卡券id")
    private String changecodeId;

    @ApiModelProperty("兑奖结束时间")
    private Integer endtime;

    @ApiModelProperty("卡券模板ID")
    private String generateId;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户电话")
    private String phone;

    @ApiModelProperty("卡券兑换码")
    private String redeemcode;

    @ApiModelProperty("小区代码")
    private String smallRegionCode;

    @ApiModelProperty("兑奖开始时间")
    private Integer starttime;

    @ApiModelProperty("vin码")
    private String vin;

    @ApiModelProperty("核销时间")
    private Integer accountTime;

    @ApiModelProperty("工单号")
    private String verificationNo;

    @ApiModelProperty("业务类型代码")
    private String businessCode;
}
