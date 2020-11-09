package com.fawvw.ms.voucher.basedao.model.voucher;

/**
 * @author zhubiyuan
 * @Type UserVehicle
 * @Desc 用户车辆信息(用于mongodb数据库对应的实体)
 * @date 2019-09-02 11:20
 */
public class UserVehicle {

    //用户aid
    private long aId;

    //用户类型：1-车主，2-潜客
    private int userType;

    //车系：探岳
    private long seriesId;

    //车系名称
    private String seriesName;

    //车牌：京A-HU0001
    private String vehicleNumber;

    //车牌简写：京A
    private String simpleVehicleNumber;

    //销售日期
    private String saleDate;

    //车辆id
    private long vId;

    //vin码
    private String vin;

    //最后入库时间
    private String lastBackStoreTime;

    //最后入库里程
    private int lastBackStoreMileage;

    @Override
    public String toString() {
        return "UserVehicle{" +
                "aId=" + aId +
                ", userType=" + userType +
                ", seriesId=" + seriesId +
                ", seriesName='" + seriesName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", simpleVehicleNumber='" + simpleVehicleNumber + '\'' +
                ", saleDate='" + saleDate + '\'' +
                ", vId=" + vId +
                ", vin='" + vin + '\'' +
                ", lastBackStoreTime='" + lastBackStoreTime + '\'' +
                ", lastBackStoreMileage=" + lastBackStoreMileage +
                '}';
    }

    public long getaId() {
        return aId;
    }

    public void setaId(long aId) {
        this.aId = aId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getSimpleVehicleNumber() {
        return simpleVehicleNumber;
    }

    public void setSimpleVehicleNumber(String simpleVehicleNumber) {
        this.simpleVehicleNumber = simpleVehicleNumber;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public long getvId() {
        return vId;
    }

    public void setvId(long vId) {
        this.vId = vId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLastBackStoreTime() {
        return lastBackStoreTime;
    }

    public void setLastBackStoreTime(String lastBackStoreTime) {
        this.lastBackStoreTime = lastBackStoreTime;
    }

    public int getLastBackStoreMileage() {
        return lastBackStoreMileage;
    }

    public void setLastBackStoreMileage(int lastBackStoreMileage) {
        this.lastBackStoreMileage = lastBackStoreMileage;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}
