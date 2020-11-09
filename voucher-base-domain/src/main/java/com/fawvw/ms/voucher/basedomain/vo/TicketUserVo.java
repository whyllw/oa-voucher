package com.fawvw.ms.voucher.basedomain.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhubiyuan
 * @Type TicketUserVo
 * @Desc
 * @date 2019-08-14 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketUserVo {
    /**
     * 用户id
     */
    private String aid;
    /**
     * 卡券生效开始日期
     */
    private String starttime;
    /**
     * 卡券有效截止日期
     */
    private String endtime;
    /**
     * 发放数量
     */
    private String generateNum;
    /**
     * 业务类型代码
     */
    private String businessCode;
    /**
     * 大区代码
     */
    private String bigRegionCode;
    /**
     * 小区代码
     */
    private String smallRegionCode;
    /**
     * 经销商代码列表
     */
    private List<DealerCodeVo> dealercodeList;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户身份证
     */
    private String idCard;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 车辆VIN
     */
    private String vin;
}
