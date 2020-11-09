package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemVoucherUserSelectTriggerReceiptionMapping;
import org.springframework.stereotype.Component;

@Component
public interface OemVoucherUserSelectTriggerReceiptionMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemVoucherUserSelectTriggerReceiptionMapping record);

    int insertSelective(OemVoucherUserSelectTriggerReceiptionMapping record);

    OemVoucherUserSelectTriggerReceiptionMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemVoucherUserSelectTriggerReceiptionMapping record);

    int updateByPrimaryKey(OemVoucherUserSelectTriggerReceiptionMapping record);

}