package com.baizhi.service;

import com.baizhi.entity.EchartsUser2;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-16 20:49
 */
public interface UserService {


    //   登录
    User userLogin(String username);

    //    分页查询
    Map<String, Object> selectAllByPage(Integer page, Integer rows);

    //    添加
    String add(User album);

    //    编辑
    String edit(User album);

    //    删除
    void del(User album);

    //    获取第一周的注册人数
    Map<Object, Object> enrolCount();

    //    用户分布
    Map<String, List<EchartsUser2>> userDistribution();

    //接口文档验证
    Map<String, String> addUser(String phone, String password);
}