package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */

import top.sea521.entity.User;
import top.sea521.service.ArticleService;
import top.sea521.service.CommentService;
import top.sea521.service.LinkService;
import top.sea521.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.CommentListVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.sea521.util.Functions.getIpAddr;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LinkService linkService;

    @ModelAttribute
    public void init(Model model) throws Exception {

    }

    /**
     * 1 管理员登录后跳转到后台首页
     */
    @RequestMapping("/admin")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        /**文章列表*/
        List<ArticleListVo> articleCustomList = articleService.listArticle(null);
        modelAndView.addObject("articleCustomList", articleCustomList);
        /**评论列表、*/
        List<CommentListVo> commentListVoList = commentService.listCommentVo(null);
        modelAndView.addObject("commentListVoList", commentListVoList);
        /**评论数*/
        Integer allCommentCount = commentService.countComment(null);
        Integer approvedCommentCount = commentService.countComment(1);
        Integer hiddenCommentCount = commentService.countComment(0);
        modelAndView.addObject("allCommentCount", allCommentCount);
        modelAndView.addObject("approvedCommentCount", approvedCommentCount);
        modelAndView.addObject("hiddenCommentCount", hiddenCommentCount);

        modelAndView.setViewName("/Admin/index");
        return modelAndView;
    }

    /**
     * 2 登录页面显示
     */
    @RequestMapping("/login")
    public ModelAndView loginView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/Admin/login");
        return modelAndView;
    }

    /**
     * 3 登录验证
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        /**登录方案：根据用户名查找用户实体，取出密码和传入的密码比较
         *
         * */

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        /**是否记住密码，传入标识符号*/
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效！");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误！");
        } else {
            //登录成功
            map.put("code", 1);
            map.put("msg", "");
            /**添加session,把用户信息放入*/
            request.getSession().setAttribute("user", user);
            //添加cookie
            if (rememberme != null) {
                /**创建两个Cookie对象??????????????????????/*/
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            /**最近的登录时间和登录的ip*/
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(getIpAddr(request));
            userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 3退出登录,销毁session
     */
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }


}
