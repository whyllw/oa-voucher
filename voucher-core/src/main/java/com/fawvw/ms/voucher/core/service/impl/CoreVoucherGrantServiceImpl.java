package com.fawvw.ms.voucher.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.fawvw.ms.oa.core.constants.CDPConstants;
import com.fawvw.ms.oa.core.constants.RequestConstants;
import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.redis.redisson.RedissonService;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.result.ResultUtils;
import com.fawvw.ms.oa.core.utils.CommonConvertUtil;
import com.fawvw.ms.oa.core.utils.JackJsonUtil;
import com.fawvw.ms.oa.core.utils.ServiceExceptionUtil;
import com.fawvw.ms.oneappserver.vo.qanda.UserVo;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantRecord;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantTrigger;
import com.fawvw.ms.voucher.basedao.model.OemVoucherSelectGrantTrigger;
import com.fawvw.ms.voucher.basedao.model.OemVoucherUserSelectTriggerReceiptionMapping;
import com.fawvw.ms.voucher.basedao.model.OemVoucherUserTriggerReceiptionMapping;
import com.fawvw.ms.voucher.basedao.model.OperationRedisUser;
import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import com.fawvw.ms.voucher.basedao.model.VoucherLimitedDealerMapping;
import com.fawvw.ms.voucher.basedomain.vo.VoucherLimitedPartsMapping;
import com.fawvw.ms.voucher.basedao.model.voucher.UserVehicle;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.enums.result.VoucherErrorEnum;
import com.fawvw.ms.voucher.basedomain.vo.DealerCodeVo;
import com.fawvw.ms.voucher.basedomain.vo.DealerInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.PartInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.SendVoucherBatchGrantVo;
import com.fawvw.ms.voucher.basedomain.vo.SendVoucherInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartShopResultVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartShopVo;
import com.fawvw.ms.voucher.basedomain.vo.TicketUserVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchGrantInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchMyWelfareInfoListVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchMyWelfareInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherPopUpMessageInfo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherRedPacketVo;
import com.fawvw.ms.voucher.basedomain.vo.req.MessageType;
import com.fawvw.ms.voucher.basedomain.vo.req.MessageVoucherContent;
import com.fawvw.ms.voucher.basedomain.vo.req.SendVoucherMessageVo;
import com.fawvw.ms.voucher.baseservice.client.message.core.MessageCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.client.runlin.VoucherByRunlinCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.client.tima.VehicheByTimaCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.util.DateUtil;
import com.fawvw.ms.voucher.core.mapper.CoreOemVoucherBatchSelectTriggerMappingMapper;
import com.fawvw.ms.voucher.core.mapper.CoreOemVoucherGrantRecordMapper;
import com.fawvw.ms.voucher.core.mapper.CoreOemVoucherGrantTriggerMapper;
import com.fawvw.ms.voucher.core.mapper.CoreOemVoucherSelectGrantTriggerMapper;
import com.fawvw.ms.voucher.core.mapper.CoreOemVoucherUserSelectTriggerReceiptionMappingMapper;
import com.fawvw.ms.voucher.core.mapper.CoreOemVoucherUserTriggerReceiptionMappingMapper;
import com.fawvw.ms.voucher.core.mapper.CoreVoucherBatchMapper;
import com.fawvw.ms.voucher.core.mapper.CoreVoucherLimitedDealerMappingMapper;
import com.fawvw.ms.voucher.core.mapper.CoreVoucherLimitedPartsMappingMapper;
import com.fawvw.ms.voucher.core.service.CoreVoucherGrantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CoreVoucherGrantServiceImpl implements CoreVoucherGrantService {
    @Autowired
    private CoreOemVoucherGrantTriggerMapper coreOemVoucherGrantTriggerMapper;
    @Autowired
    private CoreOemVoucherUserTriggerReceiptionMappingMapper coreOemVoucherUserTriggerReceiptionMappingMapper;
    @Autowired
    private CoreVoucherBatchMapper coreVoucherBatchMapper;
    @Autowired
    private CoreVoucherLimitedDealerMappingMapper coreVoucherLimitedDealerMappingMapper;
    @Autowired
    private CoreVoucherLimitedPartsMappingMapper coreVoucherLimitedPartsMappingMapper;
    @Autowired
    private CoreOemVoucherBatchSelectTriggerMappingMapper coreOemVoucherBatchSelectTriggerMappingMapper;
    @Autowired
    private CoreOemVoucherUserSelectTriggerReceiptionMappingMapper coreOemVoucherUserSelectTriggerReceiptionMappingMapper;
    @Autowired
    private CoreOemVoucherSelectGrantTriggerMapper coreOemVoucherSelectGrantTriggerMapper;
    @Autowired
    private CoreOemVoucherGrantRecordMapper coreOemVoucherGrantRecordMapper;
    @Autowired
    private MessageCoreFeignClient messageCoreFeignClient;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private VoucherByRunlinCoreFeignClient voucherByRunlinCoreFeignClient;
    @Autowired
    private VehicheByTimaCoreFeignClient vehicheByTimaCoreFeignClient;
    @Autowired
    private RedissonService redissonService;

    @Value(value = "${img.message.voucher-pic}")
    String voucherPic;
    @Value(value = "${voucher.card-ticket-Key}")
    String cardTicketKey;

    @Override
    public VoucherBatchMyWelfareInfoListVo
        voucherGrantList(UserVo userVo, Byte grantType, int pageSize, int currentPage,
        String createTime) throws Exception {
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String, Object>> list = new ArrayList<>();
        List<Byte> statusList = new ArrayList<>();
        if (VoucherConstants.TAKE_THE_CAR.equals(grantType)) {
            //查询已生成未领取
            statusList.add(VoucherConstants.GENERATE);
            PageHelper.orderBy(VoucherConstants.VOUCHER_GRANT_LIST_ORDER_ONE);
            list = coreOemVoucherGrantTriggerMapper.voucherGrantList(grantType,
                VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, statusList, createTime,
                VoucherConstants.REDEEM_NOT_IN_DMS);
        } else {
            //查询已生成未领取，已过期
            statusList.add(VoucherConstants.GENERATE);
            if (VoucherConstants.MYWELFARE.equals(grantType)) {
                statusList.add(VoucherConstants.EXPIRED);
            }
            PageHelper.orderBy(VoucherConstants.VOUCHER_GRANT_LIST_ORDER_TWO);
            list = coreOemVoucherGrantTriggerMapper.voucherGrantList(grantType,
                VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, statusList, createTime, null);
        }
        log.info(VoucherConstants.VOUCHER_GRANT_LIST_NAME + list);
        List<VoucherBatchMyWelfareInfoVo> batchGrantInfoVos = CommonConvertUtil
            .mapListToVOList(list, VoucherBatchMyWelfareInfoVo.class);
        List<VoucherBatchMyWelfareInfoVo> result = new ArrayList<>();
        //筛选出用户不能领取的优惠券
        for (VoucherBatchMyWelfareInfoVo batchGrantInfoVo : batchGrantInfoVos) {
            wrapBatchInfo(batchGrantInfoVo);
            //第一步：如果是取送车优惠券列表，则维修保养及保养的业务类型要排除
            if (VoucherConstants.TAKE_THE_CAR.equals(grantType)) {
                if (VoucherConstants.MAINTENANCE.equals(batchGrantInfoVo.getBusinessType())
                    || VoucherConstants.SERVICE_MAINTENANCE
                    .equals(batchGrantInfoVo.getBusinessType())) {
                    continue;
                }
                if (batchGrantInfoVo.getIsDealerLimited().equals(VoucherConstants.SOME_DEALERS)
                    && coreVoucherLimitedDealerMappingMapper.countByBatchIdAndDealerCode(batchGrantInfoVo.getBatchId()) == 0) {
                    continue;
                }
            }
            if (VoucherConstants.MYWELFARE.equals(grantType)
                && VoucherConstants.EXPIRED.equals(batchGrantInfoVo.getBatchStatus())) {
                VoucherBatchMyWelfareInfoVo myWelfareInfoVo = new VoucherBatchMyWelfareInfoVo();
                BeanUtils.copyProperties(batchGrantInfoVo, myWelfareInfoVo);
                result.add(myWelfareInfoVo);
                continue;
            }
            //第二步：判断该批次卡券是否还有库存，库存是否满足给用户待发放的数量，
            // 如果有库存，但不满足给用户发放的数量，则需要设置发放数量
            int stock = isStock(batchGrantInfoVo);
            if (stock < 1) {
                continue;
            }
            if (!VoucherConstants.THIRDPART_VOUCHER.equals(batchGrantInfoVo.getVoucherType())) {
                //获取限制经销商列表
                queryDealerList(batchGrantInfoVo);
                queryPartInfoList(batchGrantInfoVo);
            } else {
                batchGrantInfoVo.setDealerList(new ArrayList<>());
                batchGrantInfoVo.setPartInfos(new ArrayList<>());
            }
            //初始化发放数量（如果库存小于发放量，发库存数量，反之发放限制数量）
            int limitPer = initLimitPerTime(stock, batchGrantInfoVo.getGranLimitPerTime());
            batchGrantInfoVo.setVoucherStatus(VoucherConstants.UNCLAIMED_STATUS);//TODO 暂时给卡券一个默认状态为未领取   定义“100”是为了不和cdp返回的卡券状态码产生冲突
            //获取用户的领取信息,根据领取类型分别判断用户是否已领取
            if (VoucherConstants.AUTOMATIC.equals(grantType)) {
                //自动发放暂时没有用
                OemVoucherUserTriggerReceiptionMapping receiptionMapping = coreOemVoucherUserTriggerReceiptionMappingMapper
                    .selectByGrantIdAndUserId(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                        batchGrantInfoVo.getBatchId());
                if (null == receiptionMapping) {
                    //克隆卡券
                    cloneVoucher(limitPer, batchGrantInfoVo, result, grantType);
                } else {
                    if (receiptionMapping.getLastTimes() > 0) {
                        //克隆卡券
                        cloneVoucher(limitPer, batchGrantInfoVo, result, grantType);
                    }
                }
            } else {
                int count = coreOemVoucherUserTriggerReceiptionMappingMapper
                    .countGrantNum(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                        batchGrantInfoVo.getBatchId());
                if (count < batchGrantInfoVo.getGranLimitPerTime()) {
                    limitPer = limitPer - count;
                    //克隆卡券
                    cloneVoucher(limitPer, batchGrantInfoVo, result, grantType);
                }
            }

        }
        PageInfo<VoucherBatchMyWelfareInfoVo> pageInfo = new PageInfo<>(result);
        VoucherBatchMyWelfareInfoListVo infoListVo = new VoucherBatchMyWelfareInfoListVo();
        infoListVo.setList(pageInfo.getList());
        infoListVo.setHasMore(true);
        return infoListVo;
    }


    @Override
    public VoucherBatchMyWelfareInfoListVo
        voucherGrantListForApplet(UserVo userVo, Byte grantType, int pageSize, int currentPage, String createTime) throws Exception {
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String, Object>> list = new ArrayList<>();
        List<Byte> statusList = new ArrayList<>();
        if (VoucherConstants.TAKE_THE_CAR.equals(grantType)) {
            statusList.add(VoucherConstants.GENERATE);
            PageHelper.orderBy(VoucherConstants.VOUCHER_GRANT_LIST_ORDER_ONE);
            list = coreOemVoucherGrantTriggerMapper.voucherGrantList(grantType,
                VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, statusList, createTime, VoucherConstants.REDEEM_NOT_IN_DMS);
        } else {
            statusList.add(VoucherConstants.GENERATE);
            if (VoucherConstants.MYWELFARE.equals(grantType)) {
                statusList.add(VoucherConstants.EXPIRED);
            }
            PageHelper.orderBy(VoucherConstants.VOUCHER_GRANT_LIST_ORDER_TWO);
            list = coreOemVoucherGrantTriggerMapper.voucherGrantList(grantType,
                VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, statusList, createTime, null);
        }
        log.info(VoucherConstants.VOUCHER_GRANT_LIST_NAME + list);
        List<VoucherBatchMyWelfareInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchMyWelfareInfoVo.class);
        List<VoucherBatchMyWelfareInfoVo> result = new ArrayList<>();
        //筛选出用户不能领取的优惠券
        for (VoucherBatchMyWelfareInfoVo batchGrantInfoVo : batchGrantInfoVos) {
            wrapBatchInfo(batchGrantInfoVo);
            //第一步：如果是取送车优惠券列表，则维修保养及保养的业务类型要排除
            if (VoucherConstants.TAKE_THE_CAR.equals(grantType)) {
                if (VoucherConstants.MAINTENANCE.equals(batchGrantInfoVo.getBusinessType())
                    || VoucherConstants.SERVICE_MAINTENANCE
                    .equals(batchGrantInfoVo.getBusinessType())) {
                    continue;
                }
            }
            if (VoucherConstants.MYWELFARE.equals(grantType)
                && VoucherConstants.EXPIRED.equals(batchGrantInfoVo.getBatchStatus())) {
                VoucherBatchMyWelfareInfoVo myWelfareInfoVo = new VoucherBatchMyWelfareInfoVo();
                BeanUtils.copyProperties(batchGrantInfoVo, myWelfareInfoVo);
                result.add(myWelfareInfoVo);
                continue;
            }
            //第二步：判断该批次卡券是否还有库存，库存是否满足给用户待发放的数量，
            // 如果有库存，但不满足给用户发放的数量，则需要设置发放数量
            int stock = isStock(batchGrantInfoVo);
            if (stock < 1) {
                continue;
            }
            if (!VoucherConstants.THIRDPART_VOUCHER.equals(batchGrantInfoVo.getVoucherType())) {
                //获取限制经销商列表
                queryDealerList(batchGrantInfoVo);
                queryPartInfoList(batchGrantInfoVo);
            } else {
                batchGrantInfoVo.setDealerList(new ArrayList<>());
                batchGrantInfoVo.setPartInfos(new ArrayList<>());
            }
            //初始化发放数量
            int limitPer = initLimitPerTime(stock, batchGrantInfoVo.getGranLimitPerTime());
            batchGrantInfoVo.setVoucherStatus(VoucherConstants.UNCLAIMED_STATUS);//TODO 暂时给卡券一个默认状态为未领取   定义“100”是为了不和cdp返回的卡券状态码产生冲突
            //获取用户的领取信息,根据领取类型分别判断用户是否已领取
            if (VoucherConstants.AUTOMATIC.equals(grantType)) {
                OemVoucherUserTriggerReceiptionMapping receiptionMapping = coreOemVoucherUserTriggerReceiptionMappingMapper
                    .selectByGrantIdAndUserId(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                        batchGrantInfoVo.getBatchId());
                if (null == receiptionMapping) {
                    //克隆卡券
                    cloneVoucher(limitPer, batchGrantInfoVo, result, grantType);
                } else {
                    if (receiptionMapping.getLastTimes() > 0) {
                        //克隆卡券
                        cloneVoucher(limitPer, batchGrantInfoVo, result, grantType);
                    }
                }
            } else {
                int count = coreOemVoucherUserTriggerReceiptionMappingMapper
                    .countGrantNum(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                        batchGrantInfoVo.getBatchId());
                if (count < batchGrantInfoVo.getGranLimitPerTime()) {
                    limitPer = limitPer - count;
                    //克隆卡券
                    cloneVoucher(limitPer, batchGrantInfoVo, result, grantType);
                }
            }

        }

        ////创建Page类
        //Page page = new Page(currentPage, pageSize);
        ////为Page类中的total属性赋值
        //int total = result.size();
        //page.setTotal(total);
        ////计算当前需要显示的数据下标起始值
        //int startIndex = (currentPage - 1) * pageSize;
        //int endIndex = Math.min(startIndex + pageSize,total);
        ////从链表中截取需要显示的子链表，并加入到Page
        //page.addAll(result.subList(startIndex,endIndex));
        //PageInfo<VoucherBatchMyWelfareInfoVo> pageInfo = new PageInfo<>(page);
        
        PageInfo<VoucherBatchMyWelfareInfoVo> pageInfo = new PageInfo<>(result);
        VoucherBatchMyWelfareInfoListVo infoListVo = new VoucherBatchMyWelfareInfoListVo();
        infoListVo.setList(pageInfo.getList());
        infoListVo.setHasMore(true);
        return infoListVo;
    }


    private void wrapBatchInfo(VoucherBatchMyWelfareInfoVo batchGrantInfoVo) {
        VoucherBatch voucherBatch = new VoucherBatch();
        voucherBatch.setBusinessTypeOption(batchGrantInfoVo.getBusinessTypeOption());
        voucherBatch.setBusinessType(batchGrantInfoVo.getBusinessType());
        batchGrantInfoVo.setBusinessTypeOption(ConvertToVo.getBusinessTypeOptions(voucherBatch));
    }

    private void queryDealerList(VoucherBatchMyWelfareInfoVo batchGrantInfoVo) {
        List<Map<String, Object>> list = coreVoucherLimitedDealerMappingMapper.selectByBatchId(batchGrantInfoVo.getBatchId());
        List<DealerInfoVo> dealerCodeVos = CommonConvertUtil.mapListToVOList(list, DealerInfoVo.class);
        batchGrantInfoVo.setDealerList(dealerCodeVos);
    }

    private void queryPartInfoList(VoucherBatchMyWelfareInfoVo batchGrantInfoVo) {
        List<VoucherLimitedPartsMapping> list = coreVoucherLimitedPartsMappingMapper.selectByBatchId(batchGrantInfoVo.getBatchId());
        List<PartInfoVo> partInfoVos = new ArrayList<>();
        list.forEach(partInfo -> {
            PartInfoVo partInfoVo = new PartInfoVo();
            partInfoVo.setPartCode(partInfo.getPartCode());
            partInfoVo.setPartName(partInfo.getPartName());
            partInfoVo.setCarModel(partInfo.getCarModel());
            partInfoVos.add(partInfoVo);
        });
        batchGrantInfoVo.setPartInfos(partInfoVos);
    }

    private int initLimitPerTime(int stock, Integer granLimitPerTime) {
        int limitPer = 0;
        if (stock < granLimitPerTime) {
            limitPer = stock;
        }
        if (stock >= granLimitPerTime) {
            limitPer = granLimitPerTime;
        }
        return limitPer;
    }

    private int isStock(VoucherBatchMyWelfareInfoVo batchGrantInfoVo) {
        //查询该批次卡券数量状态
        JSONObject requestParams = new JSONObject();
        requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        requestParams.put(VoucherConstants.CDP_GENERATE_ID, batchGrantInfoVo.getVoucherTemplateId());
        log.info("isStock组装的数据为:" + requestParams);
        String result = voucherByRunlinCoreFeignClient.queryTicketStatusNumServer(requestParams);
        JSONObject resultJsonObject = JSONObject.fromObject(result);
        log.info("返回的库存结果为:" + resultJsonObject);
        int notReceivedNum;//待领取数量
        if (resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            String data = resultJsonObject.getString(VoucherConstants.RESULT_DATA);
            JSONObject object = JSONObject.fromObject(data);
            notReceivedNum = object.getInt("notReceivedNum");
        } else {
            log.error("cdp查询库存失败，refId : {}, result: {}", batchGrantInfoVo.getVoucherTemplateId(), resultJsonObject.getString(VoucherConstants.RETURN_MESSAGE));
            throw new ServiceException(ResultEnum.QUERY_VOUCHER_COUNT_FAIL);
        }
        return notReceivedNum;
    }

    private void cloneVoucher(int limitPer, VoucherBatchMyWelfareInfoVo grantInfoVo, List<VoucherBatchMyWelfareInfoVo> result, Byte grantType) throws Exception {
        for (int i = 0; i < limitPer; i++) {
            VoucherBatchMyWelfareInfoVo myWelfareInfoVo = new VoucherBatchMyWelfareInfoVo();
            BeanUtils.copyProperties(grantInfoVo, myWelfareInfoVo);
            if (VoucherConstants.GENERATE.equals(grantInfoVo.getBatchStatus())) {
                Date drawDate = DateUtil.dateParse(myWelfareInfoVo.getDrawExpireDate(), DateUtil.DATE_TIME_SLANTING_BAR_PATTERN);
                myWelfareInfoVo.setLastDrawVoucherDays(
                    DateUtil.dateDiffDays(DateUtil.dateTimeToDate(new Date()), drawDate) + 1);
            }
            result.add(myWelfareInfoVo);
        }
    }

    @Override
    public VoucherBatchMyWelfareInfoListVo getMyVocherList(UserVo userVo, String accessToken, Byte voucherType,
                                                           String voucherStatus, int pageSize, int currentPage, String businessCode) throws Exception {
        VoucherBatchMyWelfareInfoListVo voucherList = new VoucherBatchMyWelfareInfoListVo();
        JSONArray vouchersJson = getUserVouchersByCdp(userVo, accessToken, voucherType, voucherStatus, pageSize, currentPage, businessCode);
        List<String> templateIds = new ArrayList<>();
        Map<String, List<JSONObject>> templateId2JsonObject = new HashMap<>();
        Integer totalPages = 0;
        for (Object json : vouchersJson) {
            JSONObject jsonObject = (JSONObject) json;
            templateIds.add(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID));
            totalPages = jsonObject.getInt("totalPages");
            if (!templateId2JsonObject.containsKey(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID))) {
                templateId2JsonObject.put(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID), new ArrayList<>());
            }
            templateId2JsonObject.get(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID)).add(jsonObject);
        }

        Map<String, VoucherBatch> templateId2VoucherBatch = new HashMap<>();
        Map<Integer, List<DealerInfoVo>> batchId2Dealers = new HashMap<>();
        Map<String, List<DealerInfoVo>> redeemCode2Dealers = new HashMap<>();
        Map<Integer, List<PartInfoVo>> batchId2PartInfos = new HashMap<>();

        if (!templateIds.isEmpty()) {
            List<VoucherBatch> voucherBatches = coreVoucherBatchMapper.selectBytemplateIdList(templateIds);
            voucherBatches.forEach(voucherBatch -> {
                templateId2VoucherBatch.put(voucherBatch.getVoucherTemplateId(), voucherBatch);
            });
            List<Integer> batchIds = voucherBatches.stream().map(VoucherBatch::getId).collect(Collectors.toList());
            wrapDealerInfos(batchIds, batchId2Dealers);
            wrapPartInfos(batchIds, batchId2PartInfos);
            redeemCode2Dealers = getRedeemCode2DealersForTcq(voucherBatches, templateId2JsonObject);
        }
        Map<String, List<DealerInfoVo>> historyRedeemCode2Dealers = getDealersOfHistoryVouchers(templateId2VoucherBatch, vouchersJson);
        voucherList.setList(ConvertToVo.convertJsonToVoucherInfoVo(vouchersJson, templateId2VoucherBatch, batchId2Dealers,
                voucherStatus, businessCode, voucherType, historyRedeemCode2Dealers, redeemCode2Dealers, batchId2PartInfos));
        voucherList.setHasMore(currentPage <= totalPages);
        return voucherList;
    }

    private void wrapDealerInfos(List<Integer> batchIds, Map<Integer, List<DealerInfoVo>> batchId2Dealers) {
        List<VoucherLimitedDealerMapping> dealers = new ArrayList<>();
        if (!batchIds.isEmpty()) {
            dealers = coreVoucherLimitedDealerMappingMapper.selectByBatchIdList(batchIds);
        }
        dealers.forEach(dealer -> {
            DealerInfoVo dealerInfoVo = new DealerInfoVo();
            dealerInfoVo.setDealerCode(dealer.getServiceCode());
            dealerInfoVo.setDealerName(dealer.getName());
            if (!batchId2Dealers.containsKey(dealer.getFkBatchId())) {
                batchId2Dealers.put(dealer.getFkBatchId(), new ArrayList<>());
            }
            batchId2Dealers.get(dealer.getFkBatchId()).add(dealerInfoVo);
        });
    }

    private void wrapPartInfos(List<Integer> batchIds, Map<Integer, List<PartInfoVo>> batchId2PartInfos) {
        List<VoucherLimitedPartsMapping> partInfos = new ArrayList<>();
        if (!batchIds.isEmpty()) {
            partInfos = coreVoucherLimitedPartsMappingMapper.selectByBatchIdList(batchIds);
        }
        partInfos.forEach(partInfo -> {
            PartInfoVo partInfoVo = new PartInfoVo();
            partInfoVo.setPartCode(partInfo.getPartCode());
            partInfoVo.setPartName(partInfo.getPartName());
            partInfoVo.setCarModel(partInfo.getCarModel());
            if (!batchId2PartInfos.containsKey(partInfo.getFkBatchId())) {
                batchId2PartInfos.put(partInfo.getFkBatchId(), new ArrayList<>());
            }
            batchId2PartInfos.get(partInfo.getFkBatchId()).add(partInfoVo);
        });
    }

    private Map<String, List<DealerInfoVo>> getDealersOfHistoryVouchers(Map<String, VoucherBatch> templateId2VoucherBatch, JSONArray vouchersJson) throws Exception {
        Map<String, List<DealerInfoVo>> historyRedeemCode2Dealers = new HashMap<>();
        for (Object json : vouchersJson) {
            JSONObject jsonObject = (JSONObject) json;
            String templateId = jsonObject.getString(VoucherConstants.CDP_GENERATE_ID);
            if (!templateId2VoucherBatch.containsKey(templateId)) {
                String redeemCode = jsonObject.getString(VoucherConstants.REDEEM_CODE);
                wrapRedeemcode2Dealers(redeemCode, jsonObject, historyRedeemCode2Dealers);
            }
        }

        return historyRedeemCode2Dealers;
    }

    private void wrapRedeemcode2Dealers(String redeemCode, JSONObject jsonObject,
                                        Map<String, List<DealerInfoVo>> redeemCode2Dealers) {
        String dealercodeJson = jsonObject.getString(VoucherConstants.DEALER_CODE_LIST);
        if (null != dealercodeJson && !dealercodeJson.equals(VoucherConstants.STRING_NULL)) {
            JSONArray dealerCodesJson = JSONArray.fromObject(jsonObject.getString(VoucherConstants.DEALER_CODE_LIST));
            List<String> dealerCodes = new ArrayList<>();
            for (Object object : dealerCodesJson) {
                JSONObject dealerCodeObject = (JSONObject) object;
                dealerCodes.add(dealerCodeObject.getString("dealercode"));
            }

            dealerCodes.forEach(dealerCode -> {
                String redisKey = VoucherConstants.REF_ID_TO_OPERATION_USER_MAP_CACHE_KEY + dealerCode;
                RMap<String, OperationRedisUser> idOperationUserVORMap = redissonService.getRMap(redisKey);
                DealerInfoVo dealerInfoVo = new DealerInfoVo();
                dealerInfoVo.setDealerCode(dealerCode);
                if (idOperationUserVORMap.containsKey(redisKey)) {
                    dealerInfoVo.setDealerName(idOperationUserVORMap.get(redisKey).getVerifiedName());
                }

                if (!redeemCode2Dealers.containsKey(redeemCode)) {
                    redeemCode2Dealers.put(redeemCode, new ArrayList<>());
                }
                redeemCode2Dealers.get(redeemCode).add(dealerInfoVo);
            });
        }
    }

    private Map<String, List<DealerInfoVo>> getRedeemCode2DealersForTcq(List<VoucherBatch> voucherBatches, Map<String, List<JSONObject>> templateId2JsonObject) {
        //特殊处理，针对套餐劵，没法用配置的经销商列表，因为从cdp获取的经销商列表就是发放时候的经销商，c端显示只能拿cdp的数据来显示
        Map<String, List<DealerInfoVo>> redeemCode2Dealers = new HashMap<>();
        for (VoucherBatch voucherBatch : voucherBatches) {
            if (voucherBatch.getVoucherType().equals(VoucherConstants.PACKAGE_VOUCHER)) {
                List<JSONObject> jsonObjects = templateId2JsonObject.getOrDefault(voucherBatch.getVoucherTemplateId(), null);
                if (null == jsonObjects) {
                    continue;
                }
                for (JSONObject jsonObject : jsonObjects) {
                    String redeemCode = jsonObject.getString(VoucherConstants.REDEEM_CODE);
                    wrapRedeemcode2Dealers(redeemCode, jsonObject, redeemCode2Dealers);
                }
            }
        }
        return redeemCode2Dealers;
    }

    @Override
    public VoucherBatchMyWelfareInfoListVo grantAutomatic(UserVo userVo, Integer businessType, String token, String vin, String validStartTime) throws Exception {
        if (!userVo.getRefId().equals(getRefIdByVinFromCdp(vin))) {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.VOUCHER_VIN_AND_AID_NOT_REF);
        }
        String lockKey = String.format(VoucherConstants.AUTOMATIC_GRANT_VOUCHER_LOCK, userVo.getRefId());
        RLock rlock = redissonService.getRLock(lockKey);
        boolean lock = rlock.tryLock();
        try {
            if (!lock) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_ACCESS_LIMIT);
            }
            if (null == businessType) {
                throw new ServiceException(ResultEnum.ONE_ACTIVITY_PARAMETER_ERROR);
            }
            log.info("grantAutomaticParam：userId={},businessType={},vin={},validStartTime={}", userVo.getUserId(), businessType, vin, validStartTime);
            //获取该发放场景所有的优惠券批次
            List<Map<String, Object>> list = coreOemVoucherGrantTriggerMapper.selectByBusinessType(businessType, VoucherConstants.AUTOMATIC,
                    VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, null, VoucherConstants.GENERATE);
            List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchGrantInfoVo.class);
            log.info("grantAutomaticBatchData：" + batchGrantInfoVos);
            //初始化发放结果集合
            List<VoucherBatchMyWelfareInfoVo> resultVos = new ArrayList<>();
            //token = getHttpToken();
            //发送到cdp，执行优惠券发放操作
            sendCdp(batchGrantInfoVos, userVo, resultVos, token, validStartTime, vin);
            VoucherBatchMyWelfareInfoListVo voucherBatchMyWelfareInfoListVo = new VoucherBatchMyWelfareInfoListVo();
            voucherBatchMyWelfareInfoListVo.setList(resultVos);
            voucherBatchMyWelfareInfoListVo.setHasMore(Boolean.FALSE);
            return voucherBatchMyWelfareInfoListVo;
        } finally {
            if (rlock.isLocked() && rlock.isHeldByCurrentThread()) {
                rlock.unlock();
            }
        }
    }

    @Override
    public VoucherRedPacketVo getVoucherRedPacketInfo(UserVo userVo) throws Exception {
        //获取该发放场景所有的优惠券批次
        List<Map<String, Object>> list = coreOemVoucherGrantTriggerMapper.selectByBusinessType(Integer.valueOf(VoucherConstants.GRANT_MAINTAION_AND_ORDER_TYPE), VoucherConstants.AUTOMATIC,
            VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, null, VoucherConstants.GENERATE);
        List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchGrantInfoVo.class);
        if (batchGrantInfoVos.isEmpty()) {
            return null;
        }
        VoucherRedPacketVo voucherRedPacketVo = new VoucherRedPacketVo();
        for (VoucherBatchGrantInfoVo voucherBatchGrantInfoVo : batchGrantInfoVos) {
            VoucherBatchMyWelfareInfoVo batchGrantInfoVo = new VoucherBatchMyWelfareInfoVo();
            batchGrantInfoVo.setVoucherTemplateId(voucherBatchGrantInfoVo.getGenerateId());
            if (isStock(batchGrantInfoVo) < 1) {
                continue;
            }
            if (!isCanGrantVoucher(voucherBatchGrantInfoVo, userVo)) {
                continue;
            }
            voucherRedPacketVo.setExpiryDateType(voucherBatchGrantInfoVo.getExpiryDateType());
            voucherRedPacketVo.setGrantTimeType(voucherBatchGrantInfoVo.getGrantTimeType());
            if (VoucherConstants.DYNAMIC_EXPIRED_DATE.equals(voucherBatchGrantInfoVo.getExpiryDateType())) {
                voucherRedPacketVo.setEndTime(null);
                voucherRedPacketVo.setStartTime(null);
                break;
            } else {
                if (Objects.isNull(voucherRedPacketVo.getStartTime())) {
                    voucherRedPacketVo.setStartTime(voucherBatchGrantInfoVo.getStartTime());
                } else {
                    Date lastStartDate = DateUtil.dateParse(voucherRedPacketVo.getStartTime(), DateUtil.DATE_TIME_PATTERN);
                    Date curStartDate = DateUtil.dateParse(voucherBatchGrantInfoVo.getStartTime(), DateUtil.DATE_TIME_PATTERN);
                    if (curStartDate.before(lastStartDate)) {
                        voucherRedPacketVo.setStartTime(voucherBatchGrantInfoVo.getStartTime());
                    }
                }

                if (Objects.isNull(voucherRedPacketVo.getEndTime())) {
                    voucherRedPacketVo.setEndTime(voucherBatchGrantInfoVo.getEndTime());
                } else {
                    Date lastEndDate = DateUtil.dateParse(voucherRedPacketVo.getEndTime(), DateUtil.DATE_TIME_PATTERN);
                    Date curEndDate = DateUtil.dateParse(voucherBatchGrantInfoVo.getEndTime(), DateUtil.DATE_TIME_PATTERN);
                    if (curEndDate.after(lastEndDate)) {
                        voucherRedPacketVo.setEndTime(voucherBatchGrantInfoVo.getEndTime());
                    }
                }
            }
        }
        if (Objects.isNull(voucherRedPacketVo.getGrantTimeType())) {
            return null;
        }
        return voucherRedPacketVo;
    }

    private Boolean isCanGrantVoucher(VoucherBatchGrantInfoVo voucherBatchGrantInfoVo, UserVo userVo) {
        OemVoucherUserTriggerReceiptionMapping receiptionMapping = coreOemVoucherUserTriggerReceiptionMappingMapper
            .selectByGrantIdAndUserId(voucherBatchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                voucherBatchGrantInfoVo.getBatchId());
        if (null == receiptionMapping) {
            voucherBatchGrantInfoVo.setCurLastGrantTimes(voucherBatchGrantInfoVo.getGrantTimesLimit());
            return true;
        } else {
            if (receiptionMapping.getLastTimes() > VoucherConstants.TIMES_ZERO) {
                voucherBatchGrantInfoVo.setCurLastGrantTimes(receiptionMapping.getLastTimes());
                return true;
            }
        }
        return false;
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

    private void sendVoucherCdpForDirecting(List<VoucherBatchGrantInfoVo> batchGrantInfoVos, List<VoucherBatchMyWelfareInfoVo> resultVos,
                                            UserVo userVo, String token, Map<Integer, OemVoucherGrantRecord> grantIdToObject) {
        batchGrantInfoVos.forEach(batchGrantInfoVo -> {
            String vin = queryCarVin(grantIdToObject.get(batchGrantInfoVo.getGrantTypeId()));
            batchGrantInfoVo.setCurLastGrantTimes(VoucherConstants.DEFAULT_LAST_TIMES);
            JSONObject paramValues = getJsonObject(batchGrantInfoVo, userVo, null, vin, false);
            log.info("sendVoucherCdpForDirecting组装的数据为=" + paramValues);
            String result = voucherByRunlinCoreFeignClient.ticketGrantDMS(paramValues);
            JSONObject jsonObjectP = JSONObject.fromObject(result);
            log.info("sendVoucherCdpForDirecting返回的数据为=" + result);
            if (jsonObjectP.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                //发放成功后更新用户领取次数
                updateDirectingGrantRecord(batchGrantInfoVo, userVo, grantIdToObject);
                //组装数据
                initResult(jsonObjectP, resultVos, token, batchGrantInfoVo, null, vin);
            } else {
                try {
                    if (isGrantSuccess(userVo, token, batchGrantInfoVo)) {
                        //发放成功后更新用户领取次数
                        updateDirectingGrantRecord(batchGrantInfoVo, userVo, grantIdToObject);
                        //组装数据
                        initResult(jsonObjectP, resultVos, token, batchGrantInfoVo, null, vin);
                    } else {
                        throw new Exception((String) jsonObjectP.get(VoucherConstants.RETURN_MESSAGE));
                    }
                } catch (Exception e) {
                    log.error("SaveSendVoucherCdpForDirecting e={},batchGrantInfoVo={}", e, batchGrantInfoVo);
                }
            }
        });
    }

    private void updateDirectingGrantRecord(VoucherBatchGrantInfoVo batchGrantInfoVo, UserVo userVo, Map<Integer, OemVoucherGrantRecord> grantIdToObject) {
        OemVoucherGrantRecord oemVoucherGrantRecord = coreOemVoucherGrantRecordMapper.selectByPrimaryKey(grantIdToObject.get(batchGrantInfoVo.getGrantTypeId()).getId());
        oemVoucherGrantRecord.setGrantVoucherStatus(VoucherConstants.GRANTEE_CHECK_SUCESS);
        oemVoucherGrantRecord.setAidRef(userVo.getRefId());
        coreOemVoucherGrantRecordMapper.updateByPrimaryKeySelective(oemVoucherGrantRecord);
        OemVoucherUserTriggerReceiptionMapping add = new OemVoucherUserTriggerReceiptionMapping();
        add.setAidRef(userVo.getRefId());
        add.setFkGrantTypeId(batchGrantInfoVo.getGrantTypeId());
        add.setLastTimes(0);
        add.setFkBatchId(batchGrantInfoVo.getBatchId());
        coreOemVoucherUserTriggerReceiptionMappingMapper.insertSelective(add);
    }

    private Boolean isGrantSuccess(UserVo userVo, String accessToken, VoucherBatchGrantInfoVo batchGrantInfoVo) throws Exception {
        JSONArray vouchersJson = getUserVouchersByCdp(userVo, accessToken, batchGrantInfoVo.getVoucherType(), VoucherConstants.AVAILABLE_STATUS,
            VoucherConstants.PAGE_SIZE, VoucherConstants.CURRENT_PAGE, VoucherConstants.NA_BUSINESS);
        List<String> templateIds = new ArrayList<>();
        Map<String, List<JSONObject>> templateId2JsonObject = new HashMap<>();
        for (Object json : vouchersJson) {
            JSONObject jsonObject = (JSONObject) json;
            templateIds.add(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID));
            if (!templateId2JsonObject.containsKey(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID))) {
                templateId2JsonObject.put(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID), new ArrayList<>());
            }
            templateId2JsonObject.get(jsonObject.getString(VoucherConstants.CDP_GENERATE_ID)).add(jsonObject);
        }
        return templateId2JsonObject.containsKey(batchGrantInfoVo.getGenerateId());
    }

    private void sendCdp(List<VoucherBatchGrantInfoVo> batchGrantInfoVos, UserVo userVo, List<VoucherBatchMyWelfareInfoVo> resultVos,
                         String token, String validStartTime, String vin) {
        batchGrantInfoVos.forEach(batchGrantInfoVo -> {
            if (grantBefore(userVo, batchGrantInfoVo, validStartTime)) {
                //组装数据
                JSONObject paramValues = getJsonObject(batchGrantInfoVo, userVo, validStartTime, vin, false);
                log.info("voucherSendToCdpParam=" + paramValues);
                String result = voucherByRunlinCoreFeignClient.ticketGrantDMS(paramValues);
                JSONObject jsonObjectP = JSONObject.fromObject(result);
                log.info("voucherSendToCdpResult=" + result);
                if (jsonObjectP.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                    //发放成功后更新用户领取次数
                    grantAfter(batchGrantInfoVo, userVo);
                    //组装数据
                    initResult(jsonObjectP, resultVos, token, batchGrantInfoVo, validStartTime, vin);
                } else {
                    log.error(String.format("grantVoucherToCdpFail1, paramValues=%s, result=%s", paramValues.toString(), result));
                    try {
                        if (isGrantSuccess(userVo, token, batchGrantInfoVo)) {
                            //发放成功后更新用户领取次数
                            grantAfter(batchGrantInfoVo, userVo);
                            //组装数据
                            initResult(jsonObjectP, resultVos, token, batchGrantInfoVo, validStartTime, vin);
                        } else {
                            throw new Exception((String) jsonObjectP.get(VoucherConstants.RETURN_MESSAGE));
                        }
                    } catch (Exception e) {
                        log.error(String.format("grantVoucherToCdpFail2, e=%s, paramValues=%s, result=%s", e.toString(), paramValues.toString(), result));
                    }
                }
            }
        });
    }

    private void initResult(JSONObject jsonObjectP, List<VoucherBatchMyWelfareInfoVo> resultVos, String token, VoucherBatchGrantInfoVo batchGrantInfoVo, String validStartTime, String vin) {
        //根据获取到的券码再次通过cdp查询券码详情
        JSONArray array = jsonObjectP.getJSONArray(VoucherConstants.RESULT_DATA);
        for (Object obj : array) {
            JSONObject object = JSONObject.fromObject(obj);
            String changecodeId = object.getString("changecodeId");
            String generateId = object.getString(VoucherConstants.CDP_GENERATE_ID);
            //发送到cdp获取卡券详情
            //sendCdpByVoucherInfo(changecodeId, generateId, resultVos, token);
            //组装详情
            VoucherBatchMyWelfareInfoVo myWelfareInfoVo = new VoucherBatchMyWelfareInfoVo();
            myWelfareInfoVo.setVoucherName(batchGrantInfoVo.getVoucherName());//卡券名称
            //初始化卡券有效期
            initVoucherTime(batchGrantInfoVo, myWelfareInfoVo, validStartTime);
            myWelfareInfoVo.setVoucherType(batchGrantInfoVo.getVoucherType());//卡券类型
            if (!StringUtils.isEmpty(batchGrantInfoVo.getRedeemValue())) {
                myWelfareInfoVo.setRedeemValue(batchGrantInfoVo.getRedeemValue());//券码金额
            }
            if (!StringUtils.isEmpty(batchGrantInfoVo.getMinimumSpendAmount())) {
                myWelfareInfoVo.setMinimumSpendAmount(batchGrantInfoVo.getMinimumSpendAmount());//限制使用金额
            }
            myWelfareInfoVo.setUsageRule(batchGrantInfoVo.getUsageRule());//使用规则描述
            //myWelfareInfoVo.setVoucherStatus(object.getString(VoucherConstants.CARD_TICKET_STATUS_KEY));//卡券状态
            myWelfareInfoVo.setBusinessType(batchGrantInfoVo.getBusinessType());//业务代码类型
            myWelfareInfoVo.setRedeemCode(object.getString(VoucherConstants.REDEEM_CODE));//兑换码
            myWelfareInfoVo.setGrantTypeId(batchGrantInfoVo.getGrantTypeId());
            myWelfareInfoVo.setVin(vin);//vin码
            myWelfareInfoVo.setIsVinLimited(batchGrantInfoVo.getIsVinLimited());//是否限制vin码
            myWelfareInfoVo.setBatchId(batchGrantInfoVo.getBatchId());//批次ID
            //myWelfareInfoVo.setExpiryDateType(batchGrantInfoVo.getExpiryDateType());//有效期类型
            //myWelfareInfoVo.setValidForNumberOfDays(batchGrantInfoVo.getValidForNumberOfDays());//动态核销有效天数
            resultVos.add(myWelfareInfoVo);
        }
    }

    private void initVoucherTime(VoucherBatchGrantInfoVo batchGrantInfoVo, VoucherBatchMyWelfareInfoVo myWelfareInfoVo, String validStartTime) {

        //如果为固定有效期类型
        if (batchGrantInfoVo.getExpiryDateType().equals(VoucherConstants.FIXED)) {
            try {
                Date start = DateUtil.dateParse(batchGrantInfoVo.getStartTime(), DateUtil.DATE_PATTERN);
                Date end = DateUtil.dateParse(batchGrantInfoVo.getEndTime(), DateUtil.DATE_PATTERN);
                myWelfareInfoVo.setRedeemStartTime(DateUtil.dateFormat(start, DateUtil.DATE_TIME_SLANTING_BAR_PATTERN));//有效期开始时间
                myWelfareInfoVo.setRedeemEndTime(DateUtil.dateFormat(end, DateUtil.DATE_TIME_SLANTING_BAR_PATTERN));
            } catch (Exception e) {
                log.error(String.format("grantVoucherInitVoucherTimeFail1, e=%s", e.toString()));
            }
            //如果为动态有效期
        } else {
            try {
                Date nowDate = new Date();
                Date stime = null;
                if (!StringUtils.isEmpty(validStartTime)) {
                    stime = DateUtil.dateParse(validStartTime, DateUtil.DATE_PATTERN);
                }
                Date startTime
                    = (stime == null || stime.getTime() < nowDate.getTime()) ? new Date() : stime;
                Integer validDays
                    = (null == batchGrantInfoVo.getValidForNumberOfDays() || 0 == batchGrantInfoVo
                        .getValidForNumberOfDays()) ? 0
                        : batchGrantInfoVo.getValidForNumberOfDays() - 1;
                Date date = DateUtil.dateAdd(startTime, validDays, Boolean.TRUE);
                myWelfareInfoVo.setRedeemStartTime(DateUtil.dateFormat(startTime, DateUtil.DATE_TIME_SLANTING_BAR_PATTERN));//有效期开始时间
                myWelfareInfoVo.setRedeemEndTime(DateUtil.dateFormat(date, DateUtil.DATE_TIME_SLANTING_BAR_PATTERN));//有效期结束时间
            } catch (Exception e) {
                log.error(String.format("grantVoucherInitVoucherTimeFail2, e=%s", e.toString()));
            }
        }
    }


    private void sendCdpByVoucherInfo(String changecodeId, String generateId, List<VoucherBatchMyWelfareInfoVo> resultVos, String token) {
        SendVoucherInfoVo voucherInfoVo = new SendVoucherInfoVo();
        voucherInfoVo.setCardTicketKey(cardTicketKey);
        voucherInfoVo.setChangecodeId(changecodeId);
        voucherInfoVo.setGenerateId(generateId);
        JSONObject object = JSONObject.fromObject(voucherInfoVo);
        String result = voucherByRunlinCoreFeignClient.queryTicketDetail(object);
        JSONObject jsonObjectP = JSONObject.fromObject(result);
        log.info("result=" + jsonObjectP);
        if (jsonObjectP.getString(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            //组装数据
            initVoucherInfo(jsonObjectP, resultVos);
        } else {
            try {
                throw new Exception((String) jsonObjectP.get(VoucherConstants.RETURN_MESSAGE));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initVoucherInfo(JSONObject jsonObjectP, List<VoucherBatchMyWelfareInfoVo> resultVos) {
        JSONObject object = jsonObjectP.getJSONObject(VoucherConstants.RESULT_DATA);
        VoucherBatchMyWelfareInfoVo myWelfareInfoVo = new VoucherBatchMyWelfareInfoVo();
        myWelfareInfoVo.setVoucherName(object.getString("cardTicketName"));//卡券名称
        myWelfareInfoVo.setRedeemStartTime(object.getString("starttime"));//卡券生效开始日期
        myWelfareInfoVo.setRedeemEndTime(object.getString("endtime"));//卡券有效截止日期
        //初始化卡券类型
        initVoucherType(myWelfareInfoVo, object.getString(VoucherConstants.CDP_CARD_TYPE));
        myWelfareInfoVo.setRedeemValue(object.getString(VoucherConstants.VALUE_TXT));//券码金额
        myWelfareInfoVo.setRedeemValue(object.getString("usageRule"));//使用规则描述
        myWelfareInfoVo.setVoucherStatus(object.getString(VoucherConstants.CARD_TICKET_STATUS_KEY));//卡券状态
        myWelfareInfoVo.setBusinessType(Byte.valueOf(object.getString(VoucherConstants.BUSINESS_CODE)));//业务代码类型
        //初始化业务代码类型
        initBusinessType(myWelfareInfoVo, object.getString(VoucherConstants.BUSINESS_CODE));
        resultVos.add(myWelfareInfoVo);
    }

    private void initBusinessType(VoucherBatchMyWelfareInfoVo myWelfareInfoVo, String businessCode) {
        switch (businessCode) {
            case "1"://维修保养
                myWelfareInfoVo.setBusinessType((byte) VoucherConstants.INT_FOUR);
                break;
            case VoucherConstants.TWO://保养
                myWelfareInfoVo.setVoucherType((byte) 2);
                break;
            case "3"://不限制
                myWelfareInfoVo.setVoucherType((byte) 1);
                break;
            case VoucherConstants.FOUR://取送车
                myWelfareInfoVo.setVoucherType((byte) VoucherConstants.INT_THREE);
                break;
            default:
                break;
        }
    }

    private void initVoucherType(VoucherBatchMyWelfareInfoVo myWelfareInfoVo, String type) {
        switch (type) {
            case "2"://代金券
                myWelfareInfoVo.setVoucherType((byte) 1);
                break;
            case "4"://实物券
                myWelfareInfoVo.setVoucherType((byte) VoucherConstants.INT_THREE);
                break;
            case "5"://套餐券
                myWelfareInfoVo.setVoucherType((byte) 2);
                break;
            default:
                break;
        }
    }

    private void grantAfter(VoucherBatchGrantInfoVo batchGrantInfoVo, UserVo userVo) {
        //如果是自动发放
        if (VoucherConstants.AUTOMATIC.equals(batchGrantInfoVo.getGrantType())) {
            OemVoucherUserTriggerReceiptionMapping receiptionMapping = coreOemVoucherUserTriggerReceiptionMappingMapper
                    .selectByGrantIdAndUserId(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(), batchGrantInfoVo.getBatchId());
            log.info("AUTOMATICsendVoucherGrantAfter receiptionMapping={},batchGrantInfoVo={},UserId={}", receiptionMapping, batchGrantInfoVo, userVo.getUserId());
            //如果没有领取记录，则新增用户的领取记录
            if (null == receiptionMapping) {
                OemVoucherUserTriggerReceiptionMapping add = new OemVoucherUserTriggerReceiptionMapping();
                add.setAidRef(userVo.getRefId());
                add.setFkGrantTypeId(batchGrantInfoVo.getGrantTypeId());
                add.setLastTimes(batchGrantInfoVo.getGrantTimesLimit() - 1);//剩余领取次数为可领取次数减1
                add.setFkBatchId(batchGrantInfoVo.getBatchId());
                //持久化用户发放记录
                coreOemVoucherUserTriggerReceiptionMappingMapper.insertSelective(add);
            } else {
                receiptionMapping.setLastTimes(receiptionMapping.getLastTimes() - 1);
                //更新剩余领取次数
                coreOemVoucherUserTriggerReceiptionMappingMapper.updateByPrimaryKeySelective(receiptionMapping);
            }
            //如果是取送车和我的福利，则直接进行持久化的操作
        } else {
            //这里需要判断领取的数量，一张卡券存一个领取记录
            log.info("sendVoucherGrantAfter batchGrantInfoVo={},UserId={}", batchGrantInfoVo, userVo.getUserId());
            for (int i = 0; i < batchGrantInfoVo.getGrantLimitPerTime(); i++) {
                OemVoucherUserTriggerReceiptionMapping add = new OemVoucherUserTriggerReceiptionMapping();
                add.setAidRef(userVo.getRefId());
                add.setFkGrantTypeId(batchGrantInfoVo.getGrantTypeId());
                add.setLastTimes(batchGrantInfoVo.getGrantTimesLimit() - 1);
                add.setFkBatchId(batchGrantInfoVo.getBatchId());
                coreOemVoucherUserTriggerReceiptionMappingMapper.insertSelective(add);
            }
        }

    }

    private JSONObject getJsonObject(VoucherBatchGrantInfoVo batchGrantInfoVo, UserVo userVo, String validStartTime,
        String vin, Boolean isSerialNumberHasVin) {
        if (Objects.isNull(batchGrantInfoVo.getCurLastGrantTimes())) {
            ServiceExceptionUtil
                .throwServiceException(VoucherErrorEnum.VOUCHER_NO_GRANT_TIMES);
        }
        List<TicketUserVo> ticketUserVos = new ArrayList<>();
        initTicketUserVo(ticketUserVos, batchGrantInfoVo, userVo, validStartTime, vin);
        SendVoucherBatchGrantVo sendVoucherBatchGrantVo = new SendVoucherBatchGrantVo();
        sendVoucherBatchGrantVo.setCardTicketKey(cardTicketKey);
        sendVoucherBatchGrantVo.setChannelName(VoucherConstants.OEM_CHANNEL);
        sendVoucherBatchGrantVo.setGenerateId(batchGrantInfoVo.getGenerateId());
        sendVoucherBatchGrantVo.setTicketUserList(ticketUserVos);
        if (isSerialNumberHasVin) {
            sendVoucherBatchGrantVo.setSerialNumber(ConvertToVo
                .getSerialNumber(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(), batchGrantInfoVo.getBatchId(), batchGrantInfoVo.getCurLastGrantTimes(), vin));
        } else {
            sendVoucherBatchGrantVo.setSerialNumber(ConvertToVo
                .getSerialNumber(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(), batchGrantInfoVo.getBatchId(), batchGrantInfoVo.getCurLastGrantTimes(), null));
        }

        JSONObject object = JSONObject.fromObject(sendVoucherBatchGrantVo);
        return object;
    }

    private void initTicketUserVo(List<TicketUserVo> ticketUserVos, VoucherBatchGrantInfoVo batchGrantInfoVo, UserVo userVo, String validStartTime, String vin) {

        TicketUserVo ticketUserVo = new TicketUserVo();
        ticketUserVo.setAid(userVo.getRefId());//用户ID
        initTime(batchGrantInfoVo, ticketUserVo, validStartTime);//初始化有效期时间
        ticketUserVo.setGenerateNum(String.valueOf(batchGrantInfoVo.getGrantLimitPerTime()));//卡券模版ID
        ticketUserVo.setBusinessCode(batchGrantInfoVo.getBusinessTypeOption());//业务类型代码
        if (!StringUtils.isEmpty(vin)) {
            ticketUserVo.setVin(vin);
        }
        if (!VoucherConstants.THIRDPART_VOUCHER.equals(batchGrantInfoVo.getVoucherType())) {
            //查询经销商代码列表
            List<Map<String, Object>> list = coreVoucherLimitedDealerMappingMapper.selectDealerCodeByBatchId(batchGrantInfoVo.getBatchId());
            List<DealerCodeVo> dealerCodeVos = CommonConvertUtil.mapListToVOList(list, DealerCodeVo.class);
            ticketUserVo.setDealercodeList(dealerCodeVos);//经销商代码列表
        }
        ticketUserVos.add(ticketUserVo);


    }

    private void initTime(VoucherBatchGrantInfoVo batchGrantInfoVo, TicketUserVo ticketUserVo, String validStartTime) {
        try {
            //如果为固定有效期类型
            if (batchGrantInfoVo.getExpiryDateType().equals(VoucherConstants.FIXED)) {
                ticketUserVo.setStarttime(batchGrantInfoVo.getStartTime());//有效期开始时间
                Date endTime = DateUtil.dateParse(batchGrantInfoVo.getEndTime(), DateUtil.DATE_TIME_PATTERN);
                endTime = DateUtil.dateFormatToCurDayLastTime(endTime);
                ticketUserVo.setEndtime(DateUtil.dateFormat(endTime, DateUtil.DATE_TIME_PATTERN));//有效期结束时间
                //如果为动态有效期
            } else {
                //获取当前时间
                Date nowDate = new Date();
                Date stime = null;
                if (!StringUtils.isEmpty(validStartTime)) {
                    stime = DateUtil.dateParse(validStartTime, DateUtil.DATE_PATTERN);
                }
                Date startTime
                    = (stime == null || stime.getTime() < nowDate.getTime()) ? new Date() : stime;
                Integer validDays
                    = (null == batchGrantInfoVo.getValidForNumberOfDays() || 0 == batchGrantInfoVo
                        .getValidForNumberOfDays()) ? 0
                        : batchGrantInfoVo.getValidForNumberOfDays() - 1;
                Date date = DateUtil.dateAdd(startTime, validDays, Boolean.TRUE);
                String endDateString = DateUtil.dateFormat(date, DateUtil.DATE_PATTERN) + VoucherConstants.DATE_PATTERN_END;
                Date endDate = DateUtil.dateParse(endDateString, DateUtil.DATE_TIME_PATTERN);
                date = DateUtil.dateFormatToCurDayLastTime(endDate);
                ticketUserVo.setStarttime(DateUtil.dateFormat(startTime, DateUtil.DATE_TIME_PATTERN));//有效期开始时间
                ticketUserVo.setEndtime(DateUtil.dateFormat(date, DateUtil.DATE_TIME_PATTERN));//有效期结束时间
            }
        } catch (ParseException e) {
            log.error("sendCdpParamInitTime error= " + e);
        }
    }

    private Boolean grantBefore(UserVo userVo, VoucherBatchGrantInfoVo voucherBatchGrantInfoVo, String validStartTime) {
        boolean flag = false;
        //判断当前用户是否已有领取记录,自动方法方式需要判断领取次数，取送车和我的福利只需要判断批次id存在的次数
        //所以需要分开来判断
        //如果是自动发放
        if (VoucherConstants.AUTOMATIC.equals(voucherBatchGrantInfoVo.getGrantType())) {
            if (VoucherConstants.GRANT_MAINTAION_AND_ORDER_TYPE
                .equals(voucherBatchGrantInfoVo.getGrantBusinessType())
                && !isValidGrantTime(voucherBatchGrantInfoVo, validStartTime)) {
                return false;
            }
            OemVoucherUserTriggerReceiptionMapping receiptionMapping = coreOemVoucherUserTriggerReceiptionMappingMapper
                .selectByGrantIdAndUserId(voucherBatchGrantInfoVo.getGrantTypeId(),
                    userVo.getRefId(), voucherBatchGrantInfoVo.getBatchId());
            if (null == receiptionMapping) {
                flag = true;
                voucherBatchGrantInfoVo
                    .setCurLastGrantTimes(voucherBatchGrantInfoVo.getGrantTimesLimit());
            } else {
                if (receiptionMapping.getLastTimes() > VoucherConstants.TIMES_ZERO) {
                    flag = true;
                    voucherBatchGrantInfoVo.setCurLastGrantTimes(receiptionMapping.getLastTimes());
                }
            }
        } else {
            Integer count = coreOemVoucherUserTriggerReceiptionMappingMapper
                .countGrantNum(voucherBatchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                    voucherBatchGrantInfoVo.getBatchId());
            OemVoucherGrantTrigger grantTrigger = coreOemVoucherGrantTriggerMapper
                .selectByPrimaryKey(voucherBatchGrantInfoVo.getGrantTypeId());
            if (count < grantTrigger.getGrantLimitPerTime()) {
                flag = true;
                voucherBatchGrantInfoVo
                    .setCurLastGrantTimes(grantTrigger.getGrantLimitPerTime() - count);
            }
        }
        return flag;
    }

    private Boolean isValidGrantTime(VoucherBatchGrantInfoVo voucherBatchGrantInfoVo, String validStartTime) {
        try {
            if (Objects.isNull(validStartTime)) {
                return false;
            }
            Calendar calendar = Calendar.getInstance();
            //把固定期限外下单时间不发券判断提前
            if (voucherBatchGrantInfoVo.getExpiryDateType().equals(VoucherConstants.FIXED)) {
                Date startTime = DateUtil.dateParse(voucherBatchGrantInfoVo.getStartTime(), DateUtil.DATE_TIME_PATTERN);
                Date endTime = DateUtil.dateParse(voucherBatchGrantInfoVo.getEndTime(), DateUtil.DATE_TIME_PATTERN);
                Date orderTime = DateUtil.dateParse(validStartTime, DateUtil.DATE_PATTERN);
                if (startTime.after(orderTime) || endTime.before(orderTime)) {
                    return false;
                }
            }
            calendar.setTime(DateUtil.dateParse(validStartTime, DateUtil.DATE_PATTERN));
            Integer week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            //0代表周日，6代表周六
            if (week == VoucherConstants.INT_SIX || week == 0) {
                if (!voucherBatchGrantInfoVo.getGrantTimeType().equals(VoucherConstants.WORK_DAY)) {
                    return true;
                }
            } else {
                if (!voucherBatchGrantInfoVo.getGrantTimeType().equals(VoucherConstants.WEEKDAY)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("isValidGrantTime error= " + e);
        }
        return false;
    }

    @Override
    public VoucherBatchMyWelfareInfoListVo grantVoucher(UserVo userVo, Byte grantType, String batchIds, String token,
                                                        String vin, String validStartTime) throws Exception {
        String lockKey = String.format(VoucherConstants.MY_WELFARE_GRANT_VOUCHER_LOCK, userVo.getRefId());
        RLock rlock = redissonService.getRLock(lockKey);
        boolean lock = rlock.tryLock();
        try {
            if (!lock) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_ACCESS_LIMIT);
            }
            log.info("grantVoucherParam：userId={},grantType={},vin={},validStartTime={},batchIds={}", userVo.getUserId(), grantType, vin, validStartTime, batchIds);
            //将字符串转成int数组
            String[] bids = batchIds.split(VoucherConstants.SEPARATOR);
            int[] ints = Arrays.stream(bids).mapToInt(Integer::parseInt).toArray();
            //获取该发放类型的优惠券批次
            List<Map<String, Object>> list = coreOemVoucherGrantTriggerMapper.selectByBusinessType(null, grantType,
                    VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE,
                ArrayUtils.isEmpty(ints) ? null : ints, VoucherConstants.GENERATE);
            List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchGrantInfoVo.class);
            log.info("grantVoucherData：batchGrantInfoVos={},userId={},grantType={},vin={},validStartTime={},batchIds={}", batchGrantInfoVos, userVo.getUserId(), grantType, vin, validStartTime, batchIds);
            batchGrantInfoVos.forEach(batchGrantInfoVo -> {
                batchGrantInfoVo.setGrantLimitPerTime(count(ints, batchGrantInfoVo.getBatchId()));
            });
            //初始化发放结果集合
            List<VoucherBatchMyWelfareInfoVo> resultVos = new ArrayList<>();
            //发送到cdp，执行优惠券发放操作
            sendCdp(batchGrantInfoVos, userVo, resultVos, token, validStartTime, vin);
            VoucherBatchMyWelfareInfoListVo voucherBatchMyWelfareInfoListVo = new VoucherBatchMyWelfareInfoListVo();
            voucherBatchMyWelfareInfoListVo.setList(resultVos);
            voucherBatchMyWelfareInfoListVo.setHasMore(Boolean.FALSE);
            return voucherBatchMyWelfareInfoListVo;
        } finally {
            if (rlock.isLocked() && rlock.isHeldByCurrentThread()) {
                rlock.unlock();
            }
        }
    }

    /**
     * 统计batchI出现的次数
     *
     * @param batchIds
     * @param batchId
     * @return
     */
    private int count(int[] batchIds, int batchId) {
        int count = 0;
        boolean bln = true;
        for (int i = 0; i < batchIds.length; i++) {
            if (batchId == batchIds[i]) {
                count++;
                bln = false;
            }
        }
        if (bln) {
            return count;
        }
        return 0;
    }

    private JSONArray getUserVouchersByCdp(UserVo userVo, String accessToken, Byte voucherType,
                                           String voucherStatus, int pageSize, int currentPage, String businessCode) throws Exception {
        JSONObject requestParam = new JSONObject();
        requestParam.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        requestParam.put(VoucherConstants.AID, userVo.getRefId());
        requestParam.put("currentPage", currentPage);
        requestParam.put("pageSize", pageSize);
        if (null != voucherType) {
            requestParam.put(VoucherConstants.CDP_CARD_TYPE, VoucherConstants.VOUCHER_TYPE_TO_CDP_TYPE_MAP.get(voucherType));
        }
        if (!businessCode.equals("-1")) {
            requestParam.put(VoucherConstants.BUSINESS_CODE, businessCode);
        }
        if (voucherStatus.equals("40")) {
            //目前只查询已过期的优惠劵
            requestParam.put("cardTicketChildStatus", "4001");
        } else {
            requestParam.put(VoucherConstants.CARD_TICKET_STATUS_KEY, voucherStatus);
        }
        String result = voucherByRunlinCoreFeignClient.queryTicketListClient(requestParam);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (!jsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            log.error(String.format("call getUserVouchersByCdp fail2 requestParams=%s, result=%s", requestParam.toString(), result));
            ResultEnum resultEnum = ResultEnum.VOUCHER_CDP_PARAM_FAIL;
            resultEnum.setErrorMessage((String) jsonObject.get("errorMessage"));
            throw new ServiceException(resultEnum);
        }

        JSONArray vouchersJson = jsonObject.getJSONArray(VoucherConstants.RESULT_DATA);
        return vouchersJson;
    }

    @Override
    public Object freezeVoucher(String params) {
        JSONArray requestParam = JSONArray.fromObject(params);
        JSONObject requestParamObject = JSONObject.fromObject(params);
        for (Object obj : requestParam) {
            JSONObject jsonObject = (JSONObject) obj;
            VoucherBatch voucherBatch = coreVoucherBatchMapper.selectByPrimaryKey(jsonObject.getInt(VoucherConstants.BATCH_ID));
            if (voucherBatch.getIsVinLimited() != 1) {
                if (jsonObject.containsKey(VoucherConstants.VIN)) {
                    jsonObject.remove(VoucherConstants.VIN);
                }
            }
            if (jsonObject.containsKey(VoucherConstants.BATCH_ID)) {
                jsonObject.remove("batchId");
            }
        }
        String responseString = voucherByRunlinCoreFeignClient.durationTicket(requestParamObject);
        if (responseString.contains(VoucherConstants.FAILED)) {
            log.error("freezeVoucher fail params={}, responseString={}", params, responseString);
        }
        return JSON.parse(responseString);
    }

    @Override
    public Object cancelFreezeVoucher(String params) {
        String responseString = voucherByRunlinCoreFeignClient.canceldurationTicket(JSONObject.fromObject(params));
        if (responseString.contains(VoucherConstants.FAILED)) {
            log.error("cancelFreezeVoucher fail params={}, responseString={}", params, responseString);
        }
        return JSON.parse(responseString);
    }

    @Override
    public Object redeemVoucher(String params) {
        JSONArray requestParam = JSONArray.fromObject(params);
        JSONObject requestParamObject = JSONObject.fromObject(params);
        for (Object obj : requestParam) {
            JSONObject jsonObject = (JSONObject) obj;
            VoucherBatch voucherBatch = coreVoucherBatchMapper.selectByPrimaryKey(jsonObject.getInt(VoucherConstants.BATCH_ID));
            if (voucherBatch.getIsVinLimited() != 1) {
                if (jsonObject.containsKey(VoucherConstants.VIN)) {
                    jsonObject.remove(VoucherConstants.VIN);
                }
            }
            if (jsonObject.containsKey(VoucherConstants.BATCH_ID)) {
                jsonObject.remove(VoucherConstants.BATCH_ID);
            }
        }
        String responseString = voucherByRunlinCoreFeignClient.cardTicket(requestParamObject);
        if (responseString.contains(VoucherConstants.FAILED)) {
            log.error("redeemVoucher fail params={}, responseString={}", params, responseString);
        }
        return JSON.parse(responseString);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VoucherBatchMyWelfareInfoListVo grantVoucherFilter(UserVo userVo, String token) throws Exception {
        String lockKey = String.format(VoucherConstants.FILTER_GRANT_VOUCHER_LOCK, userVo.getRefId());
        RLock rlock = redissonService.getRLock(lockKey);
        boolean lock = rlock.tryLock();
        try {
            if (!lock) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_ACCESS_LIMIT);
            }
            log.info("grantVoucherFilterParam：userId={}", userVo.getUserId());
            //初始化发放结果集合
            List<VoucherBatchMyWelfareInfoVo> resultVos = new ArrayList<>();
            //根据用户查询用户车辆信息
            List<UserVehicle> userVehicles = queryUserCar(userVo.getRefId());
            log.info("grantVoucherFilterUserVehicleInfo={},userId={} ", userVehicles, userVo.getUserId());
            //这里需要判断用户是否有车辆信息，如果没有就默认为潜客，需要去读取潜客的筛选配置
            userVehicles.forEach(userVehicle -> {
                //如果是已绑车用户
                if (VoucherConstants.BINDING_CAR == userVehicle.getUserType()) {
                    Map<String, Object> map = CommonConvertUtil.transBean2Map(userVehicle);
                    //获取满足用户车辆信息的筛选配置
                    List<Map<String, Object>> list = coreOemVoucherBatchSelectTriggerMappingMapper.queryBatchId(map, VoucherConstants.GENERATE, VoucherConstants.BINDING_CAR);
                    List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchGrantInfoVo.class);
                    //执行发放操作
                    sendGrantVoucher(batchGrantInfoVos, userVehicle, userVo, token, resultVos);
                } else {
                    List<Map<String, Object>> list = coreOemVoucherBatchSelectTriggerMappingMapper.queryBatchIdByNotBindingCar(new HashMap<>(), VoucherConstants.GENERATE, VoucherConstants.NOT_BINDING_CAR);
                    List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchGrantInfoVo.class);
                    //执行发放操作
                    sendGrantVoucher(batchGrantInfoVos, userVehicle, userVo, token, resultVos);
                }
            });
            //发放成功后，发送提醒消息
            if (!resultVos.isEmpty()) {
                sendMassage(resultVos, userVo);
            }
            VoucherBatchMyWelfareInfoListVo voucherBatchMyWelfareInfoListVo = new VoucherBatchMyWelfareInfoListVo();
            voucherBatchMyWelfareInfoListVo.setList(resultVos);
            voucherBatchMyWelfareInfoListVo.setHasMore(Boolean.FALSE);
            return voucherBatchMyWelfareInfoListVo;
        } finally {
            if (rlock.isLocked() && rlock.isHeldByCurrentThread()) {
                rlock.unlock();
            }
        }
    }

    @Override
    @Async(value = "asyncExecutor")
    public void grantDirectingUser(UserVo userVo, String token) throws Exception {
        String lockKey = String.format(VoucherConstants.DIRECTING_GRANT_VOUCHER_LOCK, userVo.getRefId());
        RLock rlock = redissonService.getRLock(lockKey);
        boolean lock = rlock.tryLock();
        try {
            if (!lock) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_ACCESS_LIMIT);
            }
            log.info("grantDirectingUserParam：userId={}", userVo.getUserId());
            List<OemVoucherGrantRecord> oemVoucherGrantRecords = coreOemVoucherGrantRecordMapper.selectUserGrantRecord(userVo.getRefId());
            String mobile = getUserMobile(userVo.getRefId());
            if (StringUtils.isNotEmpty(mobile)) {
                List<OemVoucherGrantRecord> oemVoucherGrantRecordsByMobile = coreOemVoucherGrantRecordMapper.selectUserGrantRecordByMobile(mobile);
                if (null != oemVoucherGrantRecordsByMobile && !oemVoucherGrantRecordsByMobile.isEmpty()) {
                    oemVoucherGrantRecords.addAll(oemVoucherGrantRecordsByMobile);
                }
            }
            List<Integer> grantIds = oemVoucherGrantRecords.stream().map(OemVoucherGrantRecord::getFkGrantTypeId).collect(Collectors.toList());
            Map<Integer, OemVoucherGrantRecord> grantIdToObject = getGrantIdToObject(oemVoucherGrantRecords);
            List<Map<String, Object>> list = coreOemVoucherGrantTriggerMapper.selectByGrantTypeId(VoucherConstants.DIRECTING_GRANT,
                VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE,
                CollectionUtils.isNotEmpty(grantIds) ? grantIds : null, VoucherConstants.GENERATE);
            List<VoucherBatchGrantInfoVo> batchGrantInfoVos = CommonConvertUtil.mapListToVOList(list, VoucherBatchGrantInfoVo.class);
            log.info("查询的grantDirectingUser卡券数据为：" + batchGrantInfoVos);
            //初始化发放结果集合
            List<VoucherBatchMyWelfareInfoVo> resultVos = new ArrayList<>();
            sendVoucherCdpForDirecting(batchGrantInfoVos, resultVos, userVo, token, grantIdToObject);
            //发放成功后，发送提醒消息
            if (!resultVos.isEmpty()) {
                sendMessageForCommonGrant(resultVos, userVo);
            }
        } finally {
            if (rlock.isLocked() && rlock.isHeldByCurrentThread()) {
                rlock.unlock();
            }
        }
    }

    private String getUserMobile(String aid) {
        try {
            Map<String, String> postParamsMap = new HashMap<>();
            postParamsMap.put(CDPConstants.A_ID, aid);
            HttpHeaders headers = new HttpHeaders();
            String result = vehicheByTimaCoreFeignClient
                .getUserAccountByAid(postParamsMap);
            JSONObject object = JSONObject.fromObject(result);
            if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
                if (!object.containsKey(VoucherConstants.RESULT_DATA)) {
                    log.error("getAidsByMobiles fail，aId : {}, result: {}", aid, result);
                    return null;
                }
                JSONObject data = object.getJSONObject(VoucherConstants.RESULT_DATA);
                return data.getString(VoucherConstants.MOBILE);
            }
        } catch (Exception e) {
            log.error("getUserMobile e={},aId={}", e, aid);
        }
        return null;
    }

    private Map<Integer, OemVoucherGrantRecord> getGrantIdToObject(List<OemVoucherGrantRecord> oemVoucherGrantRecords) {
        Map<Integer, OemVoucherGrantRecord> grantIdToObject = new HashMap<>();
        for (OemVoucherGrantRecord oemVoucherGrantRecord : oemVoucherGrantRecords) {
            if (!grantIdToObject.containsKey(oemVoucherGrantRecord.getFkGrantTypeId())) {
                grantIdToObject.put(oemVoucherGrantRecord.getFkGrantTypeId(), oemVoucherGrantRecord);
            }
        }
        return grantIdToObject;
    }

    private String queryCarVin(OemVoucherGrantRecord oemVoucherGrantRecord) {
        if (VoucherConstants.GRANTEE_VIN_TYPE.equals(oemVoucherGrantRecord.getGranteeType())) {
            return oemVoucherGrantRecord.getGrantee();
        }
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.A_ID, oemVoucherGrantRecord.getAidRef());
        HttpHeaders headers = new HttpHeaders();
        //发送cdp请求获取用户账户信息
        String result = vehicheByTimaCoreFeignClient.getVehicleList(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONArray obj = JSONArray.fromObject(str);
            if (obj.isEmpty()) {
                log.error("queryCarList fail，object : {}, result: {}", urlParamMap, result);
                throw new ServiceException(ResultEnum.VOUCHER_USER_NOT_CAR);
            }
            for (Object o : obj) {
                JSONObject info = JSONObject.fromObject(o);
                //根据业务要求，只随机取一个vin码
                return info.getString(VoucherConstants.VIN);
            }
        }
        return null;
    }

    private void sendGrantVoucher(List<VoucherBatchGrantInfoVo> batchGrantInfoVos, UserVehicle userVehicle, UserVo userVo, String token, List<VoucherBatchMyWelfareInfoVo> resultVos) {
        batchGrantInfoVos.forEach(batchGrantInfoVo -> {
            if (grantFilterBefore(batchGrantInfoVo, userVo, userVehicle.getVin())) {
                //组装数据
                JSONObject paramValues = getJsonObject(batchGrantInfoVo, userVo, null, userVehicle.getVin(), true);
                log.info("grantVoucherFilterToCDPParam=" + paramValues);
                String result = voucherByRunlinCoreFeignClient.ticketGrantDMS(paramValues);
                JSONObject jsonObjectP = JSONObject.fromObject(result);
                log.info("grantVoucherFilterToCDPResult=" + result);
                if (jsonObjectP.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                    //发放成功后更新用户领取次数
                    grantFilterAfter(batchGrantInfoVo, userVo, userVehicle.getVin());
                    //组装数据
                    initResult(jsonObjectP, resultVos, token, batchGrantInfoVo, null, userVehicle.getVin());
                } else {
                    try {
                        log.error("grantVoucherFilterToCDPFail1，result:{}", jsonObjectP.getString(VoucherConstants.RETURN_MESSAGE));
                        throw new Exception((String) jsonObjectP.get(VoucherConstants.RETURN_MESSAGE));
                    } catch (Exception e) {
                        log.error("grantVoucherFilterToCDPFail2 e={},batchGrantInfoVo={}", e, batchGrantInfoVo);
                    }
                }
            }
        });
    }

    private List<UserVehicle> queryUserCar(String refId) {
        Query query = Query.query(Criteria.where("aId").is(Integer.valueOf(refId)));
        List<UserVehicle> list = mongoTemplate.find(query, UserVehicle.class, "user_vehicle");
        return list;
    }

    private List<VoucherPopUpMessageInfo> getVoucherPopUpMessageInfos(List<VoucherBatchMyWelfareInfoVo> resultVos) {
        List<VoucherPopUpMessageInfo> voucherPopUpMessageInfos = new ArrayList<>();
        for (VoucherBatchMyWelfareInfoVo voucherBatchMyWelfareInfoVo : resultVos) {
            VoucherPopUpMessageInfo voucherPopUpMessageInfo = new VoucherPopUpMessageInfo();
            BeanUtils.copyProperties(voucherBatchMyWelfareInfoVo, voucherPopUpMessageInfo);
            voucherPopUpMessageInfos.add(voucherPopUpMessageInfo);
        }
        return voucherPopUpMessageInfos;
    }

    private void sendMassage(List<VoucherBatchMyWelfareInfoVo> resultVos, UserVo userVo) {
        Map<Integer, List<VoucherBatchMyWelfareInfoVo>> collect = resultVos.stream().collect(Collectors.groupingBy(VoucherBatchMyWelfareInfoVo::getGrantTypeId));
        //发送消息
        for (Integer grantId : collect.keySet()) {
            //获取筛选发送配置
            OemVoucherSelectGrantTrigger oemVoucherSelectGrantTrigger = coreOemVoucherSelectGrantTriggerMapper.selectByPrimaryKey(grantId);
            //判断是否需要进行应用消息提醒
            if (VoucherConstants.MESSAGE_NOTICE.equals(oemVoucherSelectGrantTrigger.getAppMessageNotice())
                    || VoucherConstants.POP_UP_MESSAGE_NOTICE.equals(oemVoucherSelectGrantTrigger.getPopUpMessageNotice())) {
                MessageVoucherContent content = new MessageVoucherContent();
                content.setDetailUrl(VoucherConstants.DETAIL_URL);
                content.setPostImageUrl(voucherPic);
                content.setPopUpMessageNotice(oemVoucherSelectGrantTrigger.getPopUpMessageNotice());
                content.setType(MessageType.VOUCHER);
                content.setPostContent(oemVoucherSelectGrantTrigger.getVoucherReceivedMessageContent());
                content.setVouchers(getVoucherPopUpMessageInfos(collect.get(grantId)));
                //发送消息
                String result = messageCoreFeignClient.sendVoucherMessage(getSendVoucherMessageVo(userVo.getRefId(), VoucherConstants.TITLE, oemVoucherSelectGrantTrigger.getVoucherReceivedMessageContent(),
                        content, RequestConstants.CDP_MSG_NOTICE_TYPE));
                log.info(VoucherConstants.SEND_MSG_RESULT + result);
            }
        }
    }

    private SendVoucherMessageVo getSendVoucherMessageVo(String refId, String title,
        String content, MessageVoucherContent params, String messageType) {
        return new SendVoucherMessageVo(refId, title, content, messageType, params);
    }

    private void sendMessageForCommonGrant(List<VoucherBatchMyWelfareInfoVo> resultVos, UserVo userVo) {
        Map<Integer, List<VoucherBatchMyWelfareInfoVo>> collect = resultVos.stream().collect(Collectors.groupingBy(VoucherBatchMyWelfareInfoVo::getGrantTypeId));
        //发送消息
        for (Integer grantId : collect.keySet()) {
            //获取筛选发送配置
            OemVoucherGrantTrigger oemVoucherGrantTrigger = coreOemVoucherGrantTriggerMapper.selectByPrimaryKey(grantId);
            //判断是否需要进行应用消息提醒
            if (VoucherConstants.MESSAGE_NOTICE.equals(oemVoucherGrantTrigger.getAppMessageNotice())
                    || VoucherConstants.POP_UP_MESSAGE_NOTICE.equals(oemVoucherGrantTrigger.getPopUpMessageNotice())) {
                MessageVoucherContent content = new MessageVoucherContent();
                content.setDetailUrl(VoucherConstants.DETAIL_URL);
                content.setPostImageUrl(voucherPic);
                content.setPopUpMessageNotice(oemVoucherGrantTrigger.getPopUpMessageNotice());
                content.setType(MessageType.VOUCHER);
                content.setPostContent(oemVoucherGrantTrigger.getVoucherReceivedMessageContent());
                content.setVouchers(getVoucherPopUpMessageInfos(collect.get(grantId)));
                //发送消息
                String result = messageCoreFeignClient.sendVoucherMessage(getSendVoucherMessageVo(userVo.getRefId(), VoucherConstants.TITLE, oemVoucherGrantTrigger.getVoucherReceivedMessageContent(),
                        content, RequestConstants.CDP_MSG_NOTICE_TYPE));
                log.info(VoucherConstants.SEND_MSG_RESULT + result);
            }
        }
    }

    private void grantFilterAfter(VoucherBatchGrantInfoVo batchGrantInfoVo, UserVo userVo, String vin) {
        OemVoucherUserSelectTriggerReceiptionMapping add = new OemVoucherUserSelectTriggerReceiptionMapping();
        add.setAidRef(userVo.getRefId());
        add.setFkSelectGrantId(batchGrantInfoVo.getGrantTypeId());
        add.setLastTimes(0);//领取后的次数都为0
        add.setFkBatchId(batchGrantInfoVo.getBatchId());
        add.setVin(StringUtils.isEmpty(vin) ? null : vin);
        coreOemVoucherUserSelectTriggerReceiptionMappingMapper.insertSelective(add);
    }

    private boolean grantFilterBefore(VoucherBatchGrantInfoVo batchGrantInfoVo, UserVo userVo,
        String vin) {
        boolean flag = false;
        //判断当前用户是否已有领取记录
        OemVoucherUserSelectTriggerReceiptionMapping receiptionMapping = coreOemVoucherUserSelectTriggerReceiptionMappingMapper
            .selectByGrantIdAndUserId(batchGrantInfoVo.getGrantTypeId(), userVo.getRefId(),
                batchGrantInfoVo.getBatchId(), vin);
        if (null == receiptionMapping) {
            flag = true;
            batchGrantInfoVo.setCurLastGrantTimes(VoucherConstants.DEFAULT_LAST_TIMES);
        }
        return flag;
    }

    public ThirdpartShopResultVo getThirdpartShops(String cityCode, String shopName, Integer batchId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        PageHelper.orderBy("id desc");
        String tmpCityCode = null;
        if (!Objects.isNull(cityCode)) {
            tmpCityCode = cityCode.substring(VoucherConstants.INIT_OFFSET, VoucherConstants.INT_FOUR) + VoucherConstants.CITY_SUFFIX;
        }
        //将4个特殊的直辖市code转成对应省的code
        if (VoucherConstants.MUNICIPALITIES.contains(cityCode)) {
            tmpCityCode = cityCode.substring(0, 2) + VoucherConstants.PROVINCE_SUFFIX;
        }
        List<VoucherLimitedDealerMapping> shops = coreVoucherLimitedDealerMappingMapper.selectShopByCityAndShopName(tmpCityCode, shopName, batchId);
        List<ThirdpartShopVo> thirdpartShopVos = new ArrayList<>();
        shops.forEach(shop -> {
            ThirdpartShopVo thirdpartShopVo = new ThirdpartShopVo();
            BeanUtils.copyProperties(shop, thirdpartShopVo);
            thirdpartShopVos.add(thirdpartShopVo);
        });
        PageInfo<VoucherLimitedDealerMapping> pageInfo = new PageInfo<>(shops);
        ThirdpartShopResultVo thirdpartShopResultVo = new ThirdpartShopResultVo();
        thirdpartShopResultVo.setShops(thirdpartShopVos);
        thirdpartShopResultVo.setHasMore(!pageInfo.isIsLastPage());
        return thirdpartShopResultVo;
    }
}
