package com.fawvw.ms.voucher.baseservice.client.sysarea;

import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.voucher.basedomain.vo.Areas;
import feign.hystrix.FallbackFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/04/09 10:05
 * @Description: 用户服务
 */
@Slf4j
@Component
public class SysAreaCoreFeignClientFallBack implements FallbackFactory<SysAreaCoreFeignClient> {

    @Override
    public SysAreaCoreFeignClient create(Throwable throwable) {
        return new SysAreaCoreFeignClient() {

            @Override
            public Result<List<Areas>> getAllAreas() {
                log.error("SysAreaCoreFeignClient.getAllAreas remote fallback", throwable);
                return null;
            }
        };
    }


}
