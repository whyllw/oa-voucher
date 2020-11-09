package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.EtlVoucherBatch;

public interface EtlVoucherBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EtlVoucherBatch record);

    int insertSelective(EtlVoucherBatch record);

    EtlVoucherBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EtlVoucherBatch record);

    int updateByPrimaryKey(EtlVoucherBatch record);
}