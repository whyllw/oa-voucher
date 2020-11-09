package com.fawvw.ms.voucher.basedomain.vo;

public class Areas {
    private Integer id;

    private String code;

    private String name;

    private String regionCode;

    public Areas(Integer id, String code, String name, String regionCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.regionCode = regionCode;
    }

    public Areas() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }
}