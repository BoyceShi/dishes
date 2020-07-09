package com.shibofu.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体
 *
 * @author potter.fu
 * @date 2018-12-12 8:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 个性签名
     */
    private String personalSignature;

    /**
     * 微信openId
     */
    private String wxOpenId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
