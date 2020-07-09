package com.shibofu.base.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author potter.fu
 * @date 2019-01-29 15:57
 */
public interface BaseService {
    /**
     * 上传阿里OSS
     *
     * @param file 文件
     * @param type main 菜谱图片、 step 步骤图片
     * @author potter.fu
     * @date 2019-01-29 15:58
     */
    String ossUpload(MultipartFile file, String type);
}
