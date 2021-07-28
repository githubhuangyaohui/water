package com.whut.water.controller;


import com.whut.water.entities.Worker;
import com.whut.water.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/updateWorker",method = RequestMethod.POST)
    public String updateWorker(Worker worker){
        int i = workerService.updateWorker(worker);
        return "redirect:/worker/listWorker";
    }
    @RequestMapping(value = "/insertWorker",method = RequestMethod.POST)
    public String insertWorker(Worker worker){
        int i = workerService.insertWorker(worker);
        return "redirect:/worker/listWorker";
    }
    @RequestMapping("/deleteWorker/{wid}")
    public String deleteWorker(@PathVariable("wid")Integer wid){
        int i = workerService.deleteWorker(wid);
        return "redirect:/worker/listWorker";
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
