package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.VoucherBatch;
import org.springframework.stereotype.Component;

@Component
public interface VoucherBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherBatch record);

    int insertSelective(VoucherBatch record);

    VoucherBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoucherBatch record);

    int updateByPrimaryKey(VoucherBatch record);

}