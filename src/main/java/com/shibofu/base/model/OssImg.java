package com.shibofu.base.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author potter.fu
 * @date 2019-01-29 16:31
 */
@Data
@Builder
public class OssImg {
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件md5
     */
    private String md5;
}
