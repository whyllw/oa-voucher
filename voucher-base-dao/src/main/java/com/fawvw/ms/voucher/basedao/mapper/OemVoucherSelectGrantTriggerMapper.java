package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherSelectGrantTrigger;
import org.springframework.stereotype.Component;

@Component
public interface OemVoucherSelectGrantTriggerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherSelectGrantTrigger record);

    int insertSelective(OemVoucherSelectGrantTrigger record);

    OemVoucherSelectGrantTrigger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherSelectGrantTrigger record);

    int updateByPrimaryKey(OemVoucherSelectGrantTrigger record);

}