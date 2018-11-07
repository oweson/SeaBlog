package top.sea521.service.impl;

import top.sea521.entity.Menu;
import top.sea521.entity.custom.MenuCustom;
import top.sea521.mapper.MenuMapper;
import top.sea521.mapper.custom.MenuMapperCustom;
import top.sea521.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapperCustom menuMapperCustom;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 1 菜单的列表
     */
    @Override
    public List<MenuCustom> listMenu(Integer status) throws Exception {
        List<MenuCustom> menuCustomList = menuMapperCustom.listMenu(status);
        return menuCustomList;
    }

    /**
     * 2 插入菜单
     */
    @Override
    public void insertMenu(Menu menu) throws Exception {
        /**菜单名字唯一的索引;同名会：
         * MySQLIntegrityConstraintViolationException:
         * Duplicate entry '听雨' for key 'menu_name'*/
        //todo 先查在插入！！！
        menuMapper.insertSelective(menu);
    }

    /**
     * 3 删除菜单
     */
    @Override
    public void deleteMenu(Integer id) throws Exception {
        menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 5 更新菜单
     */
    @Override
    public void updateMenu(Menu menu) throws Exception {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 6 查寻ById
     */
    @Override
    public MenuCustom getMenuById(Integer id) throws Exception {
        MenuCustom menuCustom = new MenuCustom();
        Menu menu = menuMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(menu, menuCustom);
        return menuCustom;
    }
}
