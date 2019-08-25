package com.baizhi.serviceImpl;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 轮播图业务类
 *
 * @author miion
 * @create 2019-08-14 16:26
 */
@Service
@Slf4j
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;

    //    加载后执行的分页查询
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectByPage(Integer rows, Integer page, String searchField, String searchString, String searchOper) {
//        分页
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Banner banner = new Banner();

        List<Banner> banners = bannerDAO.selectByRowBounds(banner, rowBounds);
//        获取总条数
        int count = bannerDAO.selectCount(banner);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("page", page);//当前页码
        map.put("rows", banners);// 分页查询到的数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);// 总页数
        map.put("records", count);// 总条数
        return map;

    }

    //    编辑执行的查询
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> coperBanner(String oper, Banner banner) {
        HashMap hashMap = new HashMap();

        if ("add".equals(oper)) {
//            添加
            banner.setId(UUID.randomUUID().toString().replace("-", "")); //添加uuid为主键
            banner.setCreateDate(new Date());//创建时间
            banner.setLastUpdateDate(new Date());//最后修改时间
            bannerDAO.insertSelective(banner);
            String data = banner.getId();
            hashMap.put("status", data);
        } else if ("del".equals(oper)) {
//            删除
            bannerDAO.delete(banner);
            hashMap.put("status", banner.getId());
        } else if ("edit".equals(oper)) {
//            修改
            banner.setLastUpdateDate(new Date());//最后修改时间
            if ("".equals(banner.getCover())) {
                banner.setCover(null);
            }
            int i = bannerDAO.updateByPrimaryKeySelective(banner);
            log.info("====!!!!!!!!!!=====" + i);

            hashMap.put("status", banner.getId());
        } else {
            hashMap.put("status", "error");
        }
        return hashMap;
    }

    @Override
    public String upload(Banner banner) {
        log.debug(banner + "======");
        bannerDAO.updateByPrimaryKeySelective(banner);
        return "ok";
    }
}
