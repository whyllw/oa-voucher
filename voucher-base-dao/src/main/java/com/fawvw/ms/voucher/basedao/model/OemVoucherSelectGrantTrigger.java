package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemVoucherSelectGrantTrigger {
    private Integer id;

    private Integer grantLimitPerTime;

    private Byte vehicleBindingStatus;

    private Byte toAllMake;

    private Byte toAllCarNumber;

    private Date vehicleSaleStartDate;

    private Date vehicleSaleEndDate;

    private Date lastBackToDealerStartTime;

    private Date lastBackDealerEndTime;

    private Integer lastBackDealerMileageMin;

    private Integer lastBackDealerMileageMax;

    private String voucherReceivedMessageContent;

    private Byte appMessageNotice;

    private Byte popUpMessageNotice;

    private Byte smsNotice;

    private Byte deleted;

    private Byte active;

    private Date createTime;

    private Date updateTime;

    public OemVoucherSelectGrantTrigger(Integer id, Integer grantLimitPerTime, Byte vehicleBindingStatus, Byte toAllMake, Byte toAllCarNumber, Date vehicleSaleStartDate, Date vehicleSaleEndDate, Date lastBackToDealerStartTime, Date lastBackDealerEndTime, Integer lastBackDealerMileageMin, Integer lastBackDealerMileageMax, String voucherReceivedMessageContent, Byte appMessageNotice, Byte popUpMessageNotice, Byte smsNotice, Byte deleted, Byte active, Date createTime, Date updateTime) {
        this.id = id;
        this.grantLimitPerTime = grantLimitPerTime;
        this.vehicleBindingStatus = vehicleBindingStatus;
        this.toAllMake = toAllMake;
        this.toAllCarNumber = toAllCarNumber;
        this.vehicleSaleStartDate = vehicleSaleStartDate;
        this.vehicleSaleEndDate = vehicleSaleEndDate;
        this.lastBackToDealerStartTime = lastBackToDealerStartTime;
        this.lastBackDealerEndTime = lastBackDealerEndTime;
        this.lastBackDealerMileageMin = lastBackDealerMileageMin;
        this.lastBackDealerMileageMax = lastBackDealerMileageMax;
        this.voucherReceivedMessageContent = voucherReceivedMessageContent;
        this.appMessageNotice = appMessageNotice;
        this.popUpMessageNotice = popUpMessageNotice;
        this.smsNotice = smsNotice;
        this.deleted = deleted;
        this.active = active;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OemVoucherSelectGrantTrigger() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrantLimitPerTime() {
        return grantLimitPerTime;
    }

    public void setGrantLimitPerTime(Integer grantLimitPerTime) {
        this.grantLimitPerTime = grantLimitPerTime;
    }

    public Byte getVehicleBindingStatus() {
        return vehicleBindingStatus;
    }

    public void setVehicleBindingStatus(Byte vehicleBindingStatus) {
        this.vehicleBindingStatus = vehicleBindingStatus;
    }

    public Byte getToAllMake() {
        return toAllMake;
    }

    public void setToAllMake(Byte toAllMake) {
        this.toAllMake = toAllMake;
    }

    public Byte getToAllCarNumber() {
        return toAllCarNumber;
    }

    public void setToAllCarNumber(Byte toAllCarNumber) {
        this.toAllCarNumber = toAllCarNumber;
    }

    public Date getVehicleSaleStartDate() {
        return vehicleSaleStartDate;
    }

    public void setVehicleSaleStartDate(Date vehicleSaleStartDate) {
        this.vehicleSaleStartDate = vehicleSaleStartDate;
    }

    public Date getVehicleSaleEndDate() {
        return vehicleSaleEndDate;
    }

    public void setVehicleSaleEndDate(Date vehicleSaleEndDate) {
        this.vehicleSaleEndDate = vehicleSaleEndDate;
    }

    public Date getLastBackToDealerStartTime() {
        return lastBackToDealerStartTime;
    }

    public void setLastBackToDealerStartTime(Date lastBackToDealerStartTime) {
        this.lastBackToDealerStartTime = lastBackToDealerStartTime;
    }

    public Date getLastBackDealerEndTime() {
        return lastBackDealerEndTime;
    }

    public void setLastBackDealerEndTime(Date lastBackDealerEndTime) {
        this.lastBackDealerEndTime = lastBackDealerEndTime;
    }

    public Integer getLastBackDealerMileageMin() {
        return lastBackDealerMileageMin;
    }

    public void setLastBackDealerMileageMin(Integer lastBackDealerMileageMin) {
        this.lastBackDealerMileageMin = lastBackDealerMileageMin;
    }

    public Integer getLastBackDealerMileageMax() {
        return lastBackDealerMileageMax;
    }

    public void setLastBackDealerMileageMax(Integer lastBackDealerMileageMax) {
        this.lastBackDealerMileageMax = lastBackDealerMileageMax;
    }

    public String getVoucherReceivedMessageContent() {
        return voucherReceivedMessageContent;
    }

    public void setVoucherReceivedMessageContent(String voucherReceivedMessageContent) {
        this.voucherReceivedMessageContent = voucherReceivedMessageContent == null ? null : voucherReceivedMessageContent.trim();
    }

    public Byte getAppMessageNotice() {
        return appMessageNotice;
    }

    public void setAppMessageNotice(Byte appMessageNotice) {
        this.appMessageNotice = appMessageNotice;
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