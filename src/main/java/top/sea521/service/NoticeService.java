package top.sea521.service;

import top.sea521.entity.Notice;
import top.sea521.entity.custom.NoticeCustom;

import java.util.List;

public interface NoticeService {


    /**
     * 1 获得公告列表
     */
    List<NoticeCustom> listNotice(Integer status) throws Exception;

    /**
     * 2 添加公告
     */
    void insertNotice(Notice notice) throws Exception;

    /**
     * 3 删除公告
     */
    void deleteNotice(Integer id) throws Exception;

    /**
     * 4 更新公告
     */
    void updateNotice(Notice notice) throws Exception;

    /**
     * 5 根据id查询公告
     */
    NoticeCustom getNoticeById(Integer id) throws Exception;
    //todo 批量的删除公告

}
