package com.baizhi.service;


import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-15 18:51
 */
public interface ChapterService {

    //    分页
    Map<String, Object> selectAllAlbumByPage(Integer page, Integer rows, String id);

    //    添加
    String add(Chapter album, String id);

    //    编辑
    String edit(Chapter album, String id
    );

    //    删除
    void del(Chapter album, String id);


    List<Chapter> findByid(String id);
}
