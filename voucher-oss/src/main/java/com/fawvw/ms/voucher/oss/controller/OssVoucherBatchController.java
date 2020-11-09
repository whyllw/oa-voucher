package com.fawvw.ms.voucher.oss.controller;
/*
 * Project: com.fawvw.ms.oneappserver.web.controller.voucher
 *
 * File Created at 2019-07-29
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import com.alibaba.excel.EasyExcel;
import com.fawvw.ms.oa.base.server.annotation.UserFromToken;
import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantRequest;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantResultRequest;
import com.fawvw.ms.voucher.basedomain.vo.ExcelParam;
import com.fawvw.ms.voucher.basedomain.vo.RedeemLimitedDealersVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartCodeFailExcelVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchAddStockParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchBaseInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchUpdateStatusParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantBySelectParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantConfigParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherReportFileVo;
import com.fawvw.ms.voucher.oss.service.OssVoucherBatchService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangyunjiao
 * @Type ticketController
 * @Desc
 * @date 2019-07-29 10:34
 */
@Api(tags = "优惠券相关API")
@RestController
@RequestMapping(value = "/oem_voucher")
@Slf4j
public class OssVoucherBatchController {
    @Autowired
    private OssVoucherBatchService ossVoucherBatchService;

    private static final String FAIL_REDEEM_CODE = "失败核销码";
    private static final String SET_HEADER_COMTENT = "attachment;filename=";
    private static final String HEADER_PARGAM = "no-cache";

    @ApiOperation(value = "创建优惠劵批次 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/create_voucher_batch")
    @UserFromToken

    public Result createVoucherBatch(@ApiParam(name = "voucherBatchInfoVo", value = "优惠劵批次信息", required = true)
                                     @RequestBody @Valid VoucherBatchInfoVo voucherBatchInfoVo,
                                     @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        if (VoucherConstants.REPAIR_AND_MAINTAION_BUSSINESS.equals(voucherBatchInfoVo.getBusinessType())) {
            String bussinessOptions = voucherBatchInfoVo.getBusinessTypeOption();
            List<String> bussinessValueOptions = Arrays.asList(bussinessOptions.split(VoucherConstants.SEPARATOR));
            for (String value : bussinessValueOptions) {
                if (!VoucherConstants.BUSSINESS_VALUE_OPTION.containsKey(value)) {
                    throw new ServiceException(ResultEnum.ONE_ACTIVITY_PARAMETER_ERROR);
                }
            }
        }
        Integer ret = ossVoucherBatchService.createVoucherBatch(voucherBatchInfoVo);

        return ResultUtils.success(ret);
    }

    @ApiOperation(value = "查询优惠劵批次列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/get_voucher_batch_list")
    @UserFromToken

