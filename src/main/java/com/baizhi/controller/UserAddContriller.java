package com.baizhi.controller;

import com.baizhi.api.UtilApiService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户注册注册的
 *
 * @author miion
 * @create 2019-08-22 18:42
 * 入参
 * 手机号
 * 密码
 * <p>
 * 出参
 * uuid
 * md5加密后的密码
 * 手机号
 */
@RestController
@RequestMapping("/addUser")
public class UserAddContriller extends UtilApiService {

    //    响应 200
    final String RES_HTTP_CODE_200_VALUE = "200";
    //        响应 500
    final String RES_HTTP_CODE_500_VALUE = "500";
    //     响应信息
    final String RES_HTTP_CODEMSG = "msg";
    //响应 成功
    String RES_HTTP_CODE_SUCCESS = "success";
    //响应  错误
    String RES_HTTP_CODE_ERROR = "error";
    //响应  code
    String RES_HTTP_CODE_NAME = "code";
    @Autowired
    private UserService userService;

    @RequestMapping
    public Map<String, String> addUser(String phone, String password) {
        Map<String, String> map = new HashMap<>();
        try {
//            直接返回
            map = userService.addUser(phone, password);
            map.put(RES_HTTP_CODE_NAME, RES_HTTP_CODE_200_VALUE);
            map.put(RES_HTTP_CODEMSG, RES_HTTP_CODE_SUCCESS);
            return map;
        } catch (Exception e) {
            map.put(RES_HTTP_CODE_ERROR, RES_HTTP_CODE_500_VALUE);
            map.put(RES_HTTP_CODEMSG, e.getMessage());
            return map;
        }

    }
}
