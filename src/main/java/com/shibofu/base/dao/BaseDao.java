package com.shibofu.base.dao;

import com.shibofu.base.model.OssImg;
import org.springframework.stereotype.Repository;

/**
 * @author potter.fu
 * @date 2019-01-29 16:14
 */
@Repository
public interface BaseDao {
    /**
     * 通过文件md5获取数据库中存在的文件名
     *
     * @param md5 文件的md5
     * @return 文件名
     * @author potter.fu
     * @date 2019-01-29 16:23
     */
    String getFileNameByMd5(String md5);

    /**
     * 插入OssImg
     *
     * @param ossImg OssImg
     * @author potter.fu
     * @date 2019-01-29 16:33
     */
    void insertOssImg(OssImg ossImg);
}
