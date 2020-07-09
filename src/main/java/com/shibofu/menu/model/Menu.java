package com.shibofu.menu.model;

import com.shibofu.common.utils.Constant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author potter.fu
 * @date 2018-12-26 15:39
 */
@Data
public class Menu {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 菜谱名
     */
    @NotEmpty(message="菜谱名不能为空")
    private String name;

    /**
     * 类型 private 私有 public 公有
     */
    private String type;

    /**
     * 菜谱图片
     */
    private String img;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最近烹饪时间
     */
    private Date recentTime;

    public String getRecentTime() {
        if(this.recentTime != null){
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.Date.YYYY_MM_DD);
            return sdf.format(this.recentTime);
        }
        return null;
    }
}
