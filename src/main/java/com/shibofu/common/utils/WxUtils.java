package com.shibofu.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shibofu.common.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author potter.fu
 * @date 2018-12-25 13:32
 */
@Slf4j
@Service
public class WxUtils {
    @Autowired
    private HttpService httpService;

    public Map<String, Object> code2Session(String appId, String secret, String code) {
        String uri = MessageFormat.format(Constant.WxApi.CODE2SESSION, appId.trim(), secret.trim(), code.trim());
        String response = httpService.get(uri);
        return JSONObject.parseObject(response, new TypeReference<Map<String, Object>>(){});
    }
}
