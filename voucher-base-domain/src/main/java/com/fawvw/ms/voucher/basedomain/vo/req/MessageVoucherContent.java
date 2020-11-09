package com.fawvw.ms.voucher.basedomain.vo.req;

import com.fawvw.ms.voucher.basedomain.vo.VoucherPopUpMessageInfo;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @program: one-app-server
 * @description: 消息内容JSON映射的对象
 * @author: zhubiyuan
 * @create: 2019-08-29 13:58
 **/
@Data
public class MessageVoucherContent extends BaseMsgParam {

    @ApiModelProperty(value = "跳转url")
    private String detailUrl;

    @ApiModelProperty(value = "优惠券默认图片")
    private String postImageUrl;

    @ApiModelProperty(value = "消息类型")
    private MessageType type;

    @ApiModelProperty(value = "是否弹屏")
    private Byte popUpMessageNotice;

    @ApiModelProperty(value = "消息内容")
    private String postContent;

    private List<VoucherPopUpMessageInfo> vouchers;
}
