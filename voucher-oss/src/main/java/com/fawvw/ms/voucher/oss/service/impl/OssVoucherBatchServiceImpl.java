package com.fawvw.ms.voucher.oss.service.impl;
/*
 * Project: com.fawvw.ms.oneappserver.services.admin.impl
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


import com.alibaba.excel.event.AnalysisEventListener;
import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.oa.core.constants.CDPConstants;
import com.fawvw.ms.oa.core.constants.RequestConstants;
import com.fawvw.ms.oa.core.constants.ShiroConstants;
import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.oa.core.utils.CommonConvertUtil;
import com.fawvw.ms.oa.core.utils.EasyExcelUtils;
import com.fawvw.ms.oa.core.utils.JackJsonUtil;
import com.fawvw.ms.oa.core.utils.ServiceExceptionUtil;
import com.fawvw.ms.voucher.basedao.model.EtlUserVehicleMapping;
import com.fawvw.ms.voucher.basedao.model.OemSelectGrantCarMakeMapping;
import com.fawvw.ms.voucher.basedao.model.OemSelectGrantCarNumberMapping;
import com.fawvw.ms.voucher.basedao.model.OemVoucherBatchSelectTriggerMapping;
import com.fawvw.ms.voucher.basedao.model.OemVoucherBatchTriggerMapping;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantRecord;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantTrigger;
import com.fawvw.ms.voucher.basedao.model.OemVoucherSelectGrantTrigger;
import com.fawvw.ms.voucher.basedao.model.OperationUser;
import com.fawvw.ms.voucher.basedao.model.ReportFile;
import com.fawvw.ms.voucher.basedao.model.ThirdpartVoucherMapping;
import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import com.fawvw.ms.voucher.basedao.model.VoucherBatchDealerGrantRecord;
import com.fawvw.ms.voucher.basedao.model.VoucherDealerGrantRecord;
import com.fawvw.ms.voucher.basedao.model.VoucherLimitedDealerMapping;
import com.fawvw.ms.voucher.basedao.model.voucher.UserVehicle;
import com.fawvw.ms.voucher.basedao.model.voucher.VehicleSeries;
import com.fawvw.ms.voucher.basedomain.constants.FileConstants;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.enums.result.VoucherErrorEnum;
import com.fawvw.ms.voucher.basedomain.vo.Areas;
import com.fawvw.ms.voucher.basedomain.vo.BigAndSmallRegionTreeVo;
import com.fawvw.ms.voucher.basedomain.vo.CdpDealerInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.DealerInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.DealersTreeVo;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantRecordCountVo;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantResultListVo;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantResultRequest;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantResultVo;
import com.fawvw.ms.voucher.basedomain.vo.ExcelParam;
import com.fawvw.ms.voucher.basedomain.vo.PartInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.RedeemLimitedDealersVo;
import com.fawvw.ms.voucher.basedomain.vo.RedeemTemplateInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.SmallRegionAndDealersTreeVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartCodeExcelVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartCodeFailExcelVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartGrantBatchVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartShopExcelVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailListVo;
import com.fawvw.ms.voucher.basedomain.vo.UserVoucherDetailVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchGrantInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchListQueryParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchListVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherCountInfo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDayReportVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantBySelectParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantConfigParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantUserVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherLimitedPartsMapping;
import com.fawvw.ms.voucher.basedomain.vo.VoucherMonthReportVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherReportFileVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherUserAndVehicleInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWithDrawInfoVo;
import com.fawvw.ms.voucher.baseservice.client.operation.OperationUserFeignClient;
import com.fawvw.ms.voucher.baseservice.client.runlin.VoucherByRunlinCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.client.sysarea.SysAreaCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.client.tima.VehicheByTimaCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.util.DateUtil;
import com.fawvw.ms.voucher.baseservice.util.ExcelUtil;
import com.fawvw.ms.voucher.baseservice.util.StreamHelper;
import com.fawvw.ms.voucher.oss.mapper.OssEtlUserVehicleMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssEtlUserVoucherBatchMappingRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemSelectGrantCarMakeMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemSelectGrantCarNumberMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherBatchSelectTriggerMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherBatchTriggerMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherGrantRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherGrantTriggerMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherSelectGrantTriggerMapper;
import com.fawvw.ms.voucher.oss.mapper.OssReportFileMapper;
import com.fawvw.ms.voucher.oss.mapper.OssThirdpartVoucherMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherBatchDealerGrantRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherBatchMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherDealerGrantRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherLimitedDealerMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherLimitedPartsMappingMapper;
import com.fawvw.ms.voucher.oss.service.OssVoucherBatchService;
import com.fawvw.ms.voucher.oss.service.OssVoucherCommonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mongodb.BasicDBObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangyunjiao
 * @Type AdminVoucherBatchServiceImpl
 * @Desc
 * @date 2019-08-11 15:51
 */
@Service
@Transactional
@Slf4j
public class OssVoucherBatchServiceImpl implements OssVoucherBatchService {

    private Random random = new Random();

    @Autowired
    private OssVoucherBatchMapper ossVoucherBatchMapper;

    @Autowired
    private OssVoucherLimitedDealerMappingMapper ossVoucherLimitedDealerMappingMapper;

    @Autowired
    private OssVoucherLimitedPartsMappingMapper ossVoucherLimitedPartsMappingMapper;

    @Autowired
    private OssEtlUserVoucherBatchMappingRecordMapper ossEtlUserVoucherBatchMappingRecordMapper;

    @Autowired
    private OssEtlUserVehicleMappingMapper ossEtlUserVehicleMappingMapper;

    @Autowired
    private OssOemVoucherSelectGrantTriggerMapper ossOemVoucherSelectGrantTriggerMapper;

    @Autowired
    private OssOemVoucherBatchTriggerMappingMapper ossOemVoucherBatchTriggerMappingMapper;

    @Autowired
    private OssOemVoucherBatchSelectTriggerMappingMapper ossOemVoucherBatchSelectTriggerMappingMapper;

    @Autowired
    private OssOemSelectGrantCarMakeMappingMapper ossOemSelectGrantCarMakeMappingMapper;

    @Autowired
    private OssOemSelectGrantCarNumberMappingMapper ossOemSelectGrantCarNumberMappingMapper;

    @Autowired
    private OssOemVoucherGrantTriggerMapper ossOemVoucherGrantTriggerMapper;

    @Autowired
    private OssOemVoucherGrantRecordMapper ossOemVoucherGrantRecordMapper;

    @Autowired
    private OssVoucherDealerGrantRecordMapper ossVoucherDealerGrantRecordMapper;

    @Autowired
    private OssVoucherBatchDealerGrantRecordMapper ossVoucherBatchDealerGrantRecordMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OperationUserFeignClient operationUserFeignClient;

    /*@Autowired
    private FileService fileService;*/

    @Autowired
    private OssReportFileMapper ossReportFileMapper;

    @Autowired
    private OssVoucherCommonService ossVoucherCommonService;

    @Autowired
    private OssThirdpartVoucherMappingMapper ossThirdpartVoucherMappingMapper;

    @Autowired
    private SysAreaCoreFeignClient sysAreaCoreFeignClient;

    @Autowired
    private VoucherByRunlinCoreFeignClient voucherByRunlinCoreFeignClient;

    @Autowired
    private VehicheByTimaCoreFeignClient vehicheByTimaCoreFeignClient;

    @Value(value = "${voucher.card-ticket-Key}")
    String cardTicketKey;

    @Value(value = "${voucher.app-keys}")
    String voucherAppKeys;

    @Value(value = "${voucher.member-app-key}")
    String memberAppKey;

    @Value(value = "${voucher.dms-app-key}")
    String dmsAppKey;

    private static final String GET_VOUCHER_BATCH_LIST_ORDER_BY = "id desc";
    private static final int THREAD_SLEEP_TIME = 200;
    private static final int INT_ZERO = 0;
    private static final int INT_ONE = 1;
    private static final int INT_TWO = 2;
    private static final int INT_THREE = 3;
    private static final int INT_FOUR = 4;
    private static final int INT_FIVE = 5;
    private static final int INT_SIX = 6;
    private static final int INT_SEVEN = 7;
    private static final int INT_EIGHT = 8;
    private static final int INT_NINE = 9;
    private static final int INT_TEN = 10;
    private static final int INT_ELEVEN = 11;
    private static final int INT_TWELVE = 12;
    private static final int INT_THIRTEEN = 13;
    private static final int INT_FOURTEEN = 14;
    private static final int INT_FIFTEEN = 15;
    private static final int INT_SIXTEEN = 16;
    private static final int INT_SEVENTEEN = 17;
    private static final int INT_EIGHTEEN = 18;
    private static final int INT_NINETEEN = 19;
    private static final int INT_TWENTY = 20;
    private static final int INT_TWENTY_ONE = 21;
    private static final int INT_TWENTY_TWO = 22;
    private static final int INT_TWENTY_THREE = 23;
    private static final int INT_TWENTY_FOUR = 24;
    private static final String STR_ZERO = "0";
    private static final String STR_ONE = "1";
    private static final String STR_TWO = "2";
    private static final int ONE_HUNDRED = 100;
    private static final int ONETHOUSAND = 1000;
    private static final int V_BRAND_ID = 10000000;
    private static final int THREAD_MILLIS = 50;
    private static final int MINUTE = 59;

