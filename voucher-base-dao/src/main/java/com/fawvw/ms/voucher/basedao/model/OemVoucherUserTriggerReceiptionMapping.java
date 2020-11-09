package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherUserTriggerReceiptionMapping {
    private Integer id;

    private String aidRef;

    private Integer lastTimes;

    private Integer fkGrantTypeId;

    private Date createTime;

    private Date updateTime;

    private Integer fkBatchId;

    public OemVoucherUserTriggerReceiptionMapping(Integer id, String aidRef, Integer lastTimes, Integer fkGrantTypeId, Date createTime, Date updateTime, Integer fkBatchId) {
        this.id = id;
        this.aidRef = aidRef;
        this.lastTimes = lastTimes;
        this.fkGrantTypeId = fkGrantTypeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.fkBatchId = fkBatchId;
    }

    public OemVoucherUserTriggerReceiptionMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAidRef() {
        return aidRef;
    }

    public void setAidRef(String aidRef) {
        this.aidRef = aidRef == null ? null : aidRef.trim();
    }

    public Integer getLastTimes() {
        return lastTimes;
    }

    public void setLastTimes(Integer lastTimes) {
        this.lastTimes = lastTimes;
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

    public Integer getFkBatchId() {
        return fkBatchId;
    }

    public void setFkBatchId(Integer fkBatchId) {
        this.fkBatchId = fkBatchId;
    }
}