package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedomain.vo.VoucherLimitedPartsMapping;
import org.springframework.stereotype.Component;

@Component
public interface VoucherLimitedPartsMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherLimitedPartsMapping record);

    int insertSelective(VoucherLimitedPartsMapping record);

    VoucherLimitedPartsMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoucherLimitedPartsMapping record);

    int updateByPrimaryKey(VoucherLimitedPartsMapping record);

}