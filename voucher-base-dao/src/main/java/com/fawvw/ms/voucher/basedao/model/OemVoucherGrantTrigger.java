package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherGrantTrigger {
    private Integer id;

    private Byte grantType;

    private Byte businessType;

    private Byte appMessageNotice;

    private Byte triggerCondition;

    private Integer grantLimitPerTime;

    private Integer grantTimesLimit;

    private String voucherReceivedMessageContent;

    private Date createTime;

    private Date updateTime;

    private Byte popUpMessageNotice;

    private Byte smsNotice;

    private Byte deleted;

    private Byte active;

    private Byte importType;

    private Integer importNum;

    private String smsTemplateId;

    private Byte grantTimeType;

    public OemVoucherGrantTrigger(Integer id, Byte grantType, Byte businessType, Byte appMessageNotice, Byte triggerCondition, Integer grantLimitPerTime, Integer grantTimesLimit, String voucherReceivedMessageContent, Date createTime, Date updateTime, Byte popUpMessageNotice, Byte smsNotice, Byte deleted, Byte active, Byte importType, Integer importNum, String smsTemplateId, Byte grantTimeType) {
        this.id = id;
        this.grantType = grantType;
        this.businessType = businessType;
        this.appMessageNotice = appMessageNotice;
        this.triggerCondition = triggerCondition;
        this.grantLimitPerTime = grantLimitPerTime;
        this.grantTimesLimit = grantTimesLimit;
        this.voucherReceivedMessageContent = voucherReceivedMessageContent;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.popUpMessageNotice = popUpMessageNotice;
        this.smsNotice = smsNotice;
        this.deleted = deleted;
        this.active = active;
        this.importType = importType;
        this.importNum = importNum;
        this.smsTemplateId = smsTemplateId;
        this.grantTimeType = grantTimeType;
    }

    public OemVoucherGrantTrigger() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getGrantType() {
        return grantType;
    }

    public void setGrantType(Byte grantType) {
        this.grantType = grantType;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public Byte getAppMessageNotice() {
        return appMessageNotice;
    }

    public void setAppMessageNotice(Byte appMessageNotice) {
        this.appMessageNotice = appMessageNotice;
    }

    public Byte getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(Byte triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public Integer getGrantLimitPerTime() {
        return grantLimitPerTime;
    }

    public void setGrantLimitPerTime(Integer grantLimitPerTime) {
        this.grantLimitPerTime = grantLimitPerTime;
    }

    public Integer getGrantTimesLimit() {
        return grantTimesLimit;
    }

    public void setGrantTimesLimit(Integer grantTimesLimit) {
        this.grantTimesLimit = grantTimesLimit;
    }

    public String getVoucherReceivedMessageContent() {
        return voucherReceivedMessageContent;
    }

    public void setVoucherReceivedMessageContent(String voucherReceivedMessageContent) {
        this.voucherReceivedMessageContent = voucherReceivedMessageContent == null ? null : voucherReceivedMessageContent.trim();
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

    public Byte getPopUpMessageNotice() {
        return popUpMessageNotice;
    }

    public void setPopUpMessageNotice(Byte popUpMessageNotice) {
        this.popUpMessageNotice = popUpMessageNotice;
    }

    public Byte getSmsNotice() {
        return smsNotice;
    }

    public void setSmsNotice(Byte smsNotice) {
        this.smsNotice = smsNotice;
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

    public Byte getImportType() {
        return importType;
    }

    public void setImportType(Byte importType) {
        this.importType = importType;
    }

    public Integer getImportNum() {
        return importNum;
    }

    public void setImportNum(Integer importNum) {
        this.importNum = importNum;
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId == null ? null : smsTemplateId.trim();
    }

    public Byte getGrantTimeType() {
        return grantTimeType;
    }

    public void setGrantTimeType(Byte grantTimeType) {
        this.grantTimeType = grantTimeType;
    }
}