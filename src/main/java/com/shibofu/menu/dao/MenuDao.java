package com.shibofu.menu.dao;

import com.shibofu.menu.model.MenuDetail;
import com.shibofu.menu.model.MenuEntity;
import com.shibofu.menu.model.MenuList;
import com.shibofu.menu.model.Step;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author potter.fu
 * @date 2018-12-26 15:57
 */
@Repository
public interface MenuDao {

    /**
     * 获取菜谱列表
     *
     * @param userId  用户id
     * @param keyWord 搜索关键字
     * @return list of MenuList
     * @author potter.fu
     * @date 2018-12-26 17:32
     */
    List<MenuList> getMenuList(@Param("userId") Integer userId, @Param("keyWord") String keyWord);

    /**
     * 获取菜谱详情
     *
     * @param id 菜谱id
     * @return MenuEntity
     * @author potter.fu
     * @date 2019-01-15 18:08
     */
    MenuEntity getMenu(Integer id);

    /**
     * 更新烹饪时间
     *
     * @param id 菜谱id
     * @author potter.fu
     * @date 2019-01-24 17:24
     */
    void updateRecentTime(Integer id);

    /**
     * 插入主菜谱
     *
     * @param menu MenuEntity
     */
    void insertMenu(MenuEntity menu);

    /**
     * 插入菜谱详情
     *
     * @param menuDetails list of MenuDetail
     */
    void insertMenuDetails(List<MenuDetail> menuDetails);

    /**
     * 插入菜谱步骤
     *
     * @param steps list of Step
     */
    void insertSteps(LinkedList<Step> steps);

    /**
     * 删除菜谱
     *
     * @param id 菜谱id
     * @author potter.fu
     * @date 2019-02-11 13:19
     */
    void deleteMenu(Integer id);
}
