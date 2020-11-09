package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherBatchMyWelfareInfoListVo
 * @Desc
 * @date 2019-08-16 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "我的福利优惠券批次信息封装")
public class VoucherBatchMyWelfareInfoListVo {
    @ApiModelProperty("我的福利优惠券批次信息")
    private List<VoucherBatchMyWelfareInfoVo> list;
    @ApiModelProperty(value = "是否有可领取的优惠券")
    private boolean hasAvailable;
    @ApiModelProperty(value = "是否还有更多数据")
    private boolean hasMore;
}
