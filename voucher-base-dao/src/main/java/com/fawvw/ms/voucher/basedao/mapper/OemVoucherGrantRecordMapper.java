package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantRecord;
import org.springframework.stereotype.Component;

@Component
public interface OemVoucherGrantRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherGrantRecord record);

    int insertSelective(OemVoucherGrantRecord record);

    OemVoucherGrantRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherGrantRecord record);

    int updateByPrimaryKey(OemVoucherGrantRecord record);

}