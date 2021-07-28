package com.whut.water.service;

import com.whut.water.WaterSpringBootApplication;
import com.whut.water.entities.Customer;
import com.whut.water.entities.History;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterSpringBootApplication.class)
public class HistoryServiceTest {

    @Autowired
    HistoryService historyService;

    @Autowired
    CustomerService customerService;

    @Test
    public void addHistory() {
//        Integer[] wid={1,2,3,4,6,7,9,83,84,85,86,87,88,89,90};
//        Integer[] cid={1,2,3,4,19,20,21};
//        //日期格式化
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            //起始日期
//            Date d1 = sdf.parse("2000-01-01");
//            //结束日期
//            Date d2 = sdf.parse("2020-01-01");
//            Date tmp=d1;
//            Calendar dd = Calendar.getInstance();
//            dd.setTime(d1);
//            //打印2001年10月1日到2001年11月4日的日期
//            while(tmp.getTime()<=d2.getTime()) {
//                tmp=dd.getTime();
//                Integer wids=wid[(int) (Math.random()*wid.length)];
//                Integer cids=cid[(int) (Math.random()*cid.length)];
//                Integer count=(int) (Math.random()*100);
//                System.out.print(sdf.format(tmp)+":");
//                System.out.print(wids+"+"+cids);
//                System.out.print("+"+count);
//                System.out.println();
//                History history = new History();
//                history.setSendWaterTime(tmp);
//                history.setSendWaterCount(count);
//                historyService.insertHistory(history,cids,wids);
//                //天数加上1
//                dd.add(Calendar.DAY_OF_MONTH, 1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void addCustomer(){
//        String[] name={
//                "宋江", "卢俊义", "吴用", "公孙胜", "关胜", "林冲", "秦明", "呼延灼",
//                "花荣",
//                "柴进",
//                "李应",
//                "朱仝",
//                "鲁智深",
//                "武松",
//                "董平",
//                "张清",
//                "杨志",
//                "徐宁",
//                "索超",
//                "戴宗",
//                "刘唐",
//                "李逵 ",
//                "史进",
//                "穆弘",
//                "雷横",
//                "李俊",
//                "阮小二",
//                "张横",
//                "阮小五",
//                "张顺",
//                "阮小七",
//                "杨雄",
//                "石秀",
//                "解珍",
//                "解宝",
//                "燕青",
//                "朱武",
//                "黄信",
//                "孙立",
//                "宣赞",
//                "郝思文",
//                "韩滔",
//                "彭玘",
//                "单廷圭",
//                "魏定国",
//                "萧让",
//                "裴宣",
//                "欧鹏",
//                "邓飞",
//                "燕顺",
//                "杨林",
//                "凌振",
//                "蒋敬",
//                "吕方",
//                "郭盛",
//                "安道全",
//                "皇甫端",
//                "王英",
//                "扈三娘",
//                "鲍旭",
//                "樊瑞",
//                "孔明",
//                "孔亮",
//                "项充",
//                "李衮",
//                "金大坚",
//                "马麟",
//                "童威",
//                "童猛",
//                "孟康",
//                "侯健",
//                "陈达",
//                "杨春",
//                "郑天寿",
//                "陶宗旺",
//                "宋清",
//                "乐和",
//                "龚旺",
//                "丁得孙",
//                "穆春",
//                "曹正",
//                "宋万",
//                "杜迁",
//                "薛永",
//                "李忠",
//                "周通",
//                "汤隆",
//                "杜兴",
//                "邹渊",
//                "邹润",
//                "朱贵",
//                "朱富",
//                "施恩",
//                "蔡福",
//                "蔡庆",
//                "李立",
//                "李云",
//                "焦挺",
//                "石勇",
//                "孙新",
//                "顾大嫂",
//                "张青",
//                "孙二娘",
//                "王定六",
//                "郁保四",
//                "白胜",
//                "时迁",
//                "段景住"};
//        String[] address={
//                "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省","江苏省",
//                "浙江省" , "安徽省", "福建省", "江西省", "山东省", "河南省",
//                "湖北省" ," 湖南省" , "广东省" , "海南省", "四川省", "贵州省",
//                "云南省" , "陕西省" , "甘肃省","青海省",  "台湾省",
//                "内蒙古自治区","广西壮族自治区", "西藏自治区", "宁夏回族自治区" ," 新疆维吾尔自治区",
//                "北京市" , "天津市", "上海市" , "重庆市","香港特别行政区","澳门特别行政区"
//        };
//        Integer[] ticket={10,20,30,50,100,200,500};
//        for (String s : name) {
//            Customer customer= new Customer();
//            customer.setCustName(s);
//            customer.setCustAddress(address[(int) (Math.random()*address.length)]);
//            customer.setCustMobile("1234567890");
//            customer.setCustTicket(ticket[(int) (Math.random()*ticket.length)]);
//            customerService.insertCustomer(customer);
//        }
    }
}