package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemSelectGrantCarNumberMapping {
    private Integer id;

    private Integer fkSelectGrantId;

    private String carNumber;

    private Date createTime;

    private Date updateTime;

    public OemSelectGrantCarNumberMapping(Integer id, Integer fkSelectGrantId, String carNumber, Date createTime, Date updateTime) {
        this.id = id;
        this.fkSelectGrantId = fkSelectGrantId;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OemSelectGrantCarNumberMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkSelectGrantId() {
        return fkSelectGrantId;
    }

    public void setFkSelectGrantId(Integer fkSelectGrantId) {
        this.fkSelectGrantId = fkSelectGrantId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
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