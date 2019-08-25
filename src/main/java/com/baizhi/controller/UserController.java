package com.baizhi.controller;

import com.baizhi.Util.Md5Util;
import com.baizhi.api.UtilApiService;
import com.baizhi.entity.EchartsUser2;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author miion
 * @create 2019-08-17 16:45
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends UtilApiService {

    @Autowired
    private UserService userService;

    //    登录
    @RequestMapping("userLogin")
    public Map<String, Object> userLogin(HttpSession session, String username, String password) {
        User user = userService.userLogin(username);
        if (user != null) {
//            通过用户输入的密码加上数据库存储得盐获取加密后的密码
            String pwd = Md5Util.getMd5Code(user.getSalt() + password);
//             判断用户输入加密后的密码和用户数据库存的密码是否一致
            if (user.getPassword().equals(pwd)) {
                session.setAttribute("user", user);
                return setResultSuccess();
            } else {
                return setResultMsg("密码不正确");
            }
        } else {
            return setResultMsg("用户名不正确");
        }
    }


    //    分页
    @RequestMapping("/byPage")
    public Map<String, Object> selectByPage(Integer page, Integer rows) {

        Map<String, Object> map = userService.selectAllByPage(page, rows);
        return map;
    }

    // 编辑方法
    @RequestMapping("/edit")
    public Map<String, Object> edit(String oper, User user) {
        if ("add".equals(oper)) {
//            执行添加
            log.debug("controller添加");
            String add = userService.add(user);
//            返回id
            return setResultSuccessData(add);
        }
        if ("edit".equals(oper)) {
            log.debug("controller修改");

            String edit = userService.edit(user);
//            返回id
            return setResultSuccessData(edit);
        }
        if ("del".equals(oper)) {
            userService.del(user);
        }
//        触发 goeasy  实时个更新数据
        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-e61b09f5c5dd45648d84b4d694e8f51a");
        goEasy.publish("test", "Hello GoEasy");

//        返回成功
        return setResultSuccess();
    }

    //     上传文件
    @RequestMapping("/upload")
    public void upload(MultipartFile photo, String id, HttpSession session) throws IOException {

        if (!"".equals(photo.getOriginalFilename())) {
//          通过相对路径获取绝路径
            String realPath = session.getServletContext().getRealPath("view/user/photo");
//            回去文件的原始名
            String name = photo.getOriginalFilename();
//            上传文件
            photo.transferTo(new File(realPath, name));
//修改数据库中的字段
            User user = new User();
            user.setId(id);
            user.setPhoto(name);
            userService.edit(user);
        }
    }

    //echarts 近期注册人数
    @RequestMapping("/enrolCount")
    public Map<Object, Object> enrolCount() {
        Map<Object, Object> map = userService.enrolCount();
        return map;
    }

//    用户地区分布

    @RequestMapping("/userDistribution")
    public Map<String, List<EchartsUser2>> userDistribution() {

        Map<String, List<EchartsUser2>> map = userService.userDistribution();
        return map;


    }
}
