package com.shibofu.menu.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author potter.fu
 * @date 2018-12-26 15:49
 */
@Data
public class Step {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 菜谱id
     */
    private Integer menuId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 步骤图片
     */
    private String img;

    /**
     * 步骤描述
     */
    @NotEmpty(message="步骤描述不能为空")
    private String description;
}
