package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */


import top.sea521.entity.User;
import top.sea521.entity.custom.UserCustom;
import top.sea521.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;

    /**
     * 1 后台用户列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView userList() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<UserCustom> userCustomList = userService.listUser();
        modelandview.addObject("userCustomList", userCustomList);

        modelandview.setViewName("Admin/User/index");
        return modelandview;

    }
    //todo.............................分页

    /**
     * 2 后台添加用户页面显示
     * 点击添加用户跳转的页面
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertUserView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/User/insert");
        return modelAndView;
    }

    /**
     * 3 添加用户的时候校验用户名
     */
    //todo
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        /**用户名已存在,但不是当前用户(编辑用户的时候，不提示)*/
        if (user != null) {
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 4 校验邮箱的
     */
    @RequestMapping(value = "/checkUserEmail", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if (user != null) {
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    /**
     * 5 后台添加用户页面提交
     * 判断name和邮箱不存在可以注册，否则定向到用户页面
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertUserSubmit(User user) throws Exception {
        User user2 = userService.getUserByName(user.getUserName());
        User user3 = userService.getUserByEmail(user.getUserEmail());
        if (user2 == null && user3 == null) {
            /**系统时间为注册时间*/
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insertUser(user);
        }
        return "redirect:/admin/user";
    }

    /**
     * 6 删除用户,restful重定向到用户列表页面
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) throws Exception {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    /**
     * 7 编辑用户页面显示,编辑之前数据 要回显示数据
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Admin/User/edit");

        UserCustom userCustom = userService.getUserById(id);
        modelAndView.addObject("userCustom", userCustom);
        return modelAndView;
    }


    /**
     * 8 编辑用户提交
     */
    //todo 如何校验名字唯一的？
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editUserSubmit(User user) throws Exception {
        userService.updateUser(user);
        //int a=1/0;todo.................................
        return "redirect:/admin/user";
    }

    /**
     * 9 基本信息页面显示,查看个人的详细信息显示
     */
    @RequestMapping(value = "/profile/{id}")
    public ModelAndView userProfileView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Admin/User/profile");
        /**用户的扩展类*/
        UserCustom userCustom = userService.getUserById(id);
        modelAndView.addObject("userCustom", userCustom);
        return modelAndView;
    }
}
