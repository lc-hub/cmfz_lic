package com.baizhi.test;

import com.baizhi.CmfzApplication;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author miion
 * @create 2019-08-22 13:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class TestGoeasy {


    @Test
    public void testGoEasy() {
        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-e61b09f5c5dd45648d84b4d694e8f51a");
            goEasy.publish("test","Hello GoEasy");
    }
}
