package com.fawvw.ms.voucher.oss.service;
/*
 * Project: com.fawvw.ms.oneappserver.services.admin
 *
 * File Created at 2019-08-11
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.voucher.basedomain.vo.BigAndSmallRegionTreeVo;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantResultListVo;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantResultRequest;
import com.fawvw.ms.voucher.basedomain.vo.ExcelParam;
import com.fawvw.ms.voucher.basedomain.vo.RedeemLimitedDealersVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartCodeFailExcelVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailListVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchListVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherCountInfo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantBySelectParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantConfigParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantUserVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherMonthReportVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherReportFileVo;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangyunjiao
 * @Type AdminVoucherBatchService
 * @Desc
 * @date 2019-08-11 15:51
 */
@Component
public interface OssVoucherBatchService {
    Integer createVoucherBatch(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception;

    VoucherBatchListVo getVoucherBatchList(
        VoucherBatchListQueryParamVo voucherBatchListQueryParamVo) throws Exception;

    String generateVoucherTemplate(Integer batchId) throws Exception;

    void delVoucherBatch(Integer batchId) throws Exception;

    void updateVoucherBatchStatus(Integer batchId, Byte status) throws Exception;

    Integer editVoucherBatch(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception;

    HSSFWorkbook downloadDealerTemplate() throws Exception;

    List<RedeemLimitedDealersVo> getLimitedDealers(MultipartFile file) throws Exception;

    UserVoucherDetailListVo getUserVoucherDetailList(UserVoucherDetailListQueryParamVo queryParam,
        AdminUserVO adminUserVO) throws Exception;

    void addBatchStoreCount(Integer batchId, Integer stockCount, boolean isAddBatchCount) throws Exception;

    VoucherBatchInfoVo getVoucherBatchDetail(Integer batchId) throws Exception;

    SXSSFWorkbook downloadUserVoucherDetailList(UserVoucherDetailListQueryParamVo queryParam,
        SXSSFWorkbook workbook, ExcelParam excelParam) throws Exception;

    VoucherGrantUserVo getUserForGrantingVoucher(
        VoucherGrantBySelectParamVo voucherGrantBySelectParamVo) throws Exception;

    Integer grantVoucherToUsers(VoucherGrantConfigParamVo voucherGrantConfigParamVo,
        AdminUserVO adminUserVO) throws Exception;

    void updateBatchExpireStatus();

    HSSFWorkbook downloadVoucherBatchList(VoucherBatchListQueryParamVo voucherBatchListQueryParamVo) throws Exception;

    PageInfo getCarMake(Integer page, Integer pageSize);

    HSSFWorkbook downloadUserVoucherDetailListForDealer(
        UserVoucherDetailListQueryParamVo queryParam, AdminUserVO adminUserVO) throws Exception;

    //void exportVoucherDetailReportTask() throws Exception;

    /*void exportVoucherDetailReport(UserVoucherDetailListQueryParamVo queryParam,
        AdminUserVO adminUserVO, VoucherCountInfo voucherCountInfo) throws Exception;*/

    boolean isBeyondMaxVoucherDetailNum(UserVoucherDetailListQueryParamVo queryParam) throws Exception;

    boolean isEmptyVoucherDetail(UserVoucherDetailListQueryParamVo queryParam) throws Exception;

    List<VoucherMonthReportVo> getVoucherMonthReportFiles(Integer year) throws Exception;

    List<BigAndSmallRegionTreeVo> getDealersOfBigAndSmallRegion() throws Exception;

    HSSFWorkbook downloadDirectingGrantTemplate() throws Exception;

    List<String> uploadMobileTemplate(MultipartFile file) throws Exception;

    List<String> uploadVinTemplate(MultipartFile file) throws Exception;

    void processMobile(List<String> mobiles, Integer grantLimitPerTime, List<Integer> batchIds,
        Byte importType) throws Exception;

    void processVin(List<String> vins, Integer grantLimitPerTime, List<Integer> batchIds,
        Byte importType) throws Exception;

    boolean isAlreadyExist(List<Integer> batchIds);

    DirectingGrantResultListVo getDirectingGrantResults(DirectingGrantResultRequest requestParam) throws Exception;

    SXSSFWorkbook downloadDirectingGranteeResult(Integer grantRecordId) throws Exception;

    SXSSFWorkbook downloadSendSmsResult(Integer grantRecordId) throws Exception;

    Boolean oemWithDrawGrantVoucher(Integer recordId, AdminUserVO adminUserVO);

    PageInfo<VoucherDealerGrantRecordInfoVo> grantRecordList(AdminUserVO adminUserVO, String vin,
        String phone, int pageSize, int currentPage);

    PageInfo<VoucherReportFileVo> getRecordFiles(String reportName, String adminUserName,
        int pageSize, int currentPage) throws Exception;

    VoucherCountInfo getVoucherDetailPageCount(UserVoucherDetailListQueryParamVo queryParam);

    Integer addExcelInfo(AdminUserVO adminUserVO, VoucherCountInfo voucherCountInfo) throws Exception;

    SXSSFWorkbook downloadThirdpartCodeTemplate() throws Exception;

    SXSSFWorkbook downloadThirdpartShopTemplate() throws Exception;

    void importThirdpartCodes(Integer batchId) throws Exception;

    void checkThirdpartVoucherBatch(Integer batchId);

    void importThirdpartShops(MultipartFile file, Integer batchId) throws Exception;

    void readThirdpartCodes(MultipartFile file, Integer batchId) throws Exception;

    List<ThirdpartCodeFailExcelVo> getFailThirdpartCodes(Integer batchId);
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-11 zhangyunjiao create
 */