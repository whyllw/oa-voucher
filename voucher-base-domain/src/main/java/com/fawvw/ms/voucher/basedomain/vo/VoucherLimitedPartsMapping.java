package com.fawvw.ms.voucher.basedomain.vo;

import java.util.Date;

public class VoucherLimitedPartsMapping {
    private Integer id;

    private String partName;

    private String partCode;

    private String carModel;

    private Integer fkBatchId;

    private Byte deleted;

    private Date createTime;

    private Date updateTime;

    private Byte active;

    public VoucherLimitedPartsMapping(Integer id, String partName, String partCode, String carModel, Integer fkBatchId, Byte deleted, Date createTime, Date updateTime, Byte active) {
        this.id = id;
        this.partName = partName;
        this.partCode = partCode;
        this.carModel = carModel;
        this.fkBatchId = fkBatchId;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.active = active;
    }

    public VoucherLimitedPartsMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode == null ? null : partCode.trim();
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
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