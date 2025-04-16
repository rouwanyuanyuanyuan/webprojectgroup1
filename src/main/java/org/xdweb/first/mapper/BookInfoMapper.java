package org.xdweb.first.mapper;

import org.apache.ibatis.annotations.Select;
import org.xdweb.first.model.BookInfo;

import java.util.List;

public interface BookInfoMapper {

    /**
     * 查询所有数量
     * @return
     */
    @Select("SELECT COUNT(*) FROM book_info")
    Integer selectCount();

    /**
     * 查询所有图书信息
     * @return
     */
    List<BookInfo> selectAll();
}
