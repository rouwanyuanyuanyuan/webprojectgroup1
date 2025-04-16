package org.xdweb.first.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdweb.first.model.BookInfo;
import org.xdweb.first.service.BookInfoService;

import javax.annotation.Resource;
import java.util.List;

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
}
