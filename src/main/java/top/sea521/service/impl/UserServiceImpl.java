package top.sea521.service.impl;

import org.apache.commons.lang3.StringUtils;
import top.sea521.entity.User;
import top.sea521.entity.custom.UserCustom;
import top.sea521.mapper.UserMapper;
import top.sea521.mapper.custom.UserMapperCustom;
import top.sea521.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapperCustom userMapperCustom;

    @Autowired
    private UserMapper userMapper;

    /**
     * 1 查所的用
     */
    @Override
    public List<UserCustom> listUser() throws Exception {
        List<UserCustom> userCustomList = userMapperCustom.listUser();
        for (int i = 0; i < userCustomList.size(); i++) {
            /**统计每个用户的文章的总数*/
            Integer articleCount = userMapperCustom.countArticleByUser(userCustomList.get(i).getUserId());
            userCustomList.get(i).setArticleCount(articleCount);
        }
        return userCustomList;
    }

    /**
     * 2 查寻单个用户
     */
    @Override
    public UserCustom getUserById(Integer id) throws Exception {

        User user = userMapper.selectByPrimaryKey(id);
        UserCustom userCustom = new UserCustom();
        BeanUtils.copyProperties(user, userCustom);
        return userCustom;
    }

    /**
     * 3 删
     */
    @Override
    public void updateUser(User user) throws Exception {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 4 根据id删除
     */
    @Override
    public void deleteUser(Integer id) throws Exception {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 5 新增用户
     */
    @Override
    public void insertUser(User user) throws Exception {
        /**参数校验，name必须；
         * 用户名唯一*/
        if (user == null || StringUtils.isBlank(user.getUserName())) {
            return;
        }
        User userByName = userMapperCustom.getUserByName(user.getUserName());
        if (userByName != null) {
            return;
        }
        user.setUserRegisterTime(new Date());
        userMapper.insertSelective(user);
    }

    /**
     * 6 根据邮箱或者用户名查找用户
     */
    @Override
    public User getUserByNameOrEmail(String str) throws Exception {
        User user = userMapperCustom.getUserByNameOrEmail(str);
        return user;
    }

    /**
     * 7  根据名字查找用户
     */
    @Override
    public User getUserByName(String name) throws Exception {
        User user = userMapperCustom.getUserByName(name);
        return user;
    }

    /**
     * 8 通过邮箱查找
     */
    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userMapperCustom.getUserByEmail(email);
        return user;
    }


}
