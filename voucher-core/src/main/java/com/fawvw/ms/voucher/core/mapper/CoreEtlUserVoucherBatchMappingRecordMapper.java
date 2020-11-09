package com.fawvw.ms.voucher.core.mapper;

import com.fawvw.ms.voucher.basedao.mapper.EtlUserVoucherBatchMappingRecordMapper;
import com.fawvw.ms.voucher.basedao.model.EtlUserVoucherBatchMappingRecord;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CoreEtlUserVoucherBatchMappingRecordMapper extends
    EtlUserVoucherBatchMappingRecordMapper {

    List selectVoucherDetailListQueryParam(@Param("conf") Map params,
        @Param("roleType") String roleType,
        @Param("dealerCode") String dealerCode, @Param("grantBatchIds") List<Integer> grantBatchIds);

    List<EtlUserVoucherBatchMappingRecord> selectByRedeemCode(String redeemCode);

    int countVoucherDetailListQueryParam(@Param("conf") Map params,
        @Param("roleType") String roleType, @Param("dealerCode") String dealerCode,
        @Param("grantBatchIds") List<Integer> grantBatchIds);
}