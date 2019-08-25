package com.baizhi.test;

import com.baizhi.CmfzApplication;
import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author miion
 * @create 2019-08-13 15:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class TestAdmin {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDAO adminDao;



    @Test
    public void test1(){
        Admin login = adminService.login("10", "1");
    }
    @Test
    public void test2(){
        Admin admin = new Admin();
//        admin.setUsername("lc");
//        Admin select = adminDao.selectOne(admin);
//        System.out.println(select);

//        根据id查询
//        Admin admin1 = adminDao.selectByPrimaryKey("1");
//        System.out.println(admin1);


//         Example查询

        Example example = new Example(Admin.class);//创建一个Example对象

//        根据某个字段条件查询
//        example.createCriteria().andEqualTo("username","lc");

//        区间查询  查询的字段："id",开始下标 ：0, 长度： 10
         example.createCriteria().andBetween("id", 0, 10);
        List<Admin> admins = adminDao.selectByExample(example);
        for (Admin admin1 : admins) {
            System.out.println(admin1);

        }


//        添加
//        for(int i=2;i<20;i++){
//            Admin admin1 = new Admin(i + "", "li"+i, "666"+i);
//            adminDao.insert(admin1);
//        }

    }
}
