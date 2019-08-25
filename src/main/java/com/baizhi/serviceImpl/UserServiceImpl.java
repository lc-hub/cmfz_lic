package com.baizhi.serviceImpl;

import com.baizhi.Util.Md5Util;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.EchartsUser;
import com.baizhi.entity.EchartsUser2;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 用户业类
 *
 * @author miion
 * @create 2019-08-17 14:55
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    //    登录
    @Override
    public User userLogin(String username) {

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        User user = userDAO.selectOneByExample(example);
        return user;
    }

    //    分页查询
    @Override
    public Map<String, Object> selectAllByPage(Integer page, Integer rows) {
        //        分页
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        User user = new User();

        List<User> albums = userDAO.selectByRowBounds(user, rowBounds);
//        获取总条数
        int count = userDAO.selectCount(user);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("page", page);//当前页码
        map.put("rows", albums);// 分页查询到的数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);// 总页数
        map.put("records", count);// 总条数
        return map;


    }

    //     添加
    @Override
    public String add(User user) {

//       调用工具类获取随机生成的盐
        String salt = Md5Util.getSalt(4);

//        调用工具类获取  密码加盐后生成加盐的密码
        String md5Code = Md5Util.getMd5Code(user.getPassword() + salt);
//        生成uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
//        将生成的uuid存到数据库
        user.setId(uuid);

//        将加盐后的密码存到数据库中
        user.setPassword(md5Code);
//        给用户状态赋予初始值
        user.setStatus("正常");

//       将盐存到数据库中
        user.setSalt(salt);

//        生成当前户注册时间
        user.setCreateDate(new Date());
//        生成最后修改时间、
        user.setLastUpdateDate(new Date());

        int i = userDAO.insertSelective(user);
        if (i == 0) throw new RuntimeException("用户添加失败");
        return uuid;
    }

    //   修改
    @Override
    public String edit(User user) {
        if ("".equals(user.getPhoto())) {
            user.setPhoto(null);
        }
        user.setLastUpdateDate(new Date());
        int i = userDAO.updateByPrimaryKeySelective(user);
        if (i == 0) throw new RuntimeException("用户修改失败");


        return user.getId();
    }

    //   删除
    @Override
    public void del(User user) {

        int delete = userDAO.delete(user);
        System.out.println(delete);
    }


    //
    @Override
    public Map<Object, Object> enrolCount() {
        //        获取第一周的人数
        int[] arr = {7, 14, 21};
//     创建存男的数组
        Integer[] maleCount = new Integer[3];
//     创建存女的数组
        Integer[] girlCount = new Integer[3];

        HashMap<Object, Object> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<EchartsUser> echartsUsers = userDAO.enrolCount(arr[i]);
            for (EchartsUser user : echartsUsers) {

                if (user.getSex().equals("男")) {
                    maleCount[i] = user.getCounts();
                } else {
                    girlCount[i] = user.getCounts();
                }
            }
        }


//        for (Integer integer : maleCount) {
//            System.out.print("nan"+integer);
//        }
//        System.out.println("======================================================");
//        for (Integer integer : girlCount) {
//            System.out.print("nv"+integer);
//        }

        map.put("maleCount", maleCount);
        map.put("girlCount", girlCount);

        return map;
    }

    //    用户分布
    @Override
    public Map<String, List<EchartsUser2>> userDistribution() {
        List<User> list = userDAO.userDistribution();
        HashMap<String, List<EchartsUser2>> map = new HashMap<>();
//        获取所有用户
        ArrayList<EchartsUser2> male = new ArrayList<>();
        ArrayList<EchartsUser2> girl = new ArrayList<>();


        for (User user : list) {
            EchartsUser2 user1 = new EchartsUser2();
            if (user.getSex().equals("男")) {
                user1.setName(user.getProvince());
                user1.setValue(Integer.valueOf(user.getId()));

                male.add(user1);
            } else {
                user1.setName(user.getProvince());
                user1.setValue(Integer.valueOf(user.getId()));

                girl.add(user1);
            }
        }

        map.put("male", male);
        map.put("girl", girl);
        return map;
    }

    @Override
    public Map<String, String> addUser(String phone, String password) {
        HashMap<String, String> map = new HashMap<>();


        return map;
    }


}
