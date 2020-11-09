package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherGrantTrigger;
import org.springframework.stereotype.Component;

@Component
public interface OemVoucherGrantTriggerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherGrantTrigger record);

    int insertSelective(OemVoucherGrantTrigger record);

    OemVoucherGrantTrigger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherGrantTrigger record);

    int updateByPrimaryKey(OemVoucherGrantTrigger record);

}