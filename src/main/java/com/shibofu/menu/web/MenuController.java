package com.shibofu.menu.web;

import com.shibofu.common.response.ResponseBody;
import com.shibofu.common.token.TokenCache;
import com.shibofu.menu.model.MenuEntity;
import com.shibofu.menu.model.MenuList;
import com.shibofu.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author potter.fu
 * @date 2018-12-26 15:56
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取菜谱列表
     *
     * @param pageNo 页数
     * @return java.lang.String
     * @author potter.fu
     * @date 2018-12-26 17:30
     */
    @GetMapping
    public String getMenuList(@RequestParam Integer pageNo, @RequestParam(required = false) String keyWord) {
        List<MenuList> menuList = menuService.getMenuList(pageNo, keyWord);
        ResponseBody<List<MenuList>> responseBody = new ResponseBody<>();
        responseBody.init(menuList);
        return responseBody.toResponse();
    }

    /**
     * 获取菜谱详情
     *
     * @param id 菜谱id
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-15 18:04
     */
    @GetMapping(value = "/{id}")
    public String getMenu(@PathVariable Integer id) {
        MenuEntity menu = menuService.getMenu(id);
        ResponseBody<MenuEntity> responseBody = new ResponseBody<>();
        responseBody.init(menu);
        return responseBody.toResponse();
    }

    /**
     * 烹饪
     *
     * @param id 菜谱id
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-24 17:15
     */
    @PostMapping(value = "/cook/{id}")
    public String cook(@PathVariable Integer id) {
        menuService.cook(id);
        ResponseBody<Object> responseBody = new ResponseBody<>();
        responseBody.setMsg("操作成功");
        return responseBody.toResponse();
    }

    /**
     * 新增菜谱
     *
     * @param menu MenuEntity
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-29 16:44
     */
    @PostMapping
    public String insertMenu(@RequestBody @Valid MenuEntity menu) {
        menu.setUserId(TokenCache.getInstance().getToken().getId());
        menu.setType("PRIVATE");
        menuService.insertMenu(menu);
        ResponseBody<Object> responseBody = new ResponseBody<>();
        responseBody.setMsg("操作成功");
        return responseBody.toResponse();
    }

    /**
     * 删除菜谱
     *
     * @param id 菜谱id
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-02-11 13:13
     */
    @DeleteMapping(value = "/{id}")
    public String deleteMenu(@PathVariable Integer id) {
        menuService.deleteMenu(id);
        ResponseBody<Object> responseBody = new ResponseBody<>();
        responseBody.setMsg("删除成功");
        return responseBody.toResponse();
    }
}
