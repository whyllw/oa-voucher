package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherBatchGrantInfoVo
 * @Desc
 * @date 2019-08-13 17:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "优惠劵发放批次信息")
public class VoucherBatchGrantInfoVo {
    @ApiModelProperty(value = "发放类型ID")
    private Integer grantTypeId;
    @ApiModelProperty(value = "发放类型 1:自动发放,2:推送至我的福利, 3:推送至取送车福利")
    private Byte grantType;
    @ApiModelProperty(value = "卡券名称")
    private String voucherName;
    @ApiModelProperty(value = "优惠劵种类: 1 代金券，2 套餐劵，3 实物劵，4 权益劵")
    private Byte voucherType;
    @ApiModelProperty(value = "券码金额")
    private String redeemValue;
    @ApiModelProperty(value = "限制使用金额(元)（仅代金劵需要）")
    private String minimumSpendAmount;
    @ApiModelProperty(value = "卡券批次ID")
    private Integer batchId;
    @ApiModelProperty(value = "卡券模板ID")
    private String generateId;
    @ApiModelProperty(value = "单人发放数量限制")
    private Integer grantLimitPerTime;
    @ApiModelProperty(value = "单人发放次数限制")
    private Integer grantTimesLimit;
    @ApiModelProperty(value = "有效期开始")
    private String startTime;
    @ApiModelProperty(value = "有效期结束")
    private String endTime;
    @ApiModelProperty(value = "限定业务类型:  1 不限制，2 保养，3 取送车，4 维修保养")
    private Byte businessType;
    @ApiModelProperty(value = "业务类型值")
    private String businessTypeOption;
    @ApiModelProperty(value = "有效期类型")
    private Byte expiryDateType;
    @ApiModelProperty(value = "动态核销有效天数")
    private Integer validForNumberOfDays;
    @ApiModelProperty(value = "使用说明")
    private String usageRule;
    @ApiModelProperty(value = "当前剩余发放次数")
    private Integer curLastGrantTimes;
    @ApiModelProperty(value = "经销商销售代码集合")
    private List<String> dealerCodeList;
    @ApiModelProperty(value = "发放时间类型：0全部 1工作日 2周末")
    private Byte grantTimeType;
    @ApiModelProperty(value = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务")
    private Byte grantBusinessType;
    @ApiModelProperty(value = "是否限制vin码，0 不限制，1 必须绑定vin码")
    private Byte isVinLimited;
}
