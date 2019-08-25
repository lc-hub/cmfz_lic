package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.api.UtilApiService;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-15 19:31
 */
@RestController
@RequestMapping("/album")
public class AlbumController extends UtilApiService {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ChapterService chapterService;

    //    页面加载后分页查询
    @RequestMapping("/byPage")
    public Map<String, Object> selectAllAlbumByPAi(Integer page, Integer rows) {

        try {
            Map<String, Object> map = albumService.selectAllAlbumByPage(page, rows);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return setResultErrorMsg("查询轮播图失败");
        }
    }

    // 编辑方法
    @RequestMapping("/edit")
    public Map<String, Object> edit(String oper, Album album) {
        if ("add".equals(oper)) {
//            执行添加

            String add = albumService.add(album);
//            返回id
            return setResultSuccessData(add);
        }
        if ("edit".equals(oper)) {
//           编辑方法
            String edit = albumService.edit(album);
//            返回id
            return setResultSuccessData(edit);
        }
        if ("del".equals(oper)) {
//            删除
            try {

                albumService.del(album);
            } catch (Exception e) {

                e.printStackTrace();
                return setResultMsg("该专辑下有音频，不可删除");
            }
        }
//        返回成功
        return setResultSuccess();
    }

    //    上传
    @RequestMapping("upload")
    public void upload(MultipartFile cover, String id, HttpSession session) throws IOException {

        if (!"".equals(cover.getOriginalFilename())) {


//          通过相对路径获取绝路径
            String realPath = session.getServletContext().getRealPath("view/album/img");
//            回去文件的原始名
            String name = cover.getOriginalFilename();
//            上传文件
            cover.transferTo(new File(realPath, name));

            // 修改数据库文件得我路径
            Album album = new Album();
            album.setId(id);
            album.setCover(name);
//            调用修改业务
            albumService.edit(album);
        }

    }

    //    文件导出
    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response) {


        List<Album> list = albumService.findAll();
        //List<Album> albums = new ArrayList<>();
        for (Album album : list) {
            List<Chapter> byid = chapterService.findByid(album.getId());
            album.setList(byid);

            //动态获取服务器IP地址
            String cover = "http://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath() + "/view/album/img/" + album.getCover();
            album.setCover(cover);
            //albums.add(album);
            System.out.println(cover);
        }

        try {

//        工作表
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑文档详情", "专辑表"),
                    Album.class, list);
            System.out.println("                   " + list.size());
            // String fileName="专辑表("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+").xls";
            // new String(fileName.getBytes("GBK"),"iso-8859-1");
            //        设置响应类型
            response.setContentType("application/vnd.ms-excel");
//            设置响应头
            //  response.setHeader("content-disposition", "attachment;fileName=" + fileName);

            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("专辑文档.xls", "UTF-8"));
            workbook.write(response.getOutputStream());
            // return "ok";
        } catch (Exception e) {

            e.printStackTrace();
            //return "error";
        }
//        3、  获取响应对象设置响应头信息   attachment 下载以附件的形式    inline 已预览的方式打开

    }


}
