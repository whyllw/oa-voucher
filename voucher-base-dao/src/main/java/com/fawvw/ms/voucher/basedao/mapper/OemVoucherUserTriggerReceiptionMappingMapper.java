package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherUserTriggerReceiptionMapping;
import org.springframework.stereotype.Component;

@Component
public interface OemVoucherUserTriggerReceiptionMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherUserTriggerReceiptionMapping record);

    int insertSelective(OemVoucherUserTriggerReceiptionMapping record);

    OemVoucherUserTriggerReceiptionMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherUserTriggerReceiptionMapping record);

    int updateByPrimaryKey(OemVoucherUserTriggerReceiptionMapping record);

}