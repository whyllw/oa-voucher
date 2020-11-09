package com.fawvw.ms.voucher.basedao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationRedisUser {
    private Integer userId;

    private String refId;

    private String nickName;

    private String mobile;

    private String verifiedName;

    private Byte roleType;

    private String avatarUrl;

}