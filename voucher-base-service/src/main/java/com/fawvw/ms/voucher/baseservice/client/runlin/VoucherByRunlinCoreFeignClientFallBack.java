package com.fawvw.ms.voucher.baseservice.client.runlin;

import feign.hystrix.FallbackFactory;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: payment-server
 * @Package: com.fawvw.ms.payment.baseservice.client.payment
 * @ClassName: AppPaymentByRunlinCoreFeignClient
 * @Author: liwu
 * @Description: ${description}
 * @Date: 2020/9/21 13:32
 * @Version: 1.0
 */

@Slf4j
@Component
public class VoucherByRunlinCoreFeignClientFallBack implements
    FallbackFactory<VoucherByRunlinCoreFeignClient> {

    @Override
    public VoucherByRunlinCoreFeignClient create(Throwable throwable) {
        return new VoucherByRunlinCoreFeignClient() {

            @Override
            public String queryTicketStatusNumServer(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.queryTicketStatusNumServer remote fallback", throwable);
                return null;
            }

            @Override
            public String ticketGrantDMS(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.ticketGrantDMS remote fallback", throwable);
                return null;
            }

            @Override
            public String queryTicketDetail(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.queryTicketDetail remote fallback", throwable);
                return null;
            }

            @Override
            public String queryTicketListClient(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.queryTicketListClient remote fallback", throwable);
                return null;
            }

            @Override
            public String durationTicket(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.durationTicket remote fallback", throwable);
                return null;
            }

            @Override
            public String canceldurationTicket(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.canceldurationTicket remote fallback", throwable);
                return null;
            }

            @Override
            public String cardTicket(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.cardTicket remote fallback", throwable);
                return null;
            }

            @Override
            public String changecodeAbndon(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.changecodeAbndon remote fallback", throwable);
                return null;
            }

            @Override
            public String ticketNumExt(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.ticketNumExt remote fallback", throwable);
                return null;
            }

            @Override
            public String querydealerinfo(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.querydealerinfo remote fallback", throwable);
                return null;
            }

            @Override
            public String ticketGeneratetDjq(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.ticketGeneratetDjq remote fallback", throwable);
                return null;
            }

            @Override
            public String ticketGeneratetSwq(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.ticketGeneratetSwq remote fallback", throwable);
                return null;
            }

            @Override
            public String ticketGeneratetYyq(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.ticketGeneratetYyq remote fallback", throwable);
                return null;
            }

            @Override
            public String templateUpdate(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.templateUpdate remote fallback", throwable);
                return null;
            }

            @Override
            public String templateDmsUpdate(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.templateDmsUpdate remote fallback", throwable);
                return null;
            }

            @Override
            public String ticketAbndon(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.ticketAbndon remote fallback", throwable);
                return null;
            }

            @Override
            public String importRedeemcode(Map map) {
                log.error("VoucherByRunlinCoreFeignClient.importRedeemcode remote fallback", throwable);
                return null;
            }
        };
    }
}