    public Result getVoucherBatchList(@ApiParam(name = "voucherBatchListQueryParamVo", value = "优惠劵批次列表查询参数", required = true)
                                      @RequestBody @Valid VoucherBatchListQueryParamVo voucherBatchListQueryParamVo,
                                      @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {

        return ResultUtils.success(ossVoucherBatchService.getVoucherBatchList(voucherBatchListQueryParamVo));
    }

    @ApiOperation(value = "生成优惠劵模版 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/generate_voucher_template")
    @UserFromToken

    //@OperationLog(operationType = OperationEnum.EDITOR)
    public Result generateVoucherTemplate(@ApiParam(name = "voucherBatchBaseInfoVo", value = "批次基本信息", required = true)
                                          @RequestBody @Valid VoucherBatchBaseInfoVo voucherBatchBaseInfoVo,
                                          @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        return ResultUtils.success(ossVoucherBatchService.generateVoucherTemplate(voucherBatchBaseInfoVo.getBatchId()));
    }

    @ApiOperation(value = "删除优惠劵批次 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/del_voucher_batch")
    @UserFromToken

    //@OperationLog(operationType = OperationEnum.DELETE)
    public Result delVoucherBatch(@ApiParam(name = "voucherBatchBaseInfoVo", value = "批次基本信息", required = true)
                                  @RequestBody @Valid VoucherBatchBaseInfoVo voucherBatchBaseInfoVo,
                                  @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        ossVoucherBatchService.delVoucherBatch(voucherBatchBaseInfoVo.getBatchId());

        return ResultUtils.success(null);
    }

    @ApiOperation(value = "更新优惠劵批次状态 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/update_voucher_batch_status")
    //@OperationLog(operationType = OperationEnum.EDITOR)
    public Result updateVoucherBatchStatus(@ApiParam(name = "voucherBatchUpdateStatusParamVo", value = "更新批次状态基本信息", required = true)
                                           @RequestBody @Valid VoucherBatchUpdateStatusParamVo voucherBatchUpdateStatusParamVo,
                                           @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        ossVoucherBatchService.updateVoucherBatchStatus(voucherBatchUpdateStatusParamVo.getBatchId(), voucherBatchUpdateStatusParamVo.getStatus());

        return ResultUtils.success(null);
    }

    @ApiOperation(value = "编辑优惠劵批次 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @UserFromToken

    @PostMapping(value = "/edit_voucher_batch")
    public Result editVoucherBatch(@ApiParam(name = "voucherBatchInfoVo", value = "优惠劵批次信息", required = true)
                                   @RequestBody @Valid VoucherBatchInfoVo voucherBatchInfoVo,
                                   @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        if (voucherBatchInfoVo.getBusinessType().equals(VoucherConstants.REPAIR_AND_MAINTAION_BUSSINESS)) {
            String bussinessOptions = voucherBatchInfoVo.getBusinessTypeOption();
            List<String> bussinessValueOptions = Arrays.asList(bussinessOptions.split(VoucherConstants.SEPARATOR));
            for (String value : bussinessValueOptions) {
                if (!VoucherConstants.BUSSINESS_VALUE_OPTION.containsKey(value)) {
                    throw new ServiceException(ResultEnum.ONE_ACTIVITY_PARAMETER_ERROR);
                }
            }
        }
        Integer ret = ossVoucherBatchService.editVoucherBatch(voucherBatchInfoVo);

        return ResultUtils.success(ret);
    }

    @ApiOperation(value = "下载可核销的经销商列表模版 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping("/download_dealer_template")
    public void downloadDealerTemplate(HttpServletResponse response,
                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "经销商列表导入模版.xls";
        getResponse(response, fileName);

        HSSFWorkbook workbook = ossVoucherBatchService.downloadDealerTemplate();
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream downloadDealerTemplate error: " + e);
        }
    }

    private void getResponse(HttpServletResponse response, String fileName) {
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response
            .setHeader("Content-Disposition", SET_HEADER_COMTENT + URLEncoder.encode(fileName));
        response.addHeader("Pargam", HEADER_PARGAM);
        response.addHeader("Cache-Control", HEADER_PARGAM);
    }

    @ApiOperation(value = "下载优惠劵批次列表模版 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/download_voucher_batch")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "voucherType", value = "优惠劵类型,1 代金券，2 套餐劵，3 实物劵，4 权益劵 5异业劵", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "voucherName", value = "优惠劵名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "batchCode", value = "批次码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "批次状态: 1 已保存，2 已生成，3 已过期，4 已终止", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "grantType", value = "发放类型，1:自动发放,2:推送至我的福利, 3:推送至取送车福利，4:筛选发放, 5:指定发放", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "grantBusinessType", value = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "businessType", value = "业务类型,1 不限制，2 保养，3 取送车，4 维修保养", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "isOverlapable", value = "是否可叠加，0 不可叠加，1 可以叠加", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "activityName", value = "活动名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "expireDateStartTime", value = "领取有效期开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "expireDateEndTime", value = "领取有效期截止时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "redeemTimeBegin", value = "核销开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "redeemTimeEnd", value = "核销截止时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = false, paramType = "query", dataType = "integer")
            })
    public void downloadVoucherBatchList(HttpServletResponse response,
                                         @RequestParam(name = "voucherType", required = false) Byte voucherType,
                                         @RequestParam(name = "voucherName", required = false) String voucherName,
                                         @RequestParam(name = "batchCode", required = false) String batchCode,
                                         @RequestParam(name = "status", required = false) Byte status,
                                         @RequestParam(name = "grantType", required = false) Byte grantType,
                                         @RequestParam(name = "grantBusinessType", required = false) Byte grantBusinessType,
                                         @RequestParam(name = "businessType", required = false) Byte businessType,
                                         @RequestParam(name = "isOverlapable", required = false) Byte isOverlapable,
                                         @RequestParam(name = "activityName", required = false) String activityName,
                                         @RequestParam(name = "expireDateStartTime", required = false) Long expireDateStartTime,
                                         @RequestParam(name = "expireDateEndTime", required = false) Long expireDateEndTime,
                                         @RequestParam(name = "redeemTimeBegin", required = false) Long redeemTimeBegin,
                                         @RequestParam(name = "redeemTimeEnd", required = false) Long redeemTimeEnd,
                                         @RequestParam(name = "currentPage", required = false) Integer currentPage,
                                         @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "优惠劵批次列表.xls";
        getResponse(response, fileName);
        VoucherBatchListQueryParamVo voucherBatchListQueryParamVo = new VoucherBatchListQueryParamVo();
        voucherBatchListQueryParamVo.setVoucherType(voucherType);
        voucherBatchListQueryParamVo.setVoucherName(voucherName);
        voucherBatchListQueryParamVo.setBatchCode(batchCode);
        voucherBatchListQueryParamVo.setStatus(status);
        voucherBatchListQueryParamVo.setBusinessType(businessType);
        voucherBatchListQueryParamVo.setIsOverlapable(isOverlapable);
        voucherBatchListQueryParamVo.setActivityName(activityName);
        voucherBatchListQueryParamVo.setExpireDateStartTime(expireDateStartTime);
        voucherBatchListQueryParamVo.setExpireDateEndTime(expireDateEndTime);
        voucherBatchListQueryParamVo.setRedeemTimeBegin(redeemTimeBegin);
        voucherBatchListQueryParamVo.setRedeemTimeEnd(redeemTimeEnd);
        voucherBatchListQueryParamVo.setGrantType(grantType);
        voucherBatchListQueryParamVo.setGrantBusinessType(grantBusinessType);
        voucherBatchListQueryParamVo.setCurrentPage(currentPage);

        HSSFWorkbook workbook = ossVoucherBatchService.downloadVoucherBatchList(voucherBatchListQueryParamVo);
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream downloadVoucherBatchList error: " + e);
        }
    }

    @ApiOperation(value = "上传经销商列表模版 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/upload_dealers_file")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传经销商模版", required = false, paramType = "query", dataType = "file")
            })
    public Result importResult(@RequestParam(name = "file", required = true, defaultValue = "") MultipartFile file,
                               @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        List<RedeemLimitedDealersVo> redeemLimitedDealersVos = ossVoucherBatchService.getLimitedDealers(file);
        return ResultUtils.success(redeemLimitedDealersVos);
    }

    @ApiOperation(value = "查询用户优惠劵明细列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @UserFromToken
    @PostMapping(value = "/get_voucher_detail_list")
    public Result getUserVoucherDetailList(@ApiParam(name = "queryParamVo", value = "用户优惠劵明细列表查询参数", required = true)
                                           @RequestBody @Valid UserVoucherDetailListQueryParamVo queryParamVo,
                                           @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {

        return ResultUtils.success(ossVoucherBatchService.getUserVoucherDetailList(queryParamVo, adminUserVO));
    }

    @ApiOperation(value = "扩充优惠劵批次库存 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @UserFromToken

    @PostMapping(value = "/add_voucher_batch_stock_count")
    //@OperationLog(operationType = OperationEnum.EDITOR)
    public Result addBatchStoreCount(@ApiParam(name = "voucherBatchUpdateStatusParamVo", value = "更新批次状态基本信息", required = true)
                                     @RequestBody @Valid VoucherBatchAddStockParamVo voucherBatchAddStockParamVo,
                                     @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        ossVoucherBatchService.addBatchStoreCount(voucherBatchAddStockParamVo.getBatchId(), voucherBatchAddStockParamVo.getStockCount(), true);
        return ResultUtils.success(voucherBatchAddStockParamVo.getBatchId());
    }

    @ApiOperation(value = "获取优惠劵批次详情 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @UserFromToken

    @GetMapping(value = "/get_voucher_batch_detail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchId", value = "批次id", required = true, paramType = "query", dataType = "int"),
            })
    public Result getVoucherBatchDetail(@RequestParam(name = "batchId", required = true, defaultValue = "") Integer batchId,
                                        @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        return ResultUtils.success(ossVoucherBatchService.getVoucherBatchDetail(batchId));
    }

    @ApiOperation(value = "下载用户优惠劵明细列表excel -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/download_user_voucher_detail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "voucherStatus", value = "优惠劵状态,1 未领取，2 已撤回，3 已领取，4 已冻结 5 已使用 6 已过期", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "vin", value = "vin码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "workSheetNo", value = "工单号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "redeemCode", value = "核销码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "redeemStartTime", value = "核销开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "redeemEndTime", value = "核销截止时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "grantChannel", value = "发放方, DLR: 经销商,OEM: 厂商", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "grantDealerCode", value = "发放方经销商代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "expireStartTime", value = "过期开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "expireEndTime", value = "过期开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "redeemDealerCode", value = "核销方经销商代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "drawStartTime", value = "发放开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "drawEndTime", value = "发放开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "grantBigRegionCode", value = "发放大区代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "redeemBigRegionCode", value = "核销大区代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "grantSmallRegionName", value = "发放小区代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "redeemSmallRegionName", value = "核销小区代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "activityName", value = "活动名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "batchId", value = "批次id", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "grantType", value = "发放类型，1:自动发放,2:推送至我的福利, 3:推送至取送车福利，4:筛选发放, 5:指定发放", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "grantBusinessType", value = "发放场景：0 不涉及，1:取送车,2:维保预约,3:延时服务", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "voucherName", value = "优惠劵名称", required = false, paramType = "query", dataType = "String")
            })
    public void downloadUserVoucherDetailList(@RequestParam(name = "voucherStatus", required = false) Byte voucherStatus,
                                              @RequestParam(name = "mobile", required = false) String mobile,
                                              @RequestParam(name = "vin", required = false) String vin,
                                              @RequestParam(name = "workSheetNo", required = false) String workSheetNo,
                                              @RequestParam(name = "redeemCode", required = false) String redeemCode,
                                              @RequestParam(name = "redeemStartTime", required = false) Long redeemStartTime,
                                              @RequestParam(name = "redeemEndTime", required = false) Long redeemEndTime,
                                              @RequestParam(name = "grantChannel", required = false) String grantChannel,
                                              @RequestParam(name = "grantDealerCode", required = false) String grantDealerCode,
                                              @RequestParam(name = "expireStartTime", required = false) Long expireStartTime,
                                              @RequestParam(name = "expireEndTime", required = false) Long expireEndTime,
                                              @RequestParam(name = "redeemDealerCode", required = false) String redeemDealerCode,
                                              @RequestParam(name = "drawStartTime", required = false) Long drawStartTime,
                                              @RequestParam(name = "drawEndTime", required = false) Long drawEndTime,
                                              @RequestParam(name = "grantBigRegionCode", required = false) String grantBigRegionCode,
                                              @RequestParam(name = "redeemBigRegionCode", required = false) String redeemBigRegionCode,
                                              @RequestParam(name = "grantSmallRegionName", required = false) String grantSmallRegionName,
                                              @RequestParam(name = "redeemSmallRegionName", required = false) String redeemSmallRegionName,
                                              @RequestParam(name = "activityName", required = false) String activityName,
                                              @RequestParam(name = "batchId", required = false) Integer batchId,
                                              @RequestParam(name = "grantType", required = false) Byte grantType,
                                              @RequestParam(name = "grantBusinessType", required = false) Byte grantBusinessType,
                                              @RequestParam(name = "voucherName", required = false) String voucherName,
                                              @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO,
                                              HttpServletResponse response) throws Exception {
        String fileName = "优惠劵明细.xlsx";
        getResponse(response, fileName);
        UserVoucherDetailListQueryParamVo queryParamVo = new UserVoucherDetailListQueryParamVo();
        queryParamVo.setVoucherStatus(voucherStatus);
        queryParamVo.setMobile(mobile);
        queryParamVo.setVin(vin);
        queryParamVo.setWorkSheetNo(workSheetNo);
        queryParamVo.setRedeemCode(redeemCode);
        queryParamVo.setRedeemStartTime(redeemStartTime);
        queryParamVo.setRedeemEndTime(redeemEndTime);
        queryParamVo.setGrantChannel(grantChannel);
        queryParamVo.setGrantDealerCode(grantDealerCode);
        queryParamVo.setExpireStartTime(expireStartTime);
        queryParamVo.setExpireEndTime(expireEndTime);
        queryParamVo.setRedeemDealerCode(redeemDealerCode);
        queryParamVo.setDrawStartTime(drawStartTime);
        queryParamVo.setDrawEndTime(drawEndTime);
        queryParamVo.setGrantBigRegionCode(grantBigRegionCode);
        queryParamVo.setRedeemBigRegionCode(redeemBigRegionCode);
        queryParamVo.setGrantSmallRegionName(grantSmallRegionName);
        queryParamVo.setRedeemSmallRegionName(redeemSmallRegionName);
        queryParamVo.setActivityName(activityName);
        queryParamVo.setBatchId(batchId);
        queryParamVo.setGrantType(grantType);
        queryParamVo.setGrantBusinessType(grantBusinessType);
        queryParamVo.setVoucherName(voucherName);
        queryParamVo.setCurrentPage(1);
        //特殊处理：设置成null表示不受条数控制
        queryParamVo.setPageSize(null);
        if (ossVoucherBatchService.isBeyondMaxVoucherDetailNum(queryParamVo)) {
            throw new ServiceException(ResultEnum.BEYOND_MAX_EXPORT_NUM);
        }
        ExcelParam excelParam = new ExcelParam();
        excelParam.setOffset(VoucherConstants.INIT_OFFSET);
        excelParam.setSheetIndex(0);
        SXSSFWorkbook workbook = ossVoucherBatchService.downloadUserVoucherDetailList(queryParamVo, null, excelParam);
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream getVoucherBatchDetail error: " + e);
        } finally {
            if (null != workbook) {
                workbook.dispose();
            }
        }
    }


    /*@ApiOperation(value = "异步导出用户优惠劵明细列表excel -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/export_user_voucher_detail")
    @UserFromToken

    public Result exportVoucherDetailReport(@ApiParam(name = "queryParamVo", value = "优惠劵批次列表查询参数", required = true)
                                            @RequestBody @Valid UserVoucherDetailListQueryParamVo queryParamVo,
                                            @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        VoucherCountInfo voucherCountInfo = ossVoucherBatchService.getVoucherDetailPageCount(queryParamVo);
        if (voucherCountInfo.getTotalCount() == 0) {
            throw new ServiceException(ResultEnum.EXPORT_NUM_IS_EMPTY);
        }
        String fileName = "Coupon" + DateUtil.dateFormat(new Date(), DateUtil.DATE_NO_SPLIT_PATTERN);
        voucherCountInfo.setFileName(fileName);
        voucherCountInfo.setIsZip(false);
        Integer reportFileId = ossVoucherBatchService.addExcelInfo(adminUserVO, voucherCountInfo);
        voucherCountInfo.setReportFileId(reportFileId);
        ossVoucherBatchService.exportVoucherDetailReport(queryParamVo, adminUserVO, voucherCountInfo);
        return ResultUtils.success(fileName);
    }*/

    @ApiOperation(value = "筛选发放，获取用户id")
    @PostMapping(value = "/get_user_by_vehicle_info")
    @UserFromToken

    public Result getUserForGrantingVoucher(@ApiParam(name = "voucherBatchListQueryParamVo", value = "筛选发放条件", required = true)
                                            @RequestBody @Valid VoucherGrantBySelectParamVo voucherGrantBySelectParamVo,
                                            @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {

        return ResultUtils.success(ossVoucherBatchService.getUserForGrantingVoucher(voucherGrantBySelectParamVo));
    }

    @ApiOperation(value = "筛选发放，给用户批量发劵 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "/grant_vouchers_to_users")
    @UserFromToken

    //@OperationLog(operationType = OperationEnum.EDITOR)
    public Result grantVouchersToUsers(@ApiParam(name = "voucherGrantConfigParamVo", value = "筛选发放条件及配置", required = true)
                                       @RequestBody @Valid VoucherGrantConfigParamVo voucherGrantConfigParamVo,
                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {

        return ResultUtils.success(ossVoucherBatchService.grantVoucherToUsers(voucherGrantConfigParamVo, adminUserVO));
    }

    @ApiOperation(value = "筛选发放获取全部车型 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_car_make")

    public Result getCarMake(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return ResultUtils.success(ossVoucherBatchService.getCarMake(page, pageSize));
    }

    @UserFromToken
    @ApiOperation(value = "按年查询优惠劵明细报表下载列表")
    @GetMapping(value = "/get_voucher_reports")

    public Result getVoucherMonthReportFiles(@RequestParam(name = "year", required = true) Integer year) throws Exception {
        return ResultUtils.success(ossVoucherBatchService.getVoucherMonthReportFiles(year));
    }

    @ApiOperation(value = "获取大小区的经销商列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_dealers")

    public Result getDealersOfBigAndSmallRegion() throws Exception {
        return ResultUtils.success(ossVoucherBatchService.getDealersOfBigAndSmallRegion());
    }

    @ApiOperation(value = "下载指定发放手机号/VIN码模版 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping("/download_directing_grant_template")
    public void downloadDirectingGrantTemplate(HttpServletResponse response,
                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "指定发放导入模版.xls";
        getResponse(response, fileName);

        HSSFWorkbook workbook = ossVoucherBatchService.downloadDirectingGrantTemplate();
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream downloadDirectingGrantTemplate error: " + e);
        }
    }

    @ApiOperation(value = "上传指定发放手机号 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/upload_mobile_template")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传经销商模版", required = false, paramType = "query", dataType = "file"),
            @ApiImplicitParam(name = "grantLimitPerTime", value = "单次发放数量", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "batchIds", value = "批次id，多个批次id以逗号分隔", required = false, paramType = "query", dataType = "String"),

            })
    public Result uploadMobileTemplate(@RequestParam(name = "file", required = true, defaultValue = "") MultipartFile file,
                                       @RequestParam(name = "grantLimitPerTime", required = true, defaultValue = "") Integer grantLimitPerTime,
                                       @RequestParam(name = "batchIds", required = true, defaultValue = "") String batchIds,
                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        List<Integer> batchIdList = getBatchIdsArray(batchIds);
        if (ossVoucherBatchService.isAlreadyExist(batchIdList)) {
            log.error("uploadMobileTemplate fail batchIds={}", batchIds);
            throw new ServiceException(ResultEnum.VOUCHER_GRANT_TYPE_FAIL);
        }
        List<String> mobiles = ossVoucherBatchService.uploadMobileTemplate(file);
        ossVoucherBatchService.processMobile(mobiles, grantLimitPerTime, batchIdList, VoucherConstants.EXCEL_IMPORT);
        return ResultUtils.success(true);
    }

    @ApiOperation(value = "上传指定发放VIN号 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/upload_vin_template")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传经销商模版", required = false, paramType = "query", dataType = "file"),
            @ApiImplicitParam(name = "grantLimitPerTime", value = "单次发放数量", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "batchIds", value = "批次id，多个批次id以逗号分隔", required = false, paramType = "query", dataType = "String"),

            })
    public Result uploadVinTemplate(@RequestParam(name = "file", required = true, defaultValue = "") MultipartFile file,
                                    @RequestParam(name = "grantLimitPerTime", required = true, defaultValue = "") Integer grantLimitPerTime,
                                    @RequestParam(name = "batchIds", required = true, defaultValue = "") String batchIds,
                                    @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        List<Integer> batchIdList = getBatchIdsArray(batchIds);
        if (ossVoucherBatchService.isAlreadyExist(batchIdList)) {
            log.error("uploadVinTemplate fail batchIds={}", batchIds);
            throw new ServiceException(ResultEnum.VOUCHER_GRANT_TYPE_FAIL);
        }
        List<String> vins = ossVoucherBatchService.uploadVinTemplate(file);
        ossVoucherBatchService.processVin(vins, grantLimitPerTime, batchIdList, VoucherConstants.EXCEL_IMPORT);
        return ResultUtils.success(true);
    }

    private List<Integer> getBatchIdsArray(String batchIdsStr) {
        if (null == batchIdsStr || batchIdsStr.isEmpty()) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_PARAM_IS_EMPTY);
        }
        String[] batchIdList = batchIdsStr.split(VoucherConstants.SEPARATOR);
        List<Integer> batchIds = new ArrayList<>();
        for (String batchId : batchIdList) {
            batchIds.add(Integer.valueOf(batchId));
        }
        return batchIds;
    }

    @UserFromToken

    @ApiOperation(value = "通过手机号或VIN码，指定发放劵 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/grant_by_mobile_or_vin")
    public Result grantByMobileOrVin(@RequestBody @Valid DirectingGrantRequest directingGrantRequest,
                                    @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        if (ossVoucherBatchService.isAlreadyExist(directingGrantRequest.getBatchIds())) {
            log.error("grantByMobileOrVin fail batchIds={}", directingGrantRequest.getBatchIds());
            throw new ServiceException(ResultEnum.VOUCHER_GRANT_TYPE_FAIL);
        }
        if (directingGrantRequest.getDataType().equals(VoucherConstants.MOBILE_TYPE)) {
            ossVoucherBatchService.processMobile(directingGrantRequest.getDataList(), directingGrantRequest.getGrantLimitPerTime(),
                    directingGrantRequest.getBatchIds(), VoucherConstants.MANUAL_IMPORT);
        } else {
            ossVoucherBatchService.processVin(directingGrantRequest.getDataList(), directingGrantRequest.getGrantLimitPerTime(),
                    directingGrantRequest.getBatchIds(), VoucherConstants.MANUAL_IMPORT);
        }
        return ResultUtils.success(true);
    }

    @ApiOperation(value = "查询名条发放列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/get_directing_grant_results")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "importType", value = "导入类型, 1:手工导入, 2:批量导入", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "isGrant", value = "发放状态, 0:未发放, 1:已发放", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "importEndTime", value = "导入截止时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "importStartTime", value = "导入开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "当前页记录数", required = false, paramType = "query", dataType = "Integer"),
            })
    public Result getDirectingGrantResults(@RequestParam(name = "importType", required = false) Byte importType,
                                           @RequestParam(name = "isGrant", required = false) Byte isGrant,
                                           @RequestParam(name = "importEndTime", required = false) Long importEndTime,
                                           @RequestParam(name = "importStartTime", required = false) Long importStartTime,
                                           @RequestParam(name = "currentPage", required = true) Integer currentPage,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) throws Exception {
        DirectingGrantResultRequest directingGrantResultRequest = new DirectingGrantResultRequest();
        directingGrantResultRequest.setCurrentPage(currentPage);
        directingGrantResultRequest.setPageSize(pageSize);
        directingGrantResultRequest.setImportType(importType);
        directingGrantResultRequest.setIsGrant(isGrant);
        directingGrantResultRequest.setImportEndTime(importEndTime);
        directingGrantResultRequest.setImportStartTime(importStartTime);
        return ResultUtils.success(ossVoucherBatchService.getDirectingGrantResults(directingGrantResultRequest));
    }

    @ApiOperation(value = "下载导入失败结果 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping("/download_grantee_check_result")
    public void downloadDirectingGranteeResult(HttpServletResponse response,
                                               @RequestParam(name = "grantRecordId", required = true) Integer grantRecordId,
                                               @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "导入失败结果.xlsx";
        getResponse(response, fileName);

        SXSSFWorkbook workbook = ossVoucherBatchService.downloadDirectingGranteeResult(grantRecordId);
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream downloadDirectingGranteeResult error: " + e);
        } finally {
            if (null != workbook) {
                workbook.dispose();
            }
        }
    }

    @ApiOperation(value = "下载发送短信失败结果 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping("/download_send_sms_result")
    public void downloadSendSmsResult(HttpServletResponse response,
                                      @RequestParam(name = "grantRecordId", required = true) Integer grantRecordId,
                                      @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "发送短信失败结果.xlsx";
        getResponse(response, fileName);

        SXSSFWorkbook workbook = ossVoucherBatchService.downloadSendSmsResult(grantRecordId);
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream downloadSendSmsResult error: " + e);
        } finally {
            if (null != workbook) {
                workbook.dispose();
            }
        }
    }

    @UserFromToken

    @ApiOperation(value = "OEM撤回套餐券发放 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "withdraw_user_voucher")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "发放记录ID", required = true, paramType = "query", dataType = "Integer")
            })
    public Result withDrawGrantVoucher(@RequestParam(name = "recordId") Integer recordId,
                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) {
        Boolean result = ossVoucherBatchService.oemWithDrawGrantVoucher(recordId, adminUserVO);
        return ResultUtils.success(result);
    }

    @UserFromToken

    @ApiOperation(value = "OEM查询经销商发放记录列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "get_grant_record_list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vin", value = "vin码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面数", required = true, paramType = "query", dataType = "Integer", defaultValue = "10"),
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "Integer", defaultValue = "10")
            })
    public Result grantRecordList(@RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO,
                                  @RequestParam(name = "vin", required = false) String vin,
                                  @RequestParam(name = "phone", required = false) String phone,
                                  @RequestParam(name = "pageSize", required = true, defaultValue = "10") int pageSize,
                                  @RequestParam(name = "currentPage", required = true, defaultValue = "1") int currentPage) {
        PageInfo<VoucherDealerGrantRecordInfoVo> pageInfo = ossVoucherBatchService.grantRecordList(adminUserVO, vin, phone, pageSize, currentPage);
        return ResultUtils.success(pageInfo);
    }

    @UserFromToken
    @ApiOperation(value = "查询可下载的报表列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "get_record_file_list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportName", value = "报表编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "adminUserName", value = "用户名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面数", required = true, paramType = "query", dataType = "Integer", defaultValue = "10"),
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "Integer", defaultValue = "1")
            })
    public Result getRecordFiles(@RequestParam(name = "reportName", required = false) String reportName,
                                 @RequestParam(name = "adminUserName", required = false) String adminUserName,
                                 @RequestParam(name = "pageSize", required = true, defaultValue = "10") int pageSize,
                                  @RequestParam(name = "currentPage", required = true, defaultValue = "1") int currentPage) throws Exception {
        PageInfo<VoucherReportFileVo> pageInfo = ossVoucherBatchService.getRecordFiles(reportName, adminUserName, pageSize, currentPage);
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation(value = "下载核销码模版 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping("/download_thirdpart_code_template")
    public void downloadThirdpartCodeTemplate(HttpServletResponse response,
                                               @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "核销码导入模版.xlsx";
        getResponse(response, fileName);

        SXSSFWorkbook workbook = ossVoucherBatchService.downloadThirdpartCodeTemplate();
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream downloadThirdpartCodeTemplate error: " + e);
        }
    }

    @ApiOperation(value = "下载异业商家模版 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping("/download_thirdpart_shop_template")
    public void downloadThirdpartShopTemplate(HttpServletResponse response,
                                              @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        String fileName = "商家导入模版.xlsx";
        getResponse(response, fileName);

        SXSSFWorkbook workbook = ossVoucherBatchService.downloadThirdpartShopTemplate();
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream error: " + e);
        }
    }

    @ApiOperation(value = "导入异业核销码 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping("/import_thirdpart_code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传异业核销码模版", required = false, paramType = "query", dataType = "file"),
            @ApiImplicitParam(name = "batchId", value = "批次id", required = false, paramType = "query", dataType = "integer"),
            })
    public Result uploadThirdpartCodeTemplate(@RequestParam(name = "file", required = true, defaultValue = "") MultipartFile file,
                                              @RequestParam(name = "batchId", required = true, defaultValue = "") Integer batchId,
                                              @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
        ossVoucherBatchService.checkThirdpartVoucherBatch(batchId);
        ossVoucherBatchService.readThirdpartCodes(file, batchId);
        ossVoucherBatchService.importThirdpartCodes(batchId);

        return ResultUtils.success(true);
    }

    //@ApiOperation(value = "导入第三方商家 -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    //@PostMapping("/import_thirdpart_shop")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "file", value = "上传异业商家模版", required = false, paramType = "query", dataType = "file"),
    //        @ApiImplicitParam(name = "batchId", value = "批次id", required = false, paramType = "query", dataType = "integer"),
    //})
    //public Result uploadThirdpartShopTemplate(@RequestParam(name = "file", required = true, defaultValue = "") MultipartFile file,
    //                                          @RequestParam(name = "batchId", required = true, defaultValue = "") Integer batchId,
    //                                          @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) throws Exception {
    //    ossVoucherBatchService.importThirdpartShops(file, batchId);
    //    return ResultUtils.success(true);
    //}

    @ApiOperation(value = "下载导入失败异业码数据 -【张云蛟】", notes = "", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "export_fail_thirdpart_codes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchId", value = "批次id", required = false, paramType = "query", dataType = "integer")
            })
    public void exportFailThirdpartCodes(HttpServletResponse response,
                                 @RequestParam(name = "batchId", required = true, defaultValue = "") Integer batchId) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = FAIL_REDEEM_CODE;
            response.setHeader("Content-disposition", SET_HEADER_COMTENT + fileName + ".xlsx");
            List<ThirdpartCodeFailExcelVo> data = ossVoucherBatchService.getFailThirdpartCodes(batchId);
            String sheetName = FAIL_REDEEM_CODE;
            EasyExcel.write(response.getOutputStream(), ThirdpartCodeFailExcelVo.class).sheet(sheetName).doWrite(data);
        } catch (Exception e) {
            log.error("导出核销码数据失败", e);
        }
    }
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-07-29 zhangyunjiao create
 */