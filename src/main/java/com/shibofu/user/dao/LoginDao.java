package com.shibofu.user.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author potter.fu
 * @date 2018-12-14 9:33
 */
@Repository
public interface LoginDao {
    /**
     * 通过openId获取客户信息
     *
     * @param openId 客户openId
     * @return java.util.Map
     * @author potter.fu
     * @date 2018-12-25 16:04
     */
    Map<String, Object> getTokenMap(String openId);

    /**
     * 注册
     *
     * @param openId 微信openId
     * @author potter.fu
     * @date 2018-12-26 09:22
     */
    void register(String openId);
}
