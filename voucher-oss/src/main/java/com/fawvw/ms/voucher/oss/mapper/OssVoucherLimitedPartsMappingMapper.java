package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.VoucherLimitedPartsMappingMapper;
import com.fawvw.ms.voucher.basedomain.vo.VoucherLimitedPartsMapping;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface OssVoucherLimitedPartsMappingMapper extends VoucherLimitedPartsMappingMapper {

    void batchInsertLimitedParts(List<VoucherLimitedPartsMapping> list);

    void deletePartsByBatchId(Integer batchId);

    List<VoucherLimitedPartsMapping> selectByBatchId(Integer batchId);

    List<VoucherLimitedPartsMapping> selectByBatchIdList(List<Integer> list);
}