package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.OemVoucherGrantRecordMapper;
import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantRecord;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssOemVoucherGrantRecordMapper extends OemVoucherGrantRecordMapper {

    void insertOemGrantRecordByBatch(List<OemVoucherGrantRecord> list);

    List<Map<String, Object>> selectCheckSuccessRecordCount(
        @Param("grantTypeIds") List<Integer> grantTypeIds);

    List<Map<String, Object>> selectCheckFailRecordCount(
        @Param("grantTypeIds") List<Integer> grantTypeIds);

    List<Map<String, Object>> selectSendSmsSuccessRecordCount(
        @Param("grantTypeIds") List<Integer> grantTypeIds);

    List<Map<String, Object>> selectSendSmsFailRecordCount(
        @Param("grantTypeIds") List<Integer> grantTypeIds);

    List<OemVoucherGrantRecord> selectUserGrantRecord(@Param("aidRef") String aidRef);

    List<OemVoucherGrantRecord> selectUserGrantRecordByMobile(String mobile);

    List<OemVoucherGrantRecord> selectCheckFailRecords(Integer grantTypeId);

    List<OemVoucherGrantRecord> selectSmsSendFailRecords(Integer grantTypeId);

    List<OemVoucherGrantRecord> selectRecordsByGrantTypeId(Integer grantTypeId);
}