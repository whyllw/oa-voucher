package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class ThirdpartVoucherMapping {
    private Integer id;

    private String redeemCode;

    private Byte codeType;

    private Byte importStatus;

    private String failReason;

    private Integer fkBatchId;

    private Byte deleted;

    private Date createTime;

    private Date updateTime;

    private Byte active;

    public ThirdpartVoucherMapping(Integer id, String redeemCode, Byte codeType, Byte importStatus, String failReason, Integer fkBatchId, Byte deleted, Date createTime, Date updateTime, Byte active) {
        this.id = id;
        this.redeemCode = redeemCode;
        this.codeType = codeType;
        this.importStatus = importStatus;
        this.failReason = failReason;
        this.fkBatchId = fkBatchId;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.active = active;
    }

    public ThirdpartVoucherMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode == null ? null : redeemCode.trim();
    }

    public Byte getCodeType() {
        return codeType;
    }

    public void setCodeType(Byte codeType) {
        this.codeType = codeType;
    }

    public Byte getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(Byte importStatus) {
        this.importStatus = importStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public Integer getFkBatchId() {
        return fkBatchId;
    }

    public void setFkBatchId(Integer fkBatchId) {
        this.fkBatchId = fkBatchId;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
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

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }
}