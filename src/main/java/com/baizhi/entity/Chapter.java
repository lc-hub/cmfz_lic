package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_chapter")
@ExcelTarget(value = "chapter")
public class Chapter implements Serializable {
    @Excel(name = "编号", width = 20, height = 10)
    @Id
    private String id;
    @Excel(name = "专辑ID", width = 20, height = 10)
    private String albumId;
    @Excel(name = "路径", width = 20, height = 10)
    private String audio;
    @Excel(name = "名称", width = 20, height = 10)
    private String title;
    @Excel(name = "大小", width = 20, height = 10)
    private String size;
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20, height = 10)
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss") //后台UtilDate 转换json响应字符串格式到页面
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前台data转换后台日期格式
    private Date createDate;
    @Excel(name = "时长", width = 20, height = 10)
    private String duration;

}