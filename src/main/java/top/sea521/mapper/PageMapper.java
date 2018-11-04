package top.sea521.mapper;

import top.sea521.entity.Page;

public interface PageMapper {
    int deleteByPrimaryKey(Integer pageId);

    int insert(Page record);

    int insertSelective(Page record);

    Page selectByPrimaryKey(Integer pageId);

    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKey(Page record);
}