package com.fawvw.ms.voucher.oss.service.impl;

import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.oa.core.constants.CDPConstants;
import com.fawvw.ms.oa.core.constants.RequestConstants;
import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.redis.redisson.RedissonService;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.oa.core.utils.CommonConvertUtil;
import com.fawvw.ms.oa.core.utils.ServiceExceptionUtil;
import com.fawvw.ms.voucher.basedao.model.EtlUserVoucherBatchMappingRecord;
import com.fawvw.ms.voucher.basedao.model.OemVoucherBatchTriggerMapping;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantRecord;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantTrigger;
import com.fawvw.ms.voucher.basedao.model.OperationUser;
import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import com.fawvw.ms.voucher.basedao.model.VoucherBatchDealerGrantRecord;
import com.fawvw.ms.voucher.basedao.model.VoucherDealerGrantRecord;
import com.fawvw.ms.voucher.basedao.model.voucher.UserVehicle;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.enums.result.VoucherErrorEnum;
import com.fawvw.ms.voucher.basedomain.vo.DealerCodeVo;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantVoucherRequest;
import com.fawvw.ms.voucher.basedomain.vo.PackageVoucherInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.SendVoucherBatchGrantVo;
import com.fawvw.ms.voucher.basedomain.vo.TicketUserVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantBySelectParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantCarInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherLimitedPartsMapping;
import com.fawvw.ms.voucher.basedomain.vo.VoucherSmsParam;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWithDrawInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWriteOffInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.req.MessageType;
import com.fawvw.ms.voucher.basedomain.vo.req.MessageVoucherContent;
import com.fawvw.ms.voucher.basedomain.vo.req.SendVoucherMessageVo;
import com.fawvw.ms.voucher.baseservice.client.message.core.MessageCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.client.operation.OperationUserFeignClient;
import com.fawvw.ms.voucher.baseservice.client.runlin.VoucherByRunlinCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.client.tima.VehicheByTimaCoreFeignClient;
import com.fawvw.ms.voucher.baseservice.util.DateUtil;
import com.fawvw.ms.voucher.oss.mapper.OssEtlUserVoucherBatchMappingRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherBatchSelectTriggerMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherBatchTriggerMappingMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherGrantRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssOemVoucherGrantTriggerMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherBatchDealerGrantRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherBatchMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherDealerGrantRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherLimitedPartsMappingMapper;
import com.fawvw.ms.voucher.oss.service.OssVoucherCommonService;
import com.fawvw.ms.voucher.oss.service.OssVoucherGrantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OssVoucherGrantServiceImpl implements OssVoucherGrantService {
    @Autowired
    private OssOemVoucherGrantTriggerMapper ossOemVoucherGrantTriggerMapper;

    @Autowired
    private OssOemVoucherBatchTriggerMappingMapper ossOemVoucherBatchTriggerMappingMapper;

    @Autowired
    private OssVoucherBatchMapper ossVoucherBatchMapper;

    @Autowired
    private OssVoucherDealerGrantRecordMapper ossVoucherDealerGrantRecordMapper;

    @Autowired
    private OssVoucherBatchDealerGrantRecordMapper ossVoucherBatchDealerGrantRecordMapper;

    @Autowired
    private OssEtlUserVoucherBatchMappingRecordMapper ossEtlUserVoucherBatchMappingRecordMapper;

    @Autowired
    private OssVoucherLimitedPartsMappingMapper ossVoucherLimitedPartsMappingMapper;

    @Autowired
    private OssOemVoucherBatchSelectTriggerMappingMapper ossOemVoucherBatchSelectTriggerMappingMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private MessageCoreFeignClient messageCoreFeignClient;

    @Autowired
    private OperationUserFeignClient operationUserFeignClient;

    @Autowired
    private OssVoucherCommonService ossVoucherCommonService;

    @Autowired
    private OssOemVoucherGrantRecordMapper ossOemVoucherGrantRecordMapper;

    @Autowired
    private VoucherByRunlinCoreFeignClient voucherByRunlinCoreFeignClient;

    @Autowired
    private VehicheByTimaCoreFeignClient vehicheByTimaCoreFeignClient;

    @Value(value = "${img.message.voucher-pic}")
    String voucherPic;
    @Value(value = "${voucher.card-ticket-Key}")
    String cardTicketKey;
    @Value(value = "${msg.channel-app-key}")
    String msgChannelAppKey;

    private static final int NUM_SIX = 6;
    /**
     * 新增发放信息记录
     *
     * @param voucherGrantInfoVo
     * @return Integer
     * @throws Exception
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer createVoucherGrant(VoucherGrantInfoVo voucherGrantInfoVo) throws Exception {
        List<Integer> batchId = voucherGrantInfoVo.getVoucherBatchId();
        if (batchId.isEmpty()) {
            throw new ServiceException(ResultEnum.ONE_ACTIVITY_PARAMETER_ERROR);
        }
        //如果是自动发放，需要判断同一个发放场景只能存在一个配置
        if (VoucherConstants.AUTOMATIC.equals(voucherGrantInfoVo.getGrantType()) && isOnlyOne(
            voucherGrantInfoVo.getBusinessType(), voucherGrantInfoVo.getGrantType())) {
            throw new ServiceException(ResultEnum.VOUCHER_BUSINESS_TYPE_FAIL);
        }
        OemVoucherGrantTrigger voucherGrantTrigger = new OemVoucherGrantTrigger();
        BeanUtils.copyProperties(voucherGrantInfoVo, voucherGrantTrigger);
        if (null == voucherGrantTrigger.getGrantTimesLimit()) {
            voucherGrantTrigger.setGrantTimesLimit(1);
        }
        if (VoucherConstants.AUTOMATIC.equals(voucherGrantInfoVo.getGrantType())
            && VoucherConstants.GRANT_MAINTAION_AND_ORDER_TYPE.equals(voucherGrantInfoVo.getBusinessType())) {
            voucherGrantTrigger.setGrantTimeType(voucherGrantInfoVo.getGrantTimeType());
        }
        //持久化发放信息
        ossOemVoucherGrantTriggerMapper.insertSelective(voucherGrantTrigger);
        int grantId = voucherGrantTrigger.getId();
        saveBatchGrant(batchId, voucherGrantTrigger);
        return grantId;
    }

    private boolean isOnlyOne(Byte businessType, Byte grantType) {
        boolean flag = false;
        int count = ossOemVoucherGrantTriggerMapper.countBusinessType(businessType, grantType, VoucherConstants.GENERATE,
                VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<VoucherGrantCarInfoVo> grantCarList(Byte type, String value) {
        List<VoucherGrantCarInfoVo> list = new ArrayList<>();
        switch (type) {
            case 1:
                initCarByPhone(list, value);//根据手机号码查询车辆列表
                break;
            case 2:
                initCarByVin(list, value);//根据Vin码查询车辆列表
                break;
            default:
                break;
        }
        return list;
    }

    @Override
    public List<PackageVoucherInfoVo> getPackageVoucherList(String aid, String carAge, AdminUserVO adminUserVO) {
        //获取套餐券批次列表
        List<Map<String, Object>> mapList = ossVoucherBatchMapper.selectByVoucherType(VoucherConstants.PACKAGE_VOUCHER,
                VoucherConstants.GENERATE, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE, carAge, adminUserVO.getRefId());
        List<PackageVoucherInfoVo> packageVoucherInfoVos = CommonConvertUtil
            .mapListToVOList(mapList, PackageVoucherInfoVo.class);
        //获取套餐券库存
        packageVoucherInfoVos.forEach(packageVoucherInfoVo -> {
            getNotReceivedNum(aid, packageVoucherInfoVo);
        });
        return packageVoucherInfoVos;
    }

    @Override
    public int grantPackageVoucher(VoucherDealerGrantRecordVo voucherDealerGrantRecordVo, AdminUserVO adminUserVO) {
        String lockKey = String.format(VoucherConstants.DEALER_GRANT_VOUCHER_LOCK, adminUserVO.getRefId());
        RLock rLock = redissonService.getRLock(lockKey);
        boolean lock = rLock.tryLock();
        try {
            if (!lock) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_ACCESS_LIMIT);
            }
            //TODO：业务要求去掉vin码限制，先临时注释掉
            //List<VoucherBatchDealerGrantRecord> batchRecords = ossVoucherBatchDealerGrantRecordMapper.queryByVin(voucherDealerGrantRecordVo.getVin());
            //if (batchRecords.isEmpty()) {
            //    List<VoucherDealerGrantRecord> records = ossVoucherDealerGrantRecordMapper.queryByVin(voucherDealerGrantRecordVo.getVin());
            //    if (!records.isEmpty()) {
            //        throw new ServiceException(ResultEnum.SAME_VIN_GRANT_PACKGE_VOUCHER);
            //    }
            //} else {
            //    throw new ServiceException(ResultEnum.SAME_VIN_GRANT_PACKGE_VOUCHER);
            //}

            //获取该发放套餐券信息
            VoucherBatch batch = ossVoucherBatchMapper.selectByPrimaryKey(voucherDealerGrantRecordVo.getBatchId());
            //组装数据
            JSONObject paramValues = getJsonObject(batch, voucherDealerGrantRecordVo, adminUserVO);
            log.info("grantPackageVoucher paramValues=" + paramValues);
            String result = voucherByRunlinCoreFeignClient.ticketGrantDMS(paramValues);
            JSONObject jsonObjectP = JSONObject.fromObject(result);

            log.info("grantPackageVoucher result=" + result);
            int recordId = 0;
            if (jsonObjectP.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
                try {
                    //持久化经销商发放记录
                    recordId = saveGrantRecord(jsonObjectP, voucherDealerGrantRecordVo, adminUserVO.getRefId(), batch);
                    sendMessage(voucherDealerGrantRecordVo.getAid(),
                        String.format(VoucherConstants.VOUCHER_MSG_CONTENT, voucherDealerGrantRecordVo.getGrantNum(), batch.getVoucherName()));
                } catch (Exception e) {
                    log.error("savePackageVoucherGrantRecordFail paramValues={}, result={}, error={}", paramValues, result, e.toString());
                }
            } else {
                log.error("grantPackageVoucherFail paramValues={}, result={}", paramValues, result);
                ResultEnum resultEnum = ResultEnum.GRANT_VOUCHER_FAIL;
                resultEnum.setErrorMessage((String) jsonObjectP.get(VoucherConstants.RETURN_MESSAGE));
                throw new ServiceException(resultEnum);
            }
            return recordId;
        } finally {
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }

    private void sendMessage(Integer refId, String msgContent) {
        MessageVoucherContent content = new MessageVoucherContent();
        content.setDetailUrl(VoucherConstants.DETAIL_URL);
        content.setPostImageUrl(voucherPic);
        content.setType(MessageType.VOUCHER);
        content.setPostContent(msgContent);
        //发送消息
        String result = messageCoreFeignClient.sendVoucherMessage(getSendVoucherMessageVo(String.valueOf(refId), VoucherConstants.TITLE, msgContent,
            content, RequestConstants.CDP_MSG_NOTICE_TYPE));
        log.info("发送消息返回的数据为:" + result);
    }

    private SendVoucherMessageVo getSendVoucherMessageVo(String refId, String title,
        String content, MessageVoucherContent params, String messageType) {
        return new SendVoucherMessageVo(refId, title, content, messageType, params);
    }

    @Override
    public Boolean withDrawGrantVoucher(Integer recordId, AdminUserVO adminUserVO) {
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
        PageHelper.orderBy("r.create_time desc");
        //获取发放记录列表
        List<Map<String, Object>> maps = ossVoucherBatchDealerGrantRecordMapper.grantBatchRecordList(vin, phone, adminUserVO.getRefId());
        List<VoucherDealerGrantRecordInfoVo> list = CommonConvertUtil.mapListToVOList(maps, VoucherDealerGrantRecordInfoVo.class);
        PageInfo<VoucherDealerGrantRecordInfoVo> pageInfo = new PageInfo<>(list);
        PageInfo<Map<String, Object>> pageInfoAll = new PageInfo<>(maps);
        pageInfo.setTotal(pageInfoAll.getTotal());
        return pageInfo;
    }

    @Override
    public Result getVoucherInfo(String redeemCode, AdminUserVO adminUserVO) {
        /*VoucherWriteOffInfoVo voucherWriteOffInfoVo = new VoucherWriteOffInfoVo();
        //判断该核销码是否有效
        //获取该核销码详情
        JSONObject paramValues = new JSONObject();
        paramValues.put(VoucherConstants.CDP_CARD_KEY, serviceCommonConfig.getCardTicketKey());
        paramValues.put("redeemcode", redeemCode);
        String result = TCEUtil.getInstance().callApiWithoutAccessTokenByPost(paramValues, VoucherConstants.TICKET_LIST);
        JSONObject object = JSONObject.fromObject(result);

        if ("SUCCESS".equals(object.get("returnStatus"))) {
            String data = object.getString("data");
            JSONArray array = JSONArray.fromObject(data);
            if (array.isEmpty()) {
                return ResultUtils.fail(ResultEnum.VOUCHER_NOT_FOUND_FAIL);
            }
            //判断是否已经核销
            for (Object obj : array) {
                JSONObject jsonObject = JSONObject.fromObject(obj);
                String cardTicketStatus = jsonObject.getString("cardTicketStatus");
                if (VoucherConstants.CARD_TICKET_STATUS.equals(cardTicketStatus)) {
                    return ResultUtils.fail(ResultEnum.VOUCHER_WRITE_OFF_FAIL);
                }
                //获取核销卡券信息
                initWriteOffInfo(voucherWriteOffInfoVo, jsonObject, adminUserVO.getRefId());
            }

        } else {
            return ResultUtils.fail(object.getString("returnStatusCode"), object.getString("returnMessage"));
        }
        return ResultUtils.success(voucherWriteOffInfoVo);*/
        return null;
    }

    @Override
    public Result writeOffVoucher(VoucherWriteOffInfoVo voucherWriteOffInfoVo, AdminUserVO adminUserVO) {

        /*log.info("核销的券码信息为:" + voucherWriteOffInfoVo);
        //组装数据
        JSONObject jsonObject = initData(voucherWriteOffInfoVo, adminUserVO);
        //执行核销
        String result = TCEUtil.getInstance().callApiWithoutAccessTokenByPost(jsonObject, VoucherConstants.DMS_REDEEM_VOUCHER);
        JSONObject object = JSONObject.fromObject(result);
        if ("SUCCESS".equals(object.get("returnStatus"))) {
            return ResultUtils.success(Boolean.TRUE);
        } else {
            return ResultUtils.fail(object.getString("returnStatusCode"), object.getString("returnMessage"));
        }*/
        return null;
    }

    @Override
    public Integer getUserCount(VoucherGrantBySelectParamVo voucherGrantConfigParamVo) {

        Criteria criteria = new Criteria();
        //判断用户类型
        Byte userType = initUserType(voucherGrantConfigParamVo.getUserType());
        if (null != userType) {
            criteria.and("userType").is(userType);
        }
        //判断车型
        /*String makeId = voucherGrantConfigParamVo.getMakeId();
        if (!StringUtils.isEmpty(makeId)) {
            criteria.and("seriesId").in(initMakeData(makeId));
        }*/
        String makeName = voucherGrantConfigParamVo.getMakeName();
        if (!StringUtils.isEmpty(makeName)) {
            criteria.and("seriesName").in(initMakeData(makeName));
        }
        //判断车牌
        String vehicleNo = voucherGrantConfigParamVo.getVehicleNo();
        if (!StringUtils.isEmpty(vehicleNo)) {
            criteria.and("simpleVehicleNumber").in(initCarNumData(vehicleNo));
        }
        //判断销售日期
        String saleStartTime = voucherGrantConfigParamVo.getSaleStartTime();
        String saleEndTime = voucherGrantConfigParamVo.getSaleEndTime();
        Criteria saleStartTimeCriteria = new Criteria();
        if (null != saleStartTime) {
            saleStartTimeCriteria.and(VoucherConstants.SALE_DATE).gte(saleStartTime);
        }
        Criteria saleStartEndCriteria = new Criteria();
        if (null != saleEndTime) {
            saleStartEndCriteria.and(VoucherConstants.SALE_DATE).lte(saleEndTime);
        }
        //判断最后入库时间
        String lastBackStartTime = voucherGrantConfigParamVo.getLastBackStartTime();
        String lastBackEndTime = voucherGrantConfigParamVo.getLastBackEndTime();
        Criteria backCriteria = new Criteria();
        if (null != lastBackStartTime) {
            backCriteria.and(VoucherConstants.LAST_BACK_STORE_TIME).gte(lastBackStartTime);
        }
        Criteria backEndCriteria = new Criteria();
        if (null != lastBackEndTime) {
            backEndCriteria.and(VoucherConstants.LAST_BACK_STORE_TIME).lte(lastBackEndTime);
        }
        //判断入库里程
        Integer mileageMin = voucherGrantConfigParamVo.getMileageMin();
        Integer mileageMax = voucherGrantConfigParamVo.getMileageMax();
        Criteria mileageCriteria = new Criteria();
        if (null != mileageMin) {
            mileageCriteria.and(VoucherConstants.LAST_BACK_STORE_MILEAGE).gte(mileageMin);
        }
        Criteria mileageMaxCriteria = new Criteria();
        if (null != mileageMax) {
            mileageMaxCriteria.and(VoucherConstants.LAST_BACK_STORE_MILEAGE).lte(mileageMax);
        }
        criteria.andOperator(saleStartTimeCriteria, saleStartEndCriteria, backCriteria, backEndCriteria, mileageCriteria, mileageMaxCriteria);
        Query query = Query.query(criteria);
        log.info("查询的QUERY为:" + query.toString());
        long count = mongoTemplate.count(query, UserVehicle.class, "user_vehicle");
        return Math.toIntExact(count);
    }

    private Byte initUserType(Byte userType) {
        byte type = 0;
        switch (userType) {
            case 0:
                type = 2;
                break;
            case 1:
                type = 1;
                break;
            default:
                break;
        }
        return type;
    }

    private List<String> initCarNumData(String vehicleNo) {
        String[] ids = vehicleNo.split(VoucherConstants.SEPARATOR);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        return list;
    }

    private List<String> initMakeData(String makeName) {
        String[] names = makeName.split(",");
        List<String> list = Arrays.asList(names);
        return list;
    }


    private JSONObject initData(VoucherWriteOffInfoVo voucherWriteOffInfoVo, AdminUserVO adminUserVO) {
        JSONObject object = new JSONObject();
        try {
            //初始化当前时间
            String nowDate = DateUtil.dateFormat(new Date(), DateUtil.DATE_TIME_PATTERN);
            object.put("redeemCode", voucherWriteOffInfoVo.getRedeemCode());//兑换码
            object.put("preferentialAmount", "");//优惠金额
            object.put(VoucherConstants.DEALER_CODE, adminUserVO.getRefId());//经销商代码
            object.put(VoucherConstants.VIN, voucherWriteOffInfoVo.getVin());//vin码
            object.put("phone", voucherWriteOffInfoVo.getPhone());//电话号码
            object.put("idCard", voucherWriteOffInfoVo.getIdCard());//身份证
            object.put("useTime", nowDate);//使用时间
            object.put("verificationNo", voucherWriteOffInfoVo.getOrderNumber());//核销单号
            object.put("roType", "");//委托书类别
            object.put("roCreateDate", nowDate);//委托书建立日期
            object.put("awardingDate", nowDate);//兑奖时间
            object.put("amountPayable", "");//应付金额
            object.put("amountReceived", "");//实付金额
            object.put("itemList", "");//项目表
            object.put("partList", "");//备件表
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return object;
    }

    private void initWriteOffInfo(VoucherWriteOffInfoVo voucherWriteOffInfoVo, JSONObject jsonObject, String refId) {
        //判断该经销商是否能核销此优惠券
        String dealercodeList = jsonObject.getString("dealercodeList");
        JSONArray array = JSONArray.fromObject(dealercodeList);
        if (!array.contains(refId)) {
            throw new ServiceException(ResultEnum.VOUCHER_CAN_NOT_WRITE_OFF_FAIL);
        }
        String generateId = jsonObject.getString(VoucherConstants.CDP_GENERATE_ID);//卡券模版ID
        //查询卡券批次信息
        List<String> tids = new ArrayList<>();
        tids.add(generateId);
        List<VoucherBatch> list = ossVoucherBatchMapper.selectBytemplateIdList(tids);
        VoucherBatch batch = list.get(0);
        //获取卡券附件
        List<VoucherLimitedPartsMapping> limitedPartsMappings = ossVoucherLimitedPartsMappingMapper.selectByBatchId(batch.getId());
        //TODO 还未获取到发方法名称和服务代码
        //voucherWriteOffInfoVo.setGrantName();//发放方名称
        //voucherWriteOffInfoVo.setDealerCode();//服务代码
        voucherWriteOffInfoVo.setBatchCode(batch.getBatchCode());//优惠券批次
        voucherWriteOffInfoVo.setRedeemCode(jsonObject.getString(VoucherConstants.REDEEM_CODE));//券码
        voucherWriteOffInfoVo.setVoucherName(batch.getVoucherName());//优惠券名称
        voucherWriteOffInfoVo.setVoucherType(String.valueOf(batch.getVoucherType()));//优惠券类型
        voucherWriteOffInfoVo.setBusinessType(String.valueOf(batch.getBusinessType()));//限定业务类型
        voucherWriteOffInfoVo.setRedeemValue(jsonObject.getString(VoucherConstants.VALUE_TXT));//券面金额
        voucherWriteOffInfoVo.setMinimumSpendAmount(batch.getMinimumSpendAmount());//使用消费额度
        voucherWriteOffInfoVo.setExpiredTime(jsonObject.getString("endtime"));//过期时间
        voucherWriteOffInfoVo.setLimitedPartsMappings(limitedPartsMappings);//备/附件
    }

    private int saveGrantRecord(JSONObject jsonObjectP, VoucherDealerGrantRecordVo voucherDealerGrantRecordVo, String refId, VoucherBatch batch) {
        VoucherDealerGrantRecord record = new VoucherDealerGrantRecord();
        VoucherBatchDealerGrantRecord batchRecord = new VoucherBatchDealerGrantRecord();
        //获取经销商信息
        wrapDealerInfo(refId, record);
        Date createTime = new Date();
        batchRecord.setAidRef(String.valueOf(voucherDealerGrantRecordVo.getAid()));
        batchRecord.setMobile(voucherDealerGrantRecordVo.getPhone());
        batchRecord.setVin(voucherDealerGrantRecordVo.getVin());
        batchRecord.setDealerCode(refId);//经销商ID
        batchRecord.setGrantCount((null == voucherDealerGrantRecordVo.getGrantNum()) ? 0 : voucherDealerGrantRecordVo.getGrantNum());//发放数量
        batchRecord.setFkBatchId(voucherDealerGrantRecordVo.getBatchId());//批次ID
        batchRecord.setCarModelName((null == voucherDealerGrantRecordVo.getVehicleMode()) ? "" : voucherDealerGrantRecordVo.getVehicleMode());//车型名称
        batchRecord.setStatus(VoucherConstants.ALREADY_GRANT);//发放状态
        batchRecord.setFkCarModelId((null == voucherDealerGrantRecordVo.getCarModelId()) ? 0 : voucherDealerGrantRecordVo.getCarModelId());
        batchRecord.setCreateTime(createTime);
        ossVoucherBatchDealerGrantRecordMapper.insertSelective(batchRecord);

        record.setAidRef(String.valueOf(voucherDealerGrantRecordVo.getAid()));//发放用户ID
        record.setMobile(voucherDealerGrantRecordVo.getPhone());//手机号码
        record.setVin(voucherDealerGrantRecordVo.getVin());//vin码
        record.setDealerCode(refId);//经销商ID
        record.setGrantCount((null == voucherDealerGrantRecordVo.getGrantNum()) ? 0 : voucherDealerGrantRecordVo.getGrantNum());//发放数量
        record.setFkBatchId(voucherDealerGrantRecordVo.getBatchId());//批次ID
        record.setCarModelName((null == voucherDealerGrantRecordVo.getVehicleMode()) ? "" : voucherDealerGrantRecordVo.getVehicleMode());//车型名称
        record.setStatus(VoucherConstants.ALREADY_GRANT);//发放状态
        record.setFkCarModelId((null == voucherDealerGrantRecordVo.getCarModelId()) ? 0 : voucherDealerGrantRecordVo.getCarModelId());
        record.setFkBatchGrantRecordId(batchRecord.getId());
        record.setCreateTime(createTime);
        JSONArray array = JSONArray.fromObject(jsonObjectP.get(VoucherConstants.RESULT_DATA));
        for (Object obj : array) {
            JSONObject object = JSONObject.fromObject(obj);
            record.setVoucherCodeId(object.getString("changecodeId"));//CDP 卡劵id
            record.setRedeemCode(object.getString(VoucherConstants.REDEEM_CODE));//兑换码
            //持久化操作
            ossVoucherDealerGrantRecordMapper.insertSelective(record);
            addEtlVoucherRecord(record, batch, voucherDealerGrantRecordVo);
        }
        return batchRecord.getId();
    }

    private void addEtlVoucherRecord(VoucherDealerGrantRecord record, VoucherBatch batch, VoucherDealerGrantRecordVo voucherDealerGrantRecordVo) {
        EtlUserVoucherBatchMappingRecord etlUserVoucherBatchMappingRecord = new EtlUserVoucherBatchMappingRecord();
        etlUserVoucherBatchMappingRecord.setFkBatchId(batch.getId());
        etlUserVoucherBatchMappingRecord.setVoucherStatus(VoucherConstants.ALREADY_RECIVE_VOUCHER);
        etlUserVoucherBatchMappingRecord.setAidRef(record.getAidRef());
        etlUserVoucherBatchMappingRecord.setMobile(record.getMobile());
        etlUserVoucherBatchMappingRecord.setVin(record.getVin());
        etlUserVoucherBatchMappingRecord.setGrantSmallRegionName(record.getGrantSmallRegionName());
        etlUserVoucherBatchMappingRecord.setGrantSmallRegionCode(record.getGrantSmallRegionCode());
        etlUserVoucherBatchMappingRecord.setGrantBigRegionName(record.getGrantBigRegionName());
        etlUserVoucherBatchMappingRecord.setGrantBigRegionCode(record.getGrantBigRegionCode());
        etlUserVoucherBatchMappingRecord.setGrantTime(new Date());
        etlUserVoucherBatchMappingRecord.setGrantDealerCode(record.getDealerCode());
        etlUserVoucherBatchMappingRecord.setGrantDealerName(record.getGrantDealerName());
        etlUserVoucherBatchMappingRecord.setDrawTime(new Date());
        etlUserVoucherBatchMappingRecord.setMakeName(record.getCarModelName());
        etlUserVoucherBatchMappingRecord.setRedeemCode(record.getRedeemCode());
        etlUserVoucherBatchMappingRecord.setRedeemCode(record.getRedeemCode());
        etlUserVoucherBatchMappingRecord.setGrantChannel(VoucherConstants.DLR_CHANNEL);
        etlUserVoucherBatchMappingRecord.setVoucherId(record.getVoucherCodeId());
        etlUserVoucherBatchMappingRecord.setVoucherTemplateId(batch.getVoucherTemplateId());
        etlUserVoucherBatchMappingRecord.setVoucherType(batch.getVoucherType());
        etlUserVoucherBatchMappingRecord.setBusinessType(batch.getBusinessType());
        etlUserVoucherBatchMappingRecord.setVehicleSaleDate(voucherDealerGrantRecordVo.getVehicleSaleDate());
        ossEtlUserVoucherBatchMappingRecordMapper.insertSelective(etlUserVoucherBatchMappingRecord);
    }

    private void wrapDealerInfo(String refId, VoucherDealerGrantRecord record) {
        try {
            queryDealerInfo(refId, record);
            if (null == record.getGrantBigRegionCode() || null == record.getGrantBigRegionName()
                    || null == record.getGrantSmallRegionCode() || null == record.getGrantSmallRegionName()
                    || null == record.getGrantDealerName()) {
                retryQueryDealerInfo(refId, record);
            }
        } catch (Exception e) {
            log.error("wrapDealerInfoFail e=", e);
        }
    }

    private void queryDealerInfo(String refId, VoucherDealerGrantRecord record) {
        JSONObject object = new JSONObject();
        object.put("tenantId", "2");
        object.put(VoucherConstants.DEALER_CODE, refId);
        String result = voucherByRunlinCoreFeignClient.querydealerinfo(object);
        JSONObject jsonObject = JSONObject.fromObject(result);
        try {
            if (VoucherConstants.RESULT_STATUS.equals(jsonObject.getString(VoucherConstants.RETURN_STATUS))) {
                JSONArray array = JSONArray.fromObject(jsonObject.get("list"));
                for (Object o : array) {
                    JSONObject recordObject = JSONObject.fromObject(o);
                    record.setGrantBigRegionCode(recordObject.getString("saleBigRegionCode"));//发放大区代码
                    record.setGrantBigRegionName(recordObject.getString("saleBigRegion"));//发放大区名字
                    record.setGrantSmallRegionCode(recordObject.getString("saleSmallRegionCode"));//发放小区代码
                    record.setGrantSmallRegionName(recordObject.getString("saleSmallRegion"));//发放小区名称
                    record.setGrantDealerName(recordObject.getString("dealerName"));//发放方经销商名称
                    record.setGrantDealerCode(refId);//发放方经销商代码
                }
            } else {
                log.error("queryDealerInfo fail1，object : {}, result: {}", object, result);
            }
        } catch (Exception e) {
            log.error("queryDealerInfo fail2，object : {}, result: {}", object, result);
        }
    }

    private void retryQueryDealerInfo(String refId, VoucherDealerGrantRecord record) {
        PageHelper.startPage(1, 1);
        VoucherDealerGrantRecord voucherDealerGrantRecord = ossVoucherDealerGrantRecordMapper.queryByDealerCode(refId);
        if (null != voucherDealerGrantRecord) {
            record.setGrantBigRegionCode(voucherDealerGrantRecord.getGrantBigRegionCode());//发放大区代码
            record.setGrantBigRegionName(voucherDealerGrantRecord.getGrantBigRegionName());//发放大区名字
            record.setGrantSmallRegionCode(voucherDealerGrantRecord.getGrantSmallRegionCode());//发放小区代码
            record.setGrantSmallRegionName(voucherDealerGrantRecord.getGrantSmallRegionName());//发放小区名称
            record.setGrantDealerName(voucherDealerGrantRecord.getGrantDealerName());//发放方经销商名称
        } else {
            queryDealerInfo(refId, record);
            if (null == record.getGrantDealerName()) {
                Result<OperationUser> operationUserResult = operationUserFeignClient
                    .getOperationUserByRefId(refId);
                OperationUser operationUser = null;
                if (operationUserResult.getReturnStatus()
                    .equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
                    operationUser = operationUserResult.getData();
                } else {
                    ServiceExceptionUtil
                        .throwServiceException(VoucherErrorEnum.GET_OPERATION_USER_ERROR);
                }
                record.setGrantDealerName(operationUser.getIdentityTag());
            }
        }
    }

    private JSONObject getJsonObject(VoucherBatch batch, VoucherDealerGrantRecordVo voucherDealerGrantRecordVo, AdminUserVO adminUserVO) {
        List<TicketUserVo> ticketUserVos = new ArrayList<>();
        initTicketUserVo(ticketUserVos, batch, voucherDealerGrantRecordVo, adminUserVO);
        SendVoucherBatchGrantVo sendVoucherBatchGrantVo = new SendVoucherBatchGrantVo();
        sendVoucherBatchGrantVo.setCardTicketKey(cardTicketKey);
        sendVoucherBatchGrantVo.setChannelName(VoucherConstants.DLR_CHANNEL);
        sendVoucherBatchGrantVo.setGenerateId(batch.getVoucherTemplateId());
        sendVoucherBatchGrantVo.setTicketUserList(ticketUserVos);
        sendVoucherBatchGrantVo.setSerialNumber(String.format("%d%d%d", batch.getId(), voucherDealerGrantRecordVo.getAid(), new Date().getTime() / VoucherConstants.MAX_IMPORT_CODE_COUNT));
        JSONObject object = JSONObject.fromObject(sendVoucherBatchGrantVo);
        return object;
    }

    private void initTicketUserVo(List<TicketUserVo> ticketUserVos, VoucherBatch batch, VoucherDealerGrantRecordVo voucherDealerGrantRecordVo, AdminUserVO adminUserVO) {

        TicketUserVo ticketUserVo = new TicketUserVo();
        ticketUserVo.setAid(String.valueOf(voucherDealerGrantRecordVo.getAid()));//用户ID
        initTime(batch, ticketUserVo, null);//初始化有效期时间
        ticketUserVo.setGenerateNum(String.valueOf(voucherDealerGrantRecordVo.getGrantNum()));//发放数量
        ticketUserVo.setVin(voucherDealerGrantRecordVo.getVin());
        //查询经销商代码列表
        List<DealerCodeVo> dealerCodeVos = new ArrayList<>();
        DealerCodeVo dealerCodeVo = new DealerCodeVo();
        dealerCodeVo.setDealercode(adminUserVO.getRefId());
        dealerCodeVos.add(dealerCodeVo);
        ticketUserVo.setDealercodeList(dealerCodeVos);//经销商代码列表
        ticketUserVos.add(ticketUserVo);

    }

    private void initTime(VoucherBatch batch, TicketUserVo ticketUserVo, Date validStartTime) {
        //如果为固定有效期类型
        if (batch.getExpiryDateType() == VoucherConstants.FIXED) {
            try {
                ticketUserVo.setStarttime(DateUtil.dateFormat(batch.getRedeemStartTime(), DateUtil.DATE_TIME_PATTERN));//有效期开始时间
                Date endTime = DateUtil.dateFormatToCurDayLastTime(batch.getRedeemEndTime());
                ticketUserVo.setEndtime(DateUtil.dateFormat(endTime, DateUtil.DATE_TIME_PATTERN));//有效期结束时间
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //如果为动态有效期
        } else {
            try {
                //获取当前时间
                Date nowDate = new Date();
                Date startTime = (validStartTime == null || validStartTime.getTime() < nowDate.getTime()) ? new Date() : validStartTime;
                Integer validDays = (null == batch.getValidForNumberOfDays() || 0 == batch
                    .getValidForNumberOfDays()) ? 0 : batch.getValidForNumberOfDays() - 1;
                Date date = DateUtil.dateAdd(startTime, validDays, Boolean.TRUE);
                ticketUserVo.setStarttime(DateUtil.dateFormat(startTime, DateUtil.DATE_TIME_PATTERN));//有效期开始时间
                String endDateString = DateUtil.dateFormat(date, DateUtil.DATE_PATTERN) + VoucherConstants.DATE_PATTERN_END;
                Date endDate = DateUtil.dateParse(endDateString, DateUtil.DATE_TIME_PATTERN);
                date = DateUtil.dateFormatToCurDayLastTime(endDate);
                ticketUserVo.setEndtime(DateUtil.dateFormat(date, DateUtil.DATE_TIME_PATTERN));//有效期结束时间
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void getNotReceivedNum(String aid, PackageVoucherInfoVo packageVoucherInfoVo) {
        JSONObject urlParamMap = new JSONObject();
        urlParamMap.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        urlParamMap.put(VoucherConstants.CDP_GENERATE_ID, packageVoucherInfoVo.getVoucherTemplateId());
        String result = voucherByRunlinCoreFeignClient.queryTicketStatusNumServer(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONObject obj = JSONObject.fromObject(str);
            packageVoucherInfoVo.setNotReceivedNum(obj.getString("notReceivedNum"));//待领取数量
        }
    }

    private void initCarByVin(List<VoucherGrantCarInfoVo> list, String value) {
        //根据vin码获取车辆详情
        getCarInfoByVin(list, value);
        //根据vin码获取车主aid
        getAidByVin(list, value);
        //根据aid获取车主信息
        getUserInfoByAid(list, value);

    }

    private void getUserInfoByAid(List<VoucherGrantCarInfoVo> list, String value) {
        Map<String, String> urlParamMap = new HashMap<>();
        List<String> aids = new ArrayList<>();
        list.forEach(voucherGrantCarInfoVo -> {
            aids.add(voucherGrantCarInfoVo.getAid());
        });
        String aid = String.join(VoucherConstants.SEPARATOR, aids);
        urlParamMap.put(CDPConstants.A_IDS, aid);
        String result = vehicheByTimaCoreFeignClient.getSimpleUserInfoByAids(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONArray array = JSONArray.fromObject(str);
            for (Object obj : array) {
                JSONObject jsonObject = JSONObject.fromObject(obj);
                VoucherGrantCarInfoVo carInfoVo = list.get(0);
                if (null == carInfoVo) {
                    log.error("getUserInfoByAid carInfoVo is null obj:{}", jsonObject);
                    continue;
                }
                carInfoVo.setClientName(
                    jsonObject.containsKey(VoucherConstants.NICK_NAME) ? jsonObject
                        .getString(VoucherConstants.NICK_NAME) : String
                        .format(VoucherConstants.NICK_NAME_PRE,
                            jsonObject.getString(VoucherConstants.AID)));
                carInfoVo.setPhone(jsonObject.containsKey(VoucherConstants.MOBILE) ? jsonObject.getString(VoucherConstants.MOBILE) : null);
            }
        }

    }

    private void getAidByVin(List<VoucherGrantCarInfoVo> list, String value) {
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.VIN, value);
        //发送cdp请求获取用户账户信息
        String result = vehicheByTimaCoreFeignClient.getOwnerAidByVin(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            if (!object.containsKey(VoucherConstants.RESULT_DATA)) {
                log.error("getAidByVin fail，object : {}, result: {}", urlParamMap, result);
                throw new ServiceException(ResultEnum.VOUCHER_VIN_NOT_USER_FAIL);
            }
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONObject obj = JSONObject.fromObject(str);
            if (!list.isEmpty()) {
                list.get(0).setAid(String.valueOf(obj.getLong(VoucherConstants.AID)));
            }
        }
    }

    private void getCarInfoByVin(List<VoucherGrantCarInfoVo> list, String value) {
        VoucherGrantCarInfoVo carInfoVo = new VoucherGrantCarInfoVo();
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.VIN, value);
        //发送cdp请求获取用户账户信息
        String result = vehicheByTimaCoreFeignClient.getVehicleDetail(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            if (!object.containsKey(VoucherConstants.RESULT_DATA)) {
                log.error("getCarInfoByVin fail，object : {}, result: {}", urlParamMap, result);
                throw new ServiceException(ResultEnum.VOUCHER_QUERY_VIN_FAIL);
            }
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONObject obj = JSONObject.fromObject(str);
            //车型
            carInfoVo.setVehicleMode(!obj.containsKey(VoucherConstants.VEHICLE_SERIES_NAME) ? null : obj.getString(VoucherConstants.VEHICLE_SERIES_NAME));
            carInfoVo.setVehicleNumber(!obj.containsKey(VoucherConstants.VEHICLE_NUMBER) ? null : obj.getString(VoucherConstants.VEHICLE_NUMBER));
            //购买日期
            carInfoVo.setPurchaseDate(!obj.containsKey(VoucherConstants.PURCHASE_DATE) ? null : new Date(obj.getLong(VoucherConstants.PURCHASE_DATE)));
            carInfoVo.setVin(!obj.containsKey(VoucherConstants.VIN) ? null : obj.getString(VoucherConstants.VIN));
            carInfoVo.setCarModelId(VoucherConstants.DEFAULT_FAKE_VALUE);
            try {
                if (null != carInfoVo.getPurchaseDate()) {
                    int age = getMonthSpace(DateUtil.dateFormat(carInfoVo.getPurchaseDate(), DateUtil.DATE_PATTERN),
                            DateUtil.dateFormat(new Date(), DateUtil.DATE_PATTERN));
                    carInfoVo.setCarAge(String.valueOf(age));//车龄
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(carInfoVo);
        }
    }

    private void initCarByPhone(List<VoucherGrantCarInfoVo> list, String value) {
        //判断该手机号是否注册
        isRegistered(value);
        //判断手机号是否绑车
        //isBindingCar(value);
        //获取用户信息
        VoucherGrantCarInfoVo carInfoVo = queryUserInfo(value);
        if (null != carInfoVo) {
            //根据aid获取用户车辆列表
            queryCarList(carInfoVo, list);
        }


    }

    private void queryCarList(VoucherGrantCarInfoVo carInfoVo, List<VoucherGrantCarInfoVo> list) {
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.A_ID, carInfoVo.getAid());
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
                VoucherGrantCarInfoVo infoVo = new VoucherGrantCarInfoVo();
                BeanUtils.copyProperties(carInfoVo, infoVo);
                infoVo.setVehicleMode(!info.containsKey(VoucherConstants.VEHICLE_SERIES_NAME) ? null : info.getString(VoucherConstants.VEHICLE_SERIES_NAME));//车型
                infoVo.setVehicleNumber(!info.containsKey(VoucherConstants.VEHICLE_NUMBER) ? null : info.getString(VoucherConstants.VEHICLE_NUMBER));//车牌号
                infoVo.setVin(!info.containsKey(VoucherConstants.VIN) ? null : info.getString(VoucherConstants.VIN));//车架号
                infoVo.setPurchaseDate(!info.containsKey(VoucherConstants.PURCHASE_DATE) ? null : new Date(info.getLong(VoucherConstants.PURCHASE_DATE)));//购买日期
                infoVo.setCarModelId(VoucherConstants.DEFAULT_FAKE_VALUE);//车辆ID
                try {
                    if (null != infoVo.getPurchaseDate()) {
                        int age = getMonthSpace(DateUtil.dateFormat(infoVo.getPurchaseDate(), DateUtil.DATE_PATTERN),
                                DateUtil.dateFormat(new Date(), DateUtil.DATE_PATTERN));
                        infoVo.setCarAge(String.valueOf(age));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                list.add(infoVo);
            }
        }
    }

    /**
     * @param date1
     * @param date2
     * @return int
     * @throws ParseException
     */
    public int getMonthSpace(String date1, String date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        if (c1.getTime().getTime() < c2.getTime().getTime()) {
            int dayDiff = (c2.get(Calendar.DAY_OF_MONTH) > c1.get(Calendar.DAY_OF_MONTH)) ? 1 : 0;
            result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * Calendar.MINUTE + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH)) + dayDiff;
        }
        return result;

    }

    private VoucherGrantCarInfoVo queryUserInfo(String value) {
        VoucherGrantCarInfoVo carInfoVo = new VoucherGrantCarInfoVo();
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.ACCOUNT, value);
        urlParamMap.put(CDPConstants.TENANTID, VoucherConstants.BRAND_VM);
        //发送cdp请求获取用户账户信息
        String result = vehicheByTimaCoreFeignClient.getUserAccount(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONObject obj = JSONObject.fromObject(str);
            carInfoVo.setAid(obj.getString(VoucherConstants.ID));//用户ID
            carInfoVo.setClientName(obj.containsKey(VoucherConstants.OTHER_NICK_NAME) ? obj
                .getString(VoucherConstants.OTHER_NICK_NAME) : String
                .format(VoucherConstants.NICK_NAME_PRE, obj.getString(VoucherConstants.ID)));//用户名
            carInfoVo.setPhone(obj.containsKey(VoucherConstants.MOBILE) ? obj.getString(VoucherConstants.MOBILE) : null);//手机号
        } else {
            log.error("queryUserInfo fail，object : {}, result: {}", urlParamMap, result);
            throw new ServiceException(ResultEnum.VOUCHER_QUERY_USER_FAIL);
        }
        return carInfoVo;
    }

    private void isBindingCar(String value) {

    }

    private void isRegistered(String value) {
        //发送cdp请求查询该用户和品牌是否已注册
        Map<String, String> urlParamMap = new HashMap<>();
        urlParamMap.put(CDPConstants.ACCOUNT, value);
        urlParamMap.put(VoucherConstants.BRAND, VoucherConstants.BRAND_VM);
        //发送cdp请求获取用户账户信息
        String result = vehicheByTimaCoreFeignClient.checkAccountIsRegistered(urlParamMap);
        JSONObject object = JSONObject.fromObject(result);
        if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
            String str = object.getString(VoucherConstants.RESULT_DATA);
            JSONObject obj = JSONObject.fromObject(str);
            if (!obj.getString("isRegistered").equals("YES")) {
                log.error("isRegistered fail，object : {}, result: {}", urlParamMap, result);
                throw new ServiceException(ResultEnum.VOUCHER_PHONE_NOT_REGISTERED);
            }
        }

    }

    private void saveBatchGrant(List<Integer> batchIds, OemVoucherGrantTrigger voucherGrantTrigger) {
        batchIds.forEach(batchId -> {
            //同批次的券只能存在一种发放方式
            int count = ossOemVoucherBatchTriggerMappingMapper.selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
            int selectCount = ossOemVoucherBatchSelectTriggerMappingMapper.selectCount(batchId, VoucherConstants.ENABLE, VoucherConstants.NOT_DELETE);
            if (count > 0 || selectCount > 0) {
                throw new ServiceException(ResultEnum.VOUCHER_GRANT_TYPE_FAIL);
            }
            OemVoucherBatchTriggerMapping batchTriggerMapping = new OemVoucherBatchTriggerMapping();
            batchTriggerMapping.setFkBatchId(batchId);
            batchTriggerMapping.setFkGrantTypeId(voucherGrantTrigger.getId());
            batchTriggerMapping.setCreateTime(new Date());
            ossOemVoucherBatchTriggerMappingMapper.insertSelective(batchTriggerMapping);
        });
    }

    public void grantDirectingUser(DirectingGrantVoucherRequest directingGrantVoucherRequest) throws Exception {
        OemVoucherGrantTrigger oemVoucherGrantTrigger = ossOemVoucherGrantTriggerMapper.selectByPrimaryKey(directingGrantVoucherRequest.getId());
        oemVoucherGrantTrigger.setActive(VoucherConstants.ENABLE);
        oemVoucherGrantTrigger.setAppMessageNotice(directingGrantVoucherRequest.getAppMessageNotice());
        oemVoucherGrantTrigger.setSmsNotice(directingGrantVoucherRequest.getSmsNotice());
        oemVoucherGrantTrigger.setPopUpMessageNotice(directingGrantVoucherRequest.getPopUpMessageNotice());
        oemVoucherGrantTrigger.setVoucherReceivedMessageContent(directingGrantVoucherRequest.getMessageContent());
        ossOemVoucherGrantTriggerMapper.updateByPrimaryKeySelective(oemVoucherGrantTrigger);
    }

    public boolean isValidSmsTemplate(DirectingGrantVoucherRequest directingGrantVoucherRequest) {
        List<OemVoucherGrantRecord> list = ossOemVoucherGrantRecordMapper.selectRecordsByGrantTypeId(directingGrantVoucherRequest.getId());
        for (OemVoucherGrantRecord oemVoucherGrantRecord : list) {
            if (VoucherConstants.MOBILE_SMS_TEMPLATE.equals(directingGrantVoucherRequest.getSmsTemplateId())) {
                return oemVoucherGrantRecord.getGranteeType()
                    .equals(VoucherConstants.GRANTEE_MOBILE_TYPE);
            }
            if (VoucherConstants.VIN_SMS_TEMPLATE.equals(directingGrantVoucherRequest.getSmsTemplateId())) {
                return oemVoucherGrantRecord.getGranteeType().equals(VoucherConstants.GRANTEE_VIN_TYPE);
            }
        }
        return false;
    }

    /*@Override
    @Async(value = "asyncExecutor")
    public void sendVoucherSms(DirectingGrantVoucherRequest directingGrantVoucherRequest) throws Exception {
        Integer pageCount = getGrantVoucherPageCount(directingGrantVoucherRequest);
        List<Map<String, Object>> oemTriggerBatchs = ossOemVoucherBatchTriggerMappingMapper.selectByGrantTypeId(directingGrantVoucherRequest.getId());
        List<VoucherBatchGrantInfoVo> voucherInfoVos = CommonConvertUtil.mapListToVOList(oemTriggerBatchs, VoucherBatchGrantInfoVo.class);
        for (Integer currentPage = 1; currentPage <= pageCount; currentPage++ ) {
            PageHelper.startPage(currentPage, VoucherConstants.MAX_GRANT_VOUCHER_COUNT);
            PageHelper.orderBy("id desc");
            List<OemVoucherGrantRecord> oemVoucherGrantRecords = ossOemVoucherGrantRecordMapper.selectRecordsByGrantTypeId(directingGrantVoucherRequest.getId());
            for (OemVoucherGrantRecord oemVoucherGrantRecord : oemVoucherGrantRecords) {
                for (VoucherBatchGrantInfoVo voucherBatchGrantInfoVo : voucherInfoVos) {
                    boolean isSendSucess = true;
                    List<String> smsInfos = new ArrayList<>();
                    smsInfos.add(voucherBatchGrantInfoVo.getVoucherName());
                    if (VoucherConstants.VIN_SMS_TEMPLATE.equals(directingGrantVoucherRequest.getSmsTemplateId())) {
                        if (null != oemVoucherGrantRecord.getGrantee() && oemVoucherGrantRecord.getGrantee().length() > NUM_SIX) {
                            smsInfos.add(oemVoucherGrantRecord.getGrantee().substring(oemVoucherGrantRecord.getGrantee().length() - NUM_SIX));
                        } else {
                            smsInfos.add(oemVoucherGrantRecord.getGrantee());
                        }
                    }
                    if (voucherBatchGrantInfoVo.getVoucherType().equals(VoucherConstants.REDEEM_VALUE_VOUCHER)) {
                        smsInfos.add(String.format(VoucherConstants.REDEEM_VOUCHER_SMS_CONTENT, voucherBatchGrantInfoVo.getMinimumSpendAmount(),
                                voucherBatchGrantInfoVo.getRedeemValue()));
                    } else if (voucherBatchGrantInfoVo.getVoucherType().equals(VoucherConstants.GOODS_VOUCHER)) {
                        smsInfos.add(String.format(VoucherConstants.GOODS_VOUCHER_SMS_CONTENT, voucherBatchGrantInfoVo.getRedeemValue()));
                    }
                    VoucherSmsParam voucherSmsParam = new VoucherSmsParam();
                    voucherSmsParam.setTplId(directingGrantVoucherRequest.getSmsTemplateId());
                    voucherSmsParam.setChannelAppKey(msgChannelAppKey);
                    voucherSmsParam.setParams(smsInfos);
                    if (oemVoucherGrantRecord.getGranteeType().equals(VoucherConstants.MOBILE_TYPE)) {
                        voucherSmsParam.setMobile(oemVoucherGrantRecord.getGrantee());
                        isSendSucess = sendSms(voucherSmsParam, oemVoucherGrantRecord);
                    } else {
                        List<VoucherGrantCarInfoVo> voucherGrantCarInfoVos = new ArrayList<>();
                        VoucherGrantCarInfoVo voucherGrantCarInfoVo = new VoucherGrantCarInfoVo();
                        voucherGrantCarInfoVo.setAid(oemVoucherGrantRecord.getAidRef());
                        voucherGrantCarInfoVos.add(voucherGrantCarInfoVo);
                        getUserInfoByAid(voucherGrantCarInfoVos, null);
                        for (VoucherGrantCarInfoVo userInfo : voucherGrantCarInfoVos) {
                            voucherSmsParam.setMobile(userInfo.getPhone());
                            isSendSucess = sendSms(voucherSmsParam, oemVoucherGrantRecord);
                        }
                    }
                    if (!isSendSucess) {
                        break;
                    }
                }
            }
        }
    }*/

    private boolean sendSms(VoucherSmsParam voucherSmsParam, OemVoucherGrantRecord oemVoucherGrantRecord) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String result = vehicheByTimaCoreFeignClient.sendSmsMessage(voucherSmsParam);
            JSONObject object = JSONObject.fromObject(result);
            if (object.get(VoucherConstants.RETURN_STATUS).equals(RequestConstants.RETURN_STATUS_SUCCESS)) {
                oemVoucherGrantRecord.setSmsSendStatus(VoucherConstants.SUCCESS);
            } else {
                oemVoucherGrantRecord.setSmsSendStatus(VoucherConstants.FAIL);
                oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(result);
            }
        } catch (Exception e) {
            log.error("sendVoucherSms e=", e);
            oemVoucherGrantRecord.setSmsSendStatus(VoucherConstants.FAIL);
            oemVoucherGrantRecord.setGranteeCheckStatusFailedReason(e.getMessage());
        } finally {
            ossOemVoucherGrantRecordMapper.updateByPrimaryKeySelective(oemVoucherGrantRecord);
        }
        return oemVoucherGrantRecord.getSmsSendStatus().equals(VoucherConstants.SUCCESS);
    }

    private Integer getGrantVoucherPageCount(DirectingGrantVoucherRequest directingGrantVoucherRequest) {
        PageHelper.startPage(1, 1);
        List list = ossOemVoucherGrantRecordMapper.selectRecordsByGrantTypeId(directingGrantVoucherRequest.getId());
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        Long pageCount = pageInfo.getTotal() / VoucherConstants.MAX_GRANT_VOUCHER_COUNT + 1;

        return Integer.valueOf(String.valueOf(pageCount));
    }
}
