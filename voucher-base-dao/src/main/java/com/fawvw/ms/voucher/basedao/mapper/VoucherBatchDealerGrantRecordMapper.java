package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.VoucherBatchDealerGrantRecord;

public interface VoucherBatchDealerGrantRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherBatchDealerGrantRecord record);

    int insertSelective(VoucherBatchDealerGrantRecord record);

    VoucherBatchDealerGrantRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoucherBatchDealerGrantRecord record);

    int updateByPrimaryKey(VoucherBatchDealerGrantRecord record);

}