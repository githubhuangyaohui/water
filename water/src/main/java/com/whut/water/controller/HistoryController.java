package com.whut.water.controller;

import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Customer;
import com.whut.water.entities.History;
import com.whut.water.entities.Worker;
import com.whut.water.service.CustomerService;
import com.whut.water.service.HistoryService;
import com.whut.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WorkerService workerService;

    /**
     * 获取送水历史列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/listHistory",method = RequestMethod.GET)
    public String listCustomer( Model model){
        List<History> histories = historyService.listHistory();
        model.addAttribute("historyList",histories);
        if (log.isInfoEnabled()) {
            log.info("histories = "+histories);
        }

        return "history";
    }

    /**
     * 根据日期搜索历史信息
     * @param start
     * @param end
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchHistory",method = RequestMethod.POST)
    public String searchCustomer(String start,String end, Model model){
        if(log.isDebugEnabled()){
            log.debug("start: "+start);
            log.debug("end: "+end);
        }
        List<History> histories = historyService.searchHistory(start,end);
        model.addAttribute("historyList",histories);
        return "history";
    }


    @RequestMapping("/historyListPage")
    public String historyListPage(
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model) {
        // 调用业务逻辑层，获取分页数据
        PageInfo<History> pageInfo = historyService.listHistoryForPage(pageNum);
        // 获取当前页的历史列表
        List<History> historyList = pageInfo.getList();
        // 客户列表、分页对象传入前端页面
        model.addAttribute("historyList",historyList);
        model.addAttribute("pageInfo",pageInfo);
        // 表示普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","listData");
        return "history";
    }

    /**
     * 搜索分页
     * @param searchName
     * @param model
     * @return
     */
    @RequestMapping("/searchHistoryPage")
    public String searchCustomer(
            String searchName,
            String searchName2,
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            Model model) {
        if(log.isInfoEnabled()) {
            log.info("searchHistory name = "+ searchName+":"+searchName2);
        }
        PageInfo<History> historyPageInfo = historyService.searchHistory(pageNum, searchName, searchName2);
        // 数据传入到前端
        model.addAttribute("historyList",historyPageInfo.getList());
        model.addAttribute("pageInfo",historyPageInfo);
        // 按条件搜索分页查询
        model.addAttribute("pageData","searchData");
        model.addAttribute("searchName",searchName);
        model.addAttribute("searchName2",searchName2);
        // 跳转到客户列表页面
        return "history";
    }




    /**
     * 根据日期统计薪水
     * @param start
     * @param end
     * @param model
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/getSalary",method = RequestMethod.POST)
    public List<Map> getSalary(String start, String end, Model model){
        if(log.isDebugEnabled()){
            log.debug("start: "+start);
            log.debug("end: "+end);
        }
        if(null==start||null==end){
            start="2020-01-01";
            end="2021-01-01";
        }
        List<Map> salaryMap = historyService.getSalary(start, end);
        return salaryMap;
    }

    /**
     * 获取每天统计送水量数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public List<Map> getCount(){
        List<Map> salaryMap = historyService.getCount();
        return salaryMap;
    }

    /**
     * 删除历史信息
     * @param hid
     * @return
     */
    @RequestMapping("/deleteHistory/{hid}")
    public String deleteCustomer(@PathVariable("hid")Integer hid,Model model){
        int i = historyService.deleteHistory(hid);
        if(i>0){
            model.addAttribute("successMassage","删除成功");
        }else{
            model.addAttribute("warningMassage","删除失败");
        }
        return "forward:/history/historyListPage";
    }

    /**
     * 更新历史信息
     * @param history
     * @param custId
     * @param workerId
     * @return
     */
    @RequestMapping(value = "/updateHistory",method = RequestMethod.POST)
    public String updateHistory(History history,Integer custId,Integer workerId,Model model) {

        int i = historyService.updateHistory(history,custId,workerId);
        if(i>0){
            model.addAttribute("successMassage","更新成功");
        }else{
            model.addAttribute("warningMassage","更新失败");
        }
        return "forward:/history/historyListPage";
    }

    @RequestMapping(value = "/insertHistory",method = RequestMethod.POST)
    public String insertHistory(History history,Integer custId,Integer workerId,Model model) {
        int i = historyService.insertHistory(history,custId,workerId);
        if(i>0){
            model.addAttribute("successMassage","添加成功");
        }else{
            model.addAttribute("warningMassage","添加失败");
        }
        return "forward:/history/historyListPage";
    }

    @ResponseBody
    @RequestMapping("/getCustomerList")
    public List<Customer> getCustomerList(Model model){
        List<Customer> customers = customerService.listCustomer();
        return customers;
    }


    @ResponseBody
    @RequestMapping("/getWorkerList")
    public List<Worker> getWorkerList(Model model){
        List<Worker> workers = workerService.listWorker();
        return workers;
    }
}
