package top.sea521.service;

import top.sea521.entity.Link;
import top.sea521.entity.custom.LinkCustom;

import java.util.List;


public interface LinkService {

    /**
     * 1 获得链接总数
     */
    Integer countLink(Integer status) throws Exception;

    /**
     * 2 获得链接列表
     */
    List<LinkCustom> listLink(Integer status) throws Exception;

    /**
     * 3 添加链接
     */
    void insertLink(Link link) throws Exception;

    /**
     * 4 删除链接
     */
    void deleteLink(Integer id) throws Exception;

    /**
     * 5 更新链接
     */
    void updateLink(Link link) throws Exception;

    /**
     * 6 根据id查询链接
     */
    LinkCustom getLinkById(Integer id) throws Exception;


}
