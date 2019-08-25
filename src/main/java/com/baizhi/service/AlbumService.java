package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-15 18:45
 */
public interface AlbumService {

    //    分页
    Map<String, Object> selectAllAlbumByPage(Integer page, Integer rows);

    //    添加
    String add(Album album);

    //    编辑
    String edit(Album album
    );

    //    删除
    void del(Album album);

    List<Album> findAll();
}
