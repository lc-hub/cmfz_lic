package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-14 17:03
 */
@RestController
@RequestMapping("/banner")
@Slf4j
public class BannerController {

    @Autowired
    private BannerService bannerService;

    //    加载后分页查询
    @RequestMapping("/byPage")
    public Map<String, Object> selectByPage(Integer rows, Integer page, String searchField, String searchString, String searchOper) {

        Map<String, Object> stringObjectMap = bannerService.selectByPage(rows, page, searchField, searchString, searchOper);
        return stringObjectMap;
    }

    //   jqGrid编辑后提交的路径
    @RequestMapping("/edit")
    public Map<String, Object> editBanner(String oper, Banner banner) throws IOException {


        log.debug(oper);
        log.debug("" + banner);

        Map<String, Object> stringStringMap = bannerService.coperBanner(oper, banner);
        return stringStringMap;

    }

    @RequestMapping("/upload")
    public void upload(MultipartFile cover, HttpServletRequest request, String id) throws IOException {
        if (!"".equals(cover.getOriginalFilename())) {
            //        根据相对路径获取绝对路径
            String realPath = request.getSession().getServletContext().getRealPath("file");

            //       获取文件原始名
            String name = cover.getOriginalFilename();
            //        上传文件
            cover.transferTo(new File(realPath, name));


            Banner banner = new Banner();
            banner.setId(id);
            //       修改文件路径
            banner.setCover(name);

            //       最后一次修改时间
            banner.setLastUpdateDate(new Date());
            log.debug("上传：===" + banner);
            bannerService.upload(banner);
        }

    }

}
