package com.shibofu.common.utils;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author potter.fu
 * @date 2018-12-26 16:01
 */
public class RequestUtils {

    /**
     * get请求解析参数转换map
     *
     * @param request HttpServletRequest
     * @return java.util.Map
     * @author potter.fu
     * @date 2018-12-26 17:23
     */
    public static Map<String, Object> requestToMap(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        for (String item : request.getParameterMap().keySet()) {
            Object value = request.getParameterMap().get(item);
            if (value != null) {
                if (!"null".equals(((String[]) value)[0].toLowerCase()) && !"".equals(((String[]) value)[0])) {
                    map.put(item, ((String[]) value)[0].trim());
                }
            }
        }
        return map;
    }
}
