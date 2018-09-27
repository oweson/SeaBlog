package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.entity.Link;
import com.liuyanzhao.blog.entity.custom.LinkCustom;
import org.apache.xpath.operations.Bool;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/4.
 */
public interface LinkService {

    /**
     * 获得链接总数
     */
    Integer countLink(Integer status) throws Exception;

    /**
     * 获得链接列表
     */
    List<LinkCustom> listLink(Integer status) throws Exception;

    /**
     * 添加链接
     */
    void insertLink(Link link) throws Exception;

    /**
     * 删除链接
     */
    void deleteLink(Integer id) throws Exception;

    /**
     * 更新链接
     */
    void updateLink(Link link) throws Exception;

    /**
     * 根据id查询链接
     */
    LinkCustom getLinkById(Integer id) throws Exception;


}
