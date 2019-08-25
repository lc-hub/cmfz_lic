package com.baizhi.serviceImpl;

import com.baizhi.dao.ProvinceDAO;
import com.baizhi.entity.Province;
import com.baizhi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 省业务类
 *
 * @author miion
 * @create 2019-08-17 14:58
 */
@Service
@Transactional
public class provinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDAO provinceDAO;

    //    查询所有省
    @Override
    public List<Province> findAll() {

        List<Province> all = provinceDAO.selectAll();
        return all;
    }
}
