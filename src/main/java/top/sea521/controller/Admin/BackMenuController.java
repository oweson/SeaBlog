package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */

import top.sea521.entity.Menu;
import top.sea521.entity.custom.MenuCustom;
import top.sea521.enums.MenuStatusEnum;
import top.sea521.enums.OrderStatesEnum;
import top.sea521.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 1 后台菜单列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView menuList() throws Exception {
        ModelAndView modelandview = new ModelAndView("Admin/Menu/index");
        List<MenuCustom> menuCustomList = menuService.listMenu(null);
        modelandview.addObject("menuCustomList", menuCustomList);
        return modelandview;
    }

    /**
     * 2 添加菜单内容提交
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu) throws Exception {
        /**设置状态和排序*/
        menu.setMenuStatus(MenuStatusEnum.FIRSE.getCode());
        menu.setMenuOrder(OrderStatesEnum.FIRSE.getCode());
        menuService.insertMenu(menu);
        return "redirect:/admin/menu";
    }

    /**
     * 3 删除菜单内容
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id) throws Exception {

        menuService.deleteMenu(id);
        return "redirect:/admin/menu";
    }

    /**
     * 4 编辑菜单内容显示
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Admin/Menu/edit");
        /**原本的页面的回显示*/
        MenuCustom menuCustom = menuService.getMenuById(id);
        modelAndView.addObject("menuCustom", menuCustom);
        /**右侧所有的页面的显示*/
        List<MenuCustom> menuCustomList = menuService.listMenu(null);
        modelAndView.addObject("menuCustomList", menuCustomList);
        return modelAndView;
    }


    /**
     * 5 编辑菜单内容提交
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu) throws Exception {
        menuService.updateMenu(menu);
        return "redirect:/admin/menu";
    }

    /**
     * 6 显示菜单内容;
     * 把不可用也就是状态为0的设置为1，入库
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.POST)
    public void showMenu(@PathVariable("id") Integer id) throws Exception {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setMenuStatus(MenuStatusEnum.FIRSE.getCode());
        menuService.updateMenu(menu);
    }

    /**
     * 7 隐藏菜单内容
     */
    @RequestMapping(value = "/hide/{id}", method = RequestMethod.POST)
    public void hideMenu(@PathVariable("id") Integer id) throws Exception {
        Menu menu = new Menu();
        menu.setMenuId(id);
        /**有选择的更新，把menu的状态进行修改*/
        menu.setMenuStatus(MenuStatusEnum.SECOND.getCode());
        menuService.updateMenu(menu);
    }

}
