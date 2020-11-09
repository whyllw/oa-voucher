package com.fawvw.ms.voucher.oss.controller;

import com.fawvw.ms.oa.base.server.annotation.UserFromToken;
import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWriteOffInfoVo;
import com.fawvw.ms.voucher.oss.service.OssVoucherGrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhubiyuan
 * @Type AdminVoucherWriteOffController
 * @Desc
 * @date 2019-08-22 16:22
 */
@Api(tags = "DLR优惠券核销API")
@RestController
@RequestMapping(value = "/dealer_voucher")
public class OssVoucherWriteOffController {

    @Autowired
    private OssVoucherGrantService ossVoucherGrantService;

    @UserFromToken

    @ApiOperation(value = "待核销优惠券详情")
    //@GetMapping(value = "get_voucher_info")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redeemCode", value = "核销码",
                    required = true, paramType = "query", dataType = "Byte"),
            })
    public Result getVoucherInfo(@RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO,
                                 @RequestParam("redeemCode") String redeemCode) {
        Result result = ossVoucherGrantService.getVoucherInfo(redeemCode, adminUserVO);
        return result;
    }

    @UserFromToken

    @ApiOperation(value = "卡券核销")
    //@PostMapping(value = "write_off_voucher")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redeemCode", value = "核销码",
                    required = true, paramType = "query", dataType = "Byte"),
            })
    public Result writeOffVoucher(@RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO,
                                  @RequestBody @Valid VoucherWriteOffInfoVo voucherWriteOffInfoVo) {
        Result result = ossVoucherGrantService.writeOffVoucher(voucherWriteOffInfoVo, adminUserVO);
        return result;
    }
}
