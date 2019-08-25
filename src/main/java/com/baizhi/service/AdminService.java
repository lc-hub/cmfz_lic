package com.baizhi.service;

import com.baizhi.entity.Admin;

/**
 * @author miion
 * @create 2019-08-13 15:09
 */
public interface AdminService {

    Admin login(String username, String password);
}
