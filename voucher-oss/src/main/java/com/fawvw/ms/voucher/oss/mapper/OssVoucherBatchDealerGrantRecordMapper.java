package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.VoucherBatchDealerGrantRecordMapper;
import com.fawvw.ms.voucher.basedao.model.VoucherBatchDealerGrantRecord;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssVoucherBatchDealerGrantRecordMapper extends
    VoucherBatchDealerGrantRecordMapper {

    List<Map<String, Object>> grantBatchRecordList(@Param("vin") String vin,
        @Param("phone") String phone,
        @Param("dealerCode") String dealerCode);

    List<VoucherBatchDealerGrantRecord> queryByVin(String vin);
}