package com.shibofu.base.service.impl;

import com.shibofu.base.dao.BaseDao;
import com.shibofu.base.model.OssImg;
import com.shibofu.base.service.BaseService;
import com.shibofu.common.exception.BusinessException;
import com.shibofu.common.utils.AliOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author potter.fu
 * @date 2019-01-29 15:58
 */
@Service
@Slf4j
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseDao baseDao;

    /**
     * 上传阿里OSS
     *
     * @param file 文件
     * @param type main 菜谱图片、 step 步骤图片
     * @author potter.fu
     * @date 2019-01-29 15:58
     */
    @Override
    public String ossUpload(MultipartFile file, String type) {
        String fileName = type + "_" + System.currentTimeMillis() + ".jpg";
        String md5 = "";
        // 获取文件Md5匹配本地数据库
        try {
            md5 = DigestUtils.md5Hex(file.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (!StringUtils.isEmpty(md5)) {
            // TODO 后续改成redis实现
            String existFileName = baseDao.getFileNameByMd5(md5);
            if (!StringUtils.isEmpty(existFileName)) {
                fileName = existFileName;
            } else {
                baseDao.insertOssImg(OssImg.builder().fileName(fileName).md5(md5).build());
            }
        }
        try {
            AliOssUtils.upload(fileName, file.getBytes());
        } catch (Exception e) {
            throw new BusinessException("上传失败，请重试");
        }
        return fileName;
    }
}
