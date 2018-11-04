package top.sea521.mapper.custom;

import top.sea521.entity.custom.NoticeCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 言曌 on 2017/9/4.
 */
public interface NoticeMapperCustom {

    /**
     * 获得公告总数
     */
    Integer countNotice(@Param(value = "status") Integer status) throws Exception;

    /**
     * 获得公告列表
     */
    List<NoticeCustom> listNotice(@Param(value = "status") Integer status) throws Exception;


}
