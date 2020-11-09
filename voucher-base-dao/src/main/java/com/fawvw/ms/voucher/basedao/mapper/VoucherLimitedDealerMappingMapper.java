package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.VoucherLimitedDealerMapping;
import org.springframework.stereotype.Component;

@Component
public interface VoucherLimitedDealerMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherLimitedDealerMapping record);

    int insertSelective(VoucherLimitedDealerMapping record);

    VoucherLimitedDealerMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoucherLimitedDealerMapping record);

    int updateByPrimaryKey(VoucherLimitedDealerMapping record);

}