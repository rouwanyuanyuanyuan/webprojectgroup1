package org.xdweb.first.service.impl;

import org.xdweb.first.mapper.BookInfoMapper;
import org.xdweb.first.model.BookInfo;
import org.xdweb.first.service.BookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
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
     * 添加图书信息
     * @param bookInfo
     * @return
     */
    @Override
    public Integer addBookInfo(BookInfo bookInfo) {
        return bookInfoMapper.insertSelective(bookInfo);
    }

    /**
     * 删除图书信息
     * @param bookInfo
     * @return
     */
    @Override
    public Integer deleteBookInfo(BookInfo bookInfo) {
        int count = 0;
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("bookId", bookInfo.getBookid());
            // 判断是否有借阅记录
//            if(borrowMapper.selectCountBySearch(map) > 0) {
//                return -1;
//            }
            count = bookInfoMapper.deleteByPrimaryKey(bookInfo.getBookid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 更新图书信息
     * @param bookInfo
     * @return
     */
    @Override
    public Integer updateBookInfo(BookInfo bookInfo) {
        return bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
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
