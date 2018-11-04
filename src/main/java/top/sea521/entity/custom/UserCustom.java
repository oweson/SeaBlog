package top.sea521.entity.custom;

import top.sea521.entity.User;

/**
 * 用户信息的扩展
 * Created by 言曌 on 2017/8/24.
 */
public class UserCustom extends User {
    /**新添用户的文章数*/
    private Integer articleCount;

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public String toString() {
        return "UserCustom{" +
                "articleCount=" + articleCount +
                '}';
    }
}
