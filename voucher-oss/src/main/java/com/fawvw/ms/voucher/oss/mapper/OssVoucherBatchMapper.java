package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.VoucherBatchMapper;
import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssVoucherBatchMapper extends VoucherBatchMapper {

    List selectByBatchListQueryParam(@Param("conf") Map params);

    List<VoucherBatch> selectForGrantByBatchListQueryParam(@Param("conf") Map params,
        @Param("notGrantBatchids") List<Integer> notGrantBatchids);

    List<VoucherBatch> selectExpiredBatch();

    void updateBatchStatusByIdList(@Param("batchIds") List<Integer> list,
        @Param("status") Byte status);

    List<VoucherBatch> selectBytemplateIdList(List<String> list);

    List<VoucherBatch> selectBybatchIdList(List<Integer> list);

    List<Map<String, Object>> selectByVoucherType(@Param("voucherType") Byte voucherType,
        @Param("status") Byte status,
        @Param("active") Byte active, @Param("deleted") Byte deleted,
        @Param("carAge") String carAge, @Param("dealerCode") String dealerCode);

    void updateBatchThirdpartData(@Param("id") Integer id,
        @Param("thirdpartShopName") String thirdpartShopName,
        @Param("thirdpartRedeemCode") String thirdpartRedeemCode);

    List<VoucherBatch> selectThirdpartNotImportBatch();
}