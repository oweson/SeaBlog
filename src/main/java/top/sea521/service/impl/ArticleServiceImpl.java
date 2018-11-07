package top.sea521.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import top.sea521.entity.Article;
import top.sea521.entity.Tag;
import top.sea521.entity.User;
import top.sea521.entity.custom.*;
import top.sea521.mapper.ArticleMapper;
import top.sea521.mapper.CategoryMapper;
import top.sea521.mapper.TagMapper;
import top.sea521.mapper.UserMapper;
import top.sea521.mapper.custom.ArticleMapperCustom;
import top.sea521.mapper.custom.CategoryMapperCustom;
import top.sea521.mapper.custom.CommentMapperCustom;
import top.sea521.service.ArticleService;
import top.sea521.util.Functions;
import top.sea521.util.others.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapperCustom articleMapperCustom;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapperCustom commentMapperCustom;

    /**
     * 1 统计文章的总数
     */
    @Override
    public Integer countArticle(Integer status) throws Exception {
        Integer articleCount = articleMapperCustom.countArticle(status);
        return articleCount;
    }

    /**
     * 2 统计文章的评论数量
     */
    @Override
    public Integer countArticleComment(Integer status) throws Exception {
        Integer commentCount = articleMapperCustom.countArticleComment(status);
        return commentCount;
    }

    /**
     * 3 统计文章的查看人数
     */
    @Override
    public Integer countArticleView(Integer status) throws Exception {
        Integer viewCount = articleMapperCustom.countArticleView(status);
        return viewCount;
    }

    /**
     * 4 查寻所有的问章
     */
    @Override
    public List<ArticleListVo> listArticle(Integer status) throws Exception {
        List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();

        //获得文章列表信息和分页信息
        List<ArticleCustom> articleCustomList = articleMapperCustom.listArticle(status);

        //获得分类信息
        for (int i = 0; i < articleCustomList.size(); i++) {
            ArticleListVo articleListVo = new ArticleListVo();

            //1、将文章信息装到 articleListVoList 中
            ArticleCustom articleCustom = articleCustomList.get(i);
            articleListVo.setArticleCustom(articleCustom);

            //2、将分类信息装到 articleListVoList 中
            List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
            Integer parentCategoryId = articleCustomList.get(i).getArticleParentCategoryId();
            Integer childCategoryId = articleCustomList.get(i).getArticleChildCategoryId();
            CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(1, parentCategoryId);
            CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(1, childCategoryId);
            if (categoryCustom != null) {
                categoryCustomList.add(categoryCustom);
            }
            if (categoryCustom2 != null) {
                categoryCustomList.add(categoryCustom2);
            }
            articleListVo.setCategoryCustomList(categoryCustomList);

            //3、获得标签信息
            List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
            String tagIds = articleCustomList.get(i).getArticleTagIds();
            //防止该文章没有分类，空指针
            if (tagIds != null && tagIds != "") {
                String[] tagId = tagIds.split(",");
                for (int j = 0; j < tagId.length; j++) {
                    Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
                    //防止标签不存在，被删除
                    if (tag != null) {
                        TagCustom tagCustom = new TagCustom();
                        BeanUtils.copyProperties(tag, tagCustom);
                        tagCustomList.add(tagCustom);
                    }
                }
            }
            articleListVo.setTagCustomList(tagCustomList);

            //4、获得作者信息
            User user = userMapper.selectByPrimaryKey(articleCustom.getArticleUserId());
            UserCustom userCustom = new UserCustom();
            BeanUtils.copyProperties(user, userCustom);
            articleListVo.setUserCustom(userCustom);


            articleListVoList.add(articleListVo);

        }
        return articleListVoList;
    }

    /**
     * 4 通国id查寻某一个文章
     */
    @Override
    public ArticleCustom getArticleById(Integer status, Integer id) throws Exception {
        return articleMapperCustom.getArticleById(status, id);
    }

    @Override
    public void updateArticle(Integer id, Article article) throws Exception {
        //添加业务校验，通常在service接口对关键
        article.setArticleId(id);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * 5 批量的删除文章
     */
    @Override
    public void deleteArticleBatch(Integer[] ids) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            articleMapper.deleteByPrimaryKey(ids[i]);
        }
    }

    /**
     * 6 单个的删除文章
     */
    @Override
    public void deleteArticle(Integer id) throws Exception {
        articleMapper.deleteByPrimaryKey(id);
    }


    /**
     * 7 分页显示文章列表
     */
    @Override
    public List<ArticleListVo> listArticleByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception {
        List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();

        //获得文章列表信息和分页信息
        List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();
        Page page = null;
        int totalCount = articleMapperCustom.countArticle(status);
        if (pageNow != null) {
            page = new Page(totalCount, pageNow, pageSize);
            articleCustomList = articleMapperCustom.listArticleByPage(status, page.getStartPos(), pageSize);
        } else {
            page = new Page(totalCount, 1, pageSize);
            articleCustomList = articleMapperCustom.listArticleByPage(status, page.getStartPos(), pageSize);
        }

        //获得分类信息
        for (int i = 0; i < articleCustomList.size(); i++) {
            ArticleListVo articleListVo = new ArticleListVo();

            //1、将文章信息装到 articleListVoList 中
            ArticleCustom articleCustom = articleCustomList.get(i);
            articleListVo.setArticleCustom(articleCustom);

            //2、将分类信息装到 articleListVoList 中
            List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
            Integer parentCategoryId = articleCustomList.get(i).getArticleParentCategoryId();
            Integer childCategoryId = articleCustomList.get(i).getArticleChildCategoryId();
            CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status, parentCategoryId);
            CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status, childCategoryId);
            if (categoryCustom != null) {
                categoryCustomList.add(categoryCustom);
            }
            if (categoryCustom2 != null) {
                categoryCustomList.add(categoryCustom2);
            }
            articleListVo.setCategoryCustomList(categoryCustomList);

            //3、获得标签信息
            List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
            String tagIds = articleCustomList.get(i).getArticleTagIds();
            //防止该文章没有分类，空指针
            if (tagIds != null && tagIds != "") {
                String[] tagId = tagIds.split(",");
                for (int j = 0; j < tagId.length; j++) {
                    Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
                    //防止标签不存在，被删除
                    if (tag != null) {
                        TagCustom tagCustom = new TagCustom();
                        BeanUtils.copyProperties(tag, tagCustom);
                        tagCustomList.add(tagCustom);
                    }
                }
            }
            articleListVo.setTagCustomList(tagCustomList);

            //4、获得作者信息
            User user = userMapper.selectByPrimaryKey(articleCustom.getArticleUserId());
            UserCustom userCustom = new UserCustom();
            BeanUtils.copyProperties(user, userCustom);
            articleListVo.setUserCustom(userCustom);


            articleListVoList.add(articleListVo);
        }

        if (articleListVoList.size() > 0) {
            //4、将Page信息存储在第一个元素中
            articleListVoList.get(0).setPage(page);
        }
        return articleListVoList;
    }

    /**
     * 8 文章详情页面显示
     */
    @Override
    public ArticleDetailVo getArticleDetailById(Integer id) throws Exception {
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();

        //1、获得文章信息
        ArticleCustom articleCustom = articleMapperCustom.getArticleById(1, id);
        if (articleCustom == null) {
            return null;
        }
        articleDetailVo.setArticleCustom(articleCustom);


        //2、将分类信息装到 articleListVoList 中
        List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
        Integer cate = articleCustom.getArticleParentCategoryId();
        Integer cate2 = articleCustom.getArticleChildCategoryId();
        CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(1, cate);
        CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(1, cate2);
        if (categoryCustom != null) {
            categoryCustomList.add(categoryCustom);
        }
        if (categoryCustom2 != null) {
            categoryCustomList.add(categoryCustom2);
        }
        articleDetailVo.setCategoryCustomList(categoryCustomList);

        //3、获得文章的标签
        String tag_ids = articleCustom.getArticleTagIds();
        List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
        if (tag_ids != null && tag_ids != "") {
            String[] tags = tag_ids.split(",");
            int tagLength = tags.length;

            for (int i = 0; i < tagLength; i++) {
                Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tags[i]));
                if (tag != null) {
                    TagCustom tagCustom = new TagCustom();
                    BeanUtils.copyProperties(tag, tagCustom);
                    tagCustomList.add(tagCustom);
                }
            }
        }
        articleDetailVo.setTagCustomList(tagCustomList);

        //4、获得文章的作者
        Integer userId = articleCustom.getArticleUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        UserCustom userCustom = new UserCustom();
        BeanUtils.copyProperties(user, userCustom);
        articleDetailVo.setUserCustom(userCustom);

        //5、获取评论信息列表
        List<CommentCustom> commentCustomList = commentMapperCustom.listCommentByArticleId(1, id);
        //给每个评论用户添加头像
        for (int i = 0; i < commentCustomList.size(); i++) {
            String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
            commentCustomList.get(i).setCommentAuthorAvatar(avatar);
        }
        articleDetailVo.setCommentCustomList(commentCustomList);


        return articleDetailVo;
    }


    /**
     * 9 文章查询结果分页
     */
    @Override
    public List<ArticleSearchVo> listSearchResultByPage(Integer status, HttpServletRequest request, Model model, Integer pageNow, Integer pageSize, String query) throws Exception {
        Page page = null;
        List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();
        int totalCount = articleMapperCustom.getSearchResultCount(status, query);


        if (pageNow != null) {
            page = new Page(totalCount, pageNow, pageSize);
            articleCustomList = this.articleMapperCustom.listSearchResultByPage(status, query, page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1, pageSize);
            articleCustomList = this.articleMapperCustom.listSearchResultByPage(status, query, page.getStartPos(), page.getPageSize());
        }

        List<ArticleSearchVo> articleSearchVoList = new ArrayList<ArticleSearchVo>();

        //查询结果条数为0，下面的不执行，防止空指针
        if (totalCount != 0) {
            for (int i = 0; i < articleCustomList.size(); i++) {
                ArticleSearchVo articleSearchVo = new ArticleSearchVo();

                //1、将文章信息装到 articleListVoList 中
                ArticleCustom articleCustom = articleCustomList.get(i);
                articleSearchVo.setArticleCustom(articleCustom);

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
                articleSearchVo.setCategoryCustomList(categoryCustomList);

                //3、获得标签信息
                List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
                String tagIds = articleCustomList.get(i).getArticleTagIds();
                if (tagIds != null && tagIds != "") {
                    String[] tagId = tagIds.split(",");
                    for (int j = 0; j < tagId.length; j++) {
                        Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
                        if (tag != null) {
                            TagCustom tagCustom = new TagCustom();
                            BeanUtils.copyProperties(tag, tagCustom);
                            tagCustomList.add(tagCustom);
                        }
                    }
                }
                articleSearchVo.setTagCustomList(tagCustomList);

                //4、获得作者信息
                User user = userMapper.selectByPrimaryKey(articleCustom.getArticleUserId());
                UserCustom userCustom = new UserCustom();
                BeanUtils.copyProperties(user, userCustom);
                articleSearchVo.setUserCustom(userCustom);


                articleSearchVoList.add(articleSearchVo);
            }
        } else {
            //不执行的话，也要创建一个元素，存储分页信息和查询关键字
            ArticleSearchVo articleSearchVo = new ArticleSearchVo();
            articleSearchVoList.add(articleSearchVo);
        }
        //5、page信息存储在第一个元素中
        articleSearchVoList.get(0).setPage(page);

        //6、将查询的关键词存储到第一个元素
        articleSearchVoList.get(0).setQuery(query);


        return articleSearchVoList;

    }

    /**
     * 10 相似文章获取
     */
    @Override
    public List<ArticleCustom> listArticleWithSameCategory(Integer status, Integer parentCategoryId, Integer childCategoryId, Integer limit) throws Exception {
        List<ArticleCustom> similarArticleList = articleMapperCustom.listArticleWithSameCategory(status, parentCategoryId, childCategoryId, limit);
        return similarArticleList;
    }


    /**
     * 11 访问量从多到少的文章获取
     */
    @Override
    public List<ArticleCustom> listArticleByViewCount(Integer status, Integer limit) throws Exception {
        List<ArticleCustom> mostViewArticleList = articleMapperCustom.listArticleByViewCount(status, limit);
        return mostViewArticleList;
    }

    /**
     * 12获取下一篇文章
     */
    @Override
    public ArticleCustom getAfterArticle(Integer status, Integer id) throws Exception {
        ArticleCustom articleCustom = articleMapperCustom.getAfterArticle(status, id);
        return articleCustom;
    }

    /**
     * 13获取上一篇文章
     */
    @Override
    public ArticleCustom getPreArticle(Integer status, Integer id) throws Exception {
        ArticleCustom articleCustom = articleMapperCustom.getPreArticle(status, id);
        return articleCustom;
    }

    /**
     * 14获得随机文章
     */
    @Override
    public List<ArticleCustom> listRandomArticle(Integer status, Integer limit) throws Exception {
        List<ArticleCustom> articleCustomsList = articleMapperCustom.listRandomArticle(status, limit);
        return articleCustomsList;
    }

    /**
     * 15 获得热评文章列表
     */
    @Override
    public List<ArticleCustom> listArticleByCommentCount(Integer status, Integer limit) throws Exception {
        List<ArticleCustom> articleCustomsList = articleMapperCustom.listArticleByCommentCount(status, limit);
        return articleCustomsList;
    }


    /**
     * 16 添加文章
     */
    @Override
    public void insertArticle(Article article) throws Exception {
        articleMapper.insertSelective(article);
    }

    /**
     * 17 统计某个分类的文章数*、
     */
    @Override
    public Integer countArticleWithCategory(Integer status, Integer id) throws Exception {
        int count = articleMapperCustom.countArticleByCategory(status, id);
        return count;
    }

    /**
     * 18统计某个标签的文章数
     */
    @Override
    public Integer countArticleWithTag(Integer status, Integer id) throws Exception {
        int count = articleMapperCustom.countArticleByTag(status, id);
        return count;
    }

    /**
     * 19 更寻文章评论数量
     */
    @Override
    public void updateCommentCount(Integer articleId) throws Exception {
        articleMapperCustom.updateCommentCount(articleId);
    }

    /**
     * 20 获得最后一次更寻的文章
     */
    @Override
    public ArticleCustom getLastUpdateArticle() throws Exception {
        ArticleCustom articleCustom = articleMapperCustom.getLastUpdateArticle();
        return articleCustom;
    }


}
