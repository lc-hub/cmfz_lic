package com.baizhi.controller;

import com.baizhi.api.UtilApiService;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-18 15:05
 */

@RestController
@RequestMapping("/article")
public class ArticleController extends UtilApiService {

    @Autowired
    private ArticleService articleService;


    //    页面加载后分页查询
    @RequestMapping("/byPage")
    public Map<String, Object> selectAllAlbumByPAi(Integer page, Integer rows) {

        try {
            Map<String, Object> map = articleService.selectAllByPage(page, rows);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return setResultErrorMsg("查询文章失败");
        }
    }

    // 编辑方法
    @RequestMapping("/edit")
    public Map<String, Object> edit(String oper, Article article) {


        if ("add".equals(oper)) {
//            执行添加

            String add = articleService.add(article);
//            返回id
            return setResultSuccess();
        }
        if ("edit".equals(oper)) {
//           编辑方法
            String edit = articleService.edit(article);
//            返回id
            return setResultSuccess();
        }
        if ("del".equals(oper)) {
            articleService.del(article);

        }

//        返回成功
        return setResultSuccess();
    }


    //   kindeditor 的上传文件
    @RequestMapping("/upload")
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile imageFile) {
        System.out.println("原始名称" + imageFile.getOriginalFilename());
        HashMap<String, Object> map = new HashMap<>();
        try {
//             上传文件 File 第一个参数 通过相对路径获取绝对路径  第二个参数  文件的原始名称
            imageFile.transferTo(new File(request.getSession().getServletContext().getRealPath("view/article/image"), imageFile.getOriginalFilename()));
//            设置 返回值
            String url = "http://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath() + "/view/article/image/" + imageFile.getOriginalFilename();
            map.put("error", 0);
            map.put("url", url);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            return map;
        }
    }


    //    图片存储空间
    @RequestMapping("/browser")
    public Map<String, Object> browser(HttpServletRequest request) {
//        获取文件的路径
        File file = new File(request.getSession().getServletContext().getRealPath("view/article/image"));
        File[] files = file.listFiles(); //获取所有文件名
        /**
         * request.getServerName();动态获取当前ip地址
         * request.getServerPort(); 动态获取端口号
         * request.getContextPath(); 动态获取当前项目名
         * FilenameUtils.getExtension(file.getName())  获取文件类型
         */
//        获取图片空间的url
        String current_url = "http://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath() + "/view/article/image/";

        ArrayList<Object> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("is_dir", false);
            map.put("has_file", false);
            map.put("filesize", files[i].length()); // 图片大小
            map.put("is_photo", true); //是否视图片
            map.put("filetype", FilenameUtils.getExtension(files[i].getName())); //文件类型
            map.put("filename", files[i].getName());//文件名称
            map.put("datetime", new Date()); //创建时间
            list.add(map);
        }

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("current_url", current_url); //获取当前图片文件夹的地址
        map1.put("total_count", files.length);// 获取文件的个数
        map1.put("file_list", list);
        return map1;
    }

}
