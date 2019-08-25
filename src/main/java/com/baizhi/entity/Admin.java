package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author miion
 * @create 2019-08-13 15:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_admin") //使用通用mapper表名默认是类名  需要使用@Table注解 指定所对应的表
public class Admin implements Serializable {

    @Id  //指定id为主键
    private String id;
    private String username;
    private String password;
}
