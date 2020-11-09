package com.fawvw.ms.voucher.oss.controller;


import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantBySelectParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantInfoVo;
import com.fawvw.ms.voucher.oss.service.OssVoucherGrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhubiyuan
 * @Type VoucherGrantController
 * @Desc
 * @date 2019-08-13 09:43
 */
@Api(tags = "优惠券发放API")
@RestController
@RequestMapping(value = "/oem_voucher")
public class OssVoucherGrantController {
    @Autowired
    private OssVoucherGrantService ossVoucherGrantService;
    @Autowired
    HttpServletRequest request;


    @ApiOperation(value = "优惠券发放 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "create_voucher_grant")
    public Result createVoucherGrant(@ApiParam(name = "voucherGrantInfoVo", value = "优惠劵发放信息", required = true)
                                     @RequestBody @Valid VoucherGrantInfoVo voucherGrantInfoVo) throws Exception {
        Integer ret = ossVoucherGrantService.createVoucherGrant(voucherGrantInfoVo);
        return ResultUtils.success(ret);
    }


    @ApiOperation(value = "筛选发放获取满足条件的用户数量 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "get_user_count")
    public Result getUserCount(@ApiParam(name = "VoucherGrantConfigParamVo", value = "筛选发放条件及配置", required = true)
                               @RequestBody @Valid VoucherGrantBySelectParamVo voucherGrantBySelectParamVo) {
        Integer ret = ossVoucherGrantService.getUserCount(voucherGrantBySelectParamVo);
        return ResultUtils.success(ret);
    }


    /*@ApiOperation(value = "对用户指定发放 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "grant_voucher_to_directing_user")
    public Result grantDirectingUser(@ApiParam(name = "VoucherGrantConfigParamVo", value = "筛选发放条件及配置", required = true)
                               @RequestBody @Valid DirectingGrantVoucherRequest directingGrantVoucherRequest) throws Exception {
        if (!ossVoucherGrantService.isValidSmsTemplate(directingGrantVoucherRequest)) {
            if (VoucherConstants.MOBILE_SMS_TEMPLATE.equals(directingGrantVoucherRequest.getSmsTemplateId())) {
                throw new ServiceException(ResultEnum.VOUCHER_VIN_SMS_TEMPLATE_ERROR);
            }
            if (VoucherConstants.VIN_SMS_TEMPLATE.equals(directingGrantVoucherRequest.getSmsTemplateId())) {
                throw new ServiceException(ResultEnum.VOUCHER_MOBILE_SMS_TEMPLATE_ERROR);
            }
        }
        ossVoucherGrantService.grantDirectingUser(directingGrantVoucherRequest);
        if (VoucherConstants.ENABLE.equals(directingGrantVoucherRequest.getSmsNotice())) {
            ossVoucherGrantService.sendVoucherSms(directingGrantVoucherRequest);
        }
        return ResultUtils.success(directingGrantVoucherRequest.getId());
    }*/
}
