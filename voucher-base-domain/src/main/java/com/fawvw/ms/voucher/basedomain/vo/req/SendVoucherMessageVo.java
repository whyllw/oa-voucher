package com.fawvw.ms.voucher.basedomain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: one-app-server
 * @Package: com.fawvw.ms.oneappserver.vo.client.req
 * @ClassName: SendVoucherMessageVo
 * @Author: liwu
 * @Description: ${description}
 * @Date: 2020/10/27 10:24
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "client优惠券短信发送给用户")
public class SendVoucherMessageVo {
    @ApiModelProperty("cdp的用户Id")
    private String refId;
    @ApiModelProperty(value = "消息内容")
    private String content;
    @ApiModelProperty(value = "消息标题")
    private String title;
    @ApiModelProperty(value = "消息类型(用于区分是赞与收藏还是评论消息)")
    private String messageType;
    @ApiModelProperty(value = "发送到CDP的消息封装实体")
    private MessageVoucherContent params;
}
