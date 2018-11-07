package top.sea521.service.impl;

import top.sea521.entity.Notice;
import top.sea521.entity.custom.NoticeCustom;
import top.sea521.mapper.NoticeMapper;
import top.sea521.mapper.custom.NoticeMapperCustom;
import top.sea521.service.NoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapperCustom noticeMapperCustom;

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 1 公列列表
     */
    @Override
    public List<NoticeCustom> listNotice(Integer status) throws Exception {
        List<NoticeCustom> noticeList = noticeMapperCustom.listNotice(status);
        return noticeList;
    }

    /**
     * 2 插入公告
     */
    @Override
    public void insertNotice(Notice notice) throws Exception {
        noticeMapper.insertSelective(notice);
    }

    /**
     * 3 删除公告
     */
    @Override
    public void deleteNotice(Integer id) throws Exception {
        noticeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 4 更新公告
     */
    @Override
    public void updateNotice(Notice notice) throws Exception {
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    /**
     * 5 查寻ById
     */
    @Override
    public NoticeCustom getNoticeById(Integer id) throws Exception {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        NoticeCustom noticeCustom = new NoticeCustom();
        BeanUtils.copyProperties(notice, noticeCustom);
        return noticeCustom;
    }

}
