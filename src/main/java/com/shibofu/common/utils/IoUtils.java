package com.shibofu.common.utils;

import com.shibofu.common.response.ResponseBody;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * IO工具
 *
 * @author potter.fu
 * @date 2018-12-13 17:11
 */
public class IoUtils {
    /**
     * 向客户端输出ResponseBody
     *
     * @param body com.shibofu.response
     * @author potter.fu
     * @date 2018-12-13 17:28
     */
    @SneakyThrows
    public static void writeContent(ResponseBody body) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletResponse response = servletRequestAttributes.getResponse();
            if (response != null) {
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "text/plain;charset=UTF-8");
                response.setHeader("icop-content-type", "exception");
                PrintWriter writer = response.getWriter();
                writer.print(body.toResponse());
                writer.flush();
                writer.close();
            }
        }
    }
}
