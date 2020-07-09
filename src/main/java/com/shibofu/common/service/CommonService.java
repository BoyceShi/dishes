package com.shibofu.common.service;

import com.shibofu.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 公共service
 *
 * @author potter.fu
 * @date 2018-12-13 18:32
 */
@Component
public class CommonService {

    @Value("${security.key}")
    private String securityKey;

    public boolean verifyToken(String tokenStr) {
        return JwtTokenUtils.verify(securityKey, tokenStr);
    }
}
