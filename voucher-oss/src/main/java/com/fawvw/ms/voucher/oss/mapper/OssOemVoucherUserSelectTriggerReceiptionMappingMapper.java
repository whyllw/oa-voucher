package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherUserSelectTriggerReceiptionMappingMapper;
import com.fawvw.ms.voucher.basedao.model.OemVoucherUserSelectTriggerReceiptionMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssOemVoucherUserSelectTriggerReceiptionMappingMapper extends
    OemVoucherUserSelectTriggerReceiptionMappingMapper {

    OemVoucherUserSelectTriggerReceiptionMapping selectByGrantIdAndUserId(
        @Param("grantTypeId") Integer grantTypeId, @Param("refId") String refId,
        @Param("batchId") Integer batchId, @Param("vin") String vin);
}