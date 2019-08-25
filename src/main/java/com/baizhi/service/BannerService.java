package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

/**
 * @author miion
 * @create 2019-08-14 16:22
 */
public interface BannerService {

    /**
     * @param begin        分页起始条数
     * @param end          结束条数
     * @param searchField  模糊查询的字段
     * @param searchString 查询的值
     * @param searchOper   查询的条件  ==  还是 ！=
     * @return
     */
//   首页加载分页查询
    public Map<String, Object> selectByPage(Integer begin, Integer end, String searchField, String searchString, String searchOper);

    //   编辑模式下提交的方法
    public Map<String, Object> coperBanner(String oper, Banner banner);

    //    上传文件后执行修改
    String upload(Banner banner);
}
