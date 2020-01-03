package com.study.wxapp.service.impl;

import com.study.wxapp.entity.News;
import com.study.wxapp.mapper.NewsMapper;
import com.study.wxapp.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cdn
 * @since 2020-01-03
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
