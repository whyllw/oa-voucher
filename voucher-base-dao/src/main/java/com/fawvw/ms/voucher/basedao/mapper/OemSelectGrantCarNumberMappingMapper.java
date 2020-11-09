package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.OemSelectGrantCarNumberMapping;

public interface OemSelectGrantCarNumberMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OemSelectGrantCarNumberMapping record);

    int insertSelective(OemSelectGrantCarNumberMapping record);

    OemSelectGrantCarNumberMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OemSelectGrantCarNumberMapping record);

    int updateByPrimaryKey(OemSelectGrantCarNumberMapping record);
}