package org.xdweb.first.mapper;

import org.xdweb.first.model.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookTypeMapper {


    /**
     * 主键查询
     * @param booktypeid
     * @return
     */
    BookType selectByPrimaryKey(Integer booktypeid);

    /**
     * 分页查询所有类型
     * @param begin
     * @param size
     * @return
     */
    List<BookType> selectAllByLimit(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * 查询类型数量
     * @return
     */
    Integer selectCount();

    /**
     * 查询所有类型
     * @return
     */
    List<BookType> selectAll();

    /**
     * 搜索查询数量
     * @param searchParam
     * @return
     */
    int selectCountBySearch(Map<String, Object> searchParam);

    /**
     * 搜索类型
     * @param searchParam
     * @return
     */
    List<BookType> selectBySearch(Map<String, Object> searchParam);

    int insert(BookType record);

    /**
     * 添加图书类型
     * @param record
     * @return
     */
    int insertSelective(BookType record);

    /**
     * 删除图书类型（根据主键）
     * @param booktypeid
     * @return
     */
    int deleteByPrimaryKey(Integer booktypeid);

    /**
     * 更新图书类型（根据主键）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);
}
