package org.xdweb.first.service.impl;

import org.xdweb.first.mapper.BookInfoMapper;
import org.xdweb.first.model.BookInfo;
import org.xdweb.first.service.BookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Resource
    private BookInfoMapper bookInfoMapper;

    /**
     * 获取图书数量
     * @return
     */
    @Override
    public Integer getCount() {
        return bookInfoMapper.selectCount();
    }

    /**
     * 获得总数
     * @param params
     * @return
     */
    @Override
    public int getSearchCount(Map<String, Object> params) {
        return bookInfoMapper.selectCountBySearch(params);
    }

    /**
     * 分页查询图书信息
     * @param params
     * @return
     */
    @Override
    public List<BookInfo> searchBookInfosByPage(Map<String, Object> params) {
        return bookInfoMapper.selectBySearch(params);
    }

    /**
     * 获取全部图书信息
     * @return
     */
    @Override
    public List<BookInfo> queryBookInfos() {
        return bookInfoMapper.selectAll();
    }
}
