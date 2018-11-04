package top.sea521.mapper.custom;

import top.sea521.entity.custom.NoticeCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NoticeMapperCustom {

    /**
     * 1 获得公告总数
     */
    Integer countNotice(@Param(value = "status") Integer status) throws Exception;

    /**
     * 2 根据可用状态获得公告列表
     */
    List<NoticeCustom> listNotice(@Param(value = "status") Integer status) throws Exception;


}
