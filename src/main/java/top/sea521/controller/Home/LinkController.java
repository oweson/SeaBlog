package top.sea521.controller.Home;

import top.sea521.entity.Link;
import top.sea521.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */
@Controller
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 1 申请链接
     */
    @RequestMapping("/applyLink")
    public ModelAndView applyLinkView() throws Exception {
        return new ModelAndView("Home/Page/applyLink");
    }

    /**
     * 2 提交链接
     */
    @RequestMapping(value = "/applyLinkSubmit", method = {RequestMethod.POST})
    @ResponseBody
    public void applyLinkSubmit(Link link) throws Exception {
        link.setLinkStatus(0);
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkService.insertLink(link);
    }
}
