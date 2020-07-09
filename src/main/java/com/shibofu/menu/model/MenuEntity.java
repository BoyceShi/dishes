package com.shibofu.menu.model;

import com.shibofu.common.utils.AliOssUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * @author potter.fu
 * @date 2019-01-15 18:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuEntity extends Menu {
    /**
     * 菜谱食材详情
     */
    @Valid
    private List<MenuDetail> menuDetails;

    /**
     * 菜谱烹饪步骤
     */
    @Valid
    private LinkedList<Step> steps;

    /**
     * 转换图片
     */
    public void convertImg() {
        if (!StringUtils.isEmpty(super.getImg())) {
            super.setImg(AliOssUtils.getOssUrl(super.getImg()));
        }
        for (Step step : steps) {
            if (!StringUtils.isEmpty(step.getImg())) {
                step.setImg(AliOssUtils.getOssUrl(step.getImg()));
            }
        }
    }

}
