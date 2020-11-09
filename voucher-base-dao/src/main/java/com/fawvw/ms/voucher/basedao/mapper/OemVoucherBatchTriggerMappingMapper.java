package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherBatchTriggerMapping;

public interface OemVoucherBatchTriggerMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherBatchTriggerMapping record);

    int insertSelective(OemVoucherBatchTriggerMapping record);

    OemVoucherBatchTriggerMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherBatchTriggerMapping record);

    int updateByPrimaryKey(OemVoucherBatchTriggerMapping record);

}