package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_album")
@ExcelTarget(value = "album")
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    @Excel(name = "编号", width = 20, height = 20)
    @Id
    private String id;
    @Excel(name = "标题头", width = 20, height = 20)
    private String title;
    @Excel(name = "简介", width = 20, height = 20)
    private String detail;
    @Excel(name = "作者", width = 20, height = 20)
    private String author;
    @Excel(name = "播音", width = 20, height = 20)
    private String broadcast;
    @Excel(name = "集数", width = 20, height = 20)
    private Integer count;
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20, height = 20)
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss") //后台UtilDate 转换json响应字符串格式到页面
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前台data转换后台日期格式
    private Date createDate;
    @Excel(name = "专辑图", width = 20, height = 20, type = 2, imageType = 1)
    private String cover;
    @Excel(name = "简介", width = 20, height = 20)
    private String score;
    @ExcelCollection(name = "专辑文章")
    @Transient
    private List<Chapter> list;
}