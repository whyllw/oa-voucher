package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.VoucherDealerGrantRecordMapper;
import com.fawvw.ms.voucher.basedao.model.VoucherDealerGrantRecord;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssVoucherDealerGrantRecordMapper extends VoucherDealerGrantRecordMapper {

    List<Map<String, Object>> grantRecordList(@Param("refId") String refId,
        @Param("vin") String vin, @Param("phone") String phone);

    List<VoucherDealerGrantRecord> queryByAidAndBatchId(@Param("refId") String refId,
        @Param("vin") String vin,
        @Param("batchId") Integer batchId, @Param("dealerCode") String dealerCode,
        @Param("createTime") Date createTime);

    List<VoucherDealerGrantRecord> queryByVin(String vin);

    VoucherDealerGrantRecord queryByDealerCode(String dealerCode);

    List<VoucherDealerGrantRecord> queryByAidAndBatchIdForOem(@Param("refId") String refId,
        @Param("vin") String vin,
        @Param("batchId") Integer batchId, @Param("createTime") Date createTime);

    List<VoucherDealerGrantRecord> queryByfkBatchGrantRecordId(Integer batchGrantRecordId);

    List<Map<String, Object>> grantRecordListForOem(@Param("vin") String vin,
        @Param("phone") String phone);
}