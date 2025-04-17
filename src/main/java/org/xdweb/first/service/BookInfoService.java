package org.xdweb.first.service;

import org.xdweb.first.model.BookInfo;

import java.util.List;
import java.util.Map;

public interface BookInfoService {

    /**
     * 获取图书数量
     * @return
     */
    Integer getCount();

    /**
     * 查询所有图书信息
     * @return
     */
    List<BookInfo> queryBookInfos();

    /**
     * 获得总数
     * @param params
     * @return
     */
    int getSearchCount(Map<String, Object> params);

    /**
     * 分页查询图书信息
     * @param params
     * @return
     */
    List<BookInfo> searchBookInfosByPage(Map<String, Object> params);

    /**
     * 添加图书信息
     * @param bookInfo
     * @return
     */
    Integer addBookInfo(BookInfo bookInfo);

    /**
     * 删除图书信息
     * @param bookInfo
     * @return
     */
    Integer deleteBookInfo(BookInfo bookInfo);

    /**
     * 更新图书信息
     * @param bookInfo
     * @return
     */
    Integer updateBookInfo(BookInfo bookInfo);

    /**
     * 批量删除图书信息
     * @param bookInfos
     * @return
     */
    Integer deleteBookInfos(List<BookInfo> bookInfos);
}
