package com.shibofu.user.service;

/**
 * @author potter.fu
 * @date 2018-12-14 9:31
 */
public interface LoginService {

    /**
     * 通过openId注册或登录，返回token
     * 
     * @param openId 微信openId
     * @return token
     * @author potter.fu
     * @date 2018-12-25 15:54
     */
    String loginOrRegister(String openId);
}
