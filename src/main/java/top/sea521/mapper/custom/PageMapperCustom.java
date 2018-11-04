package top.sea521.mapper.custom;

import top.sea521.entity.custom.PageCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PageMapperCustom {
    /**
     *1 获得页面列表
     */
    List<PageCustom> listPage(@Param(value = "status") Integer status) throws Exception;

    /**
     * 2 根据key获得页面
     */
    PageCustom getPageByKey(@Param(value = "status") Integer status, @Param(value = "key") String key) throws Exception;
}
