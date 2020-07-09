package com.shibofu.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 业务异常类
 *
 * @author potter.fu
 * @date 2018-12-13 16:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    /**
     * 构造方法
     *
     * @param message 异常信息
     * @author potter.fu
     * @date 2018-12-13 16:17
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param code    异常编码
     * @param message 异常信息
     * @author potter.fu
     * @date 2018-12-13 16:21
     */
    public BusinessException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    /**
     * 构造方法
     *
     * @param message 异常信息
     * @param cause   根异常类
     * @author potter.fu
     * @date 2018-12-13 16:21
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
