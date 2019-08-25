package com.baizhi.serviceImpl;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 文章业务类
 *
 * @author miion
 * @create 2019-08-15 21:21
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;

    @Autowired
    private AlbumDAO albumDAO;

    @Override
    public Map<String, Object> selectAllAlbumByPage(Integer rows, Integer page, String id) {

//        创建条件查询对象
        Example example = new Example(Chapter.class);
//        按id查询
        example.createCriteria().andEqualTo("albumId", id);
//        创建分页查询对象
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
//        集成 按条件查询并分页
        List<Chapter> chapters = chapterDAO.selectByExampleAndRowBounds(example, rowBounds);


        //        //分页
        Chapter chapter = new Chapter();
        //      List<Chapter> chapters = chapterDAO.byAlbumIdAndByPage(((page - 1) * rows + 1)-1, rows, id);


        int count = chapterDAO.selectCount(chapter);

        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("page", page);//当前页码
        map.put("rows", chapters);// 分页查询到的数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);// 总页数
        map.put("records", count);// 总条数
        return map;
    }

    //添加
    @Override
    public String add(Chapter album, String fid) {
//        创建条件查询对象
        Example example = new Example(Album.class);
//        id查询
        example.createCriteria().andEqualTo("id", fid);

        Album album1 = albumDAO.selectOneByExample(example);
//        将查询到的集数取出来进行加1 在存进去
        album1.setCount(album1.getCount() + 1);
        int insert = albumDAO.updateByPrimaryKeySelective(album1);
//        获取uuid
        String id = UUID.randomUUID().toString().replace("-", "");
        album.setId(id);
        album.setCreateDate(new Date());
        album.setAlbumId(fid);
        int i = chapterDAO.insertSelective(album);
        if (i == 0) throw new RuntimeException("章节添加异常");
        return id;
    }

    //    修改
    @Override
    public String edit(Chapter album, String id) {

        if ("".equals(album.getAudio())) {
            album.setAudio(null);
        }
        int i = chapterDAO.updateByPrimaryKeySelective(album);
        if (i == 0) throw new RuntimeException("章节修改异常");

        return album.getId();

    }

    //删除
    @Override
    public void del(Chapter album, String id) {

        //        创建条件查询对象
        Example example = new Example(Album.class);
//        id查询
        example.createCriteria().andEqualTo("id", id);

        Album album1 = albumDAO.selectOneByExample(example);
//        将查询到的集数取出来进行加1 在存进去
        album1.setCount(album1.getCount() - 1);
        int insert = albumDAO.updateByPrimaryKeySelective(album1);
        int delete = chapterDAO.delete(album);
        if (delete == 0) new RuntimeException("章节删除异常");

    }

    @Override
    public List<Chapter> findByid(String id) {
        Example example = new Example(Chapter.class);
        example.createCriteria().andEqualTo("albumId", id);
        List<Chapter> list = chapterDAO.selectByExample(example);

        return list;
    }
}
