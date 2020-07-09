package com.shibofu.user.service.impl;

import com.shibofu.common.utils.JwtTokenUtils;
import com.shibofu.user.dao.LoginDao;
import com.shibofu.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author potter.fu
 * @date 2018-12-14 9:32
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class LoginServiceImpl implements LoginService {

    @Value("${security.key}")
    private String securityKey;

    @Autowired
    private LoginDao loginDao;

    /**
     * 校验用户是否存在
     *
     * @param openId 微信openId
     * @return token
     * @author potter.fu
     * @date 2018-12-25 15:54
     */
    @Override
    public String loginOrRegister(String openId) {
        Map<String, Object> user = loginDao.getTokenMap(openId);
        if (user == null){
            loginDao.register(openId);
        }else{
            return JwtTokenUtils.generateTokenString(securityKey, user);
        }
        return null;
    }
}
