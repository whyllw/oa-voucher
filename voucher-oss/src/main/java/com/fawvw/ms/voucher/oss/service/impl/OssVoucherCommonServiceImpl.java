package com.fawvw.ms.voucher.oss.service.impl;
/*
 * Project: com.fawvw.ms.oneappserver.services.admin.impl
 *
 * File Created at 2019-12-10
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */


import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.voucher.basedao.model.EtlUserVoucherBatchMappingRecord;
import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import com.fawvw.ms.voucher.basedao.model.VoucherDealerGrantRecord;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWithDrawInfoVo;
import com.fawvw.ms.voucher.baseservice.client.runlin.VoucherByRunlinCoreFeignClient;
import com.fawvw.ms.voucher.oss.mapper.OssEtlUserVoucherBatchMappingRecordMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherBatchMapper;
import com.fawvw.ms.voucher.oss.mapper.OssVoucherDealerGrantRecordMapper;
import com.fawvw.ms.voucher.oss.service.OssVoucherCommonService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhangyunjiao
 * @Type AdminVoucherCommonServiceImpl
 * @Desc
 * @date 2019-12-10 10:20
 */
@Service
@Slf4j
public class OssVoucherCommonServiceImpl implements OssVoucherCommonService {
    @Autowired
    private OssVoucherDealerGrantRecordMapper ossVoucherDealerGrantRecordMapper;

    @Autowired
    private OssVoucherBatchMapper ossVoucherBatchMapper;

    @Autowired
    private OssEtlUserVoucherBatchMappingRecordMapper ossEtlUserVoucherBatchMappingRecordMapper;

    @Autowired
    private VoucherByRunlinCoreFeignClient voucherByRunlinCoreFeignClient;

    @Value(value = "${voucher.card-ticket-Key}")
    String cardTicketKey;

    @Override
    public void voucherInvalid(List<VoucherDealerGrantRecord> records) {
        List<VoucherWithDrawInfoVo> list = new ArrayList<>();
        int batchId = 0;
        for (VoucherDealerGrantRecord record : records) {
            VoucherWithDrawInfoVo infoVo = new VoucherWithDrawInfoVo();
            batchId = record.getFkBatchId();
            infoVo.setChangecodeId(record.getVoucherCodeId());
            list.add(infoVo);
        }
        JSONObject paramValues = new JSONObject();
        paramValues.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        paramValues.put("cancellationReason", "经销商撤回");//作废原因
        paramValues.put("changecodeList", list);
        log.info("voucherInvalid组装的数据为:" + paramValues);
        String result = voucherByRunlinCoreFeignClient.changecodeAbndon(paramValues);
        JSONObject object = JSONObject.fromObject(result);
        if (VoucherConstants.RESULT_STATUS.equals(object.get(VoucherConstants.RETURN_STATUS))) {
            JSONObject data = JSONObject.fromObject(object.getString(VoucherConstants.RESULT_DATA));
            if ("true".equals(data.getString("success"))) {
                try {
                    //执行释放库存操作
                    addBatchStoreCount(batchId, list.size(), false);
                } catch (Exception e) {
                    log.error("voucherInvalid fail3，paramValues : {}, result: {}", paramValues, result);
                    e.printStackTrace();
                }
                //修改发放记录状态
                updateRecord(records);
            } else {
                log.error("voucherInvalid fail1，paramValues : {}, result: {}", paramValues, result);
                throw new ServiceException(ResultEnum.VOUCHER_WITH_DRAW_FAIL);
            }
        } else {
            log.error("voucherInvalid fail2，paramValues : {}, result: {}", paramValues, result);
            throw new ServiceException(ResultEnum.VOUCHER_ABNDON_FAIL);
        }
    }

    private void updateRecord(List<VoucherDealerGrantRecord> records) {
        records.forEach(voucherDealerGrantRecord -> {
            voucherDealerGrantRecord.setStatus(VoucherConstants.ALREADY_WITH_OUT_GRANT);
            ossVoucherDealerGrantRecordMapper.updateByPrimaryKeySelective(voucherDealerGrantRecord);
            List<EtlUserVoucherBatchMappingRecord> etlVoucherRecords = ossEtlUserVoucherBatchMappingRecordMapper.selectByRedeemCode(voucherDealerGrantRecord.getRedeemCode());
            for (EtlUserVoucherBatchMappingRecord etlVoucherRecord : etlVoucherRecords) {
                etlVoucherRecord.setVoucherStatus(VoucherConstants.ALREADY_WITH_DRAW_VOUCHER);
                ossEtlUserVoucherBatchMappingRecordMapper.updateByPrimaryKeySelective(etlVoucherRecord);
            }
        });
    }

    @Override
    public void addBatchStoreCount(Integer batchId, Integer stockCount, boolean isAddBatchCount) throws Exception {
        VoucherBatch voucherBatch = ossVoucherBatchMapper.selectByPrimaryKey(batchId);
        if (null == voucherBatch) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_NOT_EXIST);
        }
        if (voucherBatch.getStatus().equals(VoucherConstants.FINISH)) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_FINISHED);
        }
        if (null != voucherBatch.getDrawExpireDate() && voucherBatch.getDrawExpireDate().before(new Date())) {
            throw new ServiceException(ResultEnum.VOUCHER_BATCH_EXPIRED);
        }
        JSONObject requestParams = new JSONObject();
        requestParams.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
        requestParams.put(VoucherConstants.CDP_GENERATE_ID, voucherBatch.getVoucherTemplateId());
        requestParams.put("supplyCardTicketNum", stockCount);
        requestParams.put("supplementaryType", 1);
        String result = voucherByRunlinCoreFeignClient.ticketNumExt(requestParams);
        if (null == result) {
            log.info("call addBatchStoreCount fail1 requestParams=" + requestParams);
            throw new ServiceException(ResultEnum.VOUCHER_CDP_RESULT_NULL);
        }
        JSONObject resultJsonObject = JSONObject.fromObject(result);
        if (!resultJsonObject.get(VoucherConstants.RETURN_STATUS).equals(VoucherConstants.RESULT_STATUS)) {
            log.info(String.format("call addBatchStoreCount fail2 requestParams=%s, result=%s", requestParams.toString(), result));
            ResultEnum resultEnum = ResultEnum.VOUCHER_CDP_PARAM_FAIL;
            resultEnum.setErrorMessage((String) resultJsonObject.get(VoucherConstants.RETURN_MESSAGE));
            throw new ServiceException(resultEnum);
        }
        if (isAddBatchCount) {
            voucherBatch.setVoucherCount(voucherBatch.getVoucherCount() + stockCount);
            ossVoucherBatchMapper.updateByPrimaryKeySelective(voucherBatch);
        }
    }

    public Boolean isGrantInfo(List<VoucherDealerGrantRecord> records, String voucherTemplateId, List<VoucherWithDrawInfoVo> list) {
        boolean flag = true;
        //组装发送到cdp的数据
        for (VoucherDealerGrantRecord voucherDealerGrantRecord : records) {
            JSONObject paramValues = new JSONObject();
            paramValues.put(VoucherConstants.CDP_CARD_KEY, cardTicketKey);
            paramValues.put(VoucherConstants.CDP_GENERATE_ID, voucherTemplateId);
            paramValues.put(VoucherConstants.CHANGE_CODE_ID, voucherDealerGrantRecord.getVoucherCodeId());//cdp 卡券id
            log.info("isGrantInfo组装的数据为:" + paramValues);
            String result = voucherByRunlinCoreFeignClient.queryTicketDetail(paramValues);
            JSONObject object = JSONObject.fromObject(result);
            if (VoucherConstants.RESULT_STATUS.equals(object.get(VoucherConstants.RETURN_STATUS))) {
                JSONObject jsonObject = JSONObject.fromObject(object.get(VoucherConstants.RESULT_DATA));
                String cardTicketStatus = jsonObject.getString("cardTicketStatus");
                if (VoucherConstants.CARD_TICKET_STATUS.equals(cardTicketStatus)) {
                    log.error("PACKAGE_VOUCHER_EXIST_USED，paramValues : {}, result: {}", paramValues, result);
                    throw new ServiceException(ResultEnum.PACKAGE_VOUCHER_EXIST_USED);
                }
                if (VoucherConstants.CARD_TICKET_EXPIRED_STATUS.equals(cardTicketStatus)) {
                    log.error("PACKAGE_VOUCHER_EXPIRED，paramValues : {}, result: {}", paramValues, result);
                    throw new ServiceException(ResultEnum.PACKAGE_VOUCHER_EXPIRED);
                }
            } else {
                log.error("isGrantInfo fail，paramValues : {}, result: {}", paramValues, result);
                throw new ServiceException(ResultEnum.VOUCHER_INFO_FAIL);
            }
        }
        return flag;
    }
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-12-10 zhangyunjiao create
 */