package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherGrantTriggerMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssOemVoucherGrantTriggerMapper extends OemVoucherGrantTriggerMapper {

    List<Map<String, Object>> selectByBusinessType(@Param("businessType") Integer businessType,
        @Param("grantType") Byte grantType,
        @Param("active") Byte active, @Param("deleted") Byte deleted,
        @Param("batchIds") int[] batchIds, @Param("status") Byte status);

    List<Map<String, Object>> voucherGrantList(@Param("grantType") Byte grantType,
        @Param("active") Byte active, @Param("deleted") Byte deleted,
        @Param("statusList") List<Byte> statusList,
        @Param("createTime") String createTime, @Param("redeemChannel") Byte redeemChannel);

    int countBusinessType(@Param("businessType") Byte businessType,
        @Param("grantType") Byte grantType, @Param("status") Byte status,
        @Param("active") Byte active, @Param("deleted") Byte deleted);

    List selectByBatchId(Integer batchId);

    List<Map<String, Object>> selectDirectingGrantResults(Byte importType, Byte isGrant,
        Long importStartTime, Long importEndTime);

    List<Map<String, Object>> selectByGrantTypeId(@Param("grantType") Byte grantType,
        @Param("active") Byte active, @Param("deleted") Byte deleted,
        @Param("grantTypeIds") List<Integer> grantTypeIds, @Param("status") Byte status);

    List<Map<String, Object>> selectByQueryParam(@Param("businessType") Byte businessType,
        @Param("grantType") Byte grantType, @Param("batchIds") List<Integer> batchIds);
}