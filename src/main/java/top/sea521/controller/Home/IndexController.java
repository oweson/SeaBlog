package top.sea521.controller.Home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.LinkCustom;
import top.sea521.entity.custom.NoticeCustom;
import top.sea521.service.ArticleService;
import top.sea521.service.LinkService;
import top.sea521.service.NoticeService;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private NoticeService noticeService;

    @ModelAttribute
    public void init(Model model) throws Exception {
        /** 1 友情链接*/
        List<LinkCustom> linkCustomList = linkService.listLink(1);
        model.addAttribute("linkCustomList", linkCustomList);

        /** 2 公告*/
        List<NoticeCustom> noticeCustomList = noticeService.listNotice(1);
        model.addAttribute("noticeCustomList", noticeCustomList);
    }

    /**
     * 首页显示
     */
    @RequestMapping("/")
    public ModelAndView IndexView() throws Exception {
        ModelAndView modelAndView = new ModelAndView("/Home/index");
        //文章列表
        int pageSize = 10;
        List<ArticleListVo> articleListVoList = articleService.listArticleByPage(1, null, pageSize);
        modelAndView.addObject("articleListVoList", articleListVoList);
        return modelAndView;
    }

    /** 2 文章分页显示*/
    @RequestMapping("p/{pageNow}")
    //适合RESTful
    public @ResponseBody
    ModelAndView ArticleListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 10;
        List<ArticleListVo> articleListVoList = articleService.listArticleByPage(1, pageNow, pageSize);
        modelAndView.addObject("articleListVoList", articleListVoList);
        modelAndView.setViewName("Home/index");
        return modelAndView;//不会被解析为跳转路径，而是直接写入HTTP response body中
    }

}




