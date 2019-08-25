package com.baizhi.controller;

import com.baizhi.api.UtilApiService;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-15 22:46
 */

@RestController
@RequestMapping("/chapter")
public class ChapterController extends UtilApiService {


    @Autowired
    private ChapterService chapterService;

    //  加载后分页查询
    @RequestMapping("byPage")
    public Map<String, Object> selectByPage(Integer rows, Integer page, String id) {
        Map<String, Object> stringObjectMap = chapterService.selectAllAlbumByPage(rows, page, id);
        return stringObjectMap;
    }

    // 编辑方法
    @RequestMapping("/edit")
    public Map<String, Object> edit(String oper, Chapter chapter, String aid) {


        if ("add".equals(oper)) {
//            执行添加

            String add = chapterService.add(chapter, aid);
//            返回id
            return setResultSuccessData(add);
        }
        if ("edit".equals(oper)) {
//           编辑方法
            String edit = chapterService.edit(chapter, aid);
//          返回id
            return setResultSuccessData(edit);
        }
        if ("del".equals(oper)) {
//         删除
            chapterService.del(chapter, aid);
        }
//        返回成功
        return setResultSuccess();
    }

    //    上传
    @RequestMapping("upload")
    public void upload(MultipartFile audio, String id, HttpSession session) throws IOException, EncoderException {

        System.out.println(id + "进来了" + audio);


        if (!"".equals(audio.getOriginalFilename())) {


//          通过相对路径获取绝路径
            String realPath = session.getServletContext().getRealPath("view/album/MP3");
//            获取文件的原始名
            String name = audio.getOriginalFilename();
//            获取文件大小

            File file = new File(realPath, name);
//            获取文件字节长度
            long length = audio.getSize();
//            通过这个类计算不会丢失精度
            BigDecimal bigDecimal = new BigDecimal(length);
            BigDecimal decimal = new BigDecimal(1024);
//            q取小数点前两位
            BigDecimal scale = bigDecimal.divide(decimal).divide(decimal).setScale(2, BigDecimal.ROUND_HALF_UP);

//            获取音频时长
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(new File(realPath, name));
            long ls = m.getDuration();
            String s = ls / 1000 / 60 + ":" + ls / 1000 % 60;
//            上传文件
            audio.transferTo(new File(realPath, name));
//            修改数据库文件得路径
            Chapter album = new Chapter();
            album.setId(id);
            album.setAudio(name);
//            文件大小
            album.setSize(scale + "MB");
//            文件时长
            album.setDuration(s);
//            调用修改业务
            chapterService.edit(album, id);
        }

    }


}
