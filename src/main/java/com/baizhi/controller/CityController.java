package com.baizhi.controller;

import com.baizhi.entity.City;
import com.baizhi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 市控制类
 *
 * @author miion
 * @create 2019-08-17 16:20
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("findAll")
    public String findAll(String pid) {

        List<City> all = cityService.findAll(pid);
        StringBuilder sb = new StringBuilder("<select>");
        for (City city : all) {
            sb.append("<option value='" + city.getName() + "'>" + city.getName() + "</option>");
        }
        sb.append("</select>");
        return sb.toString();
    }
}
