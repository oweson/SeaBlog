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
import top.sea521.entity.custom.TagCustom;
import top.sea521.service.TagService;

import java.util.List;


@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @ModelAttribute
    public void init(Model model) throws Exception {

    }

    /**
     * 1 根据标签查询文章
     */
    @RequestMapping("tag/{tagId}")
    @ResponseBody
    public ModelAndView ArticleListByTagView(@PathVariable("tagId") Integer tagId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示条数、
        int pageSize = 10;
        List<ArticleListVo> articleListVoList = tagService.getArticleListByPage(1, null, pageSize, tagId);

        modelAndView.addObject("articleListVoList", articleListVoList);

        /**标签信息*/
        TagCustom tagCustom = tagService.getTagById(tagId);
        modelAndView.addObject("tagCustom", tagCustom);

        modelAndView.setViewName("Home/Page/articleListByTag");
        return modelAndView;
    }

    /**
     * 2 根据标签查询文章分页
     */
    @RequestMapping("tag/{tagId}/p/{pageNow}")
    @ResponseBody
    public ModelAndView ArticleListByTagAndPageView(@PathVariable("pageNow") Integer pageNow, @PathVariable("tagId") Integer tagId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        /**设置每页显示条数*/
        int pageSize = 10;
        List<ArticleListVo> articleListVoList = tagService.getArticleListByPage(1, pageNow, pageSize, tagId);
        modelAndView.addObject("articleListVoList", articleListVoList);
        modelAndView.setViewName("Home/Page/articleListByTag");
        /**标签信息*/
        TagCustom tagCustom = tagService.getTagById(tagId);
        modelAndView.addObject("tagCustom", tagCustom);

        return modelAndView;
    }
}
