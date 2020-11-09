package com.fawvw.ms.voucher.basedomain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type VoucherWithDrawInfoVo
 * @Desc
 * @date 2019-08-20 21:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "撤回套餐券的信息")
public class VoucherWithDrawInfoVo {

    @ApiModelProperty("卡券ID")
    private String changecodeId;

}
