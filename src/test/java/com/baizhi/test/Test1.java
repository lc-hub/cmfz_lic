package com.baizhi.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

新2222轮测试
 * @author miion
 * @create 2019-08-19 22:47
 */
public class Test1 {


    @Test
    public  void test1(){
//        String str = "Ab2Ad3A4";
//        Pattern pattern = Pattern.compile("A");
//        Matcher findMatcher = pattern.matcher(str);
//        int number = 0;
//        while(findMatcher.find()) {
//            number++;
//            if(number == 2){//当“A”第二次出现时停止
//                break;
//            }
//        }
//
//
//        int i = findMatcher.start();//“A”第二次出现的位置
//

        String s ="<h1>这是h1标签</h1><p></p>";
//        要截取的字符
        Pattern p = Pattern.compile(">");
        Matcher m = p.matcher(s);
        int count =0;
        while(m.find()){
            count ++;
//            第二次出现跳出循环

            new Test1();
            if(count==2){
                break;
            }
        }
        int start = m.start();
        //结果 <h1>这是h1标签</h1>
        System.out.println( s.substring(s.indexOf("<"),start+1));
    }
}
