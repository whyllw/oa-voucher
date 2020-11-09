package com.fawvw.ms.voucher.basedomain.enums.result;

import lombok.Getter;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/09/10 10:28
 * @Description: 订单错误返回 枚举类
 */
@Getter
public enum  VoucherErrorEnum {

    VOUCHER_NO_GRANT_TIMES("VOUCHER_SERVICE_ERROR.0045","无可用剩余发放次数"),
    BATCH_ExPIRED("VOUCHER_SERVICE_ERROR.0048","该批次已经过期"),
    VOUCHER_VIN_AND_AID_NOT_REF("VOUCHER_SERVICE_ERROR.0046","请使用自己的vin码"),
    CITY_NAME_FORMAT_ERROR("VOUCHER_SERVICE_ERROR.0044","城市名称格式错误"),
    VOUCHER_TEMPLATE_NOT_GENERATE("VOUCHER_SERVICE_ERROR.0041","优惠劵模版未生成"),
    VOUCHER_BATCH_NOT_SUPPORT_IMPORT("VOUCHER_SERVICE_ERROR.0042","该异业劵批次不支持导入劵码"),
    VOUCHER_COUNT_BEYOND("VOUCHER_SERVICE_ERROR.0043","导入劵码数不能超过库存"),
    GET_AREAS_ERROR("GET_AREAS_ERROR_ERROR.0002","区域查询不存在"),
    GET_OPERATION_USER_ERROR("GET_OPERATION_USER_ERROR.0001","用户不存在");

    private String errorCode;
    private String errorMessage;

    VoucherErrorEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
