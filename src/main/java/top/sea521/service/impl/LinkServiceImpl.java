package top.sea521.service.impl;

import top.sea521.entity.Link;
import top.sea521.mapper.LinkMapper;
import top.sea521.mapper.custom.LinkMapperCustom;
import top.sea521.entity.custom.LinkCustom;
import top.sea521.service.LinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapperCustom linkMapperCustom;

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 1 统计链接
     */
    @Override
    public Integer countLink(Integer status) throws Exception {
        Integer linkCount = linkMapperCustom.countLink(status);
        return linkCount;
    }

    /**
     * 2 链接的列表
     */
    @Override
    public List<LinkCustom> listLink(Integer status) throws Exception {
        List<LinkCustom> linkList = linkMapperCustom.listLink(status);
        return linkList;
    }

    /**
     * 3 插入链接
     */
    @Override
    public void insertLink(Link link) throws Exception {
        linkMapper.insertSelective(link);
    }

    /**
     * 4 删除链接
     */
    @Override
    public void deleteLink(Integer id) throws Exception {
        linkMapper.deleteByPrimaryKey(id);
    }

    /**
     * 5 更新链接
     */
    @Override
    public void updateLink(Link link) throws Exception {
        linkMapper.updateByPrimaryKeySelective(link);
    }

    /**
     * 6 查寻ById
     */
    @Override
    public LinkCustom getLinkById(Integer id) throws Exception {
        Link link = linkMapper.selectByPrimaryKey(id);
        LinkCustom linkCustom = new LinkCustom();
        BeanUtils.copyProperties(link, linkCustom);
        return linkCustom;
    }

}
