package com.fawvw.ms.voucher.core.controller.miniapp;
/*
 * Project: com.fawvw.ms.oneappserver.server.voucher.controller.miniapp
 *
 * File Created at 2019-08-22
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyunjiao
 * @Type VoucherMiniAppController
 * @Desc
 * @date 2019-08-22 10:40
 */
@Api(tags = "车主小程序优惠券API")
@RestController
@RequestMapping(value = "/voucher/mini_app")
public class VoucherMiniAppController {
    @Autowired
    private CoreVoucherGrantService coreVoucherGrantService;

    @Autowired
    HttpServletRequest request;

    @UserFromCache
    @ApiOperation(value = "小程序查询我的优惠券列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_my_voucher_list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "voucherType", value = "优惠劵类型 1 代金券，2 套餐劵，3 实物劵，4 权益劵", required = true, paramType = "query", dataType = "Byte"),
        @ApiImplicitParam(name = "voucherStatus", value = "卡券状态（10-待激活，20-可用，30-已使用，40-无效）", required = true, paramType = "query", dataType = "String"),
        })
    public Result getMyVoucherList(@RequestParam(name = "userVo", required = false) UserVo userVo,
        @RequestParam(name = "voucherType", required = false) Byte voucherType,
        @RequestParam(name = "voucherStatus", required = true) String voucherStatus,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
        @RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage) throws Exception {
        if (userVo.getUserId() == VoucherConstants.NOT_EXIST_USER_ID) {
            return ResultUtils.fail(ResultEnum.Q_AND_A_PARAMS_ERROR_USER_VO_IS_NULL);
        }
        String accessToken = request.getHeader(VoucherConstants.AUTHORIZATION);
        VoucherBatchMyWelfareInfoListVo infoListVo = coreVoucherGrantService
            .getMyVocherList(userVo, accessToken, voucherType, voucherStatus, pageSize, currentPage, "-1");
        return ResultUtils.success(infoListVo);
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
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-22 zhangyunjiao create
 */