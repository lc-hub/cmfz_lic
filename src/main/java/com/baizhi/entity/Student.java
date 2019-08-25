package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author miion
 * @create 2019-08-20 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "student")
public class Student implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "生日", format = "yyyy-MM-dd", width = 30)
    private Date bir;
    @ExcelIgnore
    @Excel(name = "头像", width = 20, height = 20, type = 2, imageType = 1)
    private String image;
}
