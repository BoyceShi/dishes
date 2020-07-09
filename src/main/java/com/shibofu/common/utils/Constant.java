package com.shibofu.common.utils;

/**
 * 常量类
 *
 * @author potter.fu
 * @date 2018-12-13 16:35
 */
public class Constant {

    /**
     * 后端报错数据集
     *
     * @author potter.fu
     * @date 2018-12-13 16:40
     */
    public static final class ErrorMessage {
        public static final String DEFAULT_EXCEPTION = "系统处理异常";
        public static final String BUSINESS_EXCEPTION = "业务处理异常";
        public static final String SQL_EXCEPTION = "数据库SQL执行出错";
        public static final String NULL_POINTER_EXCEPTION = "空指针异常";
        public static final String ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = "数组越界";
        public static final String CLASS_CAST_EXCEPTION = "强制类型转换异常";
        public static final String FILE_NOT_FOUND_EXCEPTION = "文件未找到";
        public static final String ILLEGAL_ARGUMENT_EXCEPTION = "方法参数异常";
        public static final String VERIFY_FAIL = "令牌校验失败，请重新登录";
        public static final String CODE2SESSION_FAIL = "微信换算openId失败，请退出重试";
    }

    /**
     * 微信api集合
     *
     * @author potter.fu
     * @date 2018-12-25 15:09
     */
    public static final class WxApi {
        /**
         * code2Session
         */
        public static final String CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
    }

    /**
     * 微信返回字段集
     *
     * @author potter.fu
     * @date 2018-12-25 15:09
     */
    public static final class WxParameter {
        /**
         * 错误码
         */
        public static final String ERR_CODE = "errcode";

        /**
         * 错误信息
         */
        public static final String ERR_MSG = "errmsg";

        /**
         * 用户唯一标识
         */
        public static final String OPEN_ID = "openid";

        /**
         * 会话密钥
         */
        public static final String SESSION_KEY = "session_key";
    }

    /**
     * 时间常量
     *
     * @author potter.fu
     * @date 2018-12-28 10:50
     */
    public static final class Date {
        /**
         * YYYY-MM-DD
         */
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
    }
}
