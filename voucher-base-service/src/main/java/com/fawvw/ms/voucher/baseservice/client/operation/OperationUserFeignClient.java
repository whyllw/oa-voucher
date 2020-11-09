package com.fawvw.ms.voucher.baseservice.client.operation;

import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.voucher.basedao.model.OperationUser;
import org.springframework.cloud.openfeign.FeignClient;
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

@FeignClient(name = "server-general-v1", decode404 = true, fallbackFactory = OperationUserFeignClientFallBack.class)
public interface OperationUserFeignClient {

    @RequestMapping(value = "/operationUser/get_user_by_id", method = RequestMethod.GET)
    Result<OperationUser> getUserById(Integer userId);

    @RequestMapping(value = "/operationUser/get_user_by_ref_id", method = RequestMethod.GET)
    Result<OperationUser> getOperationUserByRefId(String refId);
}
