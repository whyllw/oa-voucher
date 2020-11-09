package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.EtlUserVehicleMapping;
import org.springframework.stereotype.Component;

@Component
public interface EtlUserVehicleMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EtlUserVehicleMapping record);

    int insertSelective(EtlUserVehicleMapping record);

    EtlUserVehicleMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EtlUserVehicleMapping record);

    int updateByPrimaryKey(EtlUserVehicleMapping record);
}