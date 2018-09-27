package com.liuyanzhao.blog.mapper.custom;

import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.entity.custom.UserCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 言曌 on 2017/8/24.
 */

@Repository
public interface UserMapperCustom {

    /**
     * 1 获得用户列表
     */
    List<UserCustom> listUser() throws Exception;


    /**
     * 2 根据用户名或Email获得用户
     */
    User getUserByNameOrEmail(String str) throws Exception;

    /**
     * 3 根据用户名查用户
     */
    User getUserByName(String name) throws Exception;

    /**
     * 4 根据Email查询用户
     */
    User getUserByEmail(String email) throws Exception;

    /**
     * 5 用户的文章数
     */
    Integer countArticleByUser(@Param(value = "id") Integer id) throws Exception;
}
