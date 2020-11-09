package com.fawvw.ms.voucher.oss.service;
/*
 * Project: com.fawvw.ms.oneappserver.services.admin
 *
 * File Created at 2019-12-10
 *
 * Copyright 2019 FAW-VW Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FAW-VW Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import com.fawvw.ms.voucher.basedao.model.VoucherDealerGrantRecord;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWithDrawInfoVo;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author zhangyunjiao
 * @Type AdminVoucherCommonService
 * @Desc
 * @date 2019-12-10 10:22
 */
@Component
public interface OssVoucherCommonService {
    Boolean isGrantInfo(List<VoucherDealerGrantRecord> records, String voucherTemplateId,
        List<VoucherWithDrawInfoVo> list);

    void voucherInvalid(List<VoucherDealerGrantRecord> records);

    void addBatchStoreCount(Integer batchId, Integer stockCount, boolean isAddBatchCount) throws Exception;
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2019-12-10 zhangyunjiao create
 */