package com.shibofu.user.service;

import com.shibofu.user.model.User;

import java.util.Date;

/**
 * @author potter.fu
 * @date 2018-12-12 8:43
 */
public interface UserService {
    /**
     * 获取用户信息
     *
     * @param openId 微信openId
     * @return com.shibofu.user.model.User
     * @author potter.fu
     * @date 2018-12-12 08:44
     */
    User getUser(String openId);

    /**
     * 更新客户信息
     *
     * @param user User
     * @return User
     * @author potter.fu
     * @date 2018-12-26 11:07
     */
    String updateWxUserInfo(User user);

    /**
     * 更新用户昵称
     *
     * @param nickName 昵称
     * @author potter.fu
     * @date 2018-12-28 17:30
     */
    void updateNickName(String nickName);

    /**
     * 更新用户性别
     *
     * @param gender 0未知 1男 2女
     * @author potter.fu
     * @date 2019-01-03 09:30
     */
    void updateGender(Integer gender);

    /**
     * 更新生日
     *
     * @param birthday 生日
     * @author potter.fu
     * @date 2019-01-04 16:36
     */
    void updateBirthday(Date birthday);

    /**
     * 更新个性签名
     *
     * @param personalSignature 个性签名
     * @author potter.fu
     * @date 2019-01-04 22:05
     */
    void updatePersonalSignature(String personalSignature);
}
