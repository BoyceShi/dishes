package com.shibofu.user.service.impl;

import com.shibofu.common.token.TokenCache;
import com.shibofu.user.dao.UserDao;
import com.shibofu.user.model.User;
import com.shibofu.user.service.LoginService;
import com.shibofu.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author potter.fu
 * @date 2018-12-12 8:44
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginService loginService;

    /**
     * 获取用户信息
     *
     * @param openId 微信openId
     * @return com.shibofu.user.model.User
     * @author potter.fu
     * @date 2018-12-12 08:44
     */
    @Override
    public User getUser(String openId) {
        User user = userDao.getUser(openId);
        try {
            user.setNickName(URLDecoder.decode(user.getNickName(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    /**
     * 更新客户信息
     *
     * @param user User
     * @author potter.fu
     * @date 2018-12-26 11:07
     */
    @Override
    public String updateWxUserInfo(User user) {
        User existUser = userDao.getUser(user.getWxOpenId());
        if (existUser != null && !StringUtils.isEmpty(existUser.getNickName())) {
            user.setNickName(null);
        } else {
            try {
                user.setNickName(URLEncoder.encode(user.getNickName(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
        }
        userDao.updateWxUserInfo(user);
        return loginService.loginOrRegister(user.getWxOpenId());
    }

    /**
     * 更新用户昵称
     *
     * @param nickName 昵称
     * @author potter.fu
     * @date 2018-12-28 17:30
     */
    @Override
    public void updateNickName(String nickName) {
        String openId = TokenCache.getInstance().getToken().getOpenId();
        try {
            nickName = URLEncoder.encode(nickName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        User user = User.builder().wxOpenId(openId).nickName(nickName).build();
        userDao.updateWxUserInfo(user);
    }

    /**
     * 更新用户性别
     *
     * @param gender 0未知 1男 2女
     * @author potter.fu
     * @date 2019-01-03 09:30
     */
    @Override
    public void updateGender(Integer gender) {
        String openId = TokenCache.getInstance().getToken().getOpenId();
        User user = User.builder().wxOpenId(openId).gender(gender).build();
        userDao.updateWxUserInfo(user);
    }

    /**
     * 更新生日
     *
     * @param birthday 生日
     * @author potter.fu
     * @date 2019-01-04 16:36
     */
    @Override
    public void updateBirthday(Date birthday) {
        String openId = TokenCache.getInstance().getToken().getOpenId();
        User user = User.builder().wxOpenId(openId).birthday(birthday).build();
        userDao.updateWxUserInfo(user);
    }

    @Override
    public void updatePersonalSignature(String personalSignature) {
        String openId = TokenCache.getInstance().getToken().getOpenId();
        User user = User.builder().wxOpenId(openId).personalSignature(personalSignature).build();
        userDao.updateWxUserInfo(user);
    }
}
