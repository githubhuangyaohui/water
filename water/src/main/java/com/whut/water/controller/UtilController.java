package com.whut.water.controller;

import com.whut.water.entities.History;
import com.whut.water.entities.Worker;
import com.whut.water.service.HistoryService;
import com.whut.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/util")
public class UtilController {

    @Autowired
    WorkerService workerService;

    @Autowired
    HistoryService historyService;

    /**
     * 返回首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String indexCharts(Model model){
        return "waterMainMenu";
    }

    /**
     * 测试界面
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        return "test";
    }


    @RequestMapping("/salary")
    public String salary(){
        return "salary";
    }

    @RequestMapping("/count")
    public String count(){
        return "count";
    }

    /**
     * 获取送水数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCount")
    public Map<Date,Integer> getCount(){
        List<History> history = historyService.listHistory();
        Map<Date,Integer> map = new LinkedHashMap<>();
        for (History his : history) {
            Integer integer = map.get(his.getSendWaterTime());
            if(integer==null||integer==0){
                map.put(his.getSendWaterTime(),his.getSendWaterCount());
            }else{
                map.replace(his.getSendWaterTime(),his.getSendWaterCount()+integer);
            }
        }
        Set<Map.Entry<Date, Integer>> set = map.entrySet();
        Iterator<Map.Entry<Date, Integer>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry<Date, Integer> entry = iterator.next();
            Date key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key:" + key + ", value:" + value);
        }
        return map;
    }

    //时间格式化
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    //图片上传路径
    @Value("${location}")
    private String fileSavePath;


    @ResponseBody
    @PostMapping("/uploadPic")
    public String coversUpload(MultipartFile uploadFile, HttpServletRequest request) {
        log.info("开始上传图片");
        //1.后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        /**
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("图片上传，保存位置：" + fileSavePath + directory);
        //拼接生成文件名
        String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase();;
        String oldName = uploadFile.getOriginalFilename();
        log.info("上传文件的原始名称为：" + oldName);
        if (oldName == null) {
            return null;
        }
        fileName = fileName + oldName.substring(oldName.lastIndexOf("."));
        //创建上传文件
        File f = new File(fileSavePath + directory + fileName);
        //上传文件
        try {
            uploadFile.transferTo(f);
            //返回的图片url
            String imgURL = request.getScheme()
                    + "://" + request.getServerName()
                    + ":" + request.getServerPort()
                    + "/"+ directory + fileName;

            log.info("上传成功" + imgURL);
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            log.info("上传失败");
            return null;
        }
    }
}
