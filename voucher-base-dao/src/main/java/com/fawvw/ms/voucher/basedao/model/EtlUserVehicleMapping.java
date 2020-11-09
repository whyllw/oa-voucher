package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class EtlUserVehicleMapping {
    private Integer id;

    private String aidRef;

    private Integer makeId;

    private String makeName;

    private String mobile;

    private String vin;

    private String carNumber;

    private Date vehicleSaleDate;

    private Date lastBackStoreTime;

    private Integer lastBackStoreMileage;

    private Byte vehicleBindingStatus;

    public EtlUserVehicleMapping(Integer id, String aidRef, Integer makeId, String makeName, String mobile, String vin, String carNumber, Date vehicleSaleDate, Date lastBackStoreTime, Integer lastBackStoreMileage, Byte vehicleBindingStatus) {
        this.id = id;
        this.aidRef = aidRef;
        this.makeId = makeId;
        this.makeName = makeName;
        this.mobile = mobile;
        this.vin = vin;
        this.carNumber = carNumber;
        this.vehicleSaleDate = vehicleSaleDate;
        this.lastBackStoreTime = lastBackStoreTime;
        this.lastBackStoreMileage = lastBackStoreMileage;
        this.vehicleBindingStatus = vehicleBindingStatus;
    }

    public EtlUserVehicleMapping() {
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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Date getVehicleSaleDate() {
        return vehicleSaleDate;
    }

    public void setVehicleSaleDate(Date vehicleSaleDate) {
        this.vehicleSaleDate = vehicleSaleDate;
    }

    public Date getLastBackStoreTime() {
        return lastBackStoreTime;
    }

    public void setLastBackStoreTime(Date lastBackStoreTime) {
        this.lastBackStoreTime = lastBackStoreTime;
    }

    public Integer getLastBackStoreMileage() {
        return lastBackStoreMileage;
    }

    public void setLastBackStoreMileage(Integer lastBackStoreMileage) {
        this.lastBackStoreMileage = lastBackStoreMileage;
    }

    public Byte getVehicleBindingStatus() {
        return vehicleBindingStatus;
    }

    public void setVehicleBindingStatus(Byte vehicleBindingStatus) {
        this.vehicleBindingStatus = vehicleBindingStatus;
    }
}