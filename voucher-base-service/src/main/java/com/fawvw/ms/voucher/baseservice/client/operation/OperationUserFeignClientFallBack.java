package com.fawvw.ms.voucher.baseservice.client.operation;

import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.voucher.basedao.model.OperationUser;
import feign.hystrix.FallbackFactory;
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
public class OperationUserFeignClientFallBack implements
    FallbackFactory<OperationUserFeignClient> {

    @Override
    public OperationUserFeignClient create(Throwable throwable) {
        return new OperationUserFeignClient() {

            @Override
            public Result<OperationUser> getUserById(Integer userId) {
                log.error("OperationUserFeignClient.getUserById remote fallback", throwable);
                return null;
            }

            @Override
            public Result<OperationUser> getOperationUserByRefId(String refId) {
                log.error("OperationUserFeignClient.getOperationUserByRefId remote fallback", throwable);
                return null;
            }
        };
    }
}
