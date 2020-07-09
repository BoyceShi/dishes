package com.shibofu.user.dao;

import com.shibofu.user.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author potter.fu
 * @date 2018-12-12 8:45
 */
@Repository
public interface UserDao {
    /**
     * 获取用户信息
     *
     * @param openId 微信openId
     * @return com.shibofu.user.model.User
     * @author potter.fu
     * @date 2018-12-12 08:45
     */
    User getUser(String openId);

    /**
     * 更新用户信息
     *
     * @param user User
     * @author potter.fu
     * @date 2018-12-26 11:09
     */
    void updateWxUserInfo(User user);
}
