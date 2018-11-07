package top.sea521.service.impl;

import top.sea521.mapper.CategoryMapper;
import top.sea521.mapper.TagMapper;
import top.sea521.mapper.custom.ArticleMapperCustom;
import top.sea521.mapper.custom.CategoryMapperCustom;
import top.sea521.mapper.custom.TagMapperCustom;
import top.sea521.entity.Tag;
import top.sea521.entity.custom.ArticleCustom;
import top.sea521.entity.custom.ArticleListVo;
import top.sea521.entity.custom.CategoryCustom;
import top.sea521.entity.custom.TagCustom;
import top.sea521.service.TagService;
import top.sea521.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Autowired
    private TagMapperCustom tagMapperCustom;

    @Autowired
    private ArticleMapperCustom articleMapperCustom;

    /**
     * 1 获得标签总数,前台显示标签的总数
     */
    @Override
    public Integer countTag(Integer status) throws Exception {
        Integer tagCount = tagMapperCustom.countTag(status);
        return tagCount;
    }

    /**
     * 2 获得标签列表
     */
    @Override
    public List<TagCustom> listTag(Integer status) throws Exception {
        List<TagCustom> tagList = tagMapperCustom.listTag(status);
        for (int i = 0; i < tagList.size(); i++) {
            Integer tagId = tagList.get(i).getTagId();
            /**获得某个标签下的文章的总数*/
            int count = articleMapperCustom.countArticleByTag(status, tagId);
            tagList.get(i).setArticleCount(count);
        }
        return tagList;
    }


    /**
     * 3 获得含有该标签的文章列表
     */
    @Override
    public List<ArticleListVo> getArticleListByPage(Integer status, Integer pageNow, Integer pageSize, Integer tagId) throws Exception {
        List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();
        List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();


        /**获得该标签的具体信息*/
        Tag tag = tagMapper.selectByPrimaryKey(tagId);
        if (tag == null) {
            return null;
        }

        //分页显示
        Page page = null;

        int totalCount = articleMapperCustom.countArticleByTag(status, tagId);
        if (pageNow != null) {
            page = new Page(totalCount, pageNow, pageSize);
            articleCustomList = tagMapperCustom.listArticleWithTagByPage(status, tagId, page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1, pageSize);
            articleCustomList = tagMapperCustom.listArticleWithTagByPage(status, tagId, page.getStartPos(), page.getPageSize());
        }

        for (int i = 0; i < articleCustomList.size(); i++) {
            ArticleListVo articleListVo = new ArticleListVo();
            //1、将文章装入 articleListVo
            ArticleCustom articleCustom = articleCustomList.get(i);
            articleListVo.setArticleCustom(articleCustom);
            //2、将分类信息装到 articleListVoList 中
            List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
            Integer cate = articleCustomList.get(i).getArticleParentCategoryId();
            Integer cate2 = articleCustomList.get(i).getArticleChildCategoryId();
            CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status, cate);
            CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status, cate2);
            if (categoryCustom != null) {
                categoryCustomList.add(categoryCustom);
            }
            if (categoryCustom2 != null) {
                categoryCustomList.add(categoryCustom2);
            }
            articleListVo.setCategoryCustomList(categoryCustomList);

            articleListVoList.add(articleListVo);
        }
        //确保该标签有文章，防止空指针
        if (totalCount != 0) {
            //3、将Page存储在 articleListVoList 第一个元素中
            articleListVoList.get(0).setPage(page);

            //4、将标签信息 装入  articleListVoList 第一个元素中
            List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
            TagCustom tagCustom = new TagCustom();
            BeanUtils.copyProperties(tag, tagCustom);
            tagCustomList.add(tagCustom);
            articleListVoList.get(0).setTagCustomList(tagCustomList);
        }
        return articleListVoList;
    }

    /**
     * 5 格局标签的id获得标签的信息
     */
    @Override
    public TagCustom getTagById(Integer id) throws Exception {
        TagCustom tagCustom = new TagCustom();
        Tag tag = tagMapper.selectByPrimaryKey(id);
        if (tag == null) {
            return null;
        }
        BeanUtils.copyProperties(tag, tagCustom);
        return tagCustom;
    }

    /**
     * 6 插入标签
     */
    @Override
    public void insertTag(Tag tag) throws Exception {
        //todo 不会插入成功，但是没有提示....
        /**标签名是唯一的；*/
        Tag tagByName = tagMapperCustom.getTagByName(tag.getTagName());
        if (tagByName != null) {
            return;
        }
        tagMapper.insertSelective(tag);
    }

    /**
     * 7 更新标签
     */
    @Override
    public void updateTag(Tag tag) throws Exception {
        Tag tagByName = tagMapperCustom.getTagByName(tag.getTagName());
        //todo 不会更新成功，但是没有提示....

        if (tagByName != null) {
            return;
        }
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    /**
     * 8 删除标签
     */
    @Override
    public void deleteTag(Integer id) throws Exception {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Tag getTagByName(String name) throws Exception {
        Tag tag = tagMapperCustom.getTagByName(name);
        return tag;
    }


}
