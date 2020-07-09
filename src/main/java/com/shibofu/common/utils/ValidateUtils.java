package com.shibofu.common.utils;

import com.shibofu.common.exception.BusinessException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author potter.fu
 * @date 2018-12-14 9:26
 */
public class ValidateUtils {

    /**
     * 校验Map类型中的key是否为空
     *
     * @param target         Map
     * @param parameterNames key值
     * @author potter.fu
     * @date 2018-12-14 09:28
     */
    public static void validateMap(Map<String, Object> target, List<String> parameterNames) {
        for (String parameterName : parameterNames) {
            if (StringUtils.isEmpty(target.get(parameterName))) {
                throw new BusinessException("参数" + parameterName + "不能为空");
            }
        }
    }
}
