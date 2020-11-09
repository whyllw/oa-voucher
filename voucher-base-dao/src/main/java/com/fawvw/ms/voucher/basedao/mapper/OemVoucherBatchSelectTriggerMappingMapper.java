package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherBatchSelectTriggerMapping;
import org.springframework.stereotype.Component;

@Component
public interface OemVoucherBatchSelectTriggerMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherBatchSelectTriggerMapping record);

    int insertSelective(OemVoucherBatchSelectTriggerMapping record);

    OemVoucherBatchSelectTriggerMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherBatchSelectTriggerMapping record);

    int updateByPrimaryKey(OemVoucherBatchSelectTriggerMapping record);

}