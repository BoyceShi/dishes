package com.shibofu.common.exception;

import com.shibofu.common.utils.Constant;
import com.shibofu.common.utils.IoUtils;
import com.shibofu.common.response.ResponseBody;
import com.shibofu.common.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * 业务异常处理器
 *
 * @author potter.fu
 * @date 2018-12-13 16:23
 */
@RestControllerAdvice
@Slf4j
public class BusinessExceptionHandler {
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.error(e.getMessage(), e);
        String code = ResponseCode.FAIL;
        String message = Constant.ErrorMessage.DEFAULT_EXCEPTION;
        // 业务异常
        if (e instanceof BusinessException) {
            code = StringUtils.isEmpty(((BusinessException) e).getCode()) ? ResponseCode.FAIL :
                    ((BusinessException) e).getCode();
            message = StringUtils.isEmpty(e.getMessage()) ? Constant.ErrorMessage.BUSINESS_EXCEPTION : e.getMessage();
        }

        // sql异常
        if (e.getCause() instanceof SQLException) {
            message = Constant.ErrorMessage.SQL_EXCEPTION;
        }

        // 空指针异常
        if (e instanceof NullPointerException) {
            message = Constant.ErrorMessage.NULL_POINTER_EXCEPTION;
        }

        // 数组越界
        if (e instanceof ArrayIndexOutOfBoundsException) {
            message = Constant.ErrorMessage.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION;
        }

        // 强制类型转换异常
        if (e instanceof ClassCastException) {
            message = Constant.ErrorMessage.CLASS_CAST_EXCEPTION;
        }

        // 文件未找到
        if (e instanceof FileNotFoundException) {
            message = Constant.ErrorMessage.FILE_NOT_FOUND_EXCEPTION;
        }

        // 方法参数异常
        if (e instanceof IllegalArgumentException) {
            message = Constant.ErrorMessage.ILLEGAL_ARGUMENT_EXCEPTION;
        }
        // validator异常
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            if (bindingResult.hasErrors()){
                message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            }
        }

        ResponseBody body = new ResponseBody();
        body.init(code, message);
        IoUtils.writeContent(body);
    }
}
