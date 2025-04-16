package org.xdweb.first.service;

import org.xdweb.first.model.BookInfo;

import java.util.List;

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
}
