package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherBatchTriggerMapping {
    private Integer id;

    private Integer fkBatchId;

    private Integer fkGrantTypeId;

    private Date createTime;

    private Date updateTime;

    public OemVoucherBatchTriggerMapping(Integer id, Integer fkBatchId, Integer fkGrantTypeId, Date createTime, Date updateTime) {
        this.id = id;
        this.fkBatchId = fkBatchId;
        this.fkGrantTypeId = fkGrantTypeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OemVoucherBatchTriggerMapping() {
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

    public Integer getFkGrantTypeId() {
        return fkGrantTypeId;
    }

    public void setFkGrantTypeId(Integer fkGrantTypeId) {
        this.fkGrantTypeId = fkGrantTypeId;
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