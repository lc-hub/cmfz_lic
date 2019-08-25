package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author miion
 * @create 2019-08-13 15:51
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {


    private static Logger logger = Logger.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    /**
     * @param session
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @return
     */
//    管理员登录方法
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password, String code) {

        logger.debug(username);
        logger.debug(password);
        logger.debug(code);

//        获取系统生成的验证码
        String imageCode = (String) session.getAttribute("securityCode");
        logger.debug("系统生成的验证码" + imageCode);


        if (imageCode.equals(code)) {
            //            调用业务
            Admin admin = adminService.login(username, password);
            if (admin != null) {
                if (admin.getPassword().equals(password)) {
                    // 将当前管理员存到session
                    session.setAttribute("admin", admin);
                    return "ok";
                } else {
                    return "密码错误~";
                }

            } else {
                return "账号不正确~";
            }
        } else {
            return "验证码不正确~";
        }
    }
}
