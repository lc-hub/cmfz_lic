package com.baizhi.service;

import com.baizhi.entity.City;

import java.util.List;

/**
 * @author miion
 * @create 2019-08-17 14:47
 */
public interface CityService {

    List<City> findAll(String id);
}
