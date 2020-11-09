package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.ThirdpartVoucherMappingMapper;
import com.fawvw.ms.voucher.basedao.model.ThirdpartVoucherMapping;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OssThirdpartVoucherMappingMapper extends ThirdpartVoucherMappingMapper {

    void deleteThirdpartByBatchId(Integer batchId);

    void batchInsertThirdpartCodes(List<ThirdpartVoucherMapping> record);

    List<ThirdpartVoucherMapping> selectBybatchIdAndStatus(@Param("fkBatchId") Integer fkBatchId,
        @Param("importStatus") Byte importStatus);

    Integer countBybatchIdAndStatus(@Param("fkBatchId") Integer fkBatchId,
        @Param("importStatus") Byte importStatus);

    Integer countBybatchId(Integer fkBatchId);

    void updateImportStatusByIds(@Param("ids") List<Integer> list,
        @Param("importStatus") Byte importStatus, @Param("failReason") String failReason);

    List selectThirdpartGrantBatch();

}