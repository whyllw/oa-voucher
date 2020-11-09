package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class VoucherLimitedDealerMapping {
    private Integer id;

    private String saleCode;

    private String serviceCode;

    private String bigRegionCode;

    private String smallRegionCode;

    private String name;

    private Integer fkBatchId;

    private Byte deleted;

    private Date createTime;

    private Date updateTime;

    private String bigRegionName;

    private String smallRegionName;

    private Byte active;

    private Byte isThirdpart;

    private String city;

    private String shopName;

    private String shopAddress;

    public VoucherLimitedDealerMapping(Integer id, String saleCode, String serviceCode, String bigRegionCode, String smallRegionCode, String name, Integer fkBatchId, Byte deleted, Date createTime, Date updateTime, String bigRegionName, String smallRegionName, Byte active, Byte isThirdpart, String city, String shopName, String shopAddress) {
        this.id = id;
        this.saleCode = saleCode;
        this.serviceCode = serviceCode;
        this.bigRegionCode = bigRegionCode;
        this.smallRegionCode = smallRegionCode;
        this.name = name;
        this.fkBatchId = fkBatchId;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.bigRegionName = bigRegionName;
        this.smallRegionName = smallRegionName;
        this.active = active;
        this.isThirdpart = isThirdpart;
        this.city = city;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
    }

    public VoucherLimitedDealerMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode == null ? null : saleCode.trim();
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    public String getBigRegionCode() {
        return bigRegionCode;
    }

    public void setBigRegionCode(String bigRegionCode) {
        this.bigRegionCode = bigRegionCode == null ? null : bigRegionCode.trim();
    }

    public String getSmallRegionCode() {
        return smallRegionCode;
    }

    public void setSmallRegionCode(String smallRegionCode) {
        this.smallRegionCode = smallRegionCode == null ? null : smallRegionCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getBigRegionName() {
        return bigRegionName;
    }

    public void setBigRegionName(String bigRegionName) {
        this.bigRegionName = bigRegionName == null ? null : bigRegionName.trim();
    }

    public String getSmallRegionName() {
        return smallRegionName;
    }

    public void setSmallRegionName(String smallRegionName) {
        this.smallRegionName = smallRegionName == null ? null : smallRegionName.trim();
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public Byte getIsThirdpart() {
        return isThirdpart;
    }

    public void setIsThirdpart(Byte isThirdpart) {
        this.isThirdpart = isThirdpart;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }
}