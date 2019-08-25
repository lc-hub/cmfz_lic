package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterDAO extends Mapper<Chapter> {

    List<Chapter> byAlbumIdAndByPage(@Param("begin") Integer bengin, @Param("end") Integer end, @Param("id") String id);
}