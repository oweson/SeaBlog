package top.sea521.mapper;

import com.base.BaseClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.sea521.entity.Menu;

import static org.junit.Assert.*;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/2 0002 20:03
 */
public class MenuMapperTest extends BaseClass {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        Menu menu = new Menu();
        menu.setMenuName("听雨o");
        int insert = menuMapper.insert(menu);
        assertEquals(1,insert);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}