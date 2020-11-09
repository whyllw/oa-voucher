package com.fawvw.ms.voucher.core.mapper;

import com.fawvw.ms.voucher.basedao.mapper.VoucherLimitedDealerMappingMapper;
import com.fawvw.ms.voucher.basedao.model.VoucherLimitedDealerMapping;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CoreVoucherLimitedDealerMappingMapper extends VoucherLimitedDealerMappingMapper {

    void batchInsertLimitedDealers(List<VoucherLimitedDealerMapping> list);

    void batchInsertThirdpartShops(List<VoucherLimitedDealerMapping> list);

    List<Map<String, Object>> selectDealerCodeByBatchId(@Param("batchId") Integer batchId);

    void deleteDealersByBatchId(Integer batchId);

    List<VoucherLimitedDealerMapping> selectByBatchIdList(List<Integer> list);

    List<Map<String, Object>> selectByBatchId(@Param("batchId") Integer batchId);

    Integer countByBatchIdAndDealerCode(@Param("batchId") Integer batchId);

    List<VoucherLimitedDealerMapping> selectShopByCityAndShopName(
        @Param("cityCode") String cityCode,
        @Param("shopName") String shopName, @Param("batchId") Integer batchId);
}