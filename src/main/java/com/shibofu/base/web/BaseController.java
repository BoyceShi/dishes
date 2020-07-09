package com.shibofu.base.web;

import com.google.common.collect.Maps;
import com.shibofu.base.service.BaseService;
import com.shibofu.common.exception.BusinessException;
import com.shibofu.common.response.ResponseBody;
import com.shibofu.common.utils.AliOssUtils;
import com.shibofu.common.utils.Constant;
import com.shibofu.common.utils.WxUtils;
import com.shibofu.user.model.User;
import com.shibofu.user.service.LoginService;
import com.shibofu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author potter.fu
 * @date 2018-12-14 8:38
 */
@RestController
public class BaseController {
    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    /**
     * 微信code换算openId，校验是否存在用户
     *
     * @param appId  微信appId
     * @param secret 微信secret
     * @param code   微信code
     * @return java.lang.String
     * @author potter.fu
     * @date 2018-12-25 16:16
     */
    @GetMapping(value = "wx/code2Session/{appId}/{secret}/{code}")
    public String code2Session(@PathVariable String appId,
                               @PathVariable String secret,
                               @PathVariable String code) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>();
        // 换算openId
        Map<String, Object> responseMap = wxUtils.code2Session(appId, secret, code);
        if (!StringUtils.isEmpty(responseMap.get(Constant.WxParameter.ERR_CODE))) {
            // 微信接口返回失败
            String errorMsg = StringUtils.isEmpty(responseMap.get(Constant.WxParameter.ERR_MSG)) ?
                    Constant.ErrorMessage.CODE2SESSION_FAIL : responseMap.get(Constant.WxParameter.ERR_MSG).toString();
            throw new BusinessException(errorMsg);
        } else {
            // openId返回异常
            if (StringUtils.isEmpty(responseMap.get(Constant.WxParameter.OPEN_ID))) {
                throw new BusinessException(Constant.ErrorMessage.CODE2SESSION_FAIL);
            } else {
                String openId = responseMap.get(Constant.WxParameter.OPEN_ID).toString();
                String token = loginService.loginOrRegister(openId);
                Map<String, Object> resultMap = Maps.newHashMap();
                resultMap.put("openId", openId);
                resultMap.put("token", token);
                responseBody.init(resultMap);
            }
        }
        return responseBody.toResponse();
    }

    /**
     * 更新用户信息
     *
     * @param user User
     * @return java.lang.String
     * @author potter.fu
     * @date 2018-12-26 11:06
     */
    @PutMapping(value = "wx/updateWxUserInfo")
    public String updateWxUserInfo(@RequestBody User user) {
        String token = userService.updateWxUserInfo(user);
        ResponseBody<String> responseBody = new ResponseBody<>();
        responseBody.init(token);
        return responseBody.toResponse();
    }

    /**
     * oss上传
     *
     * @param file 文件
     * @param type main 菜谱图片、 step 步骤图片
     * @return java.lang.String 文件名
     * @author potter.fu
     * @date 2019-01-29 10:13
     */
    @PostMapping(value = "oss/upload")
    public String ossUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        String fileName = baseService.ossUpload(file,type);
        ResponseBody<String> responseBody = new ResponseBody<>();
        responseBody.init(fileName);
        return responseBody.toResponse();
    }

    /**
     * 获取OSS图片url
     *
     * @param fileName 文件名
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-29 11:54
     */
    @GetMapping(value = "getOssImg/{fileName}")
    public String getOssImg(@PathVariable String fileName) {
        String url = AliOssUtils.getOssUrl(fileName);
        ResponseBody<String> responseBody = new ResponseBody<>();
        responseBody.init(url);
        return responseBody.toResponse();
    }
}
