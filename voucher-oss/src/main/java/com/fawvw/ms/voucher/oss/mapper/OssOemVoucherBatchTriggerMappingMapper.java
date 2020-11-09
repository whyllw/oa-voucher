package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherBatchTriggerMappingMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssOemVoucherBatchTriggerMappingMapper extends
    OemVoucherBatchTriggerMappingMapper {

    int selectCount(@Param("batchId") Integer batchId, @Param("active") Byte active,
        @Param("deleted") Byte deleted);

    List<Map<String, Object>> selectByGrantTypeId(Integer grantTypeId);
}