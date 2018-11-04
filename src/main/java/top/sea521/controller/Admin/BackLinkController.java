package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */


import top.sea521.entity.Link;
import top.sea521.entity.custom.LinkCustom;
import top.sea521.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 1 后台链接列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView linkList() throws Exception {
        ModelAndView modelandview = new ModelAndView();
/**查询所有，没有分页bug.....................................*/
        List<LinkCustom> linkCustomList = linkService.listLink(null);
        modelandview.addObject("linkCustomList", linkCustomList);

        modelandview.setViewName("Admin/Link/index");
        return modelandview;

    }

    /**
     * 2 后台添加链接页面显示
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertLinkView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
/**跳转到添加页面，右侧显示，已经添加的连接*/
        List<LinkCustom> linkCustomList = linkService.listLink(null);
        modelAndView.addObject("linkCustomList", linkCustomList);

        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }

    /**
     * 3 后台添加链接页面提交
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertLinkSubmit(Link link) throws Exception {
        /**添加创建时间和更新时间为系统时间*/
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(1);
        linkService.insertLink(link);
        return "redirect:/admin/link/insert";
    }

    /**
     * 4 删除链接
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id) throws Exception {
/**删除连接完毕，跳转到连接的首页面*/
        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

    /**
     * 5 编辑链接页面显示
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
/**编辑之前的回显示，*/
        LinkCustom linkCustom = linkService.getLinkById(id);
        modelAndView.addObject("linkCustom", linkCustom);
/**已经存在的连接右侧显示*/
        List<LinkCustom> linkCustomList = linkService.listLink(null);
        modelAndView.addObject("linkCustomList", linkCustomList);

        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }


    //编辑链接提交
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editLinkSubmit(Link link) throws Exception {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }
}
