package com.shibofu.common.token;

import lombok.Data;

/**
 * token令牌
 *
 * @author potter.fu
 * @date 2018-12-13 18:03
 */
@Data
public class Token {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 微信openId
     */
    private String openId;
}
