package com.fawvw.ms.voucher.oss.service;


import com.fawvw.ms.oa.base.server.vo.AdminUserVO;
import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.voucher.basedomain.vo.DirectingGrantVoucherRequest;
import com.fawvw.ms.voucher.basedomain.vo.PackageVoucherInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherDealerGrantRecordVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantBySelectParamVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantCarInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherGrantInfoVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherWriteOffInfoVo;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author zhubiyuan
 * @Type AdminVoucherGrantService
 * @Desc
 * @date 2019-08-13 010:16
 */
@Component
public interface OssVoucherGrantService {
    /**
     * 创建自动发放记录
     *
     * @param voucherGrantInfoVo
     * @return Integer
     * @throws Exception
     */
    Integer createVoucherGrant(VoucherGrantInfoVo voucherGrantInfoVo) throws Exception;

    /**
     * 待发放车辆列表
     *
     * @param type
     * @param value
     * @return
     */
    List<VoucherGrantCarInfoVo> grantCarList(Byte type, String value);

    /**
     * 获取套餐券列表
     *
     * @param aid
     * @param carAge
     * @return
     */
    List<PackageVoucherInfoVo> getPackageVoucherList(String aid, String carAge,
        AdminUserVO adminUserVO);

    /**
     * 套餐券发放
     *
     * @param voucherDealerGrantRecordVo
     * @param adminUserVO
     * @return
     */
    int grantPackageVoucher(VoucherDealerGrantRecordVo voucherDealerGrantRecordVo,
        AdminUserVO adminUserVO);

    /**
     * 撤回套餐券发放
     *
     * @param recordId
     * @return
     */
    Boolean withDrawGrantVoucher(Integer recordId, AdminUserVO adminUserVO);

    /**
     * 获取经销商发放记录列表
     *
     * @param adminUserVO
     * @param vin
     * @param phone
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageInfo<VoucherDealerGrantRecordInfoVo> grantRecordList(AdminUserVO adminUserVO, String vin,
        String phone, int pageSize, int currentPage);

    /**
     * 待核销优惠券详情
     *
     * @param redeemCode
     * @param adminUserVO
     * @return
     */
    Result getVoucherInfo(String redeemCode, AdminUserVO adminUserVO);

    /**
     * 卡券核销
     *
     * @param voucherWriteOffInfoVo
     * @param adminUserVO
     * @return
     */
    Result writeOffVoucher(VoucherWriteOffInfoVo voucherWriteOffInfoVo, AdminUserVO adminUserVO);

    /**
     * 筛选发放获取满足条件的用户数量
     *
     * @param voucherGrantConfigParamVo
     * @return
     */
    Integer getUserCount(VoucherGrantBySelectParamVo voucherGrantConfigParamVo);

    void grantDirectingUser(DirectingGrantVoucherRequest directingGrantVoucherRequest) throws Exception;

    //void sendVoucherSms(DirectingGrantVoucherRequest directingGrantVoucherRequest) throws Exception;

    boolean isValidSmsTemplate(DirectingGrantVoucherRequest directingGrantVoucherRequest);
}
