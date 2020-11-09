package com.fawvw.ms.voucher.oss.controller;


import com.fawvw.ms.oa.base.server.annotation.UserFromToken;
import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.PackageVoucherInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantCarInfoVo;
import com.fawvw.ms.voucher.oss.service.OssVoucherBatchService;
import com.fawvw.ms.voucher.oss.service.OssVoucherGrantService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhubiyuan
 * @Type AdminVoucherController
 * @Desc
 * @date 2019-08-13 09:43
 */
@Api(tags = "DLR优惠券发放API")
@RestController
@RequestMapping(value = "/dealer_voucher")
@Slf4j
public class OssVoucherController {

    @Autowired
    private OssVoucherGrantService ossVoucherGrantService;

    @Autowired
    private OssVoucherBatchService ossVoucherBatchService;

    private static final String HEADER_VALUE_NO_CATCH = "no-cache";

    @ApiOperation(value = "待发放车辆列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "get_car_list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "查询车辆列表类型(1:phone 2:vin码)",
                    required = true, paramType = "query", dataType = "Byte"),
            @ApiImplicitParam(name = "value", value = "查询值", required = true, paramType
                    = "query", dataType = "String")
            })
    public Result grantCarList(@RequestParam("type") Byte type,
                               @RequestParam("value") String value) {
        List<VoucherGrantCarInfoVo> list = ossVoucherGrantService.grantCarList(type, value);
        return ResultUtils.success(list);
    }

    @UserFromToken

    @ApiOperation(value = "获取套餐券集合 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "get_package_voucher_list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "用户ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "carAge", value = "车龄", required = true, paramType = "query", dataType = "String")
            })
    public Result getPackageVoucherList(@RequestParam("aid") String aid,
                                        @RequestParam("carAge") String carAge,
                                        @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) {
        List<PackageVoucherInfoVo> list = ossVoucherGrantService.getPackageVoucherList(aid, carAge, adminUserVO);
        return ResultUtils.success(list);
    }

    @UserFromToken
    @ApiOperation(value = "发放套餐券 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @PostMapping(value = "grant_package_voucher")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "voucherDealerGrantRecordVo", value = "发放套餐券信息", required = true, paramType = "query", dataType = "body")
            })
    public Result grantPackageVoucher(@RequestBody @Valid VoucherDealerGrantRecordVo voucherDealerGrantRecordVo,
                                      @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) {
        if (VoucherConstants.INVALID_USER_ID.equals(adminUserVO.getId())) {
            throw new ServiceException(ResultEnum.SHIRO_TOKEN_EXPIRED_ERROR);
        }
        int recordId = ossVoucherGrantService.grantPackageVoucher(voucherDealerGrantRecordVo, adminUserVO);
        return ResultUtils.success(recordId);
    }

    @UserFromToken

    @ApiOperation(value = "撤回套餐券发放 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "withdraw_user_voucher")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "发放记录ID", required = true, paramType = "query", dataType = "Integer")
            })
    public Result withDrawGrantVoucher(@RequestParam(name = "recordId") Integer recordId,
                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO) {
        Boolean result = ossVoucherGrantService.withDrawGrantVoucher(recordId, adminUserVO);
        return ResultUtils.success(result);
    }

    @UserFromToken

    @ApiOperation(value = "发放记录列表 -【张云蛟】", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
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
        PageInfo<VoucherDealerGrantRecordInfoVo> pageInfo = ossVoucherGrantService.grantRecordList(adminUserVO, vin, phone, pageSize, currentPage);
        return ResultUtils.success(pageInfo);
    }

    @UserFromToken
    @ApiOperation(value = "下载经销商发放的用户优惠劵明细列表excel -【张云蛟】", notes = " ", tags = {"【优惠劵模块】自动化测试接口改造", "张云蛟"})
    @GetMapping(value = "/download_user_voucher_detail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "voucherType", value = "优惠劵类型,1 代金券，2 套餐劵，3 实物劵，4 权益劵 5异业劵", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "voucherStatus", value = "优惠劵状态,1 未领取，2 已撤回，3 已领取，4 已冻结 5 已使用 6 已过期", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "voucherName", value = "优惠劵名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "vin", value = "vin码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "workSheetNo", value = "工单号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "businessType", value = "业务类型,1 不限制，2 保养，3 取送车，4 维修保养", required = false, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "activityName", value = "活动名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "redeemStartTime", value = "核销开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "redeemEndTime", value = "核销截止时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "expireStartTime", value = "过期开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "expireEndTime", value = "过期开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "drawStartTime", value = "发放开始时间", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "drawEndTime", value = "发放开始时间", required = false, paramType = "query", dataType = "Long"),
            })
    public void downloadUserVoucherDetailListForDealer(@RequestParam(name = "voucherType", required = false) Byte voucherType,
                                                       @RequestParam(name = "voucherStatus", required = false) Byte voucherStatus,
                                                       @RequestParam(name = "voucherName", required = false) String voucherName,
                                                       @RequestParam(name = "mobile", required = false) String mobile,
                                                       @RequestParam(name = "vin", required = false) String vin,
                                                       @RequestParam(name = "workSheetNo", required = false) String workSheetNo,
                                                       @RequestParam(name = "businessType", required = false) Byte businessType,
                                                       @RequestParam(name = "activityName", required = false) String activityName,
                                                       @RequestParam(name = "redeemStartTime", required = false) Long redeemStartTime,
                                                       @RequestParam(name = "redeemEndTime", required = false) Long redeemEndTime,
                                                       @RequestParam(name = "expireStartTime", required = false) Long expireStartTime,
                                                       @RequestParam(name = "expireEndTime", required = false) Long expireEndTime,
                                                       @RequestParam(name = "drawStartTime", required = false) Long drawStartTime,
                                                       @RequestParam(name = "drawEndTime", required = false) Long drawEndTime,
                                                       @RequestParam(name = "dealerCode", required = false) String dealerCode,
                                                       @RequestParam(name = "adminUserVO", required = false) AdminUserVO adminUserVO,
                                                       HttpServletResponse response) throws Exception {
        String fileName = "优惠劵明细.xls";
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName));
        response.addHeader("Pargam", HEADER_VALUE_NO_CATCH);
        response.addHeader("Cache-Control", HEADER_VALUE_NO_CATCH);
        UserVoucherDetailListQueryParamVo queryParamVo = new UserVoucherDetailListQueryParamVo();
        queryParamVo.setVoucherStatus(voucherStatus);
        queryParamVo.setMobile(mobile);
        queryParamVo.setVin(vin);
        queryParamVo.setWorkSheetNo(workSheetNo);
        queryParamVo.setVoucherName(voucherName);
        queryParamVo.setRedeemStartTime(redeemStartTime);
        queryParamVo.setRedeemEndTime(redeemEndTime);
        queryParamVo.setBusinessType(businessType);
        queryParamVo.setExpireStartTime(expireStartTime);
        queryParamVo.setExpireEndTime(expireEndTime);
        queryParamVo.setDrawStartTime(drawStartTime);
        queryParamVo.setDrawEndTime(drawEndTime);
        queryParamVo.setActivityName(activityName);
        queryParamVo.setVoucherType(voucherType);
        queryParamVo.setCurrentPage(1);
        //特殊处理：设置成null表示不受条数控制
        queryParamVo.setPageSize(null);
        adminUserVO.setRefId(dealerCode);

        HSSFWorkbook workbook = ossVoucherBatchService.downloadUserVoucherDetailListForDealer(queryParamVo, adminUserVO);
        try (OutputStream os = response.getOutputStream()) {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            log.error("OutputStream error: " + e);
        }
    }

}
