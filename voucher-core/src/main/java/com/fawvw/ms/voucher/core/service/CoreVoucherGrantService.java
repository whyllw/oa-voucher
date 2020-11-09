package com.fawvw.ms.voucher.core.service;


import com.fawvw.ms.oneappserver.vo.qanda.UserVo;
import com.fawvw.ms.voucher.basedomain.vo.ThirdpartShopResultVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherBatchMyWelfareInfoListVo;
import com.fawvw.ms.voucher.basedomain.vo.VoucherRedPacketVo;
import org.springframework.stereotype.Component;

/**
 * @author zhubiyuan
 * @Type VoucherGrantService
 * @Desc
 * @date 2019-08-15 10:57
 */
@Component
public interface CoreVoucherGrantService {

    /**
     * 我的福利查看优惠券列表
     *
     * @param userVo
     * @param grantType
     * @param pageSize
     * @param currentPage
     * @param createTime
     * @return
     */
    VoucherBatchMyWelfareInfoListVo voucherGrantList(UserVo userVo, Byte grantType, int pageSize,
        int currentPage, String createTime) throws Exception;

    VoucherBatchMyWelfareInfoListVo getMyVocherList(UserVo userVo, String accessToken,
        Byte voucherType,
        String voucherStatus, int pageSize, int currentPage, String businessCode) throws Exception;

    /**
     * 自动发放触发
     *
     * @param userVo         用户
     * @param businessType   发放场景: 1:取送车,2:维保预约,3:延时服务
     * @param token
     * @param vin
     * @param validStartTime
     * @return
     */
    VoucherBatchMyWelfareInfoListVo grantAutomatic(UserVo userVo, Integer businessType,
        String token, String vin, String validStartTime) throws Exception;

    /**
     * 取送车、我的福利发放触发
     *
     * @param userVo
     * @param grantType
     * @param token
     * @param vin
     * @param validStartTime
     * @return
     */
    VoucherBatchMyWelfareInfoListVo grantVoucher(UserVo userVo, Byte grantType, String batchIds,
        String token, String vin, String validStartTime) throws Exception;

    Object freezeVoucher(String params);

    Object cancelFreezeVoucher(String params);

    Object redeemVoucher(String params);

    /**
     * 筛选发放触发
     *  @param userVo
     * @param token
     * @return
     */
    VoucherBatchMyWelfareInfoListVo grantVoucherFilter(UserVo userVo, String token) throws Exception;

    void grantDirectingUser(UserVo userVo, String token) throws Exception;

    ThirdpartShopResultVo getThirdpartShops(String cityCode, String shopName, Integer batchId,
        Integer pageSize, Integer currentPage);


    VoucherRedPacketVo getVoucherRedPacketInfo(UserVo userVo) throws Exception;

    VoucherBatchMyWelfareInfoListVo voucherGrantListForApplet(UserVo userVo, Byte grantType,
        int pageSize, int currentPage, String createTime) throws Exception;
}
