package com.baizhi.controller;

import com.baizhi.entity.Province;
import com.baizhi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 省控制类
 *
 * @author miion
 * @create 2019-08-17 16:20
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping("findAll")
    public String findAll(String id) {

        List<Province> all = provinceService.findAll();


        StringBuilder sb = new StringBuilder("<select>");
        for (Province province : all) {
            sb.append("<option value='" + province.getName() + "'>" + province.getName() + "</option>");
        }
        sb.append("</select>");
        return sb.toString();
    }
}
