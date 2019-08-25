package com.baizhi.test;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author miion
 * @create 2019-08-14 16:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBanner {

    @Autowired
    private BannerDAO bannerDAO;

    @Test
    public void test1(){
        Banner banner = new Banner();
//        int i = bannerDAO.selectCount(banner);
//        System.out.println(i);
      //  List<Banner> banners = bannerDAO.selectByPage(1, 2, null, null, null);

       // System.out.println(banners);
    }
}
