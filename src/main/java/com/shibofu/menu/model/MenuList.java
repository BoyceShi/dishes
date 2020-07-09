package com.shibofu.menu.model;

import com.shibofu.common.utils.AliOssUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author potter.fu
 * @date 2018-12-26 15:52
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MenuList extends Menu {
    /**
     * 菜谱详情
     */
    private List<MenuDetail> menuDetails;

    /**
     * 菜谱详情字符串
     */
    private String detailString;

    public String getDetailString() {
        if (menuDetails != null && menuDetails.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (MenuDetail menuDetail : menuDetails) {
                builder.append(menuDetail.getIngredientName()).append("、");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
        return detailString;
    }

    /**
     * 菜谱图片
     */
    @Override
    public String getImg() {
        if (!StringUtils.isEmpty(super.getImg())) {
            return AliOssUtils.getOssUrl(super.getImg());
        }
        return super.getImg();
    }
}
