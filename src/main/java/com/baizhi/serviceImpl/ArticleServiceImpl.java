package com.baizhi.serviceImpl;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author miion
 * @create 2019-08-18 14:45
 */

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Map<String, Object> selectAllByPage(Integer page, Integer rows) {

        //        分页
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Article article = new Article();

        List<Article> albums = articleDAO.selectByRowBounds(article, rowBounds);
        for (Article album : albums) {
            if (album.getContent().contains("<")) {
                //        要截取的字符
                Pattern p = Pattern.compile(">");
                Matcher m = p.matcher(album.getContent());
                int count = 0;
                while (m.find()) {
                    count++;
                    //  第二次出现跳出循环
                    if (count == 2) {
                        break;
                    }
                }
                System.out.println("======" + m.start());
                int start = m.start();

                //结果 <h1>这是h1标签</h1>
                album.setContent(album.getContent().substring(album.getContent().indexOf("<"), start + 1));
            }

        }


//        获取总条数
        int count = articleDAO.selectCount(article);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("page", page);//当前页码
        map.put("rows", albums);// 分页查询到的数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);// 总页数
        map.put("records", count);// 总条数
        return map;


    }

    @Override
    public String add(Article article) {
        article.setId(UUID.randomUUID().toString().replace("-", ""));
        article.setPublishDate(new Date());
        articleDAO.insertSelective(article);
        return null;
    }

    @Override
    public String edit(Article article) {
        articleDAO.updateByPrimaryKeySelective(article);
        return null;
    }

    @Override
    public void del(Article article) {
        articleDAO.delete(article);
    }
}
