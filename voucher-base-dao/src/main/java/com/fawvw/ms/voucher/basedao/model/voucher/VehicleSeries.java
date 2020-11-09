package com.fawvw.ms.voucher.basedao.model.voucher;

/**
 * @author zhubiyuan
 * @Type VehicleSeries
 * @Desc 车系信息(用于mongodb数据库对应的实体)
 * @date 2019-02-02 11:24
 */
public class VehicleSeries {

    /**
     * 车型ID
     */
    private Integer id;
    /**
     * 车型码
     */
    private String vehicleSeriesCode;
    /**
     * 车型名称
     */
    private String vehicleSeriesName;

    @Override
    public String toString() {
        return "VehicleSeries{" +
                "id=" + id +
                ", vehicleSeriesCode='" + vehicleSeriesCode + '\'' +
                ", vehicleSeriesName='" + vehicleSeriesName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleSeriesCode() {
        return vehicleSeriesCode;
    }

    public void setVehicleSeriesCode(String vehicleSeriesCode) {
        this.vehicleSeriesCode = vehicleSeriesCode;
    }

    public String getVehicleSeriesName() {
        return vehicleSeriesName;
    }

    public void setVehicleSeriesName(String vehicleSeriesName) {
        this.vehicleSeriesName = vehicleSeriesName;
    }
}
