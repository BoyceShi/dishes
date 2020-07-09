package com.shibofu.common.interceptor;

import com.shibofu.common.exception.BusinessException;
import com.shibofu.common.service.CommonService;
import com.shibofu.common.token.Token;
import com.shibofu.common.token.TokenCache;
import com.shibofu.common.utils.Constant;
import com.shibofu.common.utils.IoUtils;
import com.shibofu.common.utils.JwtTokenUtils;
import com.shibofu.common.response.ResponseBody;
import com.shibofu.common.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author potter.fu
 * @date 2018-12-13 18:11
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private CommonService commonService;

    /**
     * 拦截器预处理（校验token）
     *
     * @param request  HttpServletRequest请求
     * @param response HttpServletResponse响应
     * @param handler  Object
     * @return boolean
     * @author potter.fu
     * @date 2018-12-13 19:01
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        ResponseBody body = new ResponseBody();

        // 校验tokenStr
        String tokenStr = request.getHeader("token");
        if (StringUtils.isEmpty(tokenStr) || !commonService.verifyToken(tokenStr)) {
            throw new BusinessException(ResponseCode.VERIFY_FAIL, Constant.ErrorMessage.VERIFY_FAIL);
        }

        // 生成token
        try {
            Token token = JwtTokenUtils.generateToken(tokenStr);
            TokenCache.getInstance().setToken(token);
        } catch (Exception e) {
            body.init(ResponseCode.VERIFY_FAIL, Constant.ErrorMessage.VERIFY_FAIL);
            throw new BusinessException(ResponseCode.VERIFY_FAIL, Constant.ErrorMessage.VERIFY_FAIL);
        }
        return true;
    }

    /**
     * 请求处理完毕回调
     *
     * @param request  HttpServletRequest请求
     * @param response HttpServletResponse响应
     * @param handler  Object
     * @param ex       Exception异常
     * @author potter.fu
     * @date 2018-12-13 19:01
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清理token
        TokenCache.getInstance().remove();
    }
}
