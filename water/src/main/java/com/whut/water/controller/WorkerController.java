package com.whut.water.controller;


import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Worker;
import com.whut.water.service.HistoryService;
import com.whut.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/worker")
public class WorkerController {


    /**
     * 服务器上传图片路径
     */
    @Value("${location}")
    private String location;

    @Autowired
    private WorkerService workerService;

    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "/searchWorker",method = RequestMethod.POST)
    public String searchWorker(String searchName, Model model){
        List<Worker> workers = workerService.searchWorker(searchName);
        model.addAttribute("workerList",workers);
        model.addAttribute("searchName",searchName);
        return "worker";
    }

    @RequestMapping(value = "/listWorker",method = RequestMethod.GET)
    public String listWorker( Model model){
        List<Worker> workers = workerService.listWorker();
        model.addAttribute("workerList",workers);
        return "worker";
    }

    @RequestMapping("/workerListPage")
    public String listWorkerForPage(
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model) {
        // 调用业务逻辑层，获取分页数据
        PageInfo<Worker> pageInfo = workerService.listWorkerForPage(pageNum);
        // 获取当前页的员工列表
        List<Worker> workerList = pageInfo.getList();
        // 客户列表、分页对象传入前端页面
        model.addAttribute("workerList",workerList);
        model.addAttribute("pageInfo",pageInfo);
        // 表示普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","listData");
        return "worker";
    }

    /**
     * 搜索分页
     * @param searchName
     * @param model
     * @return
     */
    @RequestMapping("/searchWorkerPage")
    public String searchWorkerPage(
            String searchName,
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            Model model) {
        if(log.isInfoEnabled()) {
            log.info("searchWorker name = "+ searchName);
        }
        PageInfo<Worker> pageInfo = workerService.searchWorker(pageNum,searchName);
        // 数据传入到前端
        model.addAttribute("workerList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        // 按条件搜索分页查询
        model.addAttribute("pageData","searchData");
        model.addAttribute("searchName",searchName);
        // 跳转到客户列表页面
        return "worker";
    }






    @RequestMapping(value = "/updateWorker",method = RequestMethod.POST)
    public String updateWorker(Worker worker,Model model){
        int i = workerService.updateWorker(worker);
        if(i>0){
            model.addAttribute("successMassage","更新成功");
        }else{
            model.addAttribute("warningMassage","更新失败");
        }
        return "forward:/worker/workerListPage";
    }
    @RequestMapping(value = "/insertWorker",method = RequestMethod.POST)
    public String insertWorker(Worker worker,Model model){
        int i = workerService.insertWorker(worker);
        if(i>0){
            model.addAttribute("successMassage","添加成功");
        }else{
            model.addAttribute("warningMassage","添加失败");
        }
        return "forward:/worker/workerListPage";
    }

    @RequestMapping("/deleteWorker/{wid}")
    public String deleteWorker(@PathVariable("wid")Integer wid,Model model){
        if(historyService.isHaveHistoryByWid(wid)){
            model.addAttribute("warningMassage","有记录不能删除");
        }else{
            int i = workerService.deleteWorker(wid);
            if(i>0){
                model.addAttribute("successMassage","删除成功");
            }else{
                model.addAttribute("warningMassage","删除失败");
            }
        }
        return "forward:/worker/workerListPage";
    }

    /**
     * 处理调整工资请求
     * @param wid
     * @param workerSalary
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adjustSalary",method = RequestMethod.POST)
    public String adjustSalary(Integer wid,Integer workerSalary){
        return workerService.adjustSalary(wid,workerSalary)>0 ? "success" : "fail";
    }
    /**
     * 获取工人统计数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/workerMassage",method = RequestMethod.POST)
    public List<Map> workerMassage(){
        return workerService.workerMassage();
    }
    /**
     * 获取工人统计数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchWorkerMassage",method = RequestMethod.POST)
    public List<Map> searchWorkerMassage(String searchName){
        return workerService.searchWorkerMassage(searchName);
    }
}
