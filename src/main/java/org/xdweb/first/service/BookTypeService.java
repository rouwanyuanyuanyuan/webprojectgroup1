package org.xdweb.first.service;

import org.xdweb.first.model.BookType;

import java.util.List;
import java.util.Map;

public interface BookTypeService {
    /**
     * 查询图书类型数量
     * @return
     */
    Integer getCount();

    /**
     * 查询图书类型总数
     * @param params
     * @return
     */
    Integer getSearchCount(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<BookType> searchBookTypesByPage(Map<String, Object> params);

    /**
     * 查询所有图书类型
     * @return
     */
    List<BookType> queryBookTypes();

    /**
     * 添加类型
     * @param bookType
     * @return
     */
    Integer addBookType(BookType bookType);

    /**
     * 删除类型
     * @param bookType
     * @return
     */
    Integer deleteBookType(BookType bookType);

    /**
     * 批量删除类型
     * @param bookTypes
     * @return
     */
    Integer deleteBookTypes(List<BookType> bookTypes);

    /**
     * 更新类型
     * @param bookType
     * @return
     */
    Integer updateBookType(BookType bookType);

}
