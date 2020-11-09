package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class VoucherBatchDealerGrantRecord {
    private Integer id;

    private String aidRef;

    private String mobile;

    private String vin;

    private String dealerCode;

    private Integer grantCount;

    private Integer fkBatchId;

    private Integer fkCarModelId;

    private String carModelName;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Byte deleted;

    private Byte active;

    public VoucherBatchDealerGrantRecord(Integer id, String aidRef, String mobile, String vin, String dealerCode, Integer grantCount, Integer fkBatchId, Integer fkCarModelId, String carModelName, Byte status, Date createTime, Date updateTime, Byte deleted, Byte active) {
        this.id = id;
        this.aidRef = aidRef;
        this.mobile = mobile;
        this.vin = vin;
        this.dealerCode = dealerCode;
        this.grantCount = grantCount;
        this.fkBatchId = fkBatchId;
        this.fkCarModelId = fkCarModelId;
        this.carModelName = carModelName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.active = active;
    }

    public VoucherBatchDealerGrantRecord() {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode == null ? null : dealerCode.trim();
    }

    public Integer getGrantCount() {
        return grantCount;
    }

    public void setGrantCount(Integer grantCount) {
        this.grantCount = grantCount;
    }

    public Integer getFkBatchId() {
        return fkBatchId;
    }

    public void setFkBatchId(Integer fkBatchId) {
        this.fkBatchId = fkBatchId;
    }

    public Integer getFkCarModelId() {
        return fkCarModelId;
    }

    public void setFkCarModelId(Integer fkCarModelId) {
        this.fkCarModelId = fkCarModelId;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName == null ? null : carModelName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }
}