package org.xdweb.first.mapper;

import org.apache.ibatis.annotations.Select;
import org.xdweb.first.model.BookInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 获得总数
     * @param params
     * @return
     */
    int selectCountBySearch(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<BookInfo> selectBySearch(Map<String, Object> params);

    /**
     * 根据主键查询
     * @param bookInfo
     * @return
     */
    Integer insertSelective(BookInfo bookInfo);

    /**
     * 删除图书信息
     * @param bookid
     * @return
     */
    int deleteByPrimaryKey(Integer bookid);

    /**
     * 更新图书信息
     * @param bookInfo
     * @return
     */
    Integer updateByPrimaryKeySelective(BookInfo bookInfo);
}
