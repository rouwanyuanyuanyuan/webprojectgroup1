package org.xdweb.first.service.impl;

import org.xdweb.first.mapper.BookInfoMapper;
import org.xdweb.first.mapper.BookTypeMapper;
import org.xdweb.first.model.BookType;
import org.xdweb.first.service.BookTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class  BookTypeServiceImpl implements BookTypeService {

    @Resource
    private BookTypeMapper bookTypeMapper;

    @Resource
    private BookInfoMapper bookInfoMapper;

    /**
     * 查询图书类型数量
     * @return
     */
    @Override
    public Integer getCount() {
        return bookTypeMapper.selectCount();
    }

    /**
     * 查询图书类型总数
     * @param params
     * @return
     */
    @Override
    public Integer getSearchCount(Map<String, Object> params) {
        return bookTypeMapper.selectCountBySearch(params);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public List<BookType> searchBookTypesByPage(Map<String, Object> params) {
        return bookTypeMapper.selectBySearch(params);
    }

    /**
     * 查询所有图书类型
     * @return
     */
    @Override
    public List<BookType> queryBookTypes() {
        return bookTypeMapper.selectAll();
    }

    /**
     * 添加类型
     * @param bookType
     * @return
     */
    @Override
    public Integer addBookType(BookType bookType) {
        return bookTypeMapper.insertSelective(bookType);
    }

    /**
     * 删除类型
     * @param bookType
     * @return
     */
    @Override
    public Integer deleteBookType(BookType bookType) {
        int count = 0;
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("booktypeid", bookType.getBooktypeid());
            if(bookInfoMapper.selectCountByType(map) > 0) {
                return -1;
            }
            count = bookTypeMapper.deleteByPrimaryKey(bookType.getBooktypeid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 批量删除类型
     * @param bookTypes
     * @return
     */
    @Override
    public Integer deleteBookTypes(List<BookType> bookTypes) {
        int count = 0;
        for(BookType bookType : bookTypes) {
            count += deleteBookType(bookType);
        }
        return count;
    }

    /**
     * 更新类型
     * @param bookType
     * @return
     */
    @Override
    public Integer updateBookType(BookType bookType) {
        return bookTypeMapper.updateByPrimaryKeySelective(bookType);
    }
}
