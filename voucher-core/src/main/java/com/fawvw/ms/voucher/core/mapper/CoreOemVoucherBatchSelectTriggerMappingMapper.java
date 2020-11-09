package com.fawvw.ms.voucher.core.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherBatchSelectTriggerMappingMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CoreOemVoucherBatchSelectTriggerMappingMapper extends
    OemVoucherBatchSelectTriggerMappingMapper {

    int selectCount(@Param("batchId") Integer batchId, @Param("active") Byte active,
        @Param("deleted") Byte deleted);

    List<Map<String, Object>> queryBatchId(@Param("c") Map<String, Object> map,
        @Param("status") Byte status, @Param("bindingStatus") Byte bindingStatus);

    List<Map<String, Object>> queryBatchIdByNotBindingCar(@Param("c") Map<String, Object> map,
        @Param("status") Byte status, @Param("bindingStatus") Byte bindingStatus);

    List selectTriggerByBatchId(Integer batchId);
}