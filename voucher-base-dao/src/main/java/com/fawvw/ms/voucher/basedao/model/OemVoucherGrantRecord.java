package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherGrantRecord {
    private Integer id;

    private String aidRef;

    private String grantee;

    private Byte granteeCheckStatus;

    private Byte smsSendStatus;

    private Byte grantVoucherStatus;

    private Byte granteeType;

    private String granteeCheckStatusFailedReason;

    private Integer fkGrantTypeId;

    private Date createTime;

    private Date updateTime;

    private Byte deleted;

    private Byte active;

    public OemVoucherGrantRecord(Integer id, String aidRef, String grantee, Byte granteeCheckStatus, Byte smsSendStatus, Byte grantVoucherStatus, Byte granteeType, String granteeCheckStatusFailedReason, Integer fkGrantTypeId, Date createTime, Date updateTime, Byte deleted, Byte active) {
        this.id = id;
        this.aidRef = aidRef;
        this.grantee = grantee;
        this.granteeCheckStatus = granteeCheckStatus;
        this.smsSendStatus = smsSendStatus;
        this.grantVoucherStatus = grantVoucherStatus;
        this.granteeType = granteeType;
        this.granteeCheckStatusFailedReason = granteeCheckStatusFailedReason;
        this.fkGrantTypeId = fkGrantTypeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.active = active;
    }

    public OemVoucherGrantRecord() {
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

    public String getGrantee() {
        return grantee;
    }

    public void setGrantee(String grantee) {
        this.grantee = grantee == null ? null : grantee.trim();
    }

    public Byte getGranteeCheckStatus() {
        return granteeCheckStatus;
    }

    public void setGranteeCheckStatus(Byte granteeCheckStatus) {
        this.granteeCheckStatus = granteeCheckStatus;
    }

    public Byte getSmsSendStatus() {
        return smsSendStatus;
    }

    public void setSmsSendStatus(Byte smsSendStatus) {
        this.smsSendStatus = smsSendStatus;
    }

    public Byte getGrantVoucherStatus() {
        return grantVoucherStatus;
    }

    public void setGrantVoucherStatus(Byte grantVoucherStatus) {
        this.grantVoucherStatus = grantVoucherStatus;
    }

    public Byte getGranteeType() {
        return granteeType;
    }

    public void setGranteeType(Byte granteeType) {
        this.granteeType = granteeType;
    }

    public String getGranteeCheckStatusFailedReason() {
        return granteeCheckStatusFailedReason;
    }

    public void setGranteeCheckStatusFailedReason(String granteeCheckStatusFailedReason) {
        this.granteeCheckStatusFailedReason = granteeCheckStatusFailedReason == null ? null : granteeCheckStatusFailedReason.trim();
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