package com.shibofu.common.utils;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

/**
 * 阿里OSS工具类
 *
 * @author potter.fu
 * @date 2019-01-29 8:46
 */

@Slf4j
@Component
public class AliOssUtils {
    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    private static String bucketName;

    private static OSSClient ossClient;

    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint) {
        AliOssUtils.endpoint = endpoint;
    }

    @Value("${oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        AliOssUtils.accessKeyId = accessKeyId;
    }

    @Value("${oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        AliOssUtils.accessKeySecret = accessKeySecret;
    }

    @Value("${oss.bucketName}")
    public void setBucketName(String bucketName) {
        AliOssUtils.bucketName = bucketName;
    }

    private static OSSClient getInstance() {
        if (ossClient == null) {
            try {
                ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                log.info("AliOss服务初始化");
            } catch (Exception e) {
                log.error("AliOss服务初始化异常:" + e.getMessage(), e);
            }
        }
        return ossClient;
    }

    /**
     * 上传文件
     *
     * @param fileName 文件名
     * @param bytes    文件字节
     * @author potter.fu
     * @date 2019-01-29 09:28
     */
    public static void upload(String fileName, byte[] bytes) {
        OSSClient ossClient = getInstance();
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(bytes));
    }

    /**
     * 获取OSS的url
     *
     * @param fileName 文件名
     * @return url
     * @author potter.fu
     * @date 2019-01-29 12:01
     */
    public static String getOssUrl(String fileName) {
        OSSClient ossClient = getInstance();
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        return url.getProtocol() + "://" + url.getHost() + url.getFile();
    }
}
