package top.sea521.controller.Admin;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13
 */


import top.sea521.entity.Notice;
import top.sea521.entity.custom.NoticeCustom;
import top.sea521.enums.NoticeStatusEnum;
import top.sea521.enums.OrderStatesEnum;
import top.sea521.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 1 后台公告列表显示
     */
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelandview = new ModelAndView("Admin/Notice/index");
        List<NoticeCustom> noticeCustomList = noticeService.listNotice(null);
        modelandview.addObject("noticeCustomList", noticeCustomList);
        return modelandview;

    }

    /**
     * 2 添加公告显示
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertNoticeView() throws Exception {
        ModelAndView modelAndView = new ModelAndView("Admin/Notice/insert");
        return modelAndView;
    }

    /**
     * 3 添加公告提交
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) throws Exception {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(NoticeStatusEnum.FIRSE.getCode());
        notice.setNoticeOrder(OrderStatesEnum.FIRSE.getCode());
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice";
    }

    /**
     * 4 删除公告
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) throws Exception {
        noticeService.deleteNotice(id);

        return "redirect:/admin/notice";
    }

    /**
     * 5编辑公告页面显示
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editNoticeView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Admin/Notice/edit");
        NoticeCustom noticeCustom = noticeService.getNoticeById(id);
        modelAndView.addObject("noticeCustom", noticeCustom);
        return modelAndView;
    }


    /**
     * 6 编辑公告提交,有问题？？？？？？？提交保存没有反应
     */
    //todo
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) throws Exception {
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }


}
