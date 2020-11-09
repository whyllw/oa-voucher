package com.fawvw.ms.voucher.core.controller.client;
/*
 * Project: com.fawvw.ms.oneappserver.server.voucher.controller.client
 *
 * File Created at 2020-09-02
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */


import com.fawvw.ms.oa.base.server.annotation.UserFromCache;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.oneappserver.vo.qanda.UserVo;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchMyWelfareInfoListVo;
import com.fawvw.ms.voucher.core.service.CoreVoucherGrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengbaoLi
 * @Type VoucherClientController
 * @Desc
 * @date 2020-09-02 9:23
 */
@Api(tags = "车主小程序优惠券API")
@RestController
@RequestMapping(value = "/client/voucher")
public class VoucherClientController {

    @Autowired
    private CoreVoucherGrantService coreVoucherGrantService;

    @Autowired
    HttpServletRequest request;

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
    @ApiOperation(value = "我的福利/取送车可领取的优惠券批次列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "get_voucher_list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "grantType", value = "发放类型 2:推送至我的福利, 3:推送至取送车福利", required = true, paramType = "query", dataType = "Byte"),
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
        // VoucherConstants.DEFAULT_PAGE_SIZE
        VoucherBatchMyWelfareInfoListVo infoListVo = coreVoucherGrantService
            .voucherGrantListForApplet(userVo, grantType,
            pageSize, currentPage, createTime);
        infoListVo.setHasAvailable(false);
        if (infoListVo.getList().size() != 0) {
            if (infoListVo.getList().get(0).getBatchStatus() == 2) {
                infoListVo.setHasAvailable(true);
            }
        }
        return ResultUtils.success(infoListVo);
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
    @ApiOperation(value = "查询优惠劵红包信息 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_voucher_red_packet_info")
    public Result getVoucherRedPacketInfo(@RequestParam(name = "userVo", required = false) UserVo userVo) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        return ResultUtils.success(coreVoucherGrantService.getVoucherRedPacketInfo(userVo));
    }
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2020-09-02 chengbaoLi create
 */