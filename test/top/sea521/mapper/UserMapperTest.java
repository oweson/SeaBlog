package top.sea521.mapper;

import com.base.BaseClass;
import top.sea521.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/27 0027 19:51
 */
public class UserMapperTest extends BaseClass {
    @Autowired
    UserMapper userMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());

    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}