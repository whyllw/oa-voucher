package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemSelectGrantCarMakeMapping;

public interface OemSelectGrantCarMakeMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemSelectGrantCarMakeMapping record);

    int insertSelective(OemSelectGrantCarMakeMapping record);

    OemSelectGrantCarMakeMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemSelectGrantCarMakeMapping record);

    int updateByPrimaryKey(OemSelectGrantCarMakeMapping record);
}