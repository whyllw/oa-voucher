package com.fawvw.ms.voucher.core.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherBatchTriggerMappingMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CoreOemVoucherBatchTriggerMappingMapper extends
    OemVoucherBatchTriggerMappingMapper {

    int selectCount(@Param("batchId") Integer batchId, @Param("active") Byte active,
        @Param("deleted") Byte deleted);

    List<Map<String, Object>> selectByGrantTypeId(Integer grantTypeId);
}