package top.sea521.service;

import top.sea521.entity.Menu;
import top.sea521.entity.custom.MenuCustom;

import java.util.List;

public interface MenuService {
    /**
     * 1 获得菜单列表
     */
    List<MenuCustom> listMenu(Integer status) throws Exception;

    /**
     * 2 添加菜单项目
     */
    void insertMenu(Menu menu) throws Exception;

    /**
     * 3 删除菜单项目
     */
    void deleteMenu(Integer id) throws Exception;

    /**
     * 4 更新菜单项目
     */
    void updateMenu(Menu menu) throws Exception;

    /**
     * 5 根据id获得菜单项目信息
     */
    MenuCustom getMenuById(Integer id) throws Exception;
}
