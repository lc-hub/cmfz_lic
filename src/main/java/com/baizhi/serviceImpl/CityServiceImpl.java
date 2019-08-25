package com.baizhi.serviceImpl;

import com.baizhi.dao.CityDAO;
import com.baizhi.entity.City;
import com.baizhi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 城市实体
 *
 * @author miion
 * @create 2019-08-17 14:56
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDAO cityDAO;

    //查询所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<City> findAll(String id) {

        Example example = new Example(City.class);
        example.createCriteria().andEqualTo("provincecode", id);
        List<City> cities = cityDAO.selectByExample(example);
        return cities;
    }
}
