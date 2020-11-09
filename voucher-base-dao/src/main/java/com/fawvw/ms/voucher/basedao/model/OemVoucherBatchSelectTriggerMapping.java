package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherBatchSelectTriggerMapping {
    private Integer id;

    private Integer fkBatchId;

    private Integer fkSelectGrantId;

    private Date createTime;

    private Date updateTime;

    public OemVoucherBatchSelectTriggerMapping(Integer id, Integer fkBatchId, Integer fkSelectGrantId, Date createTime, Date updateTime) {
        this.id = id;
        this.fkBatchId = fkBatchId;
        this.fkSelectGrantId = fkSelectGrantId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OemVoucherBatchSelectTriggerMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkBatchId() {
        return fkBatchId;
    }

    public void setFkBatchId(Integer fkBatchId) {
        this.fkBatchId = fkBatchId;
    }

    public Integer getFkSelectGrantId() {
        return fkSelectGrantId;
    }

    public void setFkSelectGrantId(Integer fkSelectGrantId) {
        this.fkSelectGrantId = fkSelectGrantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}