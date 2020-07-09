package com.shibofu.menu.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author potter.fu
 * @date 2018-12-26 15:45
 */
@Data
public class MenuDetail {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 菜谱id
     */
    private Integer menuId;

    /**
     * 食材名
     */
    @NotEmpty(message = "食材名不能为空")
    private String ingredientName;

    /**
     * 食材单位
     */
    @NotEmpty(message = "食材单位不能为空")
    private String unit;

    /**
     * 数量
     */
    @Min(value = 1, message = "数量不能小于1")
    @NotNull(message = "数量不能为空")
    private Integer num;
}
