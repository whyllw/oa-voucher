package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.EtlUserVoucherBatchMappingRecord;

public interface EtlUserVoucherBatchMappingRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EtlUserVoucherBatchMappingRecord record);

    int insertSelective(EtlUserVoucherBatchMappingRecord record);

    EtlUserVoucherBatchMappingRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EtlUserVoucherBatchMappingRecord record);

    int updateByPrimaryKey(EtlUserVoucherBatchMappingRecord record);

}