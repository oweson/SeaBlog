package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */


import top.sea521.entity.Tag;
import top.sea521.entity.custom.TagCustom;
import top.sea521.service.ArticleService;
import top.sea521.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin/tag")
public class BackTagController {
    @Autowired
    private ArticleService articleService;


    @Autowired
    private TagService tagService;

    /**
     * 1 后台标签列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelandview = new ModelAndView();

        List<TagCustom> tagCustomList = tagService.listTag(null);
        modelandview.addObject("tagCustomList", tagCustomList);

        modelandview.setViewName("Admin/Tag/index");
        return modelandview;

    }


    /**
     * 2 后台添加分类页面显示
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag) throws Exception {
        if (tag.getTagName().trim().toCharArray().length>0) {
            tagService.insertTag(tag);
        }

        return "redirect:/admin/tag";
    }

    /**
     * 3 删除标签
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id) throws Exception {

        /**禁止删除有文章的分类,如果分类下有文章就不允许删除，直接跳转到tag的首页*/
        int count = articleService.countArticleWithTag(null, id);
        if (count == 0) {
            tagService.deleteTag(id);
        }

        return "redirect:/admin/tag";
    }

    /**
     * 4 编辑标签页面显示
     * 需要编辑页面的回显示
     * 右侧所有页面的显示
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        TagCustom tagCustom = tagService.getTagById(id);
        modelAndView.addObject("tagCustom", tagCustom);

        List<TagCustom> tagCustomList = tagService.listTag(null);
        modelAndView.addObject("tagCustomList", tagCustomList);

        modelAndView.setViewName("Admin/Tag/edit");
        return modelAndView;
    }


    /**
     * 5 编辑标签提交
     * 前台做了字段的判断，没有非空的判断
     * 可以插入null,这不科学。。。。。。。。。。。。。。。。
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editTagSubmit(Tag tag) throws Exception {

        // boolean notEmpty = StringUtils.isNotEmpty(tag.getTagName().trim());
        if (tag.getTagName().trim().toCharArray().length > 0) {
            tagService.updateTag(tag);
        }
        return "redirect:/admin/tag";
    }
}
