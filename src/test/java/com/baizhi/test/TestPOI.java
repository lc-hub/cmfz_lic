package com.baizhi.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;

import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Student;
import com.baizhi.entity.Teacher;
import com.baizhi.entity.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author miion
 * @create 2019-08-20 11:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPOI {

    @Autowired
    private UserDAO userDAO;

    @Test  //将数据库中表导出 成表格
    public void testPOI(){
        List<User> users = userDAO.selectAll();
//       创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
//       创建字体
        HSSFFont font = workbook.createFont();
//       创建样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font); //将字体存到样式中
//       创建日期格式的样式
        CellStyle  cellStyle1= workbook.createCellStyle();

//        创建日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        cellStyle1.setDataFormat(format);
//        创建工作表
        HSSFSheet sheet = workbook.createSheet("user");
//        第一个参数给哪一列设置宽度  下标  第二个参数  宽度* 256
        sheet.setColumnWidth(3,20*256);
//        创建行
        Row row = sheet.createRow(0);
//        创建表头
        String [] strings = {"编号","姓名","性别","生日"};
//      遍历创建表头的单元格
        for(int i= 0;i<strings.length;i++){
//            创建单元格
            Cell cell = row.createCell(i);
//            给单元格设置样式
            cell.setCellStyle(cellStyle);
//            给单元格赋值
            cell.setCellValue(strings[i]);
        }
//        表格里的数据
        for (int i=0;i<users.size();i++){
//           创建行
            Row row1 = sheet.createRow(i + 1);
//            创建单元格 存储数据
            row1.createCell(0).setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getUsername());
            row1.createCell(2).setCellValue(users.get(i).getSex());
//            获取下标为3的单元格
            Cell cell = row1.createCell(3);
//            给单元格设置样式
              cell.setCellStyle(cellStyle1);
              cell.setCellValue(users.get(i).getCreateDate());
        }

        try {
            workbook.write(new FileOutputStream(new File("F:/user/user.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//  将表格导入到数据库
    @Test
    public void testPOIImport() {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("F:/user/user.xls")));
//            获取表
            Sheet sheet = workbook.getSheet("user");
//            获取总条数
            int lastRowNum = sheet.getLastRowNum();
          for(int i=0;i<lastRowNum;i++){
              User u = new User();
//              获取当前行
              Row row = sheet.getRow(i+1);
//              把每一行的单元格的value值赋值到对应得user字段中
              u.setId(row.getCell(0).getStringCellValue());
              u.setUsername(row.getCell(1).getStringCellValue());
              u.setSex(row.getCell(2).getStringCellValue());
              Cell cell = row.getCell(3);
             // System.out.println( row.getCell(2));

              u.setCreateDate(  cell.getDateCellValue());
               System.out.println("user:"+u);
          }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    使用EachPOI导出
    @Test
    public void testEasePoi1(){
        ArrayList<Student> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Student student = new Student(i+"","张"+i,i,new Date(),"D:\\用户目录\\我的图片\\nd.jpg");
              list.add(student);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生表"), Student.class, list);

        try {
            workbook.write(new FileOutputStream(new File("F:/user/student.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //    使用EachPOI导入
    @Test
    public void testEasePoi2(){
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();
        List<Object> list = ExcelImportUtil.importExcel(
                new File("F:/user/student.xls"),
                Student.class, params
        );
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }

//集合导出

    @Test
    public void testEasePoi3(){
        ArrayList<Student> list = new ArrayList<>();

//        创建学生集合
            for(int i=0;i<10;i++){
                Student student = new Student(""+i,"张"+i,i,new Date(),"");
                list.add(student);
            }
//        创建老师集合
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher1 = new Teacher("123123", "suns",  list,"D:\\用户目录\\我的图片\\nd.jpg");
        Teacher teacher2 = new Teacher("123123", "liucy", null,"D:\\用户目录\\我的图片\\nd.jpg");
        teachers.add(teacher1);
        teachers.add(teacher2);

//        工作表

        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("计算机一班教师及学生报表", "教师"),
                Teacher.class, teachers
        );

        try {
            sheets.write(new FileOutputStream(new File("F:/user/teacher.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
