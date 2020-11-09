package com.fawvw.ms.voucher.core.mapper;

import com.fawvw.ms.voucher.basedao.mapper.EtlUserVehicleMappingMapper;
import com.fawvw.ms.voucher.basedao.model.EtlUserVehicleMapping;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CoreEtlUserVehicleMappingMapper extends EtlUserVehicleMappingMapper {

    List<EtlUserVehicleMapping> selectUsersForGrantingVoucherByParams(@Param("conf") Map params);

    EtlUserVehicleMapping selectByAid(@Param("refId") String refId);
}