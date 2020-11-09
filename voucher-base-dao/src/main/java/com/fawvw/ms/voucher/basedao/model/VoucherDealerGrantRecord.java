package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class VoucherDealerGrantRecord {
    private Integer id;

    private String aidRef;

    private String mobile;

    private String vin;

    private String dealerCode;

    private Integer grantCount;

    private Integer fkBatchId;

    private Integer fkCarModelId;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String redeemCode;

    private String carModelName;

    private String carModelCode;

    private String grantBigRegionCode;

    private String grantBigRegionName;

    private String grantSmallRegionCode;

    private String grantSmallRegionName;

    private String grantDealerName;

    private String grantDealerCode;

    private String voucherCodeId;

    private Integer fkBatchGrantRecordId;

    public VoucherDealerGrantRecord(Integer id, String aidRef, String mobile, String vin, String dealerCode, Integer grantCount, Integer fkBatchId, Integer fkCarModelId, Byte status, Date createTime, Date updateTime, String redeemCode, String carModelName, String carModelCode, String grantBigRegionCode, String grantBigRegionName, String grantSmallRegionCode, String grantSmallRegionName, String grantDealerName, String grantDealerCode, String voucherCodeId, Integer fkBatchGrantRecordId) {
        this.id = id;
        this.aidRef = aidRef;
        this.mobile = mobile;
        this.vin = vin;
        this.dealerCode = dealerCode;
        this.grantCount = grantCount;
        this.fkBatchId = fkBatchId;
        this.fkCarModelId = fkCarModelId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.redeemCode = redeemCode;
        this.carModelName = carModelName;
        this.carModelCode = carModelCode;
        this.grantBigRegionCode = grantBigRegionCode;
        this.grantBigRegionName = grantBigRegionName;
        this.grantSmallRegionCode = grantSmallRegionCode;
        this.grantSmallRegionName = grantSmallRegionName;
        this.grantDealerName = grantDealerName;
        this.grantDealerCode = grantDealerCode;
        this.voucherCodeId = voucherCodeId;
        this.fkBatchGrantRecordId = fkBatchGrantRecordId;
    }

    public VoucherDealerGrantRecord() {
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

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode == null ? null : redeemCode.trim();
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName == null ? null : carModelName.trim();
    }

    public String getCarModelCode() {
        return carModelCode;
    }

    public void setCarModelCode(String carModelCode) {
        this.carModelCode = carModelCode == null ? null : carModelCode.trim();
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

    public String getVoucherCodeId() {
        return voucherCodeId;
    }

    public void setVoucherCodeId(String voucherCodeId) {
        this.voucherCodeId = voucherCodeId == null ? null : voucherCodeId.trim();
    }

    public Integer getFkBatchGrantRecordId() {
        return fkBatchGrantRecordId;
    }

    public void setFkBatchGrantRecordId(Integer fkBatchGrantRecordId) {
        this.fkBatchGrantRecordId = fkBatchGrantRecordId;
    }
}