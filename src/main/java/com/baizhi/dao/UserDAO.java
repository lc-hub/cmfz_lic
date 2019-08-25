package com.baizhi.dao;

import com.baizhi.entity.EchartsUser;
import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDAO extends Mapper<User> {

    //  最近用户册
    List<EchartsUser> enrolCount(Integer week);

    //    用户分布
    List<User> userDistribution();

}