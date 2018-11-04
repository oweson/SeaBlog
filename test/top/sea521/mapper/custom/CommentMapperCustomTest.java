package top.sea521.mapper.custom;

import com.base.BaseClass;
import top.sea521.entity.custom.CommentCustom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/28 0028 16:21
 */
public class CommentMapperCustomTest extends BaseClass {
    @Autowired
    CommentMapperCustom comm;

    @Test
    public void listCommentByArticleId() throws Exception {
        List<CommentCustom> commentCustoms = comm.listCommentByArticleId(1,1);
        for (CommentCustom commentCustom : commentCustoms) {
            System.out.println(commentCustom);

        }
    }

    @Test
    public void listCommentByPage() {
    }

    @Test
    public void listComment() {
    }

    @Test
    public void countComment() {
    }

    @Test
    public void listRecentComment() {
    }

    @Test
    public void listChildComment() {
    }
}