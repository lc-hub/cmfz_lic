package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 安全退出 销毁session
 *
 * @author miion
 * @create 2019-08-22 15:49
 */
@RestController
@RequestMapping("safeExit")
public class SafeExitController {

    @RequestMapping("exit")
    public void exit(HttpSession session) {
        session.invalidate();
    }
}
