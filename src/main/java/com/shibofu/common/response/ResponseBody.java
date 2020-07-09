package com.shibofu.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author potter.fu
 * @date 2018-12-12 9:54
 */
public class ResponseBody<T> {

    private String code = ResponseCode.SUCCESS;

    private String msg = "";

    private T data;

    public void init(T data) {
        this.data = data;
    }

    public void init(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JSONField(ordinal = 0)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JSONField(ordinal = 1)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JSONField(ordinal = 2)
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * json filter
     *
     * @return java.lang.String
     * @author potter.fu
     * @date 2018-12-12 14:59
     */
    public String toResponse() {
        return JSON.toJSONString(this, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
