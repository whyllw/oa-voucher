package com.fawvw.ms.voucher.basedao.mapper;

import com.fawvw.ms.voucher.basedao.model.ReportFile;

public interface ReportFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportFile record);

    int insertSelective(ReportFile record);

    ReportFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportFile record);

    int updateByPrimaryKey(ReportFile record);

}