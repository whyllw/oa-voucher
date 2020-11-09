package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OemSelectGrantCarMakeMapping {
    private Integer id;

    private Integer fkSelectGrantId;

    private Integer makeId;

    private String makeName;

    private String makeCode;

    private Date createTime;

    private Date updateTime;

    public OemSelectGrantCarMakeMapping(Integer id, Integer fkSelectGrantId, Integer makeId, String makeName, String makeCode, Date createTime, Date updateTime) {
        this.id = id;
        this.fkSelectGrantId = fkSelectGrantId;
        this.makeId = makeId;
        this.makeName = makeName;
        this.makeCode = makeCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OemSelectGrantCarMakeMapping() {
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

    public Integer getMakeId() {
        return makeId;
    }

    public void setMakeId(Integer makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName == null ? null : makeName.trim();
    }

    public String getMakeCode() {
        return makeCode;
    }

    public void setMakeCode(String makeCode) {
        this.makeCode = makeCode == null ? null : makeCode.trim();
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