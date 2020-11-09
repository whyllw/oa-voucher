package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherUserSelectTriggerReceiptionMapping {
    private Integer id;

    private String aidRef;

    private Integer lastTimes;

    private Integer fkSelectGrantId;

    private Date createTime;

    private Date updateTime;

    private Integer fkBatchId;

    private String vin;

    public OemVoucherUserSelectTriggerReceiptionMapping(Integer id, String aidRef, Integer lastTimes, Integer fkSelectGrantId, Date createTime, Date updateTime, Integer fkBatchId, String vin) {
        this.id = id;
        this.aidRef = aidRef;
        this.lastTimes = lastTimes;
        this.fkSelectGrantId = fkSelectGrantId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.fkBatchId = fkBatchId;
        this.vin = vin;
    }

    public OemVoucherUserSelectTriggerReceiptionMapping() {
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

    public Integer getFkBatchId() {
        return fkBatchId;
    }

    public void setFkBatchId(Integer fkBatchId) {
        this.fkBatchId = fkBatchId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }
}