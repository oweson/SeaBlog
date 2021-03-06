package top.sea521.service;

import top.sea521.entity.Page;
import top.sea521.entity.custom.PageCustom;

import java.util.List;


public interface PageService {
    /**
     * 1获得页面列表
     */
    public List<PageCustom> listPage(Integer status) throws Exception;

    /**
     * 2根据页面key获得页面
     */
    public PageCustom getPageByKey(Integer status, String key) throws Exception;

    /**
     * 3根据id获取页面
     */
    public PageCustom getPageById(Integer id) throws Exception;

    /**
     * 4添加页面
     */
    public void insertPage(Page page) throws Exception;

    /**
     * 5删除页面
     */
    public void deletePage(Integer id) throws Exception;

    /**
     * 6编辑页面
     */
    public void updatePage(Page page) throws Exception;
}
