package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherSelectGrantTriggerMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssOemVoucherSelectGrantTriggerMapper extends OemVoucherSelectGrantTriggerMapper {

    List<Map<String, Object>> selectGrantInfoByQueryParam(@Param("batchIds") List<Integer> batchIds);
}