package com.fawvw.ms.voucher.baseservice.client.tima;

import com.fawvw.ms.oa.base.services.interceptor.CdpFeignRequestInterceptor;
import com.fawvw.ms.oa.core.constants.RequestConstants;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
import com.fawvw.ms.voucher.basedomain.vo.VoucherSmsParam;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ProjectName: payment-server
 * @Package: com.fawvw.ms.payment.baseservice.client.payment
 * @ClassName: AppPaymentByRunlinCoreFeignClient
 * @Author: liwu
 * @Description: ${description}
 * @Date: 2020/9/21 13:32
 * @Version: 1.0
 */

@FeignClient(name = "VehicheByTimaCoreFeignClient", url = "${cdp.tima.short-url}", decode404 = true, fallbackFactory = VehicheByTimaCoreFeignClientFallBack.class, configuration = CdpFeignRequestInterceptor.class)
public interface VehicheByTimaCoreFeignClient {

    @RequestMapping(value = RequestConstants.CDP_URI_INTERNAL_GET_VEHICLE_LIST, method = RequestMethod.GET)
    String getVehicleList(@RequestBody Map map);

    @RequestMapping(value = RequestConstants.CDP_URI_GET_OWNER_AID_BY_VIN, method = RequestMethod.GET)
    String getOwnerAidByVin(@RequestBody Map map);

    @RequestMapping(value = RequestConstants.CDP_URI_GET_USER_ACCOUNT_BY_AID, method = RequestMethod.GET)
    String getUserAccountByAid(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GET_USER, method = RequestMethod.GET)
    String getSimpleUserInfoByAids(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.CARINFO, method = RequestMethod.GET)
    String getVehicleDetail(@RequestBody Map map);

    @RequestMapping(value = RequestConstants.CDP_URI_GET_USER_ACCOUNT, method = RequestMethod.GET)
    String getUserAccount(@RequestBody Map map);

    @RequestMapping(value = RequestConstants.CDP_URL_CHECK_ACCOUNT_IS_REGISTERED, method = RequestMethod.GET)
    String checkAccountIsRegistered(@RequestBody Map map);

    @RequestMapping(value = RequestConstants.CDP_URL_SEND_SMS_MESSAGE, method = RequestMethod.GET)
    String sendSmsMessage(@RequestBody VoucherSmsParam voucherSmsParam);

    @RequestMapping(value = RequestConstants.CDP_URI_GET_AIDS_BY_MOBILES, method = RequestMethod.POST)
    String getAidsByMobiles(@RequestBody Map map);
}
