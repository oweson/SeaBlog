package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Notice;
import com.liuyanzhao.blog.entity.custom.NoticeCustom;

import java.util.List;

public interface NoticeService {


    /**
     * 获得公告列表
     */
    List<NoticeCustom> listNotice(Integer status) throws Exception;

    /**
     * 添加公告
     */
    void insertNotice(Notice notice) throws Exception;

    /**
     * 删除公告
     */
    void deleteNotice(Integer id) throws Exception;

    /**
     * 更新公告
     */
    void updateNotice(Notice notice) throws Exception;

    /**
     * 根据id查询公告
     */
    NoticeCustom getNoticeById(Integer id) throws Exception;

}
