package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.VoucherDealerGrantRecord;

public interface VoucherDealerGrantRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherDealerGrantRecord record);

    int insertSelective(VoucherDealerGrantRecord record);

    VoucherDealerGrantRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoucherDealerGrantRecord record);

    int updateByPrimaryKey(VoucherDealerGrantRecord record);

}