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

}
