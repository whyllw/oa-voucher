package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.ThirdpartVoucherMapping;

public interface ThirdpartVoucherMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdpartVoucherMapping record);

    int insertSelective(ThirdpartVoucherMapping record);

    ThirdpartVoucherMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdpartVoucherMapping record);

    int updateByPrimaryKey(ThirdpartVoucherMapping record);

}