    @Override
    public Integer createVoucherBatch(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        if (voucherBatchInfoVo.getDrawExpireDate().before(new Date())) {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.BATCH_ExPIRED);
        }
        if (voucherBatchInfoVo.getExpiryDateType().equals(VoucherConstants.FIXED)
            && voucherBatchInfoVo.getDrawExpireDate().after(voucherBatchInfoVo.getRedeemEndTime())) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_TIME_ERROR);
        }
        if (Objects.isNull(voucherBatchInfoVo.getBusinessType())) {
            voucherBatchInfoVo.setBusinessType(VoucherConstants.NO_LIMIT_BUSSINESS);
        }
        //将优惠券批次信息保存到本地
        Integer batchId = createVoucherBatchInfo(voucherBatchInfoVo);
        voucherBatchInfoVo.setId(batchId);
        if (voucherBatchInfoVo.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)) {
            createThirdpartVoucher(voucherBatchInfoVo);
            createThirdpardShopOfBatch(voucherBatchInfoVo);
        } else {
            createLimitedPartsOfBatch(voucherBatchInfoVo, batchId);
            createLimitedDealersOfBatch(voucherBatchInfoVo, batchId);
        }
        //调用第三方生成卡券模版
        if (INT_ONE == voucherBatchInfoVo.getIsGenerateTemplate()) {
            generateVoucherTemplate(batchId);
        }
        return batchId;
    }

    private void createThirdpartVoucher(VoucherBatchInfoVo voucherBatchInfoVo) {
        if (VoucherConstants.CODE_TYPE_SIGNAL.equals(voucherBatchInfoVo.getCodeType())) {
            ThirdpartVoucherMapping thirdpartVoucherMapping = new ThirdpartVoucherMapping();
            thirdpartVoucherMapping.setCodeType(voucherBatchInfoVo.getCodeType());
            thirdpartVoucherMapping.setRedeemCode(voucherBatchInfoVo.getVoucherCodeSignal());
            thirdpartVoucherMapping.setImportStatus(VoucherConstants.SUCCESS);
            thirdpartVoucherMapping.setFkBatchId(voucherBatchInfoVo.getId());
            ossThirdpartVoucherMappingMapper.insertSelective(thirdpartVoucherMapping);
        }
    }

    private void createLimitedPartsOfBatch(VoucherBatchInfoVo voucherBatchInfoVo, Integer batchId) throws Exception {
        if (null == voucherBatchInfoVo.getRedeemTemplateInfo()) {
            return;
        }
        List<PartInfoVo> partInfos = voucherBatchInfoVo.getRedeemTemplateInfo().getPartInfos();

        if (null == partInfos || partInfos.isEmpty()) {
            return;
        }
        List<VoucherLimitedPartsMapping> voucherLimitedPartsMappings = new ArrayList<>();
        Date updateTime = new Date();
        Map<String, PartInfoVo> partCodeToPartInfo = new HashMap<>();
        for (PartInfoVo partInfoVo : partInfos) {
            if (partCodeToPartInfo.containsKey(partInfoVo.getPartCode())) {
                continue;
            }
            partCodeToPartInfo.put(partInfoVo.getPartCode(), partInfoVo);
            VoucherLimitedPartsMapping voucherLimitedPartsMapping = new VoucherLimitedPartsMapping();
            voucherLimitedPartsMapping.setPartCode(partInfoVo.getPartCode());
            voucherLimitedPartsMapping.setPartName(partInfoVo.getPartName());
            voucherLimitedPartsMapping.setCarModel(partInfoVo.getCarModel());
            voucherLimitedPartsMapping.setUpdateTime(updateTime);
            voucherLimitedPartsMapping.setFkBatchId(batchId);
            voucherLimitedPartsMappings.add(voucherLimitedPartsMapping);
        }

        ossVoucherLimitedPartsMappingMapper.batchInsertLimitedParts(voucherLimitedPartsMappings);
    }

    private void createThirdpardShopOfBatch(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        if (!Objects.isNull(voucherBatchInfoVo.getShopName())) {
            VoucherLimitedDealerMapping voucherLimitedDealerMapping = new VoucherLimitedDealerMapping();
            voucherLimitedDealerMapping.setShopName(voucherBatchInfoVo.getShopName());
            voucherLimitedDealerMapping.setIsThirdpart(VoucherConstants.THIRDPART_CODE);
            voucherLimitedDealerMapping.setFkBatchId(voucherBatchInfoVo.getId());
            ossVoucherLimitedDealerMappingMapper.insertSelective(voucherLimitedDealerMapping);
        }
    }

    private void createLimitedDealersOfBatch(VoucherBatchInfoVo voucherBatchInfoVo, Integer batchId) throws Exception {
        if (null == voucherBatchInfoVo.getRedeemTemplateInfo()) {
            return;
        }
        List<DealerInfoVo> dealerInfoVos = voucherBatchInfoVo.getRedeemTemplateInfo().getDealerInfos();

        if (null == dealerInfoVos || dealerInfoVos.isEmpty()) {
            return;
        }
        List<VoucherLimitedDealerMapping> voucherLimitedDealerMappings = new ArrayList<>();
        Date updateTime = new Date();
        Map<String, DealerInfoVo> dealerCodeToPartInfo = new HashMap<>();

        for (DealerInfoVo dealerInfoVo : dealerInfoVos) {
            if (dealerCodeToPartInfo.containsKey(dealerInfoVo.getDealerCode())) {
                continue;
            }
            dealerCodeToPartInfo.put(dealerInfoVo.getDealerCode(), dealerInfoVo);
            VoucherLimitedDealerMapping voucherLimitedDealerMapping = new VoucherLimitedDealerMapping();
            voucherLimitedDealerMapping.setBigRegionCode(dealerInfoVo.getBigRegionCode());
            voucherLimitedDealerMapping.setBigRegionName(dealerInfoVo.getBigRegionName());
            voucherLimitedDealerMapping.setFkBatchId(batchId);
            voucherLimitedDealerMapping.setName(dealerInfoVo.getDealerName());
            voucherLimitedDealerMapping.setSaleCode(dealerInfoVo.getSaleCode());
            voucherLimitedDealerMapping.setServiceCode(dealerInfoVo.getDealerCode());
            voucherLimitedDealerMapping.setUpdateTime(updateTime);
            voucherLimitedDealerMappings.add(voucherLimitedDealerMapping);
        }

        ossVoucherLimitedDealerMappingMapper.batchInsertLimitedDealers(voucherLimitedDealerMappings);
    }

    private Integer createVoucherBatchInfo(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        String batchCode = generateBatchCode(voucherBatchInfoVo);
        VoucherBatch voucherBatch = new VoucherBatch();
        voucherBatch.setBatchCode(batchCode);
        voucherBatch.setVoucherType(voucherBatchInfoVo.getVoucherType());
        voucherBatch.setVoucherName(voucherBatchInfoVo.getVoucherName());
        voucherBatch.setDrawExpireDate(voucherBatchInfoVo.getDrawExpireDate());
        voucherBatch.setVoucherCount(voucherBatchInfoVo.getVoucherCount());
        voucherBatch.setRedeemValue(voucherBatchInfoVo.getRedeemValue());
        voucherBatch.setMinimumSpendAmount(voucherBatchInfoVo.getMinimumSpendAmount());
        voucherBatch.setExpirationReminder(voucherBatchInfoVo.getExpirationReminder());
        voucherBatch.setRemindBeforeExpireDay(voucherBatchInfoVo.getRemindBeforeExpireDay());
        voucherBatch.setRedeemStartTime(voucherBatchInfoVo.getRedeemStartTime());
        voucherBatch.setRedeemEndTime(voucherBatchInfoVo.getRedeemEndTime());
        voucherBatch.setValidForNumberOfDays(voucherBatchInfoVo.getValidForNumberOfDays());
        voucherBatch.setUsageRule(voucherBatchInfoVo.getUsageRule());
        voucherBatch.setVehicleMileageMin(voucherBatchInfoVo.getVehicleMileageMin());
        voucherBatch.setVehicleMileageMax(voucherBatchInfoVo.getVehicleMileageMax());
        voucherBatch.setSingleTimeLimitDeductionAmount(voucherBatchInfoVo.getSingleTimeLimitDeductionAmount());
        voucherBatch.setActivityName(voucherBatchInfoVo.getActivityName());
        voucherBatch.setMinPurchaseNumber(voucherBatchInfoVo.getMinPurchaseNumber());
        voucherBatch.setStatus(VoucherConstants.SAVE);
        voucherBatch.setRedeemTemplateCode("HX-" + batchCode);
        voucherBatch.setBusinessType(voucherBatchInfoVo.getBusinessType());
        voucherBatch.setBusinessTypeOption(voucherBatchInfoVo.getBusinessTypeOption());
        voucherBatch.setIsVinLimited(voucherBatchInfoVo.getIsVinLimited());
        voucherBatch.setIsOverlapable(voucherBatchInfoVo.getIsOverlapable());
        voucherBatch.setIsPartCodeLimited(voucherBatchInfoVo.getIsPartCodeLimited());
        voucherBatch.setIsDealerLimited(voucherBatchInfoVo.getIsDealerLimited());
        voucherBatch.setExpiryDateType(voucherBatchInfoVo.getExpiryDateType());
        voucherBatch.setDistributionCount(INT_ZERO);
        voucherBatch.setUpdateTime(new Date());
        if (voucherBatchInfoVo.getRedeemChannel().equals(VoucherConstants.REDEEM_IN_DMS)) {
            voucherBatch.setRedeemType(voucherBatchInfoVo.getRedeemType());
        } else {
            voucherBatch.setRedeemType(VoucherConstants.DEFAULT_REDEEM_TYPE);
        }
        voucherBatch.setRedeemChannel(voucherBatchInfoVo.getRedeemChannel());
        voucherBatch.setEntityCode(voucherBatchInfoVo.getEntityCode());
        if (voucherBatchInfoVo.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)) {
            voucherBatch.setLogoUrl(voucherBatchInfoVo.getLogoUrl());
            voucherBatch.setShopUrl(voucherBatchInfoVo.getShopUrl());
            voucherBatch.setThirdpartCodeType(voucherBatchInfoVo.getCodeType());
            voucherBatch.setThirdpartRedeemCode(voucherBatchInfoVo.getVoucherCodeSignal());
            voucherBatch.setThirdpartShopName(voucherBatchInfoVo.getShopName());
        }
        ossVoucherBatchMapper.insertSelective(voucherBatch);

        return voucherBatch.getId();
    }

    private String generateBatchCode(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        String pre = "";

        switch (voucherBatchInfoVo.getVoucherType()) {
            case INT_ONE:
                pre = "DJ";
                break;
            case INT_TWO:
                pre = "TC";
                break;
            case INT_THREE:
                pre = "SW";
                break;
            case INT_FOUR:
                pre = "QY";
                break;
            case INT_FIVE:
                pre = "YY";
                break;
            default:
                pre = "";
        }
        String date = DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss");
        String randomValue = "";
        for (int i = INT_ZERO; i < INT_SIX; i++) {
            randomValue += random.nextInt(INT_TEN);
        }

        return pre + date + randomValue;
    }

    public Integer editVoucherBatch(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(voucherBatchInfoVo.getId());
        if (null == voucherBatch) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_NOT_EXIST);
        }
        if (voucherBatchInfoVo.getExpiryDateType().equals(VoucherConstants.FIXED)
                && voucherBatchInfoVo.getDrawExpireDate().after(voucherBatchInfoVo.getRedeemEndTime())) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_TIME_ERROR);
        }
        voucherBatch.setVoucherType(voucherBatchInfoVo.getVoucherType());
        voucherBatch.setVoucherName(voucherBatchInfoVo.getVoucherName());
        voucherBatch.setDrawExpireDate(voucherBatchInfoVo.getDrawExpireDate());
        voucherBatch.setVoucherCount(voucherBatchInfoVo.getVoucherCount());
        voucherBatch.setRedeemValue(voucherBatchInfoVo.getRedeemValue());
        voucherBatch.setMinimumSpendAmount(voucherBatchInfoVo.getMinimumSpendAmount());
        voucherBatch.setExpirationReminder(voucherBatchInfoVo.getExpirationReminder());
        voucherBatch.setRemindBeforeExpireDay(voucherBatchInfoVo.getRemindBeforeExpireDay());
        voucherBatch.setRedeemStartTime(voucherBatchInfoVo.getRedeemStartTime());
        voucherBatch.setRedeemEndTime(voucherBatchInfoVo.getRedeemEndTime());
        voucherBatch.setValidForNumberOfDays(voucherBatchInfoVo.getValidForNumberOfDays());
        voucherBatch.setUsageRule(voucherBatchInfoVo.getUsageRule());
        voucherBatch.setVehicleMileageMin(voucherBatchInfoVo.getVehicleMileageMin());
        voucherBatch.setVehicleMileageMax(voucherBatchInfoVo.getVehicleMileageMax());
        voucherBatch.setSingleTimeLimitDeductionAmount(voucherBatchInfoVo.getSingleTimeLimitDeductionAmount());
        voucherBatch.setActivityName(voucherBatchInfoVo.getActivityName());
        voucherBatch.setMinPurchaseNumber(voucherBatchInfoVo.getMinPurchaseNumber());
        voucherBatch.setBusinessType(voucherBatchInfoVo.getBusinessType());
        voucherBatch.setBusinessTypeOption(voucherBatchInfoVo.getBusinessTypeOption());
        voucherBatch.setIsVinLimited(voucherBatchInfoVo.getIsVinLimited());
        voucherBatch.setIsOverlapable(voucherBatchInfoVo.getIsOverlapable());
        voucherBatch.setIsPartCodeLimited(voucherBatchInfoVo.getIsPartCodeLimited());
        voucherBatch.setIsDealerLimited(voucherBatchInfoVo.getIsDealerLimited());
        voucherBatch.setExpiryDateType(voucherBatchInfoVo.getExpiryDateType());
        if (voucherBatchInfoVo.getRedeemChannel().equals(VoucherConstants.REDEEM_IN_DMS)) {
            voucherBatch.setRedeemType(voucherBatchInfoVo.getRedeemType());
        } else {
            voucherBatch.setRedeemType(VoucherConstants.DEFAULT_REDEEM_TYPE);
        }
        voucherBatch.setRedeemChannel(voucherBatchInfoVo.getRedeemChannel());
        voucherBatch.setEntityCode(voucherBatchInfoVo.getEntityCode());
        voucherBatch.setLogoUrl(voucherBatchInfoVo.getLogoUrl());
        voucherBatch.setShopUrl(voucherBatchInfoVo.getShopUrl());
        voucherBatch.setThirdpartCodeType(voucherBatchInfoVo.getCodeType());
        voucherBatch.setThirdpartRedeemCode(voucherBatchInfoVo.getVoucherCodeSignal());
        voucherBatch.setThirdpartShopName(voucherBatchInfoVo.getShopName());
        ossVoucherBatchMapper.updateByPrimaryKeySelective(voucherBatch);
        ossVoucherBatchMapper.updateBatchThirdpartData(voucherBatch.getId(), voucherBatch.getThirdpartShopName(), voucherBatch.getThirdpartRedeemCode());
        ossVoucherLimitedDealerMappingMapper.deleteDealersByBatchId(voucherBatchInfoVo.getId());
        ossVoucherLimitedPartsMappingMapper.deletePartsByBatchId(voucherBatchInfoVo.getId());
        ossThirdpartVoucherMappingMapper.deleteThirdpartByBatchId(voucherBatchInfoVo.getId());
        if (voucherBatchInfoVo.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)) {
            createThirdpartVoucher(voucherBatchInfoVo);
            createThirdpardShopOfBatch(voucherBatchInfoVo);
        } else {
            createLimitedPartsOfBatch(voucherBatchInfoVo, voucherBatchInfoVo.getId());
            createLimitedDealersOfBatch(voucherBatchInfoVo, voucherBatchInfoVo.getId());
        }
        return voucherBatchInfoVo.getId();
    }

    @Override
    public VoucherBatchListVo getVoucherBatchList(
        VoucherBatchListQueryParamVo voucherBatchListQueryParamVo) throws Exception {
        Map params = CommonConvertUtil.transBean2Map(voucherBatchListQueryParamVo);
        List voucherBatchList = new ArrayList<>();
        if (INT_ZERO == voucherBatchListQueryParamVo.getIsGrantSelect()) {
            PageHelper.startPage(voucherBatchListQueryParamVo.getCurrentPage(), voucherBatchListQueryParamVo.getPageSize());
            PageHelper.orderBy(GET_VOUCHER_BATCH_LIST_ORDER_BY);
            voucherBatchList = ossVoucherBatchMapper.selectByBatchListQueryParam(params);
        } else {
            List list = ossThirdpartVoucherMappingMapper.selectThirdpartGrantBatch();
            List<ThirdpartGrantBatchVo> thirdpartGrantBatchVos = CommonConvertUtil.mapListToVOList(list, ThirdpartGrantBatchVo.class);
            List<Integer> notGrantBatchIds = thirdpartGrantBatchVos.stream()
                    .filter(batch -> !batch.getVoucherCount().equals(batch.getCurrentImportCount()))
                    .map(ThirdpartGrantBatchVo::getBatchId).collect(Collectors.toList());
            List<VoucherBatch> voucherBatches = ossVoucherBatchMapper.selectThirdpartNotImportBatch();
            List<Integer> notImportBatchIds = voucherBatches.stream().map(VoucherBatch::getId).collect(Collectors.toList());
            notGrantBatchIds.addAll(notImportBatchIds);
            PageHelper.startPage(voucherBatchListQueryParamVo.getCurrentPage(), voucherBatchListQueryParamVo.getPageSize());
            PageHelper.orderBy(GET_VOUCHER_BATCH_LIST_ORDER_BY);
            voucherBatchList = ossVoucherBatchMapper.selectForGrantByBatchListQueryParam(params, notGrantBatchIds);
        }
        List<VoucherBatchInfoVo> voucherBatchInfos = CommonConvertUtil.mapListToVOList(voucherBatchList, VoucherBatchInfoVo.class);
        voucherBatchInfos.forEach(voucherBatchInfoVo -> warpVoucherBatchInfoVo(voucherBatchInfoVo));

        PageInfo<VoucherBatch> pageInfo = new PageInfo<>(voucherBatchList);
        VoucherBatchListVo voucherBatchListVo = new VoucherBatchListVo();
        voucherBatchListVo.setTotalCount(pageInfo.getTotal());
        voucherBatchListVo.setVoucherBatchInfos(voucherBatchInfos);

        return voucherBatchListVo;
    }


    private void warpVoucherBatchInfoVo(VoucherBatchInfoVo voucherBatchInfoVo) {
        if (null != voucherBatchInfoVo.getVoucherTemplateId()) {
            JSONObject requestParams = new JSONObject();
            requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
            requestParams.put(VoucherConstants.CDP_GENERATE_ID, voucherBatchInfoVo.getVoucherTemplateId());
            log.info("warpVoucherBatchInfoVo requestParams=" + requestParams);
            String result = voucherByRunlinCoreFeignClient.queryTicketStatusNumServer(requestParams);
            if (null == result) {
                log.info("call VOUCHER_NUM fail1 requestParams=" + requestParams);
                return;
            }
            JSONObject resultJsonObject = JSONObject.fromObject(result);
            if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                log.info(String.format("call warpVoucherBatchInfoVo fail2 requestParams=%s, result=%s", resultJsonObject.toString(), result));
                throw new ServiceException(ResultEnum.QUERY_VOUCHER_COUNT_FAIL);
            }
            JSONObject data = resultJsonObject.getJSONObject(VoucherConstants.RESULT_DATA);
            if (!data.isEmpty()) {
                voucherBatchInfoVo.setDistributionCount(data.getInt("issuedNotUsedNum"));
                voucherBatchInfoVo.setExpiredCount(data.getInt("overdueNum"));
                voucherBatchInfoVo.setReceivedCount(data.getInt("receivedNotUsedNum"));
                voucherBatchInfoVo.setStockCount(data.getInt("notReceivedNum"));
                voucherBatchInfoVo.setUsedCount(data.getInt("usedUseUpNum"));
            } else {
                log.error(String.format("call warpVoucherBatchInfoVo fail3 requestParams=%s, result=%s", resultJsonObject.toString(), result));
            }
        } else {
            voucherBatchInfoVo.setStockCount(voucherBatchInfoVo.getVoucherCount());
        }
    }

    @Override
    public String generateVoucherTemplate(Integer batchId) throws Exception {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(batchId);
        if (!voucherBatch.getStatus().equals(VoucherConstants.SAVE)) {
            throw new ServiceException(ResultEnum.VOUCHER_NOT_SAVE_STATUS);
        }
        if (voucherBatch.getRedeemChannel().equals(VoucherConstants.REDEEM_IN_DMS)) {
            //创建在DMS核销的模版
            createDmsRedeemTemplate(voucherBatch);
        } else {
            //创建通用的核销模版
            createCommonRedeemTemplate(voucherBatch);
        }
        JSONObject requestParams = getGenerateVoucherTemplateRequestParams(voucherBatch);
        Map<Byte, String> type2Url = new HashMap<>();
        type2Url.put(VoucherConstants.REDEEM_VALUE_VOUCHER, VoucherConstants.GENERATE_VOUCHER_DJQ_TEMPLATE_URL);
        type2Url.put(VoucherConstants.PACKAGE_VOUCHER, VoucherConstants.GENERATE_VOUCHER_DJQ_TEMPLATE_URL);
        type2Url.put(VoucherConstants.GOODS_VOUCHER, VoucherConstants.GENERATE_VOUCHER_SWQ_TEMPLATE_URL);
        type2Url.put(VoucherConstants.RIGHT_VOUCHER, VoucherConstants.GENERATE_VOUCHER_SWQ_TEMPLATE_URL);
        type2Url.put(VoucherConstants.THIRDPART_VOUCHER, VoucherConstants.GENERATE_VOUCHER_YYQ_TEMPLATE_URL);
        //生成对应的卡券模版
        String result = null;
        if (voucherBatch.getVoucherType().equals(VoucherConstants.REDEEM_VALUE_VOUCHER)) {
            result = voucherByRunlinCoreFeignClient.ticketGeneratetDjq(requestParams);
        } else if (voucherBatch.getVoucherType().equals(VoucherConstants.PACKAGE_VOUCHER)) {
            result = voucherByRunlinCoreFeignClient.ticketGeneratetDjq(requestParams);
        } else if (voucherBatch.getVoucherType().equals(VoucherConstants.GOODS_VOUCHER)) {
            result = voucherByRunlinCoreFeignClient.ticketGeneratetSwq(requestParams);
        } else if (voucherBatch.getVoucherType().equals(VoucherConstants.RIGHT_VOUCHER)) {
            result = voucherByRunlinCoreFeignClient.ticketGeneratetSwq(requestParams);
        } else if (voucherBatch.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)) {
            result = voucherByRunlinCoreFeignClient.ticketGeneratetYyq(requestParams);
        }
        if (null == result) {
            log.error("call generateVoucherTemplate fail1 requestParams=" + requestParams);
            throw new ServiceException(ResultEnum.VOUCHER_CDP_RESULT_NULL);
        }
        JSONObject resultJsonObject = JSONObject.fromObject(result);
        if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS) || null == resultJsonObject.get(VoucherConstants.RESULT_DATA)) {
            log.error(String.format("call generateVoucherTemplate fail2 requestParams=%s, result=%s", requestParams.toString(), result));
            ResultEnum resultEnum = ResultEnum.VOUCHER_CDP_PARAM_FAIL;
            resultEnum.setErrorMessage((String) resultJsonObject.get(VoucherConstants.RETURN_MESSAGE));
            throw new ServiceException(resultEnum);
        }
        JSONObject data = resultJsonObject.getJSONObject(VoucherConstants.RESULT_DATA);
        String generateId = data.getString(VoucherConstants.CDP_GENERATE_ID);
        voucherBatch.setVoucherTemplateId(generateId);
        voucherBatch.setStatus(VoucherConstants.GENERATE);
        voucherBatch.setUpdateTime(new Date());
        ossVoucherBatchMapper.updateByPrimaryKeySelective(voucherBatch);
        return generateId;
    }

    private void createCommonRedeemTemplate(VoucherBatch voucherBatch) throws Exception {
        JSONObject redeemRequestParams = getCommonRedeemTemplateRequestParams(voucherBatch);
        String redeemResult = voucherByRunlinCoreFeignClient.templateUpdate(redeemRequestParams);
        if (null == redeemResult || redeemResult.equals("")) {
            log.info("call createCommonRedeemTemplate fail1 requestParams=" + redeemResult);
            throw new ServiceException(ResultEnum.VOUCHER_CDP_RESULT_NULL);
        }
        JSONObject redeemResultJsonObject = JSONObject.fromObject(redeemResult);
        if (!redeemResultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            log.info(String.format("call createCommonRedeemTemplate fail2 requestParams=%s, result=%s", redeemRequestParams.toString(), redeemResult));
            ResultEnum resultEnum = ResultEnum.VOUCHER_CDP_PARAM_FAIL;
            resultEnum.setErrorMessage((String) redeemResultJsonObject.get(VoucherConstants.RETURN_MESSAGE));
            throw new ServiceException(resultEnum);
        }
    }

    private void createDmsRedeemTemplate(VoucherBatch voucherBatch) throws Exception {
        JSONObject redeemRequestParams = getRedeemTemplateRequestParams(voucherBatch);
        String redeemResult = voucherByRunlinCoreFeignClient.templateDmsUpdate(redeemRequestParams);
        if (null == redeemResult || redeemResult.equals("")) {
            log.info("call createDmsRedeemTemplate fail1 requestParams=" + redeemRequestParams);
            throw new ServiceException(ResultEnum.VOUCHER_CDP_RESULT_NULL);
        }
        JSONObject redeemResultJsonObject = JSONObject.fromObject(redeemResult);
        if (!redeemResultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            log.info(String.format("call createDmsRedeemTemplate fail2 requestParams=%s, result=%s", redeemRequestParams.toString(), redeemResult));
            ResultEnum resultEnum = ResultEnum.VOUCHER_CDP_PARAM_FAIL;
            resultEnum.setErrorMessage((String) redeemResultJsonObject.get(VoucherConstants.RETURN_MESSAGE));
            throw new ServiceException(resultEnum);
        }
    }

    private JSONObject getCommonRedeemTemplateRequestParams(VoucherBatch voucherBatch) throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put(VoucherConstants.TEMPLATE_CODE, voucherBatch.getRedeemTemplateCode());
        requestParams.put(VoucherConstants.TEMPLATE_NAME, voucherBatch.getVoucherName());
        requestParams.put(VoucherConstants.BRAND_ID, STR_TWO);
        requestParams.put(VoucherConstants.AID, INT_ZERO);
        requestParams.put(VoucherConstants.NAME, INT_ZERO);
        requestParams.put(VoucherConstants.IDENTITY_CARD, INT_ZERO);
        requestParams.put(VoucherConstants.MOBILE, INT_ZERO);
        requestParams.put(VoucherConstants.VIN, voucherBatch.getIsVinLimited());
        requestParams.put("business", INT_ZERO);
        requestParams.put(VoucherConstants.STATUS, STR_TWO);
        return requestParams;
    }

    private JSONObject getRedeemTemplateRequestParams(VoucherBatch voucherBatch) throws Exception {
        JSONObject requestParams = new JSONObject();

        requestParams.put(VoucherConstants.TEMPLATE_CODE, voucherBatch.getRedeemTemplateCode());
        requestParams.put(VoucherConstants.TEMPLATE_NAME, voucherBatch.getVoucherName());
        requestParams.put(VoucherConstants.BRAND_ID, STR_TWO);
        requestParams.put(VoucherConstants.AID, INT_ZERO);
        requestParams.put(VoucherConstants.NAME, INT_ZERO);
        requestParams.put(VoucherConstants.IDENTITY_CARD, INT_ZERO);
        requestParams.put(VoucherConstants.MOBILE, INT_ZERO);
        requestParams.put(VoucherConstants.VIN, voucherBatch.getIsVinLimited());
        if (voucherBatch.getVoucherType().equals(VoucherConstants.PACKAGE_VOUCHER)) {
            //套餐劵是谁发放的谁核销，故必然要限制，需要特殊处理
            requestParams.put(VoucherConstants.REGION_DEALER, INT_ONE);
        } else {
            requestParams.put(VoucherConstants.REGION_DEALER, voucherBatch.getIsDealerLimited());
        }
        requestParams.put("provinceCityDealer", INT_ZERO);
        requestParams.put("subDealer", INT_ZERO);
        requestParams.put("itemCode", INT_ZERO);
        requestParams.put(VoucherConstants.STATUS, STR_TWO);
        requestParams.put("type", voucherBatch.getRedeemType());

        if (voucherBatch.getBusinessType().equals(VoucherConstants.NO_LIMIT_BUSSINESS)
                || voucherBatch.getBusinessType().equals(VoucherConstants.TAKE_AND_DELIVER_VECHILE_BUSSINESS)) {
            requestParams.put(VoucherConstants.BILL_TYPE, INT_ZERO);
        } else {
            requestParams.put(VoucherConstants.BILL_TYPE, INT_ONE);
            String businessOption = voucherBatch.getBusinessTypeOption();
            if (null == businessOption) {
                throw new ServiceException(ResultEnum.VOUCHER_BUSINESS_TYPE_OPTION_ERROR);
            }
            List<String> bussinessValueOptions = Arrays.asList(businessOption.split(VoucherConstants.SEPARATOR));
            JSONArray billList = new JSONArray();
            bussinessValueOptions.forEach(value -> {
                JSONObject bill = new JSONObject();
                bill.put("bill", value);
                billList.add(bill);
            });
            requestParams.put("billTypeList", billList);
        }
        if (INT_ZERO == voucherBatch.getIsPartCodeLimited()) {
            requestParams.put(VoucherConstants.PART_CODE, INT_ZERO);
        } else {
            requestParams.put(VoucherConstants.PART_CODE, INT_ONE);
            JSONArray partList = new JSONArray();
            List<VoucherLimitedPartsMapping> voucherLimitedPartsMappings = ossVoucherLimitedPartsMappingMapper.selectByBatchId(voucherBatch.getId());
            voucherLimitedPartsMappings.forEach(part -> {
                JSONObject partObj = new JSONObject();
                partObj.put("code", part.getPartCode());
                partList.add(partObj);
            });
            requestParams.put("partCodeList", partList);
        }

        return requestParams;
    }

    private JSONObject getGenerateVoucherTemplateRequestParams(VoucherBatch voucherBatch) throws Exception {
        JSONObject requestParams = new JSONObject();

        requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        if (voucherBatch.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)
                && (voucherBatch.getThirdpartCodeType().equals(VoucherConstants.CODE_TYPE_SIGNAL)
                || voucherBatch.getThirdpartCodeType().equals(VoucherConstants.CODE_TYPE_EMPTY))) {
            requestParams.put(VoucherConstants.IS_REDEEM_CODE, STR_ZERO);
        } else {
            requestParams.put(VoucherConstants.IS_REDEEM_CODE, STR_ONE);
        }
        requestParams.put("isGiftGive", STR_ZERO);
        if (voucherBatch.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)
                && voucherBatch.getThirdpartCodeType().equals(VoucherConstants.CODE_TYPE_THIRD)) {
            requestParams.put(VoucherConstants.GENERATE_WAY, STR_TWO);
        } else {
            requestParams.put(VoucherConstants.GENERATE_WAY, STR_ONE);
        }
        requestParams.put("provideWay", STR_ONE);
        requestParams.put("isNeedActive", STR_ZERO);
        requestParams.put("activeUrl", "");
        requestParams.put("isGiveBack", STR_ZERO);
        requestParams.put("exclusiveIdent", STR_ZERO);
        requestParams.put("newDisplay", STR_ZERO);
        requestParams.put("isOtherUse", STR_ZERO);
        requestParams.put("businessType", INT_ONE);
        requestParams.put(VoucherConstants.BUSINESS_CODE, VoucherConstants.BUSSINESS_CODE_TO_CDP_CODE.get(voucherBatch.getBusinessType()));
        requestParams.put("grantRemind", INT_ONE);
        requestParams.put("usedRemind", INT_ONE);
        requestParams.put("overdueRemind", INT_ONE);
        requestParams.put("cardTicketName", voucherBatch.getVoucherName());
        if (voucherBatch.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)) {
            requestParams.put("specialTicketName", voucherBatch.getVoucherName());
        }
        requestParams.put("provideNum", voucherBatch.getVoucherCount());
        requestParams.put("usageRule", voucherBatch.getUsageRule());
        requestParams.put("expirationRemind", voucherBatch.getExpirationReminder());
        requestParams.put("firstRemind", voucherBatch.getRemindBeforeExpireDay());

        requestParams.put("cancelAfterVerificationCode", voucherBatch.getRedeemTemplateCode());
        requestParams.put("isSameUse", voucherBatch.getIsOverlapable());
        requestParams.put("deductionAmount", voucherBatch.getRedeemValue());

        if (voucherBatch.getVoucherType().equals(VoucherConstants.REDEEM_VALUE_VOUCHER)
                || voucherBatch.getVoucherType().equals(VoucherConstants.PACKAGE_VOUCHER)) {
            requestParams.put(VoucherConstants.VALUE_TYPE, STR_ONE);
            if (voucherBatch.getVoucherType().equals(VoucherConstants.REDEEM_VALUE_VOUCHER)) {
                requestParams.put(VoucherConstants.VALUE_TXT, voucherBatch.getRedeemValue());
            } else {
                //由于套餐劵对应cdp的是代金劵，业务要求微信端套餐劵不能显示价格，故特殊处理下
                requestParams.put(VoucherConstants.VALUE_TXT, VoucherConstants.STR_NULL);
            }
        } else {
            requestParams.put(VoucherConstants.VALUE_TYPE, "3");
            requestParams.put("comment", voucherBatch.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER) ? voucherBatch.getThirdpartRedeemCode() : "no");
        }
        if (voucherBatch.getExpiryDateType().equals(VoucherConstants.FIXED_EXPIRED_DATE)) {
            requestParams.put(VoucherConstants.VALIDDATE_TYPE, INT_ONE);
            requestParams.put("validDateTime", DateUtil.dateFormat(voucherBatch.getRedeemEndTime(), DateUtil.DATE_TIME_PATTERN));
        } else {
            requestParams.put(VoucherConstants.VALIDDATE_TYPE, INT_TWO);
        }
        if (voucherBatch.getVoucherType().equals(VoucherConstants.REDEEM_VALUE_VOUCHER)
                || voucherBatch.getVoucherType().equals(VoucherConstants.PACKAGE_VOUCHER)) {
            requestParams.put("whetherLimit", INT_ONE);
            requestParams.put("useLowerLimit", (null == voucherBatch.getMinimumSpendAmount()) ? INT_ZERO : voucherBatch.getMinimumSpendAmount());
            requestParams.put("singleLimit", INT_ONE);
            requestParams.put("useNumber", STR_ONE);
            requestParams.put("iscustom", INT_ONE);
            requestParams.put("singleAmount", voucherBatch.getRedeemValue());
        }
        if (voucherBatch.getVoucherType().equals(VoucherConstants.GOODS_VOUCHER) || voucherBatch
            .getVoucherType().equals(VoucherConstants.RIGHT_VOUCHER)) {
            requestParams.put("entityName", voucherBatch.getVoucherName());
            requestParams.put("entityQuantity", STR_ONE);
            if (null != voucherBatch.getEntityCode()) {
                requestParams.put("entityCode", voucherBatch.getEntityCode());
            }
            if (voucherBatch.getVoucherType().equals(VoucherConstants.RIGHT_VOUCHER)) {
                requestParams.put(VoucherConstants.ENTITY_VALUE, STR_ZERO);
            } else {
                requestParams.put(VoucherConstants.ENTITY_VALUE, (null == voucherBatch.getRedeemValue()) ? STR_ZERO : voucherBatch.getRedeemValue());
            }
        }
        List<String> appKeys = new ArrayList<>();
        appKeys.addAll(Arrays.asList(voucherAppKeys.split(VoucherConstants.SEPARATOR)));
        appKeys.add(memberAppKey);
        if (voucherBatch.getRedeemChannel().equals(VoucherConstants.REDEEM_IN_DMS)) {
            appKeys.add(dmsAppKey);
        }
        JSONArray appKeyList = new JSONArray();
        for (String appKey : appKeys) {
            JSONObject tmpAppKey = new JSONObject();
            tmpAppKey.put("appkey", appKey);
            appKeyList.add(tmpAppKey);
        }

        requestParams.put("visibleAppKeyList", appKeyList);
        requestParams.put("usableAppKeyList", appKeyList);
        return requestParams;
    }

    @Override
    public void delVoucherBatch(Integer batchId) throws Exception {
        VoucherBatch voucherBatch = new VoucherBatch();
        voucherBatch.setId(batchId);
        voucherBatch.setDeleted(VoucherConstants.DELETE);

        ossVoucherBatchMapper.updateByPrimaryKeySelective(voucherBatch);
    }

    @Override
    public void updateVoucherBatchStatus(Integer batchId, Byte status) throws Exception {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(batchId);
        voucherBatch.setStatus(status);
        if (status.equals(VoucherConstants.FINISH)) {
            JSONObject requestParams = new JSONObject();
            requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
            requestParams.put(VoucherConstants.CDP_GENERATE_ID, voucherBatch.getVoucherTemplateId());
            requestParams.put("cancellationReason", "no need");
            String result = voucherByRunlinCoreFeignClient.ticketAbndon(requestParams);
            if (null == result) {
                log.info("call updateVoucherBatchStatus fail1 requestParams=" + requestParams);
                throw new ServiceException(ResultEnum.VOUCHER_CDP_RESULT_NULL);
            }
            JSONObject resultJsonObject = JSONObject.fromObject(result);
            if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                log.info(String.format("call updateVoucherBatchStatus fail2 requestParams=%s, result=%s", requestParams.toString(), result));
                ResultEnum resultEnum = ResultEnum.VOUCHER_CDP_PARAM_FAIL;
                resultEnum.setErrorMessage((String) resultJsonObject.get(VoucherConstants.RETURN_MESSAGE));
                throw new ServiceException(resultEnum);
            }
        }
        ossVoucherBatchMapper.updateByPrimaryKeySelective(voucherBatch);
    }

    @Override
    public HSSFWorkbook downloadDealerTemplate() throws Exception {
        String[] title = VoucherConstants.DOWNLOAD_DEALER_TEMPLATE_TITLE;
        String[][] values = {};

        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook("限定商户列表", title, values, null, null);
        return workbook;
    }

    @Override
    public List<RedeemLimitedDealersVo> getLimitedDealers(MultipartFile file) throws Exception {
        List<RedeemLimitedDealersVo> redeemLimitedDealersVos = new ArrayList<>();
        String[] title = VoucherConstants.DOWNLOAD_DEALER_TEMPLATE_TITLE;
        Integer sheetIndex = INT_ZERO;
        String[][] results = ExcelUtil.getValuesOfHSSFWorkbook(file, title, sheetIndex);
        if (null == results) {
            return redeemLimitedDealersVos;
        }
        for (String[] row : results) {
            RedeemLimitedDealersVo redeemLimitedDealersVo = new RedeemLimitedDealersVo();
            redeemLimitedDealersVo.setNumber(row[INT_ZERO]);
            redeemLimitedDealersVo.setSaleCode(row[INT_ONE]);
            redeemLimitedDealersVo.setDealerCode(row[INT_TWO]);
            redeemLimitedDealersVo.setDealerName(row[INT_THREE]);
            redeemLimitedDealersVos.add(redeemLimitedDealersVo);
        }

        return redeemLimitedDealersVos;
    }

    @Override
    public UserVoucherDetailListVo getUserVoucherDetailList(
        UserVoucherDetailListQueryParamVo queryParam, AdminUserVO adminUserVO) throws Exception {
        UserVoucherDetailListVo userVoucherDetailList = new UserVoucherDetailListVo();
        List<String> roles = adminUserVO.getRoles();
        String roleTypeString = null;
        if (!roles.isEmpty()) {
            roleTypeString = roles.get(INT_ZERO);
        } else {
            Result<OperationUser> operationUserResult = operationUserFeignClient
                .getUserById(adminUserVO.getId());
            OperationUser operationUser = null;
            if (operationUserResult.getReturnStatus()
                .equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
                operationUser = operationUserResult.getData();
            } else {
                ServiceExceptionUtil
                    .throwServiceException(VoucherErrorEnum.GET_OPERATION_USER_ERROR);
            }
            roleTypeString = String.valueOf(operationUser.getRoleType());
        }
        List list = getVoucherDetails(queryParam, roleTypeString, adminUserVO.getRefId());
        List<UserVoucherDetailVo> userVoucherDetails = CommonConvertUtil.mapListToVOList(list, UserVoucherDetailVo.class);
        wrapVoucherDetailsForGrantType(userVoucherDetails);
        userVoucherDetailList.setUserVoucherDetailList(userVoucherDetails);
        if (adminUserVO.getRoles().contains(ShiroConstants.ROLE_DEALER)) {
            warpVoucherDetailInfo(userVoucherDetailList.getUserVoucherDetailList());
        } else {
            wrapVoucherDetailInfoForOem(userVoucherDetailList.getUserVoucherDetailList());
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        userVoucherDetailList.setTotalCount(pageInfo.getTotal());
        return userVoucherDetailList;
    }

    private void wrapVoucherDetailInfoForOem(List<UserVoucherDetailVo> userVoucherDetailVos) {
        if (null == userVoucherDetailVos) {
            return;
        }
        for (UserVoucherDetailVo userVoucherDetailVo : userVoucherDetailVos) {
            if (VoucherConstants.THIRDPART_VOUCHER.equals(userVoucherDetailVo.getVoucherType())
                    && VoucherConstants.CODE_TYPE_SIGNAL.equals(userVoucherDetailVo.getCodeType())) {
                userVoucherDetailVo.setRedeemCode(userVoucherDetailVo.getVoucherCodeSignal());
            }
        }
    }

    private void warpVoucherDetailInfo(List<UserVoucherDetailVo> userVoucherDetailVos) {
        if (null == userVoucherDetailVos) {
            return;
        }

        for (UserVoucherDetailVo userVoucherDetailVo : userVoucherDetailVos) {
            String redeemCode = userVoucherDetailVo.getRedeemCode();
            userVoucherDetailVo.setRedeemCode(redeemCode.substring(INT_ZERO, INT_TWO) + "****" + redeemCode.substring(redeemCode.length() - INT_TWO));
        }
    }

    @Override
    public void addBatchStoreCount(Integer batchId, Integer stockCount, boolean isAddBatchCount) throws Exception {
        ossVoucherCommonService.addBatchStoreCount(batchId, stockCount, isAddBatchCount);
    }

    @Override
    public VoucherBatchInfoVo getVoucherBatchDetail(Integer batchId) throws Exception {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(batchId);
        if (null == voucherBatch) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_NOT_EXIST);
        }
        VoucherBatchInfoVo voucherBatchInfoVo = new VoucherBatchInfoVo();
        BeanUtils.copyProperties(voucherBatch, voucherBatchInfoVo);
        voucherBatchInfoVo.setVoucherCodeSignal(voucherBatch.getThirdpartRedeemCode());
        voucherBatchInfoVo.setCodeType(voucherBatch.getThirdpartCodeType());
        voucherBatchInfoVo.setShopName(voucherBatch.getThirdpartShopName());
        if (voucherBatch.getVoucherType().equals(VoucherConstants.THIRDPART_VOUCHER)) {
            wrapThirdpartShop(voucherBatchInfoVo);
        } else {
            wrapRedeemInfo(voucherBatchInfoVo);
        }
        warpVoucherBatchInfoVo(voucherBatchInfoVo);
        voucherBatchInfoVo.setGrantType(null);
        voucherBatchInfoVo.setGrantBusinessType(null);
        voucherBatchInfoVo.setBusinessTypeOptionText(getBusinessTypeOptions(voucherBatch));
        if (isSelectGrant(batchId)) {
            voucherBatchInfoVo.setGrantType(VoucherConstants.SELECT_GRANT);
            voucherBatchInfoVo.setGrantBusinessType(VoucherConstants.GRANT_BUSINESS_NA);
            List list = ossOemVoucherBatchSelectTriggerMappingMapper.selectTriggerByBatchId(batchId);
            List<VoucherBatchInfoVo> voucherBatchInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchInfoVo.class);
            if (!voucherBatchInfoVos.isEmpty()) {
                voucherBatchInfoVo.setGrantLimitPerTime(voucherBatchInfoVos.get(INT_ZERO).getGrantLimitPerTime());
            }
        } else {
            List list = ossOemVoucherGrantTriggerMapper.selectByBatchId(batchId);
            List<VoucherBatchInfoVo> voucherBatchInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchInfoVo.class);
            if (!voucherBatchInfoVos.isEmpty()) {
                voucherBatchInfoVo.setGrantType(voucherBatchInfoVos.get(INT_ZERO).getGrantType());
                voucherBatchInfoVo.setGrantBusinessType(voucherBatchInfoVos.get(INT_ZERO).getGrantBusinessType());
                voucherBatchInfoVo.setGrantLimitPerTime(voucherBatchInfoVos.get(INT_ZERO).getGrantLimitPerTime());
                voucherBatchInfoVo.setGrantTimesLimit(voucherBatchInfoVos.get(INT_ZERO).getGrantTimesLimit());
                if (VoucherConstants.AUTOMATIC.equals(voucherBatchInfoVos.get(INT_ZERO).getGrantType())
                    && VoucherConstants.GRANT_MAINTAION_AND_ORDER_TYPE.equals(voucherBatchInfoVos.get(INT_ZERO).getGrantBusinessType())) {
                    voucherBatchInfoVo.setGrantTimeType(voucherBatchInfoVos.get(INT_ZERO).getGrantTimeType());
                }
            }
        }
        voucherBatchInfoVo.setFailImportCount(ossThirdpartVoucherMappingMapper.countBybatchIdAndStatus(batchId, VoucherConstants.FAIL));
        voucherBatchInfoVo.setSuccessImportCount(ossThirdpartVoucherMappingMapper.countBybatchIdAndStatus(batchId, VoucherConstants.SUCCESS));
        return voucherBatchInfoVo;
    }

    private void wrapThirdpartShop(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        RedeemTemplateInfoVo redeemTemplateInfo = new RedeemTemplateInfoVo();
        List<Integer> batchIds = new ArrayList<>();
        batchIds.add(voucherBatchInfoVo.getId());
        List<DealerInfoVo> dealerInfos = new ArrayList<>();
        List<VoucherLimitedDealerMapping> dealers = ossVoucherLimitedDealerMappingMapper.selectByBatchIdList(batchIds);
        for (VoucherLimitedDealerMapping voucherLimitedDealerMapping : dealers) {
            DealerInfoVo dealerInfoVo = new DealerInfoVo();
            dealerInfoVo.setShopName(voucherLimitedDealerMapping.getShopName());
            dealerInfoVo.setShopAddress(voucherLimitedDealerMapping.getShopAddress());
            dealerInfos.add(dealerInfoVo);
        }
        redeemTemplateInfo.setPartInfos(new ArrayList<>());
        redeemTemplateInfo.setDealerInfos(dealerInfos);
        voucherBatchInfoVo.setRedeemTemplateInfo(redeemTemplateInfo);
    }

    private void wrapRedeemInfo(VoucherBatchInfoVo voucherBatchInfoVo) throws Exception {
        List<VoucherLimitedPartsMapping> voucherLimitedPartsMappings = ossVoucherLimitedPartsMappingMapper.selectByBatchId(voucherBatchInfoVo.getId());
        List<PartInfoVo> partInfos = new ArrayList<>();
        RedeemTemplateInfoVo redeemTemplateInfo = new RedeemTemplateInfoVo();
        for (VoucherLimitedPartsMapping part : voucherLimitedPartsMappings) {
            PartInfoVo partInfoVo = new PartInfoVo();
            BeanUtils.copyProperties(part, partInfoVo);
            partInfos.add(partInfoVo);
        }
        redeemTemplateInfo.setPartInfos(partInfos);

        List<Integer> batchIds = new ArrayList<>();
        batchIds.add(voucherBatchInfoVo.getId());
        List<VoucherLimitedDealerMapping> dealers = ossVoucherLimitedDealerMappingMapper.selectByBatchIdList(batchIds);
        List<DealerInfoVo> dealerInfos = new ArrayList<>();
        for (VoucherLimitedDealerMapping voucherLimitedDealerMapping : dealers) {
            DealerInfoVo dealerInfoVo = new DealerInfoVo();
            dealerInfoVo.setDealerName(voucherLimitedDealerMapping.getName());
            dealerInfoVo.setDealerCode(voucherLimitedDealerMapping.getServiceCode());
            dealerInfoVo.setBigRegionCode(voucherLimitedDealerMapping.getBigRegionCode());
            dealerInfoVo.setBigRegionName(voucherLimitedDealerMapping.getBigRegionName());
            dealerInfoVo.setSaleCode(voucherLimitedDealerMapping.getSaleCode());
            dealerInfoVo.setSmallRegionCode(voucherLimitedDealerMapping.getSmallRegionCode());
            dealerInfoVo.setSmallRegionName(voucherLimitedDealerMapping.getSmallRegionName());
            dealerInfos.add(dealerInfoVo);
        }
        redeemTemplateInfo.setDealerInfos(dealerInfos);
        voucherBatchInfoVo.setRedeemTemplateInfo(redeemTemplateInfo);
    }

    private boolean isSelectGrant(Integer batchId) throws Exception {
        Integer count = ossOemVoucherBatchSelectTriggerMappingMapper.selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
        if (count > INT_ZERO) {
            return true;
        }
        return false;
    }

    public static String getBusinessTypeOptions(VoucherBatch voucherBatch) {
        if (null == voucherBatch) {
            return "";
        }
        String businessTypeName = VoucherConstants.BUSINESS_TYPE_MAP.getOrDefault(voucherBatch.getBusinessType(), "");
        if (voucherBatch.getBusinessType().equals(VoucherConstants.REPAIR_AND_MAINTAION_BUSSINESS)) {
            String businessTypeOptions = voucherBatch.getBusinessTypeOption();
            List<String> businessTypes = new ArrayList<>();
            if (null != businessTypeOptions && !businessTypeOptions.equals("")) {
                String[] businessTypeOptionList = businessTypeOptions.split(VoucherConstants.SEPARATOR);
                for (String businessType : businessTypeOptionList) {
                    businessTypes.add(businessType + VoucherConstants.CROSSING_SPLIT + VoucherConstants.BUSSINESS_VALUE_OPTION.get(businessType));
                }
            }
            String businessTypeOptionsName = " (" + StringUtil.join(businessTypes.toArray(), VoucherConstants.SEPARATOR) + ")";
            return businessTypeOptionsName.equals("") ? businessTypeName : businessTypeName + businessTypeOptionsName;
        } else if (voucherBatch.getBusinessType().equals(VoucherConstants.MAINTAION_BUSSINESS)) {
            return "维修保养 (2-保养)";
        } else {
            return businessTypeName;
        }
    }

    @Override
    public SXSSFWorkbook downloadUserVoucherDetailList(UserVoucherDetailListQueryParamVo queryParam, SXSSFWorkbook workbook, ExcelParam excelParam) throws Exception {
        List list = getVoucherDetails(queryParam, null, null);
        String[] title = VoucherConstants.DOWNLOAD_USER_VOUCHER_DETAIL_LIST_TITLE;

        String[][] values = {};
        if (null != list) {
            values = new String[list.size()][title.length];
            Integer dataNum = list.size();
            List<UserVoucherDetailVo> userVoucherDetailList = CommonConvertUtil.mapListToVOList(list, UserVoucherDetailVo.class);
            wrapVoucherDetailsForGrantType(userVoucherDetailList);
            list.clear();
            for (Integer row = INT_ZERO; row < dataNum; row++) {
                UserVoucherDetailVo userVoucherDetailVo = userVoucherDetailList.get(row);
                values[row][INT_ZERO] = String.valueOf(row + excelParam.getOffset() + INT_ONE);
                values[row][INT_ONE] = userVoucherDetailVo.getGrantBigRegionName();
                values[row][INT_TWO] = userVoucherDetailVo.getGrantSmallRegionName();
                values[row][INT_THREE] = userVoucherDetailVo.getGrantDealerName();
                values[row][INT_FOUR] = userVoucherDetailVo.getActivityName();
                values[row][INT_FIVE] = userVoucherDetailVo.getGrantDealerCode();
                values[row][INT_SIX] = userVoucherDetailVo.getUserName();
                values[row][INT_SEVEN] = userVoucherDetailVo.getMobile();
                values[row][INT_EIGHT] = userVoucherDetailVo.getCarMake();
                values[row][INT_NINE] = (null == userVoucherDetailVo.getVehicleSaleDate()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getVehicleSaleDate(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_TEN] = userVoucherDetailVo.getVin();
                values[row][INT_ELEVEN] = (null == userVoucherDetailVo.getDrawTime()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getDrawTime(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_TWELVE] = (null == userVoucherDetailVo.getRedeemTime()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getRedeemTime(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_THIRTEEN] = (null == userVoucherDetailVo.getExpireTime()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getExpireTime(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_FOURTEEN] = userVoucherDetailVo.getWorkSheetNo();
                if (VoucherConstants.THIRDPART_VOUCHER.equals(userVoucherDetailVo.getVoucherType())
                        && VoucherConstants.CODE_TYPE_SIGNAL.equals(userVoucherDetailVo.getCodeType())) {
                    values[row][INT_FIFTEEN] = userVoucherDetailVo.getVoucherCodeSignal();
                } else {
                    values[row][INT_FIFTEEN] = userVoucherDetailVo.getRedeemCode();
                }
                values[row][INT_SIXTEEN] = VoucherConstants.USER_VOUCHER_STATUS.get(userVoucherDetailVo.getVoucherStatus());
                values[row][INT_SEVENTEEN] = userVoucherDetailVo.getRedeemBigRegionName();
                values[row][INT_EIGHTEEN] = userVoucherDetailVo.getRedeemSmallRegionName();
                values[row][INT_NINETEEN] = userVoucherDetailVo.getRedeemDealerName();
                values[row][INT_TWENTY] = userVoucherDetailVo.getRedeemDealerCode();
                values[row][INT_TWENTY_ONE] = VoucherConstants.GRANT_TYPE_MAP.getOrDefault(userVoucherDetailVo.getGrantType(), "");
                values[row][INT_TWENTY_TWO] = VoucherConstants.GRANT_BUSINESS_TYPE_MAP.getOrDefault(userVoucherDetailVo.getGrantBusinessType(), "");
                values[row][INT_TWENTY_THREE] = userVoucherDetailVo.getVoucherName();
                values[row][INT_TWENTY_FOUR] = userVoucherDetailVo.getSingleTimeLimitDeductionAmount();
            }
            userVoucherDetailList.clear();
        }

        return ExcelUtil.exportSXSSFWorkbook(VoucherConstants.EXPORT_WORKBOOK_NAME, title, values, workbook, excelParam.getOffset(), excelParam.getSheetIndex(), excelParam.getIsNewSheet());
    }

    /*@Override
    public void exportVoucherDetailReportTask() throws Exception {
        Date curDate = DateUtil.dateTimeToDate(new Date());
        Date yesterday = DateUtil.dateAdd(curDate, VoucherConstants.YESTERDAY, Boolean.TRUE);

        UserVoucherDetailListQueryParamVo queryParam = new UserVoucherDetailListQueryParamVo();
        queryParam.setDrawStartTime(yesterday.getTime() / ONETHOUSAND);
        queryParam.setDrawEndTime(curDate.getTime() / ONETHOUSAND);
        AdminUserVO adminUserVO = new AdminUserVO();
        String fileName = "Coupon" + DateUtil.dateFormat(new Date(), DateUtil.DATE_NO_SPLIT_PATTERN);
        VoucherCountInfo voucherCountInfo = getVoucherDetailPageCount(queryParam);
        voucherCountInfo.setFileName(fileName);
        voucherCountInfo.setIsZip(false);
        Integer reportFileId = addExcelInfo(adminUserVO, voucherCountInfo);
        voucherCountInfo.setReportFileId(reportFileId);
        exportVoucherDetailReport(queryParam, adminUserVO, voucherCountInfo);
    }*/

    /*@Override
    @Async(value = "asyncExecutor")
    public void exportVoucherDetailReport(UserVoucherDetailListQueryParamVo queryParam, AdminUserVO adminUserVO, VoucherCountInfo voucherCountInfo) throws Exception {
        log.info("exportVoucherDetailReport begin run... queryParam={}", queryParam);
        SXSSFWorkbook workbook = null;
        try {
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            ExcelParam excelParam = new ExcelParam();
            Integer sheetIndex = INT_ZERO;
            Integer offset = INT_ZERO;
            excelParam.setIsNewSheet(true);
            ReportFile reportFile = ossReportFileMapper.selectByPrimaryKey(voucherCountInfo.getReportFileId());
            for (Integer currentPage = INT_ONE; currentPage <= voucherCountInfo.getPageCount(); currentPage++ ) {
                offset = (currentPage - INT_ONE) * VoucherConstants.MAX_VOUCHER_DETAIL_COUNT;
                if ((INT_ZERO != offset) && (offset % VoucherConstants.MAX_SHEET_VOUCHER_COUNT) == INT_ZERO) {
                    sheetIndex += INT_ONE;
                    excelParam.setIsNewSheet(true);
                }
                excelParam.setSheetIndex(sheetIndex);
                excelParam.setOffset(offset - sheetIndex * VoucherConstants.MAX_SHEET_VOUCHER_COUNT);
                queryParam.setCurrentPage(currentPage);
                queryParam.setPageSize(VoucherConstants.MAX_VOUCHER_DETAIL_COUNT);
                workbook = downloadUserVoucherDetailList(queryParam, workbook, excelParam);
                excelParam.setIsNewSheet(false);
                try {
                    reportFile.setExportProgress(String.valueOf((currentPage - INT_ONE) * ONE_HUNDRED / voucherCountInfo.getPageCount()) + VoucherConstants.PER_CENT);
                    ossReportFileMapper.updateByPrimaryKeySelective(reportFile);
                } catch (Exception e) {
                    log.error("exportVoucherDetailReporterror e=", e);
                }
                Thread.sleep(THREAD_MILLIS);
            }
            log.info("finishWriteVoucherDetailReportToExcel {}ms", stopWatch.getTotalTimeMillis());
            //log.info("finishWriteVoucherDetailReportToInputStream size={}", (null != outputStream) ? outputStream.size():0);
            if (voucherCountInfo.getIsZip()) {
                InputStream inputStream = ExcelUtil.workbookConvertorStream(workbook);
                Map<String, Object> map = fileService.uploadZipExcel(inputStream, FileConstants.EXCEL_EXTENSION, voucherCountInfo.getFileName(), FileConstants.EXCEL_ZIP_EXTENSION);
                reportFile.setUrl((String) map.get(VoucherConstants.URL));
                reportFile.setFileSize(String.valueOf((long) map.get(VoucherConstants.FILE_SIZE) / VoucherConstants.ONE_KB) + VoucherConstants.KB);
            } else {
                Map<String, Object> map = fileService.uploadExcel(workbook, FileConstants.EXCEL_EXTENSION, voucherCountInfo.getFileName());
                reportFile.setUrl((String) map.getOrDefault(VoucherConstants.URL, null));
                reportFile.setFileSize(String.valueOf(String.valueOf((long) map.getOrDefault(VoucherConstants.FILE_SIZE, INT_ZERO) / VoucherConstants.ONE_KB) + VoucherConstants.KB));
            }
            reportFile.setUpdateTime(new Date());
            reportFile.setExportProgress(String.valueOf(ONE_HUNDRED) + VoucherConstants.PER_CENT);
            ossReportFileMapper.updateByPrimaryKeySelective(reportFile);
            stopWatch.stop();
            log.info("exportVoucherDetailReport run {}ms", stopWatch.getTotalTimeMillis());
        } catch (Exception e) {
            log.error("asyncExecutorexportVoucherDetailReport e=", e);
        } finally {
            if (null != workbook) {
                //删除临时文件
                workbook.dispose();
            }
        }
    }*/

    public Integer addExcelInfo(AdminUserVO adminUserVO, VoucherCountInfo voucherCountInfo) throws Exception {
        String extention = voucherCountInfo.getIsZip() ? FileConstants.EXCEL_ZIP_EXTENSION : FileConstants.EXCEL_EXTENSION;
        ReportFile reportFile = new ReportFile();
        reportFile.setExtention(extention);
        reportFile.setReportName(voucherCountInfo.getFileName());
        reportFile.setCreateTime(new Date());
        reportFile.setUpdateTime(new Date());
        reportFile.setFkOperationUserRefId(adminUserVO.getRefId());
        reportFile.setExportProgress(String.valueOf(INT_ZERO));
        reportFile.setFileRecordNum(voucherCountInfo.getTotalCount());
        ossReportFileMapper.insertSelective(reportFile);
        return reportFile.getId();
    }

    public VoucherCountInfo getVoucherDetailPageCount(UserVoucherDetailListQueryParamVo queryParam) {
        Integer totalNum = countVoucherDetails(queryParam, null, null);
        Integer pageCount = totalNum / VoucherConstants.MAX_VOUCHER_DETAIL_COUNT + INT_ONE;
        VoucherCountInfo voucherCountInfo = new VoucherCountInfo();
        voucherCountInfo.setPageCount(pageCount);
        voucherCountInfo.setTotalCount(totalNum);
        return voucherCountInfo;
    }

    private List getVoucherDetails(UserVoucherDetailListQueryParamVo queryParam, String roleType, String dealerCode) {
        List<Integer> batchIds = null;
        //由于前端先有发放方式，才能有发放场景，所以此次只判断发放方式
        if (null != queryParam.getGrantType()) {
            if (VoucherConstants.SELECT_GRANT.equals(queryParam.getGrantType())) {
                List voucherSelectGrantInfo = ossOemVoucherSelectGrantTriggerMapper.selectGrantInfoByQueryParam(null);
                List<VoucherBatchGrantInfoVo> batchSelectGrantInfoVos = CommonConvertUtil.mapListToVOList(voucherSelectGrantInfo, VoucherBatchGrantInfoVo.class);
                batchIds = batchSelectGrantInfoVos.stream().map(VoucherBatchGrantInfoVo::getBatchId).collect(Collectors.toList());
            } else {
                List voucherGrantInfo = ossOemVoucherGrantTriggerMapper.selectByQueryParam(queryParam.getGrantBusinessType(), queryParam.getGrantType(), null);
                List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(voucherGrantInfo, VoucherBatchGrantInfoVo.class);
                batchIds = batchGrantInfoVos.stream().map(VoucherBatchGrantInfoVo::getBatchId).collect(Collectors.toList());
            }
            if (batchIds.isEmpty()) {
                return new ArrayList();
            }
        }
        if (null != queryParam.getCurrentPage() && null != queryParam.getPageSize()) {
            PageHelper.startPage(queryParam.getCurrentPage(), queryParam.getPageSize());
            PageHelper.orderBy("e.id desc");
        }
        List list = ossEtlUserVoucherBatchMappingRecordMapper.selectVoucherDetailListQueryParam(CommonConvertUtil.transBean2Map(queryParam),
                roleType, dealerCode, batchIds);
        return list;
    }

    private  void wrapVoucherDetailsForGrantType(List<UserVoucherDetailVo> userVoucherDetailList) {
        List<Integer> batchIds = userVoucherDetailList.stream().map(UserVoucherDetailVo::getBatchId).distinct().collect(Collectors.toList());
        Map<Integer, Byte> batchIdToGrantType = new HashMap<>();
        Map<Integer, Byte> batchIdToBusinessType = new HashMap<>();
        if (!batchIds.isEmpty()) {
            List voucherGrantInfos = ossOemVoucherGrantTriggerMapper.selectByQueryParam(null, null, batchIds);
            List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(voucherGrantInfos, VoucherBatchGrantInfoVo.class);
            batchGrantInfoVos.forEach(grantInfo -> {
                batchIdToGrantType.put(grantInfo.getBatchId(), grantInfo.getGrantType());
                batchIdToBusinessType.put(grantInfo.getBatchId(), grantInfo.getBusinessType());
            });
            List voucherSelectGrantInfo = ossOemVoucherSelectGrantTriggerMapper.selectGrantInfoByQueryParam(batchIds);
            List<VoucherBatchGrantInfoVo> batchSelectGrantInfoVos = CommonConvertUtil.mapListToVOList(voucherSelectGrantInfo, VoucherBatchGrantInfoVo.class);
            batchSelectGrantInfoVos.forEach(grantInfo -> {
                batchIdToGrantType.put(grantInfo.getBatchId(), grantInfo.getGrantType());
                batchIdToBusinessType.put(grantInfo.getBatchId(), grantInfo.getBusinessType());
            });
            for (UserVoucherDetailVo userVoucherDetailVo : userVoucherDetailList) {
                userVoucherDetailVo.setGrantBusinessType(batchIdToBusinessType.getOrDefault(userVoucherDetailVo.getBatchId(), null));
                userVoucherDetailVo.setGrantType(batchIdToGrantType.getOrDefault(userVoucherDetailVo.getBatchId(), null));
            }
        }
    }

    private Integer countVoucherDetails(UserVoucherDetailListQueryParamVo queryParam, String roleType, String dealerCode) {
        List<Integer> batchIds = null;
        if (null != queryParam.getGrantType()) {
            if (VoucherConstants.SELECT_GRANT.equals(queryParam.getGrantType())) {
                List voucherSelectGrantInfo = ossOemVoucherSelectGrantTriggerMapper.selectGrantInfoByQueryParam(null);
                List<VoucherBatchGrantInfoVo> batchSelectGrantInfoVos = CommonConvertUtil.mapListToVOList(voucherSelectGrantInfo, VoucherBatchGrantInfoVo.class);
                batchIds = batchSelectGrantInfoVos.stream().map(VoucherBatchGrantInfoVo::getBatchId).collect(Collectors.toList());
            } else {
                List voucherGrantInfo = ossOemVoucherGrantTriggerMapper.selectByQueryParam(queryParam.getGrantBusinessType(), queryParam.getGrantType(), null);
                List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(voucherGrantInfo, VoucherBatchGrantInfoVo.class);
                batchIds = batchGrantInfoVos.stream().map(VoucherBatchGrantInfoVo::getBatchId).collect(Collectors.toList());
            }
            if (batchIds.isEmpty()) {
                return INT_ZERO;
            }
        }
        return ossEtlUserVoucherBatchMappingRecordMapper.countVoucherDetailListQueryParam(CommonConvertUtil.transBean2Map(queryParam), roleType, dealerCode, batchIds);
    }

    @Override
    public HSSFWorkbook downloadUserVoucherDetailListForDealer(UserVoucherDetailListQueryParamVo queryParam, AdminUserVO adminUserVO) throws Exception {
        if (isBeyondMaxNumForDealer(queryParam, adminUserVO)) {
            throw new ServiceException(ResultEnum.BEYOND_MAX_EXPORT_NUM);
        }
        List list = getVoucherDetails(queryParam, ShiroConstants.ROLE_DEALER, adminUserVO.getRefId());
        String[] title = VoucherConstants.DOWNLOAD_USER_VOUCHER_DETAIL_LIST_FOR_DEALER_TITLE;
        String[][] values = {};
        if (null != list) {
            values = new String[list.size()][title.length];
            Integer dataNum = list.size();
            List<UserVoucherDetailVo> userVoucherDetailList = CommonConvertUtil.mapListToVOList(list, UserVoucherDetailVo.class);
            warpVoucherDetailInfo(userVoucherDetailList);
            for (Integer row = INT_ZERO; row < dataNum; row++) {
                UserVoucherDetailVo userVoucherDetailVo = userVoucherDetailList.get(row);
                values[row][INT_ZERO] = String.valueOf(row + INT_ONE);
                values[row][INT_ONE] = userVoucherDetailVo.getBatchCode();
                values[row][INT_TWO] = userVoucherDetailVo.getActivityName();
                values[row][INT_THREE] = userVoucherDetailVo.getVoucherName();
                values[row][INT_FOUR] = VoucherConstants.VOUCHER_TYPE_MAP.getOrDefault(userVoucherDetailVo.getVoucherType(), "");
                values[row][INT_FIVE] = VoucherConstants.BUSINESS_TYPE_MAP.getOrDefault(userVoucherDetailVo.getBusinessType(), "");
                values[row][INT_SIX] = userVoucherDetailVo.getUserName();
                values[row][INT_SEVEN] = userVoucherDetailVo.getMobile();
                values[row][INT_EIGHT] = userVoucherDetailVo.getCarMake();
                values[row][INT_NINE] = userVoucherDetailVo.getVin();
                values[row][INT_TEN] = (null == userVoucherDetailVo.getDrawTime()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getDrawTime(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_ELEVEN] = (null == userVoucherDetailVo.getRedeemTime()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getRedeemTime(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_TWELVE] = userVoucherDetailVo.getRedeemCode();
                values[row][INT_THIRTEEN] = userVoucherDetailVo.getWorkSheetNo();
                values[row][INT_FOURTEEN] = VoucherConstants.USER_VOUCHER_STATUS.get(userVoucherDetailVo.getVoucherStatus());
                values[row][INT_FIFTEEN] = (null == userVoucherDetailVo.getExpireTime()) ? ""
                        : DateUtil.dateFormat(userVoucherDetailVo.getExpireTime(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_SIXTEEN] = userVoucherDetailVo.getRedeemValue();
                values[row][INT_SEVENTEEN] = userVoucherDetailVo.getMinimumSpendAmount();
                values[row][INT_EIGHTEEN] = userVoucherDetailVo.getRedeemValue();
                values[row][INT_NINETEEN] = userVoucherDetailVo.getSingleTimeLimitDeductionAmount();
            }
        }

        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook(VoucherConstants.EXPORT_WORKBOOK_NAME, title, values, null, null);
        return workbook;
    }

    @Override
    public HSSFWorkbook downloadVoucherBatchList(VoucherBatchListQueryParamVo voucherBatchListQueryParamVo) throws Exception {
        Map params = CommonConvertUtil.transBean2Map(voucherBatchListQueryParamVo);
        List list = ossVoucherBatchMapper.selectByBatchListQueryParam(params);
        List<VoucherBatchInfoVo> voucherBatchList = CommonConvertUtil.mapListToVOList(list, VoucherBatchInfoVo.class);
        String[] title = VoucherConstants.DOWNLOAD_VOUCHER_BATCH_LIST_TITLE;
        String[][] values = {};
        if (null != voucherBatchList) {
            values = new String[voucherBatchList.size()][title.length];
            Integer dataNum = voucherBatchList.size();
            for (Integer row = INT_ZERO; row < dataNum; row++) {
                VoucherBatchInfoVo voucherBatchInfoVo = voucherBatchList.get(row);
                values[row][INT_ZERO] = String.valueOf(row + INT_ONE);
                values[row][INT_ONE] = voucherBatchInfoVo.getActivityName();
                values[row][INT_TWO] = voucherBatchInfoVo.getVoucherName();
                values[row][INT_THREE] = VoucherConstants.VOUCHER_TYPE_MAP.get(voucherBatchInfoVo.getVoucherType());
                values[row][INT_FOUR] = VoucherConstants.BUSINESS_TYPE_MAP.get(voucherBatchInfoVo.getBusinessType());
                values[row][INT_FIVE] = voucherBatchInfoVo.getBatchCode();
                values[row][INT_SIX] = (null == voucherBatchInfoVo.getDrawExpireDate()) ? ""
                        : DateUtil.dateFormat(voucherBatchInfoVo.getDrawExpireDate(), DateUtil.DATE_TIME_PATTERN);
                values[row][INT_SEVEN] = String.valueOf(voucherBatchInfoVo.getVoucherCount());
                values[row][INT_EIGHT] = String.valueOf(voucherBatchInfoVo.getStockCount());
                values[row][INT_NINE] = (null == voucherBatchInfoVo.getRedeemValue()) ? "" : voucherBatchInfoVo.getRedeemValue();
                values[row][INT_TEN] = (null == voucherBatchInfoVo.getRedeemValue()) ? "" : voucherBatchInfoVo.getRedeemValue();
                values[row][INT_ELEVEN] = voucherBatchInfoVo.getMinimumSpendAmount();
                values[row][INT_TWELVE] = INT_ZERO == voucherBatchInfoVo.getExpiryDateType() ? "固定有效期" : "动态有效期";
                if (INT_ZERO == voucherBatchInfoVo.getExpiryDateType()) {
                    values[row][INT_THIRTEEN] = DateUtil
                        .dateFormat(voucherBatchInfoVo.getRedeemStartTime(),
                            DateUtil.DATE_TIME_PATTERN) + VoucherConstants.STR_NULL + DateUtil
                        .dateFormat(voucherBatchInfoVo.getRedeemEndTime(),
                            DateUtil.DATE_TIME_PATTERN);
                } else {
                    values[row][INT_THIRTEEN] = String
                        .valueOf(voucherBatchInfoVo.getValidForNumberOfDays());
                }
                values[row][INT_FOURTEEN] = VoucherConstants.BATCH_STATUS_MAP.get(voucherBatchInfoVo.getStatus());
                values[row][INT_FIFTEEN] = VoucherConstants.GRANT_TYPE_MAP.getOrDefault(voucherBatchInfoVo.getGrantType(), "");
                values[row][INT_SIXTEEN] = VoucherConstants.GRANT_BUSINESS_TYPE_MAP.getOrDefault(voucherBatchInfoVo.getGrantBusinessType(), "");
                values[row][INT_SEVENTEEN] = String.valueOf(voucherBatchInfoVo.getReceivedCount());
                values[row][INT_EIGHTEEN] = String.valueOf(voucherBatchInfoVo.getUsedCount());
                values[row][INT_NINETEEN] = String.valueOf(voucherBatchInfoVo.getExpiredCount());
                values[row][INT_TWENTY] = INT_ZERO == voucherBatchInfoVo.getIsOverlapable() ? "不可叠加" : "可叠加";

            }
        }

        HSSFWorkbook workbook = ExcelUtil.getHSSFWorkbook("优惠劵批次列表", title, values, null, null);
        return workbook;
    }

    @Override
    public PageInfo getCarMake(Integer page, Integer pageSize) {
        //查询总条数
        Criteria criteria = new Criteria();
        criteria.and("vbrandId").is(V_BRAND_ID);
        criteria.and(VoucherConstants.VEHICLE_SERIES_NAME).exists(true);//过滤为null的车型
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(VoucherConstants.VEHICLE_SERIES_NAME)
        );
        AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(aggregation, VoucherConstants.VEHICLE_SERIES, BasicDBObject.class);
        //查询总数
        int count = outputType.getMappedResults().size();
        //分页查询
        Sort sort = new Sort(Sort.Direction.DESC, VoucherConstants.SORT_ID);
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),//查询条件
                Aggregation.group(VoucherConstants.VEHICLE_SERIES_NAME),//分组字段
                Aggregation.sort(sort),//排序
                Aggregation.skip(page > INT_ONE ? (page.longValue() - INT_ONE) * pageSize : INT_ZERO),//从第几条数据开始取
                Aggregation.limit(pageSize)//查出多少个数据
        );
        AggregationResults<BasicDBObject> out = mongoTemplate.aggregate(agg, VoucherConstants.VEHICLE_SERIES, BasicDBObject.class);
        Object result = out.getRawResults().get("result");
        JSONArray array = JSONArray.fromObject(result);
        List<VehicleSeries> list = new ArrayList<>();
        for (Object obj : array) {
            JSONObject object = JSONObject.fromObject(obj);
            VehicleSeries vehicleSeries = new VehicleSeries();
            vehicleSeries.setVehicleSeriesName(object.getString(VoucherConstants.SORT_ID));
            list.add(vehicleSeries);
        }
        PageInfo info = new PageInfo();
        info.setTotal(count);
        info.setPageSize(pageSize);
        info.setPageNum(page);
        info.setList(list);
        return info;
    }

    public boolean isBeyondMaxVoucherDetailNum(UserVoucherDetailListQueryParamVo queryParam) throws Exception {
        Integer totalNum = countVoucherDetails(queryParam, null, null);
        return totalNum <= VoucherConstants.MAX_EXCEL_EXPORT_NUM;
    }

    public boolean isEmptyVoucherDetail(UserVoucherDetailListQueryParamVo queryParam) throws Exception {
        Integer totalNum = countVoucherDetails(queryParam, null, null);
        return totalNum == INT_ZERO;
    }

    private boolean isBeyondMaxNumForDealer(UserVoucherDetailListQueryParamVo queryParam, AdminUserVO adminUserVO) throws Exception {
        Integer totalNum = countVoucherDetails(queryParam, ShiroConstants.ROLE_DEALER, adminUserVO.getRefId());
        if (totalNum <= VoucherConstants.MAX_EXCEL_EXPORT_NUM) {
            return false;
        }
        return true;
    }

    @Override
    public VoucherGrantUserVo getUserForGrantingVoucher(
        VoucherGrantBySelectParamVo voucherGrantBySelectParamVo) throws Exception {
        Map params = CommonConvertUtil.transBean2Map(voucherGrantBySelectParamVo);
        PageHelper.startPage(voucherGrantBySelectParamVo.getCurrentPage(), voucherGrantBySelectParamVo.getPageSize());
        PageHelper.orderBy(GET_VOUCHER_BATCH_LIST_ORDER_BY);
        VoucherGrantUserVo voucherGrantUserVo = new VoucherGrantUserVo();
        List<EtlUserVehicleMapping> etlUserVehicleMappings = ossEtlUserVehicleMappingMapper.selectUsersForGrantingVoucherByParams(params);
        List<VoucherUserAndVehicleInfoVo> userAndVehicleInfos = new ArrayList<>();
        for (EtlUserVehicleMapping etlUserVehicleMapping : etlUserVehicleMappings) {
            VoucherUserAndVehicleInfoVo userAndVehicleInfo = new VoucherUserAndVehicleInfoVo();
            userAndVehicleInfo.setMakeName(etlUserVehicleMapping.getMakeName());
            userAndVehicleInfo.setMobile(etlUserVehicleMapping.getMobile());
            userAndVehicleInfo.setVin(etlUserVehicleMapping.getVin());
            userAndVehicleInfo.setAidRef(etlUserVehicleMapping.getAidRef());
            userAndVehicleInfos.add(userAndVehicleInfo);
        }

        PageInfo<EtlUserVehicleMapping> pageInfo = new PageInfo<>(etlUserVehicleMappings);
        voucherGrantUserVo.setUserAndVehicleInfos(userAndVehicleInfos);
        voucherGrantUserVo.setTotalCount(pageInfo.getTotal());
        return voucherGrantUserVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer grantVoucherToUsers(VoucherGrantConfigParamVo voucherGrantConfigParamVo, AdminUserVO adminUserVO) throws Exception {
        List<Integer> batchIds = voucherGrantConfigParamVo.getBatchIds();
        if (batchIds.isEmpty()) {
            throw new ServiceException(ResultEnum.ONE_ACTIVITY_PARAMETER_ERROR);
        }
        OemVoucherSelectGrantTrigger selectGrantTrigger = new OemVoucherSelectGrantTrigger();
        //初始化筛选发放配置
        initSelectTrigger(voucherGrantConfigParamVo, selectGrantTrigger);
        //持久化发放配置
        ossOemVoucherSelectGrantTriggerMapper.insertSelective(selectGrantTrigger);
        //持久化发放配置和优惠券批次关联
        initReceiption(batchIds, selectGrantTrigger.getId());
        //持久化筛选发放车型设置
        String makeName = voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getMakeName();
        if (StringUtils.isNotEmpty(makeName)) {
            initCarMake(makeName, selectGrantTrigger.getId());
        }
        //持久化筛选发放车牌设置
        String vehicleNo = voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getVehicleNo();
        if (StringUtils.isNotEmpty(vehicleNo)) {
            initCarNumber(vehicleNo, selectGrantTrigger.getId());
        }
        return selectGrantTrigger.getId();
    }

    private void initCarNumber(String vehicleNo, Integer id) {
        String[] vehicleNos = vehicleNo.split(VoucherConstants.SEPARATOR);
        for (String no : vehicleNos) {
            OemSelectGrantCarNumberMapping carNumberMapping = new OemSelectGrantCarNumberMapping();
            carNumberMapping.setFkSelectGrantId(id);
            carNumberMapping.setCarNumber(no);
            ossOemSelectGrantCarNumberMappingMapper.insert(carNumberMapping);
        }
    }

    private void initCarMake(String makeId, Integer id) {
        String[] makeIds = makeId.split(VoucherConstants.SEPARATOR);
        for (String str : makeIds) {
            OemSelectGrantCarMakeMapping carMakeMapping = new OemSelectGrantCarMakeMapping();
            carMakeMapping.setFkSelectGrantId(id);
            //carMakeMapping.setMakeCode(str);
            carMakeMapping.setMakeName(str);
            ossOemSelectGrantCarMakeMappingMapper.insert(carMakeMapping);
        }
    }

    private void initReceiption(List<Integer> batchIds, Integer id) {
        for (Integer batchId : batchIds) {
            //判断该批次是否存在其它发放配置中
            //由于现在的筛选发放和自动发放、我的福利、取送车的发放配置存在不同的表，所以需要对两张表进行判断
            int count = ossOemVoucherBatchTriggerMappingMapper.selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
            int selectCount = ossOemVoucherBatchSelectTriggerMappingMapper.selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
            if (count > INT_ZERO || selectCount > INT_ZERO) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_TYPE_FAIL);
            }
            OemVoucherBatchSelectTriggerMapping batchSelectTriggerMapping = new OemVoucherBatchSelectTriggerMapping();
            batchSelectTriggerMapping.setFkBatchId(batchId);
            batchSelectTriggerMapping.setFkSelectGrantId(id);
            ossOemVoucherBatchSelectTriggerMappingMapper.insert(batchSelectTriggerMapping);
        }
    }

    private void initSelectTrigger(VoucherGrantConfigParamVo voucherGrantConfigParamVo, OemVoucherSelectGrantTrigger selectGrantTrigger) {
        selectGrantTrigger.setGrantLimitPerTime(voucherGrantConfigParamVo.getProvideNum());//限制发放数量
        selectGrantTrigger
            .setAppMessageNotice(voucherGrantConfigParamVo.getAppMessageNotice());//应用消息提醒
        selectGrantTrigger
            .setPopUpMessageNotice(voucherGrantConfigParamVo.getPopUpMessageNotice());//弹屏提醒
        selectGrantTrigger.setSmsNotice(voucherGrantConfigParamVo.getSmsNotice());//短信提醒
        selectGrantTrigger.setVoucherReceivedMessageContent(
            voucherGrantConfigParamVo.getMessageContent());//消息提醒文案
        try {
            selectGrantTrigger.setVehicleSaleStartDate(DateUtil.dateParse(
                voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getSaleStartTime(),
                DateUtil.DATE_TIME_PATTERN));//销售日期开始
            selectGrantTrigger.setVehicleSaleEndDate(DateUtil.dateParse(
                voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getSaleEndTime(),
                DateUtil.DATE_TIME_PATTERN));//销售日期结束
            selectGrantTrigger.setLastBackToDealerStartTime(DateUtil.dateParse(
                voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getLastBackStartTime(),
                DateUtil.DATE_TIME_PATTERN));//最后入库时间开始
            selectGrantTrigger.setLastBackDealerEndTime(DateUtil.dateParse(
                voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getLastBackEndTime(),
                DateUtil.DATE_TIME_PATTERN));//最后入库时间结束
        } catch (ParseException e) {
            e.printStackTrace();
        }
        selectGrantTrigger.setLastBackDealerMileageMin(
            voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getMileageMin());//最后入库里程数起
        selectGrantTrigger.setLastBackDealerMileageMax(
            voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getMileageMax());//最后入库里程数止
        selectGrantTrigger.setVehicleBindingStatus(
            voucherGrantConfigParamVo.getVoucherGrantBySelectParamVo().getUserType());
    }

    private Map<String, String> getRedeemTime(VoucherBatch voucherBatch) throws Exception {
        Map<String, String> redeemTimeMap = new HashMap<>();
        String redeemStartTime = "";
        String redeemEndTime = "";
        if (voucherBatch.getExpiryDateType().equals(VoucherConstants.FIXED_EXPIRED_DATE)) {
            redeemStartTime = DateUtil.dateFormat(voucherBatch.getRedeemStartTime(), DateUtil.DATE_TIME_PATTERN);
            redeemEndTime = DateUtil.dateFormat(voucherBatch.getRedeemEndTime(), DateUtil.DATE_TIME_PATTERN);
        } else {
            Integer validForNumberOfDays = voucherBatch.getValidForNumberOfDays();
            Date curDay = new Date();
            redeemStartTime = DateUtil.dateFormat(curDay, DateUtil.DATE_PATTERN) + VoucherConstants.DATE_PATTERN_END;
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.DATE, validForNumberOfDays);
            redeemEndTime = DateUtil.dateFormat(ca.getTime(), DateUtil.DATE_PATTERN) + VoucherConstants.DATE_PATTERN_END;
        }
        redeemTimeMap.put(VoucherConstants.REDEEM_START_TIME, redeemStartTime);
        redeemTimeMap.put(VoucherConstants.REDEEM_END_TIME, redeemEndTime);
        return redeemTimeMap;
    }

    private Integer grantVoucherForManyUsers(VoucherBatch voucherBatch, List<String> userIds, JSONArray dealers, VoucherGrantConfigParamVo configParamVo) throws Exception {
        JSONArray voucherUserList = new JSONArray();
        Map<String, String> redeemTimeMap = getRedeemTime(voucherBatch);
        String businessCode = VoucherConstants.BUSSINESS_CODE_TO_CDP_CODE.get(voucherBatch.getBusinessType());
        for (String userId : userIds) {
            JSONObject voucherUserInfo = new JSONObject();
            voucherUserInfo.put(VoucherConstants.AID, userId);
            voucherUserInfo.put("starttime", redeemTimeMap.get(VoucherConstants.REDEEM_START_TIME));
            voucherUserInfo.put("endtime", redeemTimeMap.get(VoucherConstants.REDEEM_END_TIME));
            voucherUserInfo.put("generateNum", configParamVo.getProvideNum());
            voucherUserInfo.put(VoucherConstants.BUSINESS_CODE, businessCode);
            if (null != dealers) {
                voucherUserInfo.put("dealercodeList", dealers);
            }
            voucherUserList.add(voucherUserInfo);
        }
        JSONObject requestParams = new JSONObject();
        requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        requestParams.put("channelName", VoucherConstants.OEM_CHANNEL);
        requestParams.put(VoucherConstants.CDP_GENERATE_ID, voucherBatch.getVoucherTemplateId());
        requestParams.put("ticketUserList", voucherUserList);
        String result = voucherByRunlinCoreFeignClient.ticketGrantDMS(redeemTimeMap);
        JSONObject resultJsonObject = JSONObject.fromObject(result);
        if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            return userIds.size();
        }
        return INT_ZERO;
    }

    private Map<Integer, JSONArray> getBatchLimitedDealersJson(List<VoucherBatch> voucherBatches) throws Exception {
        List<Integer> batchIds = voucherBatches.stream().map(VoucherBatch::getId).collect(Collectors.toList());
        List<VoucherLimitedDealerMapping> dealers = new ArrayList<>();
        if (!batchIds.isEmpty()) {
            dealers = ossVoucherLimitedDealerMappingMapper.selectByBatchIdList(batchIds);
        }
        Map<Integer, JSONArray> batchId2Dealers = new HashMap<>();
        dealers.forEach(dealer -> {
            if (batchId2Dealers.containsKey(dealer.getFkBatchId())) {
                JSONObject dealerCode = new JSONObject();
                dealerCode.put("dealercode", dealer.getServiceCode());
                batchId2Dealers.get(dealer.getFkBatchId()).add(dealerCode);
            } else {
                batchId2Dealers.put(dealer.getFkBatchId(), new JSONArray());
            }
        });
        return batchId2Dealers;
    }

    @Override
    public void updateBatchExpireStatus() {
        List<VoucherBatch> voucherBatches = ossVoucherBatchMapper.selectExpiredBatch();
        if (!voucherBatches.isEmpty()) {
            List<Integer> batchIds = voucherBatches.stream().map(VoucherBatch::getId).collect(Collectors.toList());
            ossVoucherBatchMapper.updateBatchStatusByIdList(batchIds, VoucherConstants.EXPIRED);
        }
    }

    public List<VoucherMonthReportVo> getVoucherMonthReportFiles(Integer year) throws Exception {
        List<VoucherMonthReportVo> voucherMonthReportVos = new ArrayList<>();
        Long beginTime = DateUtil.timeTotimeStamp(year, Calendar.JANUARY,  INT_TWO, INT_ZERO, INT_ZERO, INT_ZERO);
        Long endTime = DateUtil.timeTotimeStamp(year + INT_ONE, Calendar.JANUARY,  INT_ONE, INT_TWENTY_THREE,
            MINUTE, MINUTE);
        List reportFileRecord = ossReportFileMapper.selectReportFileRecord(beginTime, endTime);
        List<VoucherDayReportVo> voucherDayReportVos = CommonConvertUtil
            .mapListToVOList(reportFileRecord, VoucherDayReportVo.class);
        wrapVoucherDayReportVos(voucherDayReportVos, voucherMonthReportVos);
        return voucherMonthReportVos;
    }

    private void wrapVoucherDayReportVos(List<VoucherDayReportVo> voucherDayReportVos, List<VoucherMonthReportVo> voucherMonthReportVos) throws Exception {
        Map<Integer, List<VoucherDayReportVo>> monthToDayReport = new HashMap<>();
        for (VoucherDayReportVo voucherDayReportVo : voucherDayReportVos) {
            Date reportDate = DateUtil.dateAdd(voucherDayReportVo.getCreateTime(), VoucherConstants.YESTERDAY, Boolean.TRUE);
            Integer month = DateUtil.getMonth(reportDate);
            if (!monthToDayReport.containsKey(month)) {
                monthToDayReport.put(month, new ArrayList<>());
            }
            voucherDayReportVo.setDay(DateUtil.getDate(reportDate));
            monthToDayReport.get(month).add(voucherDayReportVo);
        }
        List<Integer> months = new ArrayList<>(monthToDayReport.keySet());
        months.sort((aa, bb) -> aa - bb);
        for (Integer month : months) {
            VoucherMonthReportVo voucherMonthReportVo = new VoucherMonthReportVo();
            voucherMonthReportVo.setMonth(month);
            voucherMonthReportVo.setVoucherDayReportVos(monthToDayReport.get(month));
            voucherMonthReportVos.add(voucherMonthReportVo);
        }
    }

    @Override
    public List<BigAndSmallRegionTreeVo> getDealersOfBigAndSmallRegion() throws Exception {
        List<BigAndSmallRegionTreeVo> bigSmallRegionsAndDealers = new ArrayList<>();
        JSONObject requestParams = new JSONObject();
        requestParams.put("tenantId", VoucherConstants.TENANTID_VW);
        requestParams.put("dealerStatus", VoucherConstants.DEALER_THREE);
        String result = voucherByRunlinCoreFeignClient.querydealerinfo(requestParams);
        if (null == result) {
            log.info("call getDealersOfBigAndSmallRegion fail1 requestParams=" + requestParams);
            throw new ServiceException(ResultEnum.VOUCHER_CDP_RESULT_NULL);
        }
        CdpDealerInfoVo cdpDealerInfoVo = JackJsonUtil.jsonToBean(result, CdpDealerInfoVo.class);
        List<CdpDealerInfoVo.DealerInfoVo> dealerList = cdpDealerInfoVo.getList();
        if (null != dealerList) {
            Map<String, List<DealersTreeVo>> smallRegionToDealers = new HashMap<>();
            Map<String, Map<String, SmallRegionAndDealersTreeVo>> bigRegionToSmallRegions = new HashMap<>();
            Map<String, String> bigRegionToName = new HashMap<>();

            wrapBigSmallDealersMap(dealerList, smallRegionToDealers, bigRegionToSmallRegions, bigRegionToName);
            for (String bigRegionCode : bigRegionToSmallRegions.keySet()) {
                String bigRegionName = bigRegionToName.get(bigRegionCode);
                BigAndSmallRegionTreeVo bigAndSmallRegionTreeVo = new BigAndSmallRegionTreeVo();
                bigAndSmallRegionTreeVo.setBigRegionCode(bigRegionCode);
                bigAndSmallRegionTreeVo.setLabel(bigRegionName);
                List<SmallRegionAndDealersTreeVo> smallRegionAndDealersTreeList = new ArrayList<>();
                Map<String, SmallRegionAndDealersTreeVo> smallRegionAndDealersTreeVos = bigRegionToSmallRegions.get(bigRegionCode);
                for (String smallRegionCode : smallRegionAndDealersTreeVos.keySet()) {
                    SmallRegionAndDealersTreeVo smallRegionAndDealersTreeVo = smallRegionAndDealersTreeVos.get(smallRegionCode);
                    smallRegionAndDealersTreeVo.setChildren(smallRegionToDealers.get(smallRegionCode));
                    smallRegionAndDealersTreeList.add(smallRegionAndDealersTreeVo);
                }
                bigAndSmallRegionTreeVo.setChildren(smallRegionAndDealersTreeList);
                bigSmallRegionsAndDealers.add(bigAndSmallRegionTreeVo);
            }
        }
        return bigSmallRegionsAndDealers;
    }

    private void wrapBigSmallDealersMap(List<CdpDealerInfoVo.DealerInfoVo> dealerList, Map<String, List<DealersTreeVo>> smallRegionToDealers,
                                        Map<String, Map<String, SmallRegionAndDealersTreeVo>> bigRegionToSmallRegions, Map<String, String> bigRegionToName) {
        for (CdpDealerInfoVo.DealerInfoVo object : dealerList) {
            DealersTreeVo dealersTreeVo = new DealersTreeVo();
            dealersTreeVo.setDealerCode(object.getDealerCode());
            dealersTreeVo.setLabel(object.getDealerName());
            dealersTreeVo.setSaleCode(object.getSalesCode());
            String smallRegionCode = object.getSaleSmallRegionCode();
            String bigRegionCode = object.getSaleBigRegionCode();
            String smallRegionName = object.getSaleSmallRegion();

            List<DealersTreeVo> dealersTreeVos = smallRegionToDealers
                .getOrDefault(smallRegionCode, new ArrayList<>());
            dealersTreeVos.add(dealersTreeVo);
            smallRegionToDealers.put(smallRegionCode, dealersTreeVos);

            Map<String, SmallRegionAndDealersTreeVo> smallRegionCodeToTree = bigRegionToSmallRegions
                .getOrDefault(bigRegionCode, new HashMap<>());
            SmallRegionAndDealersTreeVo smallRegionAndDealersTreeVo = new SmallRegionAndDealersTreeVo();
            smallRegionAndDealersTreeVo.setLabel(smallRegionName);
            smallRegionAndDealersTreeVo.setSmallRegionCode(smallRegionCode);
            String bigRegionName = object.getSaleBigRegion();
            smallRegionCodeToTree.put(smallRegionCode, smallRegionAndDealersTreeVo);
            bigRegionToSmallRegions.put(bigRegionCode, smallRegionCodeToTree);
            bigRegionToName.put(bigRegionCode, bigRegionName);
        }
    }

    @Override
    public HSSFWorkbook downloadDirectingGrantTemplate() throws Exception {
        String[] mobileTitle = {VoucherConstants.PHONE};
        String[] vinTitle = {VoucherConstants.VIN_CODE};
        String[][] values = {};

        HSSFWorkbook workbook = ExcelUtil
            .getHSSFWorkbook(VoucherConstants.PHONE, mobileTitle, values, null, null);
        workbook = ExcelUtil
            .getHSSFWorkbook(VoucherConstants.VIN_CODE, vinTitle, values, workbook, null);
        return workbook;
    }

    @Override
    public List<String> uploadMobileTemplate(MultipartFile file) throws Exception {
        Integer sheetIndex = INT_ZERO;
        String[] mobileTitle = {VoucherConstants.PHONE};
        String[][] results = ExcelUtil.getValuesOfHSSFWorkbook(file, mobileTitle, sheetIndex);
        List<String> mobiles = new ArrayList<>();
        if (null == results) {
            return mobiles;
        }
        String regex = "\\d+";
        for (String[] row : results) {
            if (!row[INT_ZERO].matches(regex)) {
                continue;
            }
            mobiles.add(row[INT_ZERO]);
        }
        return mobiles;
    }

    @Override
    public List<String> uploadVinTemplate(MultipartFile file) throws Exception {
        Integer sheetIndex = INT_ONE;
        String[] vinTitle = {VoucherConstants.VIN_CODE};
        String[][] results = ExcelUtil.getValuesOfHSSFWorkbook(file, vinTitle, sheetIndex);
        List<String> vins = new ArrayList<>();
        if (null == results) {
            return vins;
        }
        String regex = "[a-zA-Z0-9]+";
        for (String[] row : results) {
            if (!row[INT_ZERO].matches(regex)) {
                continue;
            }
            vins.add(row[INT_ZERO]);
        }
        return vins;
    }

    private Integer insertOemVoucherGrantTrigger(Integer grantLimitPerTime, Byte grantType,
        Integer importNum, Byte importType) throws Exception {
        OemVoucherGrantTrigger oemVoucherGrantTrigger = new OemVoucherGrantTrigger();
        oemVoucherGrantTrigger.setGrantLimitPerTime(grantLimitPerTime);
        oemVoucherGrantTrigger.setGrantTimesLimit(INT_ONE);
        oemVoucherGrantTrigger.setGrantType(grantType);
        oemVoucherGrantTrigger.setImportNum(importNum);
        oemVoucherGrantTrigger.setImportType(importType);
        oemVoucherGrantTrigger.setActive(VoucherConstants.DISABLE);
        ossOemVoucherGrantTriggerMapper.insertSelective(oemVoucherGrantTrigger);
        return oemVoucherGrantTrigger.getId();
    }

    @Override
    @Async(value = "asyncExecutor")
    public void processMobile(List<String> mobiles, Integer grantLimitPerTime, List<Integer> batchIds, Byte importType) throws Exception {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Integer grantTypeId = insertOemVoucherGrantTrigger(grantLimitPerTime, VoucherConstants.DIRECTING_GRANT, mobiles.size(), importType);
        saveBatchGrant(batchIds, grantTypeId);
        List<OemVoucherGrantRecord> oemVoucherGrantRecords = new ArrayList<>();
        for (String mobile : mobiles) {
            OemVoucherGrantRecord oemVoucherGrantRecord = new OemVoucherGrantRecord();
            oemVoucherGrantRecord.setGranteeType(VoucherConstants.GRANTEE_MOBILE_TYPE);
            oemVoucherGrantRecord.setGrantee(mobile);
            oemVoucherGrantRecord.setFkGrantTypeId(grantTypeId);
            try {
                if (VoucherConstants.MOBILE_PATTERN.matcher(mobile).matches()) {
                    String aId = getAidsByMobiles(mobile);
                    if (null != aId) {
                        oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_SUCESS);
                        oemVoucherGrantRecord.setAidRef(aId);
                    } else {
                        oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_FAIL);
                        oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(VoucherConstants.MOBILE_NOT_REGISTER);
                    }
                } else {
                    oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_FAIL);
                    oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(VoucherConstants.MOBILE_FORMAT_ERROR);
                }
                oemVoucherGrantRecords.add(oemVoucherGrantRecord);
                if (VoucherConstants.MAX_CHECK_NUM <= oemVoucherGrantRecords.size()) {
                    ossOemVoucherGrantRecordMapper.insertOemGrantRecordByBatch(oemVoucherGrantRecords);
                    oemVoucherGrantRecords.clear();
                }
            } catch (Exception e) {
                log.error("processMobile isRegistered fail，mobile : {}", mobile);
                oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_FAIL);
                oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(e.getMessage());
                oemVoucherGrantRecords.add(oemVoucherGrantRecord);
            }
        }
        if (!oemVoucherGrantRecords.isEmpty()) {
            ossOemVoucherGrantRecordMapper.insertOemGrantRecordByBatch(oemVoucherGrantRecords);
        }
        stopWatch.stop();
        log.info("processMobile excute {}ms, grantTypeId: {}", stopWatch.getTotalTimeMillis(), grantTypeId);
    }

    @Override
    @Async(value = "asyncExecutor")
    public void processVin(List<String> vins, Integer grantLimitPerTime, List<Integer> batchIds,
        Byte importType) throws Exception {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Integer groupNum = vins.size() / VoucherConstants.MAX_CHECK_NUM;
        Integer inum = INT_ZERO;
        Integer grantTypeId = insertOemVoucherGrantTrigger(grantLimitPerTime,
            VoucherConstants.DIRECTING_GRANT, vins.size(), importType);
        saveBatchGrant(batchIds, grantTypeId);
        for (inum = INT_ZERO; inum < groupNum; inum++) {
            List<String> subVins = vins.subList(inum * VoucherConstants.MAX_CHECK_NUM,
                (inum + INT_ONE) * VoucherConstants.MAX_CHECK_NUM);
            processSubVins(subVins, grantTypeId);
        }
        List<String> lastSubVins = vins.subList(inum * VoucherConstants.MAX_CHECK_NUM, vins.size());
        if (!lastSubVins.isEmpty()) {
            processSubVins(lastSubVins, grantTypeId);
        }
        stopWatch.stop();
        log.info("processVin excute {}ms, grantTypeId: {}", stopWatch.getTotalTimeMillis(),
            grantTypeId);
    }

    private void processSubVins(List<String> subVins, Integer grantTypeId) throws Exception {
        List<OemVoucherGrantRecord> oemVoucherGrantRecords = new ArrayList<>();
        try {
            Map<String, Long> vinToaId = getRefIdByVinFromMongo(subVins);
            for (String vin : subVins) {
                OemVoucherGrantRecord oemVoucherGrantRecord = new OemVoucherGrantRecord();
                oemVoucherGrantRecord.setGranteeType(VoucherConstants.GRANTEE_VIN_TYPE);
                oemVoucherGrantRecord.setGrantee(vin);
                oemVoucherGrantRecord.setFkGrantTypeId(grantTypeId);
                try {
                    if (vinToaId.containsKey(vin)) {
                        oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_SUCESS);
                        oemVoucherGrantRecord.setAidRef(String.valueOf(vinToaId.get(vin)));
                    } else {
                        String aId = getRefIdByVinFromCdp(vin);
                        if (null != aId) {
                            oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_SUCESS);
                            oemVoucherGrantRecord.setAidRef(aId);
                        } else {
                            oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_FAIL);
                            oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(VoucherConstants.VIN_NOT_BIND);
                        }
                    }
                } catch (Exception e) {
                    log.error("processSubVins fail，e : {} ,vin : {}", e, vin);
                    oemVoucherGrantRecord.setGranteeCheckStatus(VoucherConstants.GRANTEE_CHECK_FAIL);
                    oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(e.getMessage());
                }
                oemVoucherGrantRecords.add(oemVoucherGrantRecord);
            }
            ossOemVoucherGrantRecordMapper.insertOemGrantRecordByBatch(oemVoucherGrantRecords);
            oemVoucherGrantRecords.clear();
        } catch (Exception e) {
            log.error("processSubVins fail，e : {} ,subVins : {}", e, subVins);
            ossOemVoucherGrantRecordMapper.insertOemGrantRecordByBatch(oemVoucherGrantRecords);
            oemVoucherGrantRecords.clear();
        }
    }

    private String getAidsByMobiles(String mobile) {
        List<String> mobiles = new ArrayList<>();
        mobiles.add(mobile);
        Map<String, Object> postParamsMap = new HashMap<>();
        postParamsMap.put(CDPConstants.MOBILES, mobiles);
        postParamsMap.put(CDPConstants.TENANTID, RequestConstants.CDP_MSG_SENDER);
        String result = vehicheByTimaCoreFeignClient.getAidsByMobiles(postParamsMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            if (!object.containsKey(VoucherConstants.RESULT_DATA)) {
                log.error("getAidsByMobiles fail，object : {}, result: {}", mobiles, result);
                return null;
            }
            JSONObject data = object.getJSONObject(VoucherConstants.RESULT_DATA);
            JSONArray aids = data.getJSONArray("aids");
            if (aids.isEmpty()) {
                log.info("getAidsByMobiles not exist，object : {}, result: {}", mobiles, result);
                return null;
            }
            return aids.getString(INT_ZERO);
        }
        return null;
    }

    private Result handleCDPResult(String getOwnerAidByVinResponseStr) {
        Result result = null;
        try {
            result = JackJsonUtil.jsonToBean(getOwnerAidByVinResponseStr, Result.class);
            if (result.getReturnStatus().equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
                return result;
            }
            result = ResultUtils.fail(ResultEnum.getResultEnumByErrorCode(result.getErrorCode()));
            return result;
        } catch (Exception e) {
            log.error("AdminVoucherBatchServiceImpl handleCDPResult erroe:{}", e.getMessage(), e);
        }
        return result;
    }

    private String getRefIdByVinFromCdp(String vin) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.VIN, vin);
        String getOwnerAidByVinResponseStr = vehicheByTimaCoreFeignClient.getOwnerAidByVin(urlParamMap);
        Result getOwnerAidByVinResult = handleCDPResult(getOwnerAidByVinResponseStr);
        if (Objects.isNull(getOwnerAidByVinResult) || Objects.isNull(getOwnerAidByVinResult.getData())) {
            return null;
        }
        LinkedHashMap linkedHashMap = (LinkedHashMap) getOwnerAidByVinResult.getData();
        Object aidObject = linkedHashMap.get(CDPConstants.A_ID);
        if (Objects.isNull(aidObject)) {
            return null;
        }
        return String.valueOf(aidObject);
    }

    private Map<String, Long> getRefIdByVinFromMongo(List<String> vins) {
        Map<String, Long> vinToaId = new HashMap<>();
        Query query = Query.query(Criteria.where(VoucherConstants.VIN).in(vins));
        List<UserVehicle> userVehicles = mongoTemplate
            .find(query, UserVehicle.class, "user_vehicle");

        for (UserVehicle userVehicle : userVehicles) {
            vinToaId.put(userVehicle.getVin(), userVehicle.getaId());
        }
        return vinToaId;
    }

    @Override
    public boolean isAlreadyExist(List<Integer> batchIds) {
        for (Integer batchId : batchIds) {
            //同批次的券只能存在一种发放方式
            int count = ossOemVoucherBatchTriggerMappingMapper
                .selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
            int selectCount = ossOemVoucherBatchSelectTriggerMappingMapper
                .selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
            if (count > INT_ZERO || selectCount > INT_ZERO) {
                return true;
            }
        }
        return false;
    }

    private void saveBatchGrant(List<Integer> batchIds, Integer grantTypeId) {
        for (Integer batchId : batchIds) {
            OemVoucherBatchTriggerMapping batchTriggerMapping = new OemVoucherBatchTriggerMapping();
            batchTriggerMapping.setFkBatchId(batchId);
            batchTriggerMapping.setFkGrantTypeId(grantTypeId);
            batchTriggerMapping.setCreateTime(new Date());
            ossOemVoucherBatchTriggerMappingMapper.insertSelective(batchTriggerMapping);
        }
    }

    public DirectingGrantResultListVo getDirectingGrantResults(
        DirectingGrantResultRequest requestParam) throws Exception {
        PageHelper.startPage(requestParam.getCurrentPage(), requestParam.getPageSize());
        PageHelper.orderBy("g.id desc");
        List<Map<String, Object>> list = ossOemVoucherGrantTriggerMapper
            .selectDirectingGrantResults(requestParam.getImportType(), requestParam.getIsGrant(),
                requestParam.getImportStartTime(), requestParam.getImportEndTime());
        List<DirectingGrantResultVo> directingGrantResultVos = CommonConvertUtil
            .mapListToVOList(list, DirectingGrantResultVo.class);
        wrapDirectGrantResult(directingGrantResultVos);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        DirectingGrantResultListVo directingGrantResultListVo = new DirectingGrantResultListVo();
        directingGrantResultListVo.setTotalCount(pageInfo.getTotal());
        directingGrantResultListVo.setDirectingGrantResultVos(directingGrantResultVos);
        return directingGrantResultListVo;
    }

    private void wrapDirectGrantResult(List<DirectingGrantResultVo> directingGrantResultVos) {
        List<Integer> grantTypeIds = directingGrantResultVos.stream().map(DirectingGrantResultVo::getId).collect(Collectors.toList());
        if (grantTypeIds.isEmpty()) {
            return;
        }
        //TODO：先保留注释掉的代码
        List<Map<String, Object>> checkSucessCounts = ossOemVoucherGrantRecordMapper.selectCheckSuccessRecordCount(grantTypeIds);
        List<Map<String, Object>> checkFailCounts = ossOemVoucherGrantRecordMapper.selectCheckFailRecordCount(grantTypeIds);
        List<Map<String, Object>> sendSmsSucessCounts = ossOemVoucherGrantRecordMapper.selectSendSmsSuccessRecordCount(grantTypeIds);
        //List<Map<String, Object>> sendSmsFailCounts = ossOemVoucherGrantRecordMapper.selectSendSmsFailRecordCount(grantTypeIds);
        List<DirectingGrantRecordCountVo> checkSucessCountList = CommonConvertUtil.mapListToVOList(checkSucessCounts, DirectingGrantRecordCountVo.class);
        List<DirectingGrantRecordCountVo> checkFailCounttList = CommonConvertUtil.mapListToVOList(checkFailCounts, DirectingGrantRecordCountVo.class);
        List<DirectingGrantRecordCountVo> sendSmsSucessCounttList = CommonConvertUtil.mapListToVOList(sendSmsSucessCounts, DirectingGrantRecordCountVo.class);
        //List<DirectingGrantRecordCountVo> sendSmsFailCountList = CommonConvertUtil.mapListToVOList(sendSmsFailCounts, DirectingGrantRecordCountVo.class);
        Map<Integer, DirectingGrantRecordCountVo> grantTypeIdToCheckSucessCount = StreamHelper
            .convertListToMap(checkSucessCountList, DirectingGrantRecordCountVo::getGrantTypeId);
        Map<Integer, DirectingGrantRecordCountVo> grantTypeIdToCheckFailCount = StreamHelper.convertListToMap(checkFailCounttList, DirectingGrantRecordCountVo::getGrantTypeId);
        Map<Integer, DirectingGrantRecordCountVo> grantTypeIdToSendSmsSucessCount = StreamHelper.convertListToMap(sendSmsSucessCounttList, DirectingGrantRecordCountVo::getGrantTypeId);
        //Map<Integer, DirectingGrantRecordCountVo> grantTypeIdToSendSmsFailCount = StreamHelper.convertListToMap(sendSmsFailCountList, DirectingGrantRecordCountVo::getGrantTypeId);

        for (DirectingGrantResultVo directingGrantResultVo : directingGrantResultVos) {
            Integer sendSmsSucessCount = INT_ZERO;
            //Integer sendSmsFailCount = 0;
            Integer checkSucessCount = INT_ZERO;
            Integer checkFailCount = INT_ZERO;
            if (grantTypeIdToCheckSucessCount.containsKey(directingGrantResultVo.getId())) {
                checkSucessCount = grantTypeIdToCheckSucessCount.get(directingGrantResultVo.getId()).getRecordCount();
            }
            if (grantTypeIdToCheckFailCount.containsKey(directingGrantResultVo.getId())) {
                checkFailCount = grantTypeIdToCheckFailCount.get(directingGrantResultVo.getId()).getRecordCount();
            }
            if (grantTypeIdToSendSmsSucessCount.containsKey(directingGrantResultVo.getId())) {
                sendSmsSucessCount = grantTypeIdToSendSmsSucessCount.get(directingGrantResultVo.getId()).getRecordCount();
            }
            //if (grantTypeIdToSendSmsFailCount.containsKey(directingGrantResultVo.getId())) {
            //    sendSmsFailCount = grantTypeIdToSendSmsFailCount.get(directingGrantResultVo.getId()).getRecordCount();
            //}
            directingGrantResultVo.setCheckSucessNum(checkSucessCount);
            //directingGrantResultVo.setCheckFailNum(checkFailCount);
            directingGrantResultVo.setCheckFailNum(directingGrantResultVo.getImportNum() - checkSucessCount);
            directingGrantResultVo.setSendSmsSucessNum(sendSmsSucessCount);
            directingGrantResultVo.setSendSmsFailNum(directingGrantResultVo.getImportNum() - sendSmsSucessCount);
            //directingGrantResultVo.setSendSmsFailNum(sendSmsFailCount);
            //directingGrantResultVo.setNoSendSmsNum(directingGrantResultVo.getImportNum() - sendSmsSucessCount - sendSmsFailCount);
            if (directingGrantResultVo.getImportNum() > INT_ZERO) {
                directingGrantResultVo.setImportProgress((checkSucessCount + checkFailCount) * ONE_HUNDRED / directingGrantResultVo.getImportNum());
            }

            if (!directingGrantResultVo.getImportProgress().equals(VoucherConstants.IMPORT_GRPGRESS_FINISH)
                    && DateUtil.dateDiff(new Date(), directingGrantResultVo.getImportTime()) > VoucherConstants.ONE_DAY) {
                directingGrantResultVo.setImportProgress(VoucherConstants.IMPORT_GRPGRESS_FINISH);
            }
        }
    }

    @Override
    public SXSSFWorkbook downloadDirectingGranteeResult(Integer grantRecordId) throws Exception {
        SXSSFWorkbook workbook = null;
        List<OemVoucherGrantRecord> oemVoucherGrantRecords = ossOemVoucherGrantRecordMapper.selectCheckFailRecords(grantRecordId);
        String[] title = VoucherConstants.DOWNLOAD_DIRECTING_GRANTEE_RESULT_TITLE;
        if (!oemVoucherGrantRecords.isEmpty()) {
            if (oemVoucherGrantRecords.get(INT_ZERO).getGranteeType().equals(VoucherConstants.MOBILE_TYPE)) {
                title[INT_ZERO] = VoucherConstants.PHONE;
            }
        }
        String[][] values = new String[oemVoucherGrantRecords.size()][title.length];
        Integer index = INT_ZERO;
        for (OemVoucherGrantRecord oemVoucherGrantRecord : oemVoucherGrantRecords) {
            values[index][INT_ZERO] = oemVoucherGrantRecord.getGrantee();
            values[index][INT_ONE] = VoucherConstants.GRANTEE_CHECK_STATUS.getOrDefault(oemVoucherGrantRecord.getGranteeCheckStatus(), "");
            values[index][INT_TWO] = oemVoucherGrantRecord.getGranteeCheckStatusFailedReason();
            index++;
        }

        return ExcelUtil.exportSXSSFWorkbook("检测结果", title, values, workbook, VoucherConstants.INIT_OFFSET, VoucherConstants.FIRST_SHEET, true);
    }

    public SXSSFWorkbook downloadSendSmsResult(Integer grantRecordId) throws Exception {
        SXSSFWorkbook workbook = null;
        List<OemVoucherGrantRecord> oemVoucherGrantRecords = ossOemVoucherGrantRecordMapper.selectSmsSendFailRecords(grantRecordId);
        String[] title = VoucherConstants.DOWNLOAD_DIRECTING_GRANTEE_RESULT_TITLE;
        if (!oemVoucherGrantRecords.isEmpty()) {
            if (oemVoucherGrantRecords.get(INT_ZERO).getGranteeType().equals(VoucherConstants.MOBILE_TYPE)) {
                title[INT_ZERO] = VoucherConstants.PHONE;
            }
        }
        String[][] values = new String[oemVoucherGrantRecords.size()][title.length];
        Integer index = INT_ZERO;
        for (OemVoucherGrantRecord oemVoucherGrantRecord : oemVoucherGrantRecords) {
            values[index][INT_ZERO] = oemVoucherGrantRecord.getGrantee();
            values[index][INT_ONE] = VoucherConstants.SEND_SMS_STATUS.getOrDefault(oemVoucherGrantRecord.getSmsSendStatus(), "");
            values[index][INT_TWO] = oemVoucherGrantRecord.getGranteeCheckStatusFailedReason();
            index++;
        }

        return ExcelUtil.exportSXSSFWorkbook("发送结果", title, values, workbook, VoucherConstants.INIT_OFFSET, VoucherConstants.FIRST_SHEET, true);
    }


    @Override
    public Boolean oemWithDrawGrantVoucher(Integer recordId, AdminUserVO adminUserVO) {
        //获取待撤回的发放记录
        VoucherBatchDealerGrantRecord record = ossVoucherBatchDealerGrantRecordMapper.selectByPrimaryKey(recordId);
        //获取卡券模版ID
        VoucherBatch batch = ossVoucherBatchMapper.selectByPrimaryKey(record.getFkBatchId());
        //获取用户该批次所有的发放记录
        List<VoucherDealerGrantRecord> recordList = ossVoucherDealerGrantRecordMapper.queryByfkBatchGrantRecordId(record.getId());
        //TODO 目前需要对套餐券进行批次撤回，不能单个卡券进行撤回，所以需要查询该用户购买的该批次所有的卡券信息，
        //TODO  但是现在存在一个问题是用户是否会多次购买同一批次套餐券，目前产品反馈的是不太可能存在这种情况，所以现在暂时不考虑这种情况
        //TODO 如果以后发生了这种情况，需要在撤回操作中进行修改
        List<VoucherWithDrawInfoVo> list = new ArrayList<>();
        //发送到cdp获取该次发放的优惠券信息并判断该发放是否能够撤回
        if (ossVoucherCommonService.isGrantInfo(recordList, batch.getVoucherTemplateId(), list)) {
            //执行卡券作废操作
            ossVoucherCommonService.voucherInvalid(recordList);
            record.setStatus(VoucherConstants.ALREADY_WITH_OUT_GRANT);
            ossVoucherBatchDealerGrantRecordMapper.updateByPrimaryKeySelective(record);
        } else {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public PageInfo<VoucherDealerGrantRecordInfoVo> grantRecordList(AdminUserVO adminUserVO, String vin, String phone, int pageSize, int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        PageHelper.orderBy("r.id desc");
        //获取发放记录列表
        List<Map<String, Object>> maps = ossVoucherBatchDealerGrantRecordMapper.grantBatchRecordList(vin, phone, null);
        List<VoucherDealerGrantRecordInfoVo> list = CommonConvertUtil.mapListToVOList(maps, VoucherDealerGrantRecordInfoVo.class);
        PageInfo<VoucherDealerGrantRecordInfoVo> pageInfo = new PageInfo<>(list);
        PageInfo<Map<String, Object>> pageInfoAll = new PageInfo<>(maps);
        pageInfo.setTotal(pageInfoAll.getTotal());
        return pageInfo;
    }

    public PageInfo<VoucherReportFileVo> getRecordFiles(String reportName, String adminUserName, int pageSize, int currentPage) throws Exception {
        PageHelper.startPage(currentPage, pageSize);
        PageHelper.orderBy(GET_VOUCHER_BATCH_LIST_ORDER_BY);
        //获取发放记录列表
        List<Map<String, Object>> reportFiles = ossReportFileMapper.selectReportFileRecordBySearchParam(adminUserName, reportName);
        List<VoucherReportFileVo> list = CommonConvertUtil.mapListToVOList(reportFiles, VoucherReportFileVo.class);
        PageInfo<VoucherReportFileVo> pageInfo = new PageInfo<>(list);
        PageInfo<Map<String, Object>> pageInfoAll = new PageInfo<>(reportFiles);
        pageInfo.setTotal(pageInfoAll.getTotal());
        return pageInfo;
    }

    @Override
    public SXSSFWorkbook downloadThirdpartCodeTemplate() throws Exception {
        String[] redeemCodeTitle = VoucherConstants.DOWNLOAD_THIRD_PART_CODE_TEMPLATE_TITLE;
        String[][] values = {};
        SXSSFWorkbook workbook = null;

        return ExcelUtil.exportSXSSFWorkbook("异业码", redeemCodeTitle, values, workbook, VoucherConstants.INIT_OFFSET, VoucherConstants.FIRST_SHEET, true);
    }

    @Override
    public SXSSFWorkbook downloadThirdpartShopTemplate() throws Exception {
        String[] shopTitle = {"序号", "城市", "异业商家店名", "异业商家地址"};
        String[][] values = {};
        SXSSFWorkbook workbook = null;

        return ExcelUtil.exportSXSSFWorkbook("商家列表", shopTitle, values, workbook, VoucherConstants.INIT_OFFSET, VoucherConstants.FIRST_SHEET, true);
    }

    @Override
    public void checkThirdpartVoucherBatch(Integer batchId) {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(batchId);
        if (Objects.isNull(voucherBatch)) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_NOT_EXIST);
        }
        if (!VoucherConstants.GENERATE.equals(voucherBatch.getStatus())) {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.VOUCHER_TEMPLATE_NOT_GENERATE);
        }
        if (!VoucherConstants.CODE_TYPE_THIRD.equals(voucherBatch.getThirdpartCodeType())) {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.VOUCHER_BATCH_NOT_SUPPORT_IMPORT);
        }
        Integer totalCount = ossThirdpartVoucherMappingMapper.countBybatchIdAndStatus(batchId, VoucherConstants.SUCCESS);
        if (totalCount >= voucherBatch.getVoucherCount()) {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.VOUCHER_COUNT_BEYOND);
        }
    }

    @Override
    @Async(value = "asyncExecutor")
    public void importThirdpartCodes(Integer batchId) throws Exception {
        try {
            importRedeemCodeToCdp(batchId, VoucherConstants.NOT_CHECK);
            importRedeemCodeToCdp(batchId, VoucherConstants.FAIL);
        } catch (Exception e) {
            log.error(String.format("importThirdpartCodes fail batchId=%d e=%s", batchId, e.toString()));
        }
    }

    @Override
    public void readThirdpartCodes(MultipartFile file, Integer batchId) throws Exception {
        AnalysisEventListener<ThirdpartCodeExcelVo> thirdpartCodeListener = EasyExcelUtils.getListener(
                this.batchInsertThirdpartCodes(batchId), VoucherConstants.MAX_BATCH_COUNT);
        EasyExcelUtils.readExcel(file.getInputStream(), ThirdpartCodeExcelVo.class, thirdpartCodeListener);
    }

    private Consumer<List<ThirdpartCodeExcelVo>> batchInsertThirdpartCodes(Integer batchId) {
        return codes -> {
            List<ThirdpartVoucherMapping> records = new ArrayList<>();
            for (ThirdpartCodeExcelVo thirdpartCodeExcelVo : codes) {
                ThirdpartVoucherMapping record = new ThirdpartVoucherMapping();
                record.setFkBatchId(batchId);
                record.setRedeemCode(thirdpartCodeExcelVo.getThirdpartRedeemCode());
                record.setCodeType(VoucherConstants.CODE_TYPE_THIRD);
                records.add(record);
            }
            ossThirdpartVoucherMappingMapper.batchInsertThirdpartCodes(records);
        };
    }

    private void importRedeemCodeToCdp(Integer batchId, Byte importStatus) {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(batchId);
        JSONObject requestParams = new JSONObject();
        requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        requestParams.put(VoucherConstants.CDP_GENERATE_ID, voucherBatch.getVoucherTemplateId());
        List<ThirdpartVoucherMapping> thirdpartVouchers = new ArrayList<>();
        Integer totalCount = ossThirdpartVoucherMappingMapper.countBybatchIdAndStatus(batchId, importStatus);
        Integer pageNum = totalCount / VoucherConstants.MAX_IMPORT_CODE_COUNT + INT_ONE;
        List<Integer> ids = new ArrayList<>();

        for (Integer currentPage = INT_ONE; currentPage <= pageNum; currentPage++) {
            try {
                PageHelper.startPage(VoucherConstants.DEFAULT_PAGE, VoucherConstants.MAX_IMPORT_CODE_COUNT);
                PageHelper.orderBy("id");
                thirdpartVouchers = ossThirdpartVoucherMappingMapper.selectBybatchIdAndStatus(batchId, importStatus);
                if (thirdpartVouchers.isEmpty()) {
                    return;
                }
                JSONArray redeemCodeList = new JSONArray();
                for (ThirdpartVoucherMapping thirdpartVoucher : thirdpartVouchers) {
                    if (VoucherConstants.SUCCESS.equals(thirdpartVoucher.getImportStatus())) {
                        continue;
                    }
                    JSONObject voucherInfo = new JSONObject();
                    voucherInfo.put(VoucherConstants.REDEEM_CODE, thirdpartVoucher.getRedeemCode());
                    redeemCodeList.add(voucherInfo);
                    ids.add(thirdpartVoucher.getId());
                }
                if (redeemCodeList.isEmpty()) {
                    continue;
                }
                requestParams.put("differentCodeList", redeemCodeList);
                String result = voucherByRunlinCoreFeignClient.importRedeemcode(requestParams);
                JSONObject resultJsonObject = JSONObject.fromObject(result);
                if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                    log.error(String.format("first call importRedeemCodeToCdp fail batchId=%d importStatus=%d result=%s  param=%s ", batchId, importStatus, result, requestParams.toString()));
                    Thread.sleep(THREAD_SLEEP_TIME);
                    result = voucherByRunlinCoreFeignClient.importRedeemcode(requestParams);
                    resultJsonObject = JSONObject.fromObject(result);
                }
                if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                    log.error(String.format("retry call importRedeemCodeToCdp fail batchId=%d importStatus=%d result=%s  param=%s ", batchId, importStatus, result, requestParams.toString()));
                    ossThirdpartVoucherMappingMapper.updateImportStatusByIds(ids, VoucherConstants.FAIL, (String) resultJsonObject.get(VoucherConstants.RETURN_MESSAGE));
                    log.info(String.format("failUpdateDbImportRedeemCodeToCdp batchId=%d importStatus=%d currentPage=%d pageNum=%d", batchId, importStatus, currentPage, pageNum));
                } else {
                    ossThirdpartVoucherMappingMapper.updateImportStatusByIds(ids, VoucherConstants.SUCCESS, null);
                    log.info(String.format("successUpdateDbImportRedeemCodeToCdp batchId=%d importStatus=%d currentPage=%d pageNum=%d", batchId, importStatus, currentPage, pageNum));
                }
                ids.clear();
            } catch (Exception e) {
                log.error(String.format("in loop importRedeemCodeToCdp fail batchId=%d importStatus=%d e=%s ", batchId, importStatus, e.toString()));
            }
        }
    }

    @Override
    public void importThirdpartShops(MultipartFile file, Integer batchId) throws Exception {
        ossVoucherLimitedDealerMappingMapper.deleteDealersByBatchId(batchId);
        AnalysisEventListener<ThirdpartShopExcelVo> thirdpartShopListener = EasyExcelUtils.getListener(
                this.batchInsertThirdpartShops(batchId), VoucherConstants.MAX_BATCH_COUNT);
        EasyExcelUtils.readExcel(file.getInputStream(), ThirdpartShopExcelVo.class, thirdpartShopListener);
    }

    private Consumer<List<ThirdpartShopExcelVo>> batchInsertThirdpartShops(Integer batchId) {
        Result<List<Areas>> allAreas = sysAreaCoreFeignClient.getAllAreas();
        List<Areas> areasList = null;
        if (allAreas.getReturnStatus()
            .equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            areasList = allAreas.getData();
        } else {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.GET_AREAS_ERROR);
        }
        Map<String, Areas> areaNameToCode = areasList.stream().collect(Collectors.toMap(Areas::getName, a -> a, (k1, k2) -> k1));
        return shops -> {
            List<VoucherLimitedDealerMapping> records = new ArrayList<>();
            for (ThirdpartShopExcelVo thirdpartShopExcelVo : shops) {
                VoucherLimitedDealerMapping record = new VoucherLimitedDealerMapping();
                record.setFkBatchId(batchId);
                record.setUpdateTime(new Date());
                record.setIsThirdpart(VoucherConstants.THIRDPART_SHOP);
                if (!areaNameToCode.containsKey(thirdpartShopExcelVo.getCity())) {
                    ServiceExceptionUtil
                        .throwServiceException(VoucherErrorEnum.CITY_NAME_FORMAT_ERROR);
                }
                record.setCity(areaNameToCode.get(thirdpartShopExcelVo.getCity()).getCode());
                record.setShopName(thirdpartShopExcelVo.getShopName());
                record.setShopAddress(thirdpartShopExcelVo.getShopAddress());
                records.add(record);
            }
            ossVoucherLimitedDealerMappingMapper.batchInsertThirdpartShops(records);
        };
    }

    @Override
    public List<ThirdpartCodeFailExcelVo> getFailThirdpartCodes(Integer batchId) {
        List<ThirdpartVoucherMapping> thirdpartVouchers = ossThirdpartVoucherMappingMapper.selectBybatchIdAndStatus(batchId, VoucherConstants.FAIL);
        List<ThirdpartVoucherMapping> notImportThirdpartVouchers = ossThirdpartVoucherMappingMapper.selectBybatchIdAndStatus(batchId, VoucherConstants.NOT_CHECK);
        thirdpartVouchers.addAll(notImportThirdpartVouchers);
        List<ThirdpartCodeFailExcelVo> failThirdpartCodes = new ArrayList<>();
        Integer index = INT_ONE;
        for (ThirdpartVoucherMapping voucher : thirdpartVouchers) {
            ThirdpartCodeFailExcelVo thirdpartCodeFailExcelVo = new ThirdpartCodeFailExcelVo();
            thirdpartCodeFailExcelVo.setIndex(index);
            thirdpartCodeFailExcelVo.setRedeemCode(voucher.getRedeemCode());
            failThirdpartCodes.add(thirdpartCodeFailExcelVo);
            index++;
        }
        return failThirdpartCodes;
    }
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-11 zhangyunjiao create
 */