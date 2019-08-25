package com.baizhi.test;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baizhi.CmfzApplication;
import com.baizhi.dao.AdminDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.EchartsUser2;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-13 15:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class TestUser {
    @Autowired
    private UserService users;
    @Test
    public void testUser(){
//        Map<String, List<EchartsUser2>> map = users.userDistribution();

//        System.out.println(map);
        short s =130;
        byte b = (byte) s;
        System.out.println(b);


    }

    @Test
    public void testALiYun(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI2FcrDQTiiWYf", "72sMwaP6iwVAnTBDFjeFpROlGgUm26");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "16604500087");
        request.putQueryParameter("SignName", "黑色星期四");
        request.putQueryParameter("TemplateCode", "SMS_172737999");
        request.putQueryParameter("TemplateParam", "{\"code\":\"666666\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
