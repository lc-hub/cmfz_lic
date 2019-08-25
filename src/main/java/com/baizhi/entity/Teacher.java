package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author miion
 * @create 2019-08-20 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "teacher")
public class Teacher implements Serializable {
    @Excel(name = "编号", needMerge = true)
    private String id;
    @Excel(name = "姓名", needMerge = true)
    private String name;
    @ExcelCollection(name = "旗下弟子")
    private List<Student> students;
    @Excel(name = "头像", width = 20, height = 20, type = 2, imageType = 1, needMerge = true)
    private String image;
}
