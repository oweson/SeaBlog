package top.sea521.service.impl;

import top.sea521.entity.Page;
import top.sea521.mapper.PageMapper;
import top.sea521.mapper.custom.PageMapperCustom;
import top.sea521.entity.custom.PageCustom;
import top.sea521.service.PageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PageServiceImpl implements PageService {
    @Autowired
    private PageMapperCustom pageMapperCustom;

    @Autowired
    private PageMapper pageMapper;

    /**
     * 1 根据关键字和状态查找
     */
    @Override
    public PageCustom getPageByKey(Integer status, String key) throws Exception {
        PageCustom pageCustom = pageMapperCustom.getPageByKey(status, key);
        return pageCustom;
    }

    /**
     * 2 根据id查找
     */
    @Override
    public PageCustom getPageById(Integer id) throws Exception {
        Page page = pageMapper.selectByPrimaryKey(id);
        PageCustom pageCustom = new PageCustom();
        BeanUtils.copyProperties(page, pageCustom);
        return pageCustom;
    }

    /**
     * 3 根据状态查找集合
     */
    @Override
    public List<PageCustom> listPage(Integer status) throws Exception {
        List<PageCustom> pageCustomList = pageMapperCustom.listPage(status);
        return pageCustomList;
    }

    /**
     * 4 插入
     */
    @Override
    public void insertPage(Page page) throws Exception {
        /**ptitle应唯*/
        //todo
        pageMapper.insert(page);
        
    }

    /**
     * 5 删除
     */
    @Override
    public void deletePage(Integer id) throws Exception {
        pageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 6 更新
     */
    @Override
    public void updatePage(Page page) throws Exception {
        pageMapper.updateByPrimaryKeySelective(page);
    }
}
