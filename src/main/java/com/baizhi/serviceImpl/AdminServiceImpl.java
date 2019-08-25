package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author miion
 * @create 2019-08-13 15:18
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override

    public Admin login(String username, String password) {

        //创建 Example
        Example example = new Example(Admin.class);

//        通过某个字段查询
        example.createCriteria().andEqualTo("username", username);

        List<Admin> admins1 = adminDAO.selectByExample(example);
        Admin admin = admins1.get(0);
        return admin;
    }
}
