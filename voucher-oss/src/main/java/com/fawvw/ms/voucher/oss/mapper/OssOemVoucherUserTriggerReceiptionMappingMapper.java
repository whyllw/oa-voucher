package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherUserTriggerReceiptionMappingMapper;
import com.fawvw.ms.voucher.basedao.model.OemVoucherUserTriggerReceiptionMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssOemVoucherUserTriggerReceiptionMappingMapper extends
    OemVoucherUserTriggerReceiptionMappingMapper {

    OemVoucherUserTriggerReceiptionMapping selectByUserId(@Param("aid") String aid);

    OemVoucherUserTriggerReceiptionMapping selectByGrantIdAndUserId(
        @Param("grantTypeId") Integer grantTypeId, @Param("refId") String refId,
        @Param("batchId") Integer batchId);

    Integer countGrantNum(@Param("grantTypeId") Integer grantTypeId, @Param("refId") String refId,
        @Param("batchId") Integer batchId);
}