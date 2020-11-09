package com.fawvw.ms.voucher.oss.mapper;

import com.fawvw.ms.voucher.basedao.mapper.ReportFileMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface OssReportFileMapper extends ReportFileMapper {

    List selectReportFileRecord(Long beginTime, Long endTime);

    List<Map<String, Object>> selectReportFileRecordBySearchParam(String adminUser,
        String reportFileName);
}