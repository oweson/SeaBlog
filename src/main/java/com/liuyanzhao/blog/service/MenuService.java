package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Menu;
import com.liuyanzhao.blog.entity.custom.MenuCustom;

import java.util.List;

public interface MenuService {
    /**
     * 获得菜单列表
     */
    List<MenuCustom> listMenu(Integer status) throws Exception;

    /**
     * 添加菜单项目
     */
    void insertMenu(Menu menu) throws Exception;

    /**
     * 删除菜单项目
     */
    void deleteMenu(Integer id) throws Exception;

    /**
     * 更新菜单项目
     */
    void updateMenu(Menu menu) throws Exception;

    /**
     * 根据id获得菜单项目信息
     */
    MenuCustom getMenuById(Integer id) throws Exception;
}
