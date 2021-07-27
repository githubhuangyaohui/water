package com.whut.water.service;

import com.whut.water.WaterSpringBootApplication;
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

    @Test
    public void listHistory() {
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
}