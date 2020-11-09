package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class VoucherBatch {
    private Integer id;

    private String voucherTemplateId;

    private String batchCode;

    private Byte voucherType;

    private String voucherName;

    private Date drawExpireDate;

    private Integer voucherCount;

    private String redeemValue;

    private String minimumSpendAmount;

    private Byte expirationReminder;

    private Integer remindBeforeExpireDay;

    private Date redeemStartTime;

    private Date redeemEndTime;

    private Integer validForNumberOfDays;

    private String usageRule;

    private Integer vehicleMileageMin;

    private Integer vehicleMileageMax;

    private String singleTimeLimitDeductionAmount;

    private String activityName;

    private Integer minPurchaseNumber;

    private Byte status;

    private Date redeemTime;

    private String redeemTemplateCode;

    private Byte businessType;

    private String businessTypeOption;

    private Byte isVinLimited;

    private Byte isOverlapable;

    private Byte isPartCodeLimited;

    private Byte isDealerLimited;

    private Byte deleted;

    private Byte active;

    private Date createTime;

    private Date updateTime;

    private Byte expiryDateType;

    private Integer stockCount;

    private Integer distributionCount;

    private Integer receivedCount;

    private Integer usedCount;

    private Integer expiredCount;

    private Byte redeemChannel;

    private Byte redeemType;

    private String entityCode;

    private String logoUrl;

    private String shopUrl;

    private String thirdpartRedeemCode;

    private String thirdpartShopName;

    private Byte thirdpartCodeType;

    public VoucherBatch(Integer id, String voucherTemplateId, String batchCode, Byte voucherType, String voucherName, Date drawExpireDate, Integer voucherCount, String redeemValue, String minimumSpendAmount, Byte expirationReminder, Integer remindBeforeExpireDay, Date redeemStartTime, Date redeemEndTime, Integer validForNumberOfDays, String usageRule, Integer vehicleMileageMin, Integer vehicleMileageMax, String singleTimeLimitDeductionAmount, String activityName, Integer minPurchaseNumber, Byte status, Date redeemTime, String redeemTemplateCode, Byte businessType, String businessTypeOption, Byte isVinLimited, Byte isOverlapable, Byte isPartCodeLimited, Byte isDealerLimited, Byte deleted, Byte active, Date createTime, Date updateTime, Byte expiryDateType, Integer stockCount, Integer distributionCount, Integer receivedCount, Integer usedCount, Integer expiredCount, Byte redeemChannel, Byte redeemType, String entityCode, String logoUrl, String shopUrl, String thirdpartRedeemCode, String thirdpartShopName, Byte thirdpartCodeType) {
        this.id = id;
        this.voucherTemplateId = voucherTemplateId;
        this.batchCode = batchCode;
        this.voucherType = voucherType;
        this.voucherName = voucherName;
        this.drawExpireDate = drawExpireDate;
        this.voucherCount = voucherCount;
        this.redeemValue = redeemValue;
        this.minimumSpendAmount = minimumSpendAmount;
        this.expirationReminder = expirationReminder;
        this.remindBeforeExpireDay = remindBeforeExpireDay;
        this.redeemStartTime = redeemStartTime;
        this.redeemEndTime = redeemEndTime;
        this.validForNumberOfDays = validForNumberOfDays;
        this.usageRule = usageRule;
        this.vehicleMileageMin = vehicleMileageMin;
        this.vehicleMileageMax = vehicleMileageMax;
        this.singleTimeLimitDeductionAmount = singleTimeLimitDeductionAmount;
        this.activityName = activityName;
        this.minPurchaseNumber = minPurchaseNumber;
        this.status = status;
        this.redeemTime = redeemTime;
        this.redeemTemplateCode = redeemTemplateCode;
        this.businessType = businessType;
        this.businessTypeOption = businessTypeOption;
        this.isVinLimited = isVinLimited;
        this.isOverlapable = isOverlapable;
        this.isPartCodeLimited = isPartCodeLimited;
        this.isDealerLimited = isDealerLimited;
        this.deleted = deleted;
        this.active = active;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.expiryDateType = expiryDateType;
        this.stockCount = stockCount;
        this.distributionCount = distributionCount;
        this.receivedCount = receivedCount;
        this.usedCount = usedCount;
        this.expiredCount = expiredCount;
        this.redeemChannel = redeemChannel;
        this.redeemType = redeemType;
        this.entityCode = entityCode;
        this.logoUrl = logoUrl;
        this.shopUrl = shopUrl;
        this.thirdpartRedeemCode = thirdpartRedeemCode;
        this.thirdpartShopName = thirdpartShopName;
        this.thirdpartCodeType = thirdpartCodeType;
    }

    public VoucherBatch() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoucherTemplateId() {
        return voucherTemplateId;
    }

    public void setVoucherTemplateId(String voucherTemplateId) {
        this.voucherTemplateId = voucherTemplateId == null ? null : voucherTemplateId.trim();
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    public Byte getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Byte voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName == null ? null : voucherName.trim();
    }

    public Date getDrawExpireDate() {
        return drawExpireDate;
    }

    public void setDrawExpireDate(Date drawExpireDate) {
        this.drawExpireDate = drawExpireDate;
    }

    public Integer getVoucherCount() {
        return voucherCount;
    }

    public void setVoucherCount(Integer voucherCount) {
        this.voucherCount = voucherCount;
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

    public Byte getExpirationReminder() {
        return expirationReminder;
    }

    public void setExpirationReminder(Byte expirationReminder) {
        this.expirationReminder = expirationReminder;
    }

    public Integer getRemindBeforeExpireDay() {
        return remindBeforeExpireDay;
    }

    public void setRemindBeforeExpireDay(Integer remindBeforeExpireDay) {
        this.remindBeforeExpireDay = remindBeforeExpireDay;
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

    public Integer getValidForNumberOfDays() {
        return validForNumberOfDays;
    }

    public void setValidForNumberOfDays(Integer validForNumberOfDays) {
        this.validForNumberOfDays = validForNumberOfDays;
    }

    public String getUsageRule() {
        return usageRule;
    }

    public void setUsageRule(String usageRule) {
        this.usageRule = usageRule == null ? null : usageRule.trim();
    }

    public Integer getVehicleMileageMin() {
        return vehicleMileageMin;
    }

    public void setVehicleMileageMin(Integer vehicleMileageMin) {
        this.vehicleMileageMin = vehicleMileageMin;
    }

    public Integer getVehicleMileageMax() {
        return vehicleMileageMax;
    }

    public void setVehicleMileageMax(Integer vehicleMileageMax) {
        this.vehicleMileageMax = vehicleMileageMax;
    }

    public String getSingleTimeLimitDeductionAmount() {
        return singleTimeLimitDeductionAmount;
    }

    public void setSingleTimeLimitDeductionAmount(String singleTimeLimitDeductionAmount) {
        this.singleTimeLimitDeductionAmount = singleTimeLimitDeductionAmount == null ? null : singleTimeLimitDeductionAmount.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getMinPurchaseNumber() {
        return minPurchaseNumber;
    }

    public void setMinPurchaseNumber(Integer minPurchaseNumber) {
        this.minPurchaseNumber = minPurchaseNumber;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }

    public String getRedeemTemplateCode() {
        return redeemTemplateCode;
    }

    public void setRedeemTemplateCode(String redeemTemplateCode) {
        this.redeemTemplateCode = redeemTemplateCode == null ? null : redeemTemplateCode.trim();
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public String getBusinessTypeOption() {
        return businessTypeOption;
    }

    public void setBusinessTypeOption(String businessTypeOption) {
        this.businessTypeOption = businessTypeOption == null ? null : businessTypeOption.trim();
    }

    public Byte getIsVinLimited() {
        return isVinLimited;
    }

    public void setIsVinLimited(Byte isVinLimited) {
        this.isVinLimited = isVinLimited;
    }

    public Byte getIsOverlapable() {
        return isOverlapable;
    }

    public void setIsOverlapable(Byte isOverlapable) {
        this.isOverlapable = isOverlapable;
    }

    public Byte getIsPartCodeLimited() {
        return isPartCodeLimited;
    }

    public void setIsPartCodeLimited(Byte isPartCodeLimited) {
        this.isPartCodeLimited = isPartCodeLimited;
    }

    public Byte getIsDealerLimited() {
        return isDealerLimited;
    }

    public void setIsDealerLimited(Byte isDealerLimited) {
        this.isDealerLimited = isDealerLimited;
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

    public Byte getExpiryDateType() {
        return expiryDateType;
    }

    public void setExpiryDateType(Byte expiryDateType) {
        this.expiryDateType = expiryDateType;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getDistributionCount() {
        return distributionCount;
    }

    public void setDistributionCount(Integer distributionCount) {
        this.distributionCount = distributionCount;
    }

    public Integer getReceivedCount() {
        return receivedCount;
    }

    public void setReceivedCount(Integer receivedCount) {
        this.receivedCount = receivedCount;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getExpiredCount() {
        return expiredCount;
    }

    public void setExpiredCount(Integer expiredCount) {
        this.expiredCount = expiredCount;
    }

    public Byte getRedeemChannel() {
        return redeemChannel;
    }

    public void setRedeemChannel(Byte redeemChannel) {
        this.redeemChannel = redeemChannel;
    }

    public Byte getRedeemType() {
        return redeemType;
    }

    public void setRedeemType(Byte redeemType) {
        this.redeemType = redeemType;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode == null ? null : entityCode.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl == null ? null : shopUrl.trim();
    }

    public String getThirdpartRedeemCode() {
        return thirdpartRedeemCode;
    }

    public void setThirdpartRedeemCode(String thirdpartRedeemCode) {
        this.thirdpartRedeemCode = thirdpartRedeemCode == null ? null : thirdpartRedeemCode.trim();
    }

    public String getThirdpartShopName() {
        return thirdpartShopName;
    }

    public void setThirdpartShopName(String thirdpartShopName) {
        this.thirdpartShopName = thirdpartShopName == null ? null : thirdpartShopName.trim();
    }

    public Byte getThirdpartCodeType() {
        return thirdpartCodeType;
    }

    public void setThirdpartCodeType(Byte thirdpartCodeType) {
        this.thirdpartCodeType = thirdpartCodeType;
    }
}