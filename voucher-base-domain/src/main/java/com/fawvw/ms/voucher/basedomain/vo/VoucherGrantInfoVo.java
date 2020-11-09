package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherGrantInfoVo
 * @Desc
 * @date 2019-08-13 09:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵发放信息")
public class VoucherGrantInfoVo {
    @ApiModelProperty(value = "发放类型: 1:自动发放,2:推送至我的福利, 3:推送至取送车福利")
    private Byte grantType;
    @ApiModelProperty(value = "发放场景: 0 不涉及，1:取送车,2:维保预约,3:延时服务")
    private Byte businessType;
    @ApiModelProperty(value = "单人发放数量限制")
    private Integer grantLimitPerTime;
    @ApiModelProperty(value = "单人发放次数限制")
    private Integer grantTimesLimit = 1;
    @ApiModelProperty(value = "触发条件，1:提交预约成功")
    private Byte triggerCondition;
    @ApiModelProperty(value = "应用消息提醒，0:不选择 ，1:选择")
    private Byte appMessageNotice;
    @ApiModelProperty(value = "弹屏提醒： 0 不选择，1 选择")
    private Byte popUpMessageNotice;
    @ApiModelProperty(value = "短信提醒：0 不选择，1 选择")
    private Byte smsNotice;
    @ApiModelProperty(value = "消息提醒文案")
    private String voucherReceivedMessageContent;
    @ApiModelProperty(value = "优惠券批次ID集合")
    private List<Integer> voucherBatchId;
    @ApiModelProperty(value = "发放时间类型：0全部 1工作日 2周末")
    private Byte grantTimeType;
}
