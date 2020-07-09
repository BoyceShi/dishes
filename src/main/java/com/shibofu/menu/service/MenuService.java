package com.shibofu.menu.service;

import com.shibofu.menu.model.MenuEntity;
import com.shibofu.menu.model.MenuList;

import java.util.List;

/**
 * @author potter.fu
 * @date 2018-12-26 15:56
 */
public interface MenuService {

    /**
     * 获取列表
     *
     * @param pageNo  页
     * @param keyWord 搜索关键字
     * @return list of MenuList
     * @author potter.fu
     * @date 2018-12-26 17:25
     */
    List<MenuList> getMenuList(Integer pageNo, String keyWord);

    /**
     * 获取菜谱相亲
     *
     * @param id 菜谱id
     * @return MenuEntity
     * @author potter.fu
     * @date 2019-01-15 18:05
     */
    MenuEntity getMenu(Integer id);

    /**
     * 烹饪
     *
     * @param id 菜谱id
     * @author potter.fu
     * @date 2019-01-24 17:15
     */
    void cook(Integer id);

    /**
     * 插入菜谱
     *
     * @param menu MenuEntity
     */
    void insertMenu(MenuEntity menu);

    /**
     * 删除菜谱
     *
     * @param id 菜谱id
     * @author potter.fu
     * @date 2019-02-11 13:18
     */
    void deleteMenu(Integer id);
}
