package top.sea521.mapper.custom;

import com.base.BaseClass;
import top.sea521.entity.User;
import top.sea521.entity.custom.UserCustom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 20:00
 */
public class UserMapperCustomTest extends BaseClass {
    @Autowired
    UserMapperCustom userMapperCustom;

    @Test
    public void listUser() throws Exception {
        List<UserCustom> userCustoms = userMapperCustom.listUser();
        for (UserCustom userCustom : userCustoms) {
            System.out.println(userCustom);

        }
    }

    @Test
    public void getUserByNameOrEmail() {
    }

    @Test
    public void getUserByName() throws Exception {
        User ppx = userMapperCustom.getUserByName("oweson");
        System.out.println(ppx+"=====================================================================");

    }

    @Test
    public void getUserByEmail() {
    }

    @Test
    public void countArticleByUser() throws Exception {
        Integer integer = userMapperCustom.countArticleByUser(1);
        System.out.println("================================================="+integer);
    }
}