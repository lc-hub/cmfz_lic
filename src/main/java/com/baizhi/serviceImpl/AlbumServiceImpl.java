package com.baizhi.serviceImpl;

import com.baizhi.api.UtilApiService;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 专辑表业务类
 *
 * @author miion
 * @create 2019-08-15 19:03
 */
@Service
@Transactional
public class AlbumServiceImpl extends UtilApiService implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectAllAlbumByPage(Integer page, Integer rows) {
//        分页
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Album album = new Album();

        List<Album> albums = albumDAO.selectByRowBounds(album, rowBounds);
//        获取总条数
        int count = albumDAO.selectCount(album);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("page", page);//当前页码
        map.put("rows", albums);// 分页查询到的数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);// 总页数
        map.put("records", count);// 总条数
        return map;
    }

    // 添加业务
    @Override
    public String add(Album album) {
//       获取uuid
        String id = UUID.randomUUID().toString().replace("-", "");
        album.setId(id);
        album.setCreateDate(new Date());
        int i = albumDAO.insertSelective(album);
        if (i == 0) new RuntimeException("专辑添加异常");
        return id;
    }

    //编辑
    @Override
    public String edit(Album album) {
//        如果专辑封面没有修改则赋值null  不做修改
        if ("".equals(album.getCover())) {
            album.setCover(null);
        }

        int i = albumDAO.updateByPrimaryKeySelective(album);
        if (i == 0) new RuntimeException("专辑修改异常");

        return album.getId();
    }

    //删除
    @Override
    public void del(Album album) {
//        查询该专辑下是否有章节
//        创建条件查询对象
        //Example example = new Example(Chapter.class);
//        按id查询
        //example.createCriteria().andEqualTo("albumId", album.getId());

        Chapter chapter = new Chapter();
        chapter.setAlbumId(album.getId());
        List<Chapter> chapters = chapterDAO.select(chapter);

        System.out.println("3" + chapters);
        if (chapters.size() == 0) {
            System.out.println("4");
            int delete = albumDAO.delete(album);
        } else {

            throw new RuntimeException("该专辑下有音频，不可删除");
        }

    }

    @Override
    public List<Album> findAll() {
        List<Album> albums = albumDAO.selectAll();


        return albums;
    }
}
