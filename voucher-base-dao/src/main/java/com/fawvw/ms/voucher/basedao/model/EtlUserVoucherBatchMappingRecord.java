package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class EtlUserVoucherBatchMappingRecord {
    private Integer id;

    private Integer fkBatchId;

    private Integer batchCode;

    private Byte voucherStatus;

    private Byte voucherType;

    private Byte businessType;

    private String voucherName;

    private String activityName;

    private String aidRef;

    private String userName;

    private String mobile;

    private String vin;

    private Date redeemTime;

    private Date grantTime;

    private String grantDealerName;

    private String grantDealerCode;

    private String redeemDealerName;

    private String redeemDealerCode;

    private String workSheetNo;

    private String redeemCode;

    private Date redeemStartTime;

    private Date redeemEndTime;

    private Date drawTime;

    private String grantBigRegionCode;

    private String grantBigRegionName;

    private String grantSmallRegionCode;

    private String grantSmallRegionName;

    private String redeemBigRegionCode;

    private String redeemBigRegionName;

    private String redeemSmallRegionCode;

    private String redeemSmallRegionName;

    private String makeName;

    private Date vehicleSaleDate;

    private String singleTimeLimitDeductionAmount;

    private String redeemValue;

    private String minimumSpendAmount;

    private String grantChannel;

    private String voucherId;

    private String voucherTemplateId;

    public EtlUserVoucherBatchMappingRecord(Integer id, Integer fkBatchId, Integer batchCode, Byte voucherStatus, Byte voucherType, Byte businessType, String voucherName, String activityName, String aidRef, String userName, String mobile, String vin, Date redeemTime, Date grantTime, String grantDealerName, String grantDealerCode, String redeemDealerName, String redeemDealerCode, String workSheetNo, String redeemCode, Date redeemStartTime, Date redeemEndTime, Date drawTime, String grantBigRegionCode, String grantBigRegionName, String grantSmallRegionCode, String grantSmallRegionName, String redeemBigRegionCode, String redeemBigRegionName, String redeemSmallRegionCode, String redeemSmallRegionName, String makeName, Date vehicleSaleDate, String singleTimeLimitDeductionAmount, String redeemValue, String minimumSpendAmount, String grantChannel, String voucherId, String voucherTemplateId) {
        this.id = id;
        this.fkBatchId = fkBatchId;
        this.batchCode = batchCode;
        this.voucherStatus = voucherStatus;
        this.voucherType = voucherType;
        this.businessType = businessType;
        this.voucherName = voucherName;
        this.activityName = activityName;
        this.aidRef = aidRef;
        this.userName = userName;
        this.mobile = mobile;
        this.vin = vin;
        this.redeemTime = redeemTime;
        this.grantTime = grantTime;
        this.grantDealerName = grantDealerName;
        this.grantDealerCode = grantDealerCode;
        this.redeemDealerName = redeemDealerName;
        this.redeemDealerCode = redeemDealerCode;
        this.workSheetNo = workSheetNo;
        this.redeemCode = redeemCode;
        this.redeemStartTime = redeemStartTime;
        this.redeemEndTime = redeemEndTime;
        this.drawTime = drawTime;
        this.grantBigRegionCode = grantBigRegionCode;
        this.grantBigRegionName = grantBigRegionName;
        this.grantSmallRegionCode = grantSmallRegionCode;
        this.grantSmallRegionName = grantSmallRegionName;
        this.redeemBigRegionCode = redeemBigRegionCode;
        this.redeemBigRegionName = redeemBigRegionName;
        this.redeemSmallRegionCode = redeemSmallRegionCode;
        this.redeemSmallRegionName = redeemSmallRegionName;
        this.makeName = makeName;
        this.vehicleSaleDate = vehicleSaleDate;
        this.singleTimeLimitDeductionAmount = singleTimeLimitDeductionAmount;
        this.redeemValue = redeemValue;
        this.minimumSpendAmount = minimumSpendAmount;
        this.grantChannel = grantChannel;
        this.voucherId = voucherId;
        this.voucherTemplateId = voucherTemplateId;
    }

    public EtlUserVoucherBatchMappingRecord() {
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

    public Integer getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(Integer batchCode) {
        this.batchCode = batchCode;
    }

    public Byte getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(Byte voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    public Byte getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Byte voucherType) {
        this.voucherType = voucherType;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName == null ? null : voucherName.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getAidRef() {
        return aidRef;
    }

    public void setAidRef(String aidRef) {
        this.aidRef = aidRef == null ? null : aidRef.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }

    public Date getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    public String getGrantDealerName() {
        return grantDealerName;
    }

    public void setGrantDealerName(String grantDealerName) {
        this.grantDealerName = grantDealerName == null ? null : grantDealerName.trim();
    }

    public String getGrantDealerCode() {
        return grantDealerCode;
    }

    public void setGrantDealerCode(String grantDealerCode) {
        this.grantDealerCode = grantDealerCode == null ? null : grantDealerCode.trim();
    }

    public String getRedeemDealerName() {
        return redeemDealerName;
    }

    public void setRedeemDealerName(String redeemDealerName) {
        this.redeemDealerName = redeemDealerName == null ? null : redeemDealerName.trim();
    }

    public String getRedeemDealerCode() {
        return redeemDealerCode;
    }

    public void setRedeemDealerCode(String redeemDealerCode) {
        this.redeemDealerCode = redeemDealerCode == null ? null : redeemDealerCode.trim();
    }

    public String getWorkSheetNo() {
        return workSheetNo;
    }

    public void setWorkSheetNo(String workSheetNo) {
        this.workSheetNo = workSheetNo == null ? null : workSheetNo.trim();
    }

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode == null ? null : redeemCode.trim();
    }

    public Date getRedeemStartTime() {
        return redeemStartTime;
    }

    public void setRedeemStartTime(Date redeemStartTime) {
        this.redeemStartTime = redeemStartTime;
    }

    public Date getRedeemEndTime() {
        return redeemEndTime;
    }

    public void setRedeemEndTime(Date redeemEndTime) {
        this.redeemEndTime = redeemEndTime;
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    public String getGrantBigRegionCode() {
        return grantBigRegionCode;
    }

    public void setGrantBigRegionCode(String grantBigRegionCode) {
        this.grantBigRegionCode = grantBigRegionCode == null ? null : grantBigRegionCode.trim();
    }

    public String getGrantBigRegionName() {
        return grantBigRegionName;
    }

    public void setGrantBigRegionName(String grantBigRegionName) {
        this.grantBigRegionName = grantBigRegionName == null ? null : grantBigRegionName.trim();
    }

    public String getGrantSmallRegionCode() {
        return grantSmallRegionCode;
    }

    public void setGrantSmallRegionCode(String grantSmallRegionCode) {
        this.grantSmallRegionCode = grantSmallRegionCode == null ? null : grantSmallRegionCode.trim();
    }

    public String getGrantSmallRegionName() {
        return grantSmallRegionName;
    }

    public void setGrantSmallRegionName(String grantSmallRegionName) {
        this.grantSmallRegionName = grantSmallRegionName == null ? null : grantSmallRegionName.trim();
    }

    public String getRedeemBigRegionCode() {
        return redeemBigRegionCode;
    }

    public void setRedeemBigRegionCode(String redeemBigRegionCode) {
        this.redeemBigRegionCode = redeemBigRegionCode == null ? null : redeemBigRegionCode.trim();
    }

    public String getRedeemBigRegionName() {
        return redeemBigRegionName;
    }

    public void setRedeemBigRegionName(String redeemBigRegionName) {
        this.redeemBigRegionName = redeemBigRegionName == null ? null : redeemBigRegionName.trim();
    }

    public String getRedeemSmallRegionCode() {
        return redeemSmallRegionCode;
    }

    public void setRedeemSmallRegionCode(String redeemSmallRegionCode) {
        this.redeemSmallRegionCode = redeemSmallRegionCode == null ? null : redeemSmallRegionCode.trim();
    }

    public String getRedeemSmallRegionName() {
        return redeemSmallRegionName;
    }

    public void setRedeemSmallRegionName(String redeemSmallRegionName) {
        this.redeemSmallRegionName = redeemSmallRegionName == null ? null : redeemSmallRegionName.trim();
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName == null ? null : makeName.trim();
    }

    public Date getVehicleSaleDate() {
        return vehicleSaleDate;
    }

    public void setVehicleSaleDate(Date vehicleSaleDate) {
        this.vehicleSaleDate = vehicleSaleDate;
    }

    public String getSingleTimeLimitDeductionAmount() {
        return singleTimeLimitDeductionAmount;
    }

    public void setSingleTimeLimitDeductionAmount(String singleTimeLimitDeductionAmount) {
        this.singleTimeLimitDeductionAmount = singleTimeLimitDeductionAmount == null ? null : singleTimeLimitDeductionAmount.trim();
    }

    public String getRedeemValue() {
        return redeemValue;
    }

    public void setRedeemValue(String redeemValue) {
        this.redeemValue = redeemValue == null ? null : redeemValue.trim();
    }

    public String getMinimumSpendAmount() {
        return minimumSpendAmount;
    }

    public void setMinimumSpendAmount(String minimumSpendAmount) {
        this.minimumSpendAmount = minimumSpendAmount == null ? null : minimumSpendAmount.trim();
    }

    public String getGrantChannel() {
        return grantChannel;
    }

    public void setGrantChannel(String grantChannel) {
        this.grantChannel = grantChannel == null ? null : grantChannel.trim();
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId == null ? null : voucherId.trim();
    }

    public String getVoucherTemplateId() {
        return voucherTemplateId;
    }

    public void setVoucherTemplateId(String voucherTemplateId) {
        this.voucherTemplateId = voucherTemplateId == null ? null : voucherTemplateId.trim();
    }
}