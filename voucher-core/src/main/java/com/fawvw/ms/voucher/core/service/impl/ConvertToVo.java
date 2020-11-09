package com.fawvw.ms.voucher.core.service.impl;
/*
 * Project: com.fawvw.ms.oneappserver.services.voucher.impl
 *
 * File Created at 2019-08-17
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */


import com.fawvw.ms.oa.core.utils.DateUtil;
import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.DealerInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.PartInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchMyWelfareInfoVo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import jodd.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangyunjiao
 * @Type ConvertToVo
 * @Desc
 * @date 2019-08-17 16:35
 */
public final class ConvertToVo {

    private ConvertToVo() {
    }

    public static List<VoucherBatchMyWelfareInfoVo> convertJsonToVoucherInfoVo(
        JSONArray vouchersJson,
        Map<String, VoucherBatch> templateId2VoucherBatch,
        Map<Integer, List<DealerInfoVo>> batchId2Dealers,
        String voucherStatus,
        String businessCode,
        Byte voucherType,
        Map<String, List<DealerInfoVo>> historyRedeemCode2Dealers,
        Map<String, List<DealerInfoVo>> redeemCode2Dealers,
        Map<Integer, List<PartInfoVo>> batchId2PartInfos) throws Exception {
        List<VoucherBatchMyWelfareInfoVo> voucherInfos = new ArrayList<>();
        for (Object json : vouchersJson) {
            VoucherBatchMyWelfareInfoVo voucherInfo = new VoucherBatchMyWelfareInfoVo();
            JSONObject jsonObject = (JSONObject) json;
            String templateId = jsonObject.getString(VoucherConstants.CDP_GENERATE_ID);
            String redeemCode = jsonObject.getString(VoucherConstants.REDEEM_CODE);
            voucherInfo.setRedeemCode(redeemCode);
            VoucherBatch voucherBatch = templateId2VoucherBatch.get(templateId);
            if (null != voucherBatch) {
                if (null != voucherType && voucherType.equals(VoucherConstants.REDEEM_VALUE_VOUCHER)
                    && businessCode.equals(VoucherConstants.CDP_TAKE_AND_DELIVER_VECHILE_BUSSINESS)
                    && !voucherBatch.getVoucherType()
                    .equals(VoucherConstants.REDEEM_VALUE_VOUCHER)) {
                    continue;
                }
                voucherInfo.setBatchId(voucherBatch.getId());
                voucherInfo.setVoucherType(voucherBatch.getVoucherType());
                if (voucherBatch.getVoucherType().equals(VoucherConstants.PACKAGE_VOUCHER)) {
                    //特殊处理，isDealerLimited本意是代表可以核销的经销商。但是对于套餐劵是可以发劵的经销商，
                    //可以谁发的劵，谁才能核销
                    voucherInfo.setIsDealerLimited(Byte.valueOf("1"));
                    voucherInfo.setDealerList(redeemCode2Dealers.get(redeemCode));
                } else {
                    voucherInfo.setIsDealerLimited(voucherBatch.getIsDealerLimited());
                    voucherInfo.setDealerList(batchId2Dealers.get(voucherBatch.getId()));
                }
                voucherInfo.setIsVinLimited(voucherBatch.getIsVinLimited());
                voucherInfo.setBusinessType(voucherBatch.getBusinessType());
                voucherInfo.setRedeemValue(voucherBatch.getRedeemValue());
                voucherInfo.setMinimumSpendAmount(voucherBatch.getMinimumSpendAmount());
                voucherInfo.setBusinessTypeOption(getBusinessTypeOptions(voucherBatch));
                voucherInfo.setPartInfos(batchId2PartInfos.get(voucherBatch.getId()));
                voucherInfo.setIsPartCodeLimited(voucherBatch.getIsPartCodeLimited());
                if (voucherBatch.getRedeemChannel().equals(VoucherConstants.REDEEM_IN_DMS)) {
                    voucherInfo.setRedeemType(voucherBatch.getRedeemType());
                } else {
                    voucherInfo.setRedeemType(VoucherConstants.DEFAULT_REDEEM_TYPE);
                }
                voucherInfo.setCarMake(wrapCarModels(batchId2PartInfos.get(voucherBatch.getId())));
                //组装异业券
                wrapThirdpartVoucher(voucherBatch, voucherInfo);
            } else {
                adaptHistoryVoucher(historyRedeemCode2Dealers, voucherInfo, redeemCode, json);
            }

            Date redeemStartTime = DateUtil
                .dateParse((String) jsonObject.get("starttime"), DateUtil.DATE_TIME_PATTERN);
            Date redeemEndTime = DateUtil
                .dateParse((String) jsonObject.get("endtime"), DateUtil.DATE_TIME_PATTERN);

            voucherInfo.setVoucherTemplateId(templateId);
            voucherInfo.setVoucherName((String) jsonObject.get("cardTicketName"));
            voucherInfo.setUsageRule((String) jsonObject.get("usageRule"));
            voucherInfo.setExpiryDateType(VoucherConstants.FIXED_EXPIRED_DATE);
            voucherInfo.setRedeemStartTime(DateUtil.dateFormat(redeemStartTime, DateUtil.DATE_TIME_SLANTING_BAR_PATTERN));
            voucherInfo.setRedeemEndTime(DateUtil.dateFormat(redeemEndTime, DateUtil.DATE_TIME_SLANTING_BAR_PATTERN));
            voucherInfo.setVoucherTemplateId((String) jsonObject.get(VoucherConstants.CDP_GENERATE_ID));
            if (null == voucherInfo.getBusinessType()) {
                voucherInfo.setBusinessType(VoucherConstants.CPD_TYPE_TO_BUSINESS_TYPE_MAP
                    .get((String) jsonObject.get(VoucherConstants.BUSINESS_CODE)));
            }
            if (jsonObject.get(VoucherConstants.VIN).equals(VoucherConstants.STRING_NULL)) {
                voucherInfo.setVin(null);
            } else {
                voucherInfo.setVin((String) jsonObject.get(VoucherConstants.VIN));
            }
            voucherInfo.setVoucherStatus(voucherStatus);
            voucherInfo.setChangeCodeId((String) jsonObject.get("changecodeId"));
            voucherInfos.add(voucherInfo);
        }

        return voucherInfos;
    }

    private static void wrapThirdpartVoucher(VoucherBatch voucherBatch, VoucherBatchMyWelfareInfoVo voucherInfo) {
        voucherInfo.setShopName(voucherBatch.getThirdpartShopName());
        voucherInfo.setShopUrl(voucherBatch.getShopUrl());
        voucherInfo.setLogoUrl(voucherBatch.getLogoUrl());
        if (VoucherConstants.THIRDPART_VOUCHER.equals(voucherBatch.getVoucherType())
                && voucherBatch.getThirdpartCodeType().equals(VoucherConstants.CODE_TYPE_SIGNAL)) {
            voucherInfo.setRedeemCode(voucherBatch.getThirdpartRedeemCode());
        }
        if (VoucherConstants.THIRDPART_VOUCHER.equals(voucherBatch.getVoucherType())
                && voucherBatch.getThirdpartCodeType().equals(VoucherConstants.CODE_TYPE_EMPTY)) {
            voucherInfo.setRedeemCode(null);
        }
    }

    public static String getBusinessTypeOptions(VoucherBatch voucherBatch) {
        if (null == voucherBatch) {
            return "";
        }
        String businessTypeName = VoucherConstants.BUSINESS_TYPE_MAP
            .getOrDefault(voucherBatch.getBusinessType(), "");
        if (voucherBatch.getBusinessType()
            .equals(VoucherConstants.REPAIR_AND_MAINTAION_BUSSINESS)) {
            String businessTypeOptions = voucherBatch.getBusinessTypeOption();
            List<String> businessTypes = new ArrayList<>();
            if (null != businessTypeOptions && !businessTypeOptions.equals("")) {
                String[] businessTypeOptionList = businessTypeOptions.split(VoucherConstants.SEPARATOR);
                for (String businessType : businessTypeOptionList) {
                    businessTypes.add(businessType + VoucherConstants.CROSSING_SPLIT
                        + VoucherConstants.BUSSINESS_VALUE_OPTION.get(businessType));
                }
            }
            String businessTypeOptionsName = " (" + StringUtil.join(businessTypes.toArray(), VoucherConstants.SEPARATOR) + ")";
            return businessTypeOptionsName.equals("") ? businessTypeName
                : businessTypeName + businessTypeOptionsName;
        } else if (voucherBatch.getBusinessType().equals(VoucherConstants.MAINTAION_BUSSINESS)) {
            return "维修保养 (2-保养)";
        } else {
            return businessTypeName;
        }
    }

    private static String wrapCarModels(List<PartInfoVo> partInfoVos) {
        if (null == partInfoVos) {
            return null;
        }
        Set<String> carModels = new HashSet<>();
        for (PartInfoVo partInfoVo : partInfoVos) {
            carModels.add(partInfoVo.getCarModel());
        }
        return StringUtil.join(carModels.toArray(), VoucherConstants.SEPARATOR);
    }

    private static void adaptHistoryVoucher(Map<String, List<DealerInfoVo>> historyRedeemCode2Dealers,
                                     VoucherBatchMyWelfareInfoVo voucherInfo, String redeemCode, Object json) {
        JSONObject jsonObject = (JSONObject) json;
        if (historyRedeemCode2Dealers.containsKey(redeemCode)) {
            voucherInfo.setDealerList(historyRedeemCode2Dealers.get(redeemCode));
            voucherInfo.setIsDealerLimited(VoucherConstants.SOME_DEALERS);
        } else {
            voucherInfo.setIsDealerLimited(VoucherConstants.ALL_DEALERS);
        }
        if (jsonObject.get(VoucherConstants.VIN).equals(VoucherConstants.STRING_NULL) || jsonObject.get(VoucherConstants.VIN).equals("")) {
            voucherInfo.setIsVinLimited(VoucherConstants.NO_LIMITED_VIN);
        } else {
            voucherInfo.setIsVinLimited(VoucherConstants.LIMITED_VIN);
        }
        String cardTicketType = jsonObject.getString(VoucherConstants.CDP_CARD_TYPE);
        String valueTxt = jsonObject.getString(VoucherConstants.VALUE_TXT);
        if (cardTicketType.equals(VoucherConstants.CDP_DJQ_CARD)
                && (valueTxt.equals("0") || valueTxt.equals(""))) {
            voucherInfo.setVoucherType(VoucherConstants.PACKAGE_VOUCHER);
        } else {
            if (!valueTxt.equals("null")) {
                voucherInfo.setRedeemValue(valueTxt);
            }
            voucherInfo.setVoucherType(VoucherConstants.CART_TICKET_TYPE_TO_VOUCHER_TYPE.get(cardTicketType));
        }
    }

    public static String getSerialNumber(Integer grantTypeId, String refId, Integer batchId, Integer lastTime, String vin) {
        if (Objects.isNull(vin)) {
            return String.format("%d%d%s%d", grantTypeId, batchId, refId, lastTime);
        } else {
            return String.format("%d%d%s%d%s", grantTypeId, batchId, refId, lastTime, vin);
        }
    }
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-08-17 zhangyunjiao create
 */