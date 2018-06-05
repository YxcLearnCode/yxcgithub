package work.util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

/**
 * 用于获取字页面的地址
 *
 */
public class getUrl {
    //返回个人信息链接
    public static String getPersonal_informationlink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(1) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式，对于华软内网更加方便匹配获得网址，以下函数相同
        Pattern p=Pattern.compile("'../../../../../?");
        String[] str=p.split(stu);
        //拼接成绝对地址
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        //无法匹配最后一个点号，所以直接操控删除
        reallink=reallink.substring(0,reallink.length()-1);
        return reallink;

    };

    //返回课程表链接
    public static String getCourselink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'/?");
        String[] str=p.split(stu);
        //拼接成绝对地址
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        return reallink;
    };

    //返回考试表链接
    public static String getExaminationlink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(3) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'../../../../../?");
        String[] str=p.split(stu);
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        reallink=reallink.substring(0,reallink.length()-1);
        return reallink;
    };

    //返回考勤信息表链接
    public static String getAttendancelink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(4) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'../../../../../?");
        String[] str=p.split(stu);
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        reallink=reallink.substring(0,reallink.length()-1);
        return reallink;
    };

    //返回奖惩链接
    public static String getPunishmenlink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(2) > td:nth-child(1) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'/?");
        String[] str=p.split(stu);
        //拼接成绝对地址
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        return reallink;
    };

    //返回全校课程链接
    public static String getOffercourseslink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(3) > td:nth-child(2) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'/?");
        String[] str=p.split(stu);
        //拼接成绝对地址
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        return reallink;
    };

    //返回晚归、违规电器链接
    public static String getMistaketUrl(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(5) > td:nth-child(2) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'../../../../../?");
        String[] str=p.split(stu);
        //拼接成绝对地址
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        reallink=reallink.substring(0,reallink.length()-1);
        return reallink;
    }
    //返回平时成绩链接
    public static String getScorelink(Document main_jsp){
        Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(5) > table > tbody > tr > td");
        String stu=stuclass.attr("onclick");
        //正则表达式的匹配模式
        Pattern p=Pattern.compile("'/?");
        String[] str=p.split(stu);
        //拼接成绝对地址
        String reallink="http://class.sise.com.cn:7001/" + str[1];
        return reallink;
    };
}
