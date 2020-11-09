package com.fawvw.ms.voucher.core.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherSelectGrantTriggerMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CoreOemVoucherSelectGrantTriggerMapper extends OemVoucherSelectGrantTriggerMapper {

    List<Map<String, Object>> selectGrantInfoByQueryParam(@Param("batchIds") List<Integer> batchIds);
}