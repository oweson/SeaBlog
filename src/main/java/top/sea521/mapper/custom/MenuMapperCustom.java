package top.sea521.mapper.custom;

import top.sea521.entity.custom.MenuCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapperCustom {
    /**
     * 获得菜单列表
     */
    List<MenuCustom> listMenu(@Param(value = "status") Integer status) throws Exception;
}
