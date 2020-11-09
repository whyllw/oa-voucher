package com.fawvw.ms.voucher.baseservice.client.sysarea;

import com.fawvw.ms.oa.core.result.Result;
import com.fawvw.ms.voucher.basedomain.vo.Areas;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: DengXiaolong
 * @mail: xiaolong.deng@faw-vw.com
 * @Date: 2020/05/13 15:35
 * @Description: 消息服务
 */
@FeignClient(name = "server-general-v1", decode404 = true, fallbackFactory = SysAreaCoreFeignClientFallBack.class)
public interface SysAreaCoreFeignClient {

    @RequestMapping(value = "/general/area/getAllAreas", method = RequestMethod.GET)
    Result<List<Areas>> getAllAreas();

}
