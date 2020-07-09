package com.shibofu.menu.service.impl;

import com.github.pagehelper.PageHelper;
import com.shibofu.common.exception.BusinessException;
import com.shibofu.common.token.TokenCache;
import com.shibofu.menu.dao.MenuDao;
import com.shibofu.menu.model.MenuDetail;
import com.shibofu.menu.model.MenuEntity;
import com.shibofu.menu.model.MenuList;
import com.shibofu.menu.model.Step;
import com.shibofu.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @author potter.fu
 * @date 2018-12-26 15:57
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class MenuServiceImpl implements MenuService {

    @Value("${pageSize}")
    private Integer pageSize;

    @Autowired
    private MenuDao menuDao;

    /**
     * 获取列表
     *
     * @param pageNo  页
     * @param keyWord 搜索关键字
     * @return list of MenuList
     * @author potter.fu
     * @date 2018-12-26 17:25
     */
    @Override
    public List<MenuList> getMenuList(Integer pageNo, String keyWord) {
        PageHelper.startPage(pageNo, pageSize);
        return menuDao.getMenuList(TokenCache.getInstance().getToken().getId(), keyWord);
    }

    /**
     * 获取菜谱相亲
     *
     * @param id 菜谱id
     * @return MenuEntity
     * @author potter.fu
     * @date 2019-01-15 18:05
     */
    @Override
    public MenuEntity getMenu(Integer id) {
        MenuEntity menu = menuDao.getMenu(id);
        menu.convertImg();
        return menu;
    }

    /**
     * 烹饪
     *
     * @param id 菜谱id
     * @author potter.fu
     * @date 2019-01-24 17:15
     */
    @Override
    public void cook(Integer id) {
        menuDao.updateRecentTime(id);
    }

    /**
     * 插入菜谱
     *
     * @param menu MenuEntity
     */
    @Override
    public void insertMenu(MenuEntity menu) {
        // 校验详情与步骤
        List<MenuDetail> menuDetails = menu.getMenuDetails();
        if (menuDetails == null || menuDetails.size() == 0) {
            throw new BusinessException("菜谱详情不能为空，请检查后重试");
        }
        LinkedList<Step> steps = menu.getSteps();
        if (steps == null || steps.size() == 0) {
            throw new BusinessException("步骤不能为空，请检查后重试");
        }

        // 插入主菜谱
        menuDao.insertMenu(menu);

        // 插入菜谱详情
        for (MenuDetail menuDetail : menuDetails) {
            menuDetail.setMenuId(menu.getId());
        }
        menuDao.insertMenuDetails(menuDetails);

        // 插入菜谱步骤
        int sort = 1;
        for (Step step : steps) {
            step.setSort(sort++);
            step.setMenuId(menu.getId());
        }
        menuDao.insertSteps(steps);
    }

    /**
     * 删除菜谱
     *
     * @param id 菜谱id
     * @author potter.fu
     * @date 2019-02-11 13:18
     */
    @Override
    public void deleteMenu(Integer id) {
        menuDao.deleteMenu(id);
    }
}
