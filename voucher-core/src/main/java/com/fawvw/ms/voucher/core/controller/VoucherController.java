package com.fawvw.ms.voucher.core.controller;


import com.fawvw.ms.oa.base.server.annotation.UserFromCache;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.oneappserver.vo.qanda.UserVo;

import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartShopResultVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchMyWelfareInfoListVo;
import com.fawvw.ms.voucher.core.service.CoreVoucherGrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhubiyuan
 * @Type VoucherGrantController
 * @Desc
 * @date 2019-08-15 10:51
 */
@Api(tags = "c端优惠券API")
@RestController
@RequestMapping(value = "/voucher")
public class VoucherController {
    @Autowired
    private CoreVoucherGrantService coreVoucherGrantService;

    @Autowired
    HttpServletRequest request;

    @UserFromCache
    @ApiOperation(value = "我的福利/取送车可领取的优惠券批次列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "get_voucher_list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "grantType", value = "发放类型 2:推送至我的福利, 3:推送至取送车福利", required = true, paramType = "query", dataType = "Byte"),
        @ApiImplicitParam(name = "dealerCode", value = "取送车的经销商", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "createTime", value = "显示时间限制", required = true, paramType = "query", dataType = "String")
        })
    public Result voucherGrantList(@RequestParam(name = "userVo", required = false) UserVo userVo,
                                   @RequestParam(name = "grantType", required = true) Byte grantType,
                                   @RequestParam(name = "createTime", required = false) String createTime,
                                   @RequestParam(name = "pageSize", required = true, defaultValue = "10") int pageSize,
                                   @RequestParam(name = "currentPage", required = true, defaultValue = "1") int currentPage) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        VoucherBatchMyWelfareInfoListVo infoListVo = coreVoucherGrantService
            .voucherGrantList(userVo, grantType,
            VoucherConstants.DEFAULT_PAGE_SIZE, currentPage, createTime);
        return ResultUtils.success(infoListVo);
    }

    @UserFromCache
    @ApiOperation(value = "查询我的优惠券列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_my_voucher_list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "voucherType", value = "优惠劵类型 1 代金券，2 套餐劵，3 实物劵，4 权益劵", required = true, paramType = "query", dataType = "Byte"),
        @ApiImplicitParam(name = "voucherStatus", value = "卡券状态（10-待激活，20-可用，30-已使用，40-无效）", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "businessCode", value = "业务类型代码:1-维修保养  2-保养  3-不限制  4-取送车）", required = true, paramType = "query", dataType = "String"),
        })
    public Result getMyVoucherList(@RequestParam(name = "userVo", required = false) UserVo userVo,
        @RequestParam(name = "voucherType", required = false) Byte voucherType,
        @RequestParam(name = "voucherStatus", required = true) String voucherStatus,
        @RequestParam(name = "businessCode", required = false, defaultValue = "-1") String businessCode,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
        @RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        String accessToken = request.getHeader(VoucherConstants.AUTHORIZATION);
        VoucherBatchMyWelfareInfoListVo infoListVo = coreVoucherGrantService
            .getMyVocherList(userVo, accessToken, voucherType, voucherStatus, pageSize, currentPage, businessCode);
        return ResultUtils.success(infoListVo);
    }

    @UserFromCache
    @ApiOperation(value = "优惠券自动发放触发 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "grant_voucher_automatic")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessType", value = "发放场景: 1:取送车,2:维保预约,3:延时服务", required = true, paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "vin", value = "VIN码", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "validStartTime", value = "动态有效期开始时间", required = true, paramType = "query", dataType = "String")
        })
    public Result voucherGrantAutomatic(@RequestParam(name = "userVo", required = false) UserVo userVo,
                                        @RequestParam(name = "businessType") Integer businessType,
                                        @RequestParam(name = "vin") String vin,
                                        @RequestParam(name = "validStartTime", required = false) String validStartTime) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        String token = request.getHeader(VoucherConstants.AUTHORIZATION);
        VoucherBatchMyWelfareInfoListVo voucherBatchMyWelfareInfoListVo = coreVoucherGrantService
            .grantAutomatic(userVo, businessType, token, vin, validStartTime);
        return ResultUtils.success(voucherBatchMyWelfareInfoListVo);
    }

    @UserFromCache
    @ApiOperation(value = "取送车、我的福利发放触发 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "grant_voucher")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "grantType", value = "发放类型，2:推送至我的福利, 3:推送至取送车福利", required = true, paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "batchIds", value = "卡券批次ID（多个ID用逗号隔开）", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "vin", value = "VIN码", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "validStartTime", value = "动态有效期开始时间", required = true, paramType = "query", dataType = "String")
        })
    public Result voucherGrant(@RequestParam(name = "userVo", required = false) UserVo userVo,
                               @RequestParam(name = "grantType") Byte grantType,
                               @RequestParam(name = "batchIds") String batchIds,
                               @RequestParam(name = "vin", required = false) String vin,
                               @RequestParam(name = "validStartTime", required = false) String validStartTime) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        String token = request.getHeader(VoucherConstants.AUTHORIZATION);
        VoucherBatchMyWelfareInfoListVo voucherBatchMyWelfareInfoListVo = coreVoucherGrantService
            .grantVoucher(userVo, grantType, batchIds, token, vin, validStartTime);
        return ResultUtils.success(voucherBatchMyWelfareInfoListVo);
    }

    @UserFromCache
    @ApiOperation(value = "筛选发放触发 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "grant_voucher_filter")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userVo", value = "用户信息", required = false, paramType = "query", dataType = "body")
        })
    public Result grantVoucherFilter(@RequestParam(name = "userVo", required = false) UserVo userVo) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        String token = request.getHeader(VoucherConstants.AUTHORIZATION);
        VoucherBatchMyWelfareInfoListVo voucherBatchMyWelfareInfoListVo = coreVoucherGrantService
            .grantVoucherFilter(userVo, token);
        //指定发放处理逻辑
        coreVoucherGrantService.grantDirectingUser(userVo, token);
        return ResultUtils.success(voucherBatchMyWelfareInfoListVo);
    }

    @UserFromCache
    @ApiOperation(value = "卡劵冻结 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/freeze_voucher")
    @ResponseBody
    public Object freezeVoucher(@RequestParam(name = "userVo", required = false) UserVo userVo,
                                @RequestBody String params) {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        return coreVoucherGrantService.freezeVoucher(params);
    }

    @UserFromCache
    @ApiOperation(value = "取消卡劵冻结 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/cancel_freeze_voucher")
    @ResponseBody
    public Object cancelFreezeVoucher(@RequestParam(name = "userVo", required = false) UserVo userVo,
                                      @RequestBody String params) {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        return coreVoucherGrantService.cancelFreezeVoucher(params);
    }

    @UserFromCache
    @ApiOperation(value = "卡券核销（通用） -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/use_voucher")
    @ResponseBody
    public Object redeemVoucher(@RequestParam(name = "userVo", required = false) UserVo userVo,
                                @RequestBody String params) {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        return coreVoucherGrantService.redeemVoucher(params);
    }

    @UserFromCache
    @ApiOperation(value = "查询适用门店信息 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_thirdpart_shop_list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "batchId", value = "批次id", required = true, paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "cityCode", value = "当前城市码", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "shopName", value = "搜索门店名", required = false, paramType = "query", dataType = "String")
        })
    public Result getThirdpartShopList(@RequestParam(name = "userVo", required = false) UserVo userVo,
                                       @RequestParam(name = "batchId", required = true) Integer batchId,
                                       @RequestParam(name = "cityCode", required = false) String cityCode,
                                       @RequestParam(name = "shopName", required = false) String shopName,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                       @RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        ThirdpartShopResultVo thirdpartShopResultVo = coreVoucherGrantService
            .getThirdpartShops(cityCode, shopName, batchId, pageSize, currentPage);
        return ResultUtils.success(thirdpartShopResultVo);
    }

    @UserFromCache
    @ApiOperation(value = "查询优惠劵红包信息 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_voucher_red_packet_info")
    public Result getVoucherRedPacketInfo(@RequestParam(name = "userVo", required = false) UserVo userVo) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        return ResultUtils.success(coreVoucherGrantService.getVoucherRedPacketInfo(userVo));
    }
}
