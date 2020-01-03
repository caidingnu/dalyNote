package com.study.wxapp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.wxapp.entity.JsonResult;
import com.study.wxapp.entity.News;
import com.study.wxapp.entity.User;
import com.study.wxapp.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cdn
 * @since 2020-01-03
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService iNewsService;


    /**
     * des:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("insert")
    public JsonResult insert(News news) {
        return JsonResult.buildSuccess(news.insert());
    }


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("delete")
    public Object delete(News news) {
        return news.deleteById();
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("update")
    public Object update(News news) {
        return JsonResult.buildSuccess(news.updateById());
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("page")
    public JsonResult page(@RequestParam(defaultValue = "0") Integer pageIndex,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<News> page = new Page<>(pageIndex, pageSize);
        return JsonResult.buildSuccess(iNewsService.page(page));
    }
}
