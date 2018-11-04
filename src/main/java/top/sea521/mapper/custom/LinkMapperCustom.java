package top.sea521.mapper.custom;

import top.sea521.entity.custom.LinkCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/4.
 */
public interface LinkMapperCustom {

    /**
     * 1 获得链接总数
     * 1 可用0是不可用
     */
    Integer countLink(@Param(value = "status") Integer status) throws Exception;

    /**
     * 2 根据状态获得链接列表
     */
    List<LinkCustom> listLink(@Param(value = "status") Integer status) throws Exception;


}
