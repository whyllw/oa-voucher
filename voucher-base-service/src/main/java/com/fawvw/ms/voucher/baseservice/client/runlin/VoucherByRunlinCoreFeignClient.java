package com.fawvw.ms.voucher.baseservice.client.runlin;

import com.fawvw.ms.oa.base.services.interceptor.CdpFeignRequestInterceptor;
import com.fawvw.ms.oa.core.constants.RequestConstants;
import com.fawvw.ms.voucher.basedomain.constants.VoucherConstants;
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

@FeignClient(name = "VoucherByRunlinCoreFeignClient", url = "${cdp.runlin.short-url}", decode404 = true, fallbackFactory = VoucherByRunlinCoreFeignClientFallBack.class, configuration = CdpFeignRequestInterceptor.class)
public interface VoucherByRunlinCoreFeignClient {

    @RequestMapping(value = VoucherConstants.VOUCHER_NUM, method = RequestMethod.POST)
    String queryTicketStatusNumServer(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GRANT, method = RequestMethod.POST)
    String ticketGrantDMS(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.VOUCHERINFO, method = RequestMethod.POST)
    String queryTicketDetail(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GET_USER_VOUCHER_LIST_URL, method = RequestMethod.POST)
    String queryTicketListClient(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.DURATION_VOUCHER, method = RequestMethod.POST)
    String durationTicket(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.CANCEL_DURATION_VOUCHER, method = RequestMethod.POST)
    String canceldurationTicket(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.COMMON_REDEEM_VOUCHER, method = RequestMethod.POST)
    String cardTicket(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.CHANGE_CODE_ABNDON, method = RequestMethod.POST)
    String changecodeAbndon(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.ADD_VOUCHER_BATCH_STORE_COUNT_URL, method = RequestMethod.POST)
    String ticketNumExt(@RequestBody Map map);

    @RequestMapping(value = RequestConstants.CDP_URI_QUERY_DEALER_INFO, method = RequestMethod.POST)
    String querydealerinfo(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GENERATE_VOUCHER_DJQ_TEMPLATE_URL, method = RequestMethod.POST)
    String ticketGeneratetDjq(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GENERATE_VOUCHER_SWQ_TEMPLATE_URL, method = RequestMethod.POST)
    String ticketGeneratetSwq(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GENERATE_VOUCHER_YYQ_TEMPLATE_URL, method = RequestMethod.POST)
    String ticketGeneratetYyq(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GENERATE_COMMON_REDEEM_TEMPLATE_URL, method = RequestMethod.POST)
    String templateUpdate(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.GENERATE_REDEEM_TEMPLATE_URL, method = RequestMethod.POST)
    String templateDmsUpdate(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.ABNDON_VOUCHER_TEMPLATE_URL, method = RequestMethod.POST)
    String ticketAbndon(@RequestBody Map map);

    @RequestMapping(value = VoucherConstants.IMPORT_REDEEM_CODE_URL, method = RequestMethod.POST)
    String importRedeemcode(@RequestBody Map map);


}
