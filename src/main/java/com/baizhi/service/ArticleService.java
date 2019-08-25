package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

/**
 * @author miion
 * @create 2019-08-18 14:43
 */
public interface ArticleService {


    //    分页查询
    Map<String, Object> selectAllByPage(Integer page, Integer rows);

    //    添加
    String add(Article article);

    //    编辑
    String edit(Article article);

    //    删除
    void del(Article article);
}
