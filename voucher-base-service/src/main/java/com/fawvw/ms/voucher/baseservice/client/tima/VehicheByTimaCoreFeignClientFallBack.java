package com.fawvw.ms.voucher.baseservice.client.tima;

import com.fawvw.ms.voucher.basedomain.vo.VoucherSmsParam;
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
public class VehicheByTimaCoreFeignClientFallBack implements
    FallbackFactory<VehicheByTimaCoreFeignClient> {

    @Override
    public VehicheByTimaCoreFeignClient create(Throwable throwable) {
        return new VehicheByTimaCoreFeignClient() {
            @Override
            public String getVehicleList(Map map) {
                log.error("VehicheByTimaCoreFeignClient.getVehicleList remote fallback", throwable);
                return null;
            }

            @Override
            public String getOwnerAidByVin(Map map) {
                log.error("VehicheByTimaCoreFeignClient.queryPaymentStatus remote fallback", throwable);
                return null;
            }

            @Override
            public String getUserAccountByAid(Map map) {
                log.error("VehicheByTimaCoreFeignClient.settleInAccount remote fallback", throwable);
                return null;
            }

            @Override
            public String getSimpleUserInfoByAids(Map map) {
                log.error("VehicheByTimaCoreFeignClient.getSimpleUserInfoByAids remote fallback", throwable);
                return null;
            }

            @Override
            public String getVehicleDetail(Map map) {
                log.error("VehicheByTimaCoreFeignClient.getVehicleDetail remote fallback", throwable);
                return null;
            }

            @Override
            public String getUserAccount(Map map) {
                log.error("VehicheByTimaCoreFeignClient.getUserAccount remote fallback", throwable);
                return null;
            }

            @Override
            public String checkAccountIsRegistered(Map map) {
                log.error("VehicheByTimaCoreFeignClient.checkAccountIsRegistered remote fallback", throwable);
                return null;
            }

            @Override
            public String sendSmsMessage(VoucherSmsParam voucherSmsParam) {
                log.error("VehicheByTimaCoreFeignClient.sendSmsMessage remote fallback", throwable);
                return null;
            }

            @Override
            public String getAidsByMobiles(Map map) {
                log.error("VehicheByTimaCoreFeignClient.getAidsByMobiles remote fallback", throwable);
                return null;
            }
        };
    }
}
