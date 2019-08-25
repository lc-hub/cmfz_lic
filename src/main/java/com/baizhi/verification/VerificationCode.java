package com.baizhi.verification;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @author miion
 * @create 2019-07-09 19:07
 */
@RequestMapping("/VerificationCode")
@RestController
public class VerificationCode {
    @RequestMapping("/createCaptchaImage")
    public void createCaptchaImage(HttpSession session, HttpServletResponse response) throws Exception {
        //1.生成验证码随机数
        String securityCode = SecurityCode.getSecurityCode();
        //2.将验证码保存到session中未来做登录验证
        session.setAttribute("securityCode", securityCode);
        //3.使用验证码随机数，绘制验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);

        //4.使用IO响应验证码图片
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
    }

}
