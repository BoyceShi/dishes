package com.shibofu.common.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.collect.Maps;
import com.shibofu.common.token.Token;

import java.util.Map;

/**
 * JwtToken工具类
 *
 * @author potter.fu
 * @date 2018-12-13 18:19
 */
public class JwtTokenUtils {

    /**
     * token校验
     *
     * @param key   securityKey
     * @param token token字符串
     * @return boolean
     * @author potter.fu
     * @date 2018-12-13 18:27
     */
    public static boolean verify(String key, String token) {
        Algorithm algorithm;
        try {
            algorithm = Algorithm.HMAC512(key);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 生成token
     *
     * @param tokenStr token字符串
     * @return com.shibofu.common.token.Token
     * @author potter.fu
     * @date 2018-12-13 18:58
     */
    public static Token generateToken(String tokenStr) {
        Map<String, Claim> claims = JWT.decode(tokenStr).getClaims();

        Map<String, Object> map = Maps.newHashMap();
        for (String key : claims.keySet()) {
            map.put(key, claims.get(key).asString());
        }
        return JSON.parseObject(JSON.toJSONString(map), Token.class);
    }

    /**
     * 生成tokenString
     *
     * @param key securityKey
     * @param map java.util.Map
     * @return token String
     * @author potter.fu
     * @date 2018-12-14 09:46
     */
    public static String generateTokenString(String key, Map<String, Object> map) {
        Algorithm algorithm;
        JWTCreator.Builder token;
        algorithm = Algorithm.HMAC512(key);
        token = JWT.create();
        for (String mapKey : map.keySet()) {
            if (map.get(mapKey) == null) {
                token.withClaim(mapKey, "");
            } else {
                token.withClaim(mapKey, map.get(mapKey).toString());
            }
        }
        return token.sign(algorithm);
    }
}
