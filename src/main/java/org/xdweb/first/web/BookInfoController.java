package org.xdweb.first.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.xdweb.first.model.BookInfo;
import org.xdweb.first.service.BookInfoService;
import org.xdweb.first.utils.MyResult;
import org.xdweb.first.utils.MyUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/bookInfo")
public class BookInfoController {

    @Resource
    private BookInfoService bookInfoService;

    /**
     * 获取图书数量
     * @return
     */
    @GetMapping(value = "/getCount")
    public Integer getCount(){
        log.info("获取图书数量");
        return bookInfoService.getCount();
    }

    /**
     * 查询所有图书信息
     * @return
     */
    @GetMapping(value = "/queryBookInfos")
    public List<BookInfo> queryBookInfos(){
        log.info("查询所有图书信息");
        return bookInfoService.queryBookInfos();
    }

    /**
     * 分页搜索查询图书信息
     * @param params
     * @return
     */
    @GetMapping(value = "/queryBookInfosByPage")
    public Map<String, Object> queryBookInfosByPage(@RequestParam Map<String, Object> params){
        MyUtils.parsePageParams(params);
        int count = bookInfoService.getSearchCount(params);  // 获得总数
        List<BookInfo> bookInfos = bookInfoService.searchBookInfosByPage(params);  // 分页查询
        return MyResult.getListResultMap(0, "success", count, bookInfos);
    }

    /**
     * 添加图书信息
     * @param bookInfo
     * @return
     */
    @PostMapping(value = "/addBookInfo")
    public Integer addBookInfo(@RequestBody BookInfo bookInfo){
        log.info("添加图书信息,{}", bookInfo);
        return bookInfoService.addBookInfo(bookInfo);
    }

    /**
     * 删除图书信息
     * @param bookInfo
     * @return
     */
    @DeleteMapping(value = "/deleteBookInfo")
    public Integer deleteBookInfo(@RequestBody BookInfo bookInfo){
        log.info("删除图书信息,{}", bookInfo);
        return bookInfoService.deleteBookInfo(bookInfo);
    }

    /**
     * 修改图书信息
     * @param bookInfo
     * @return
     */
    @PutMapping(value = "/updateBookInfo")
    public Integer updateBookInfo(@RequestBody BookInfo bookInfo){
        log.info("修改图书信息,{}", bookInfo);
        return bookInfoService.updateBookInfo(bookInfo);
    }
}
