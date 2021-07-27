package com.whut.water.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            start="2000-01-01";
            end="2030-01-01";
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
    public String deleteCustomer(@PathVariable("hid")Integer hid){
        int i = historyService.deleteHistory(hid);
        return "redirect:/history/listHistory";
    }

    /**
     * 更新历史信息
     * @param history
     * @param custId
     * @param workerId
     * @return
     */
    @RequestMapping(value = "/updateHistory",method = RequestMethod.POST)
    public String updateHistory(History history,Integer custId,Integer workerId) {
//        if (log.isInfoEnabled()) {
//            log.info("updateHistory "+history);
//            log.info("workerId = "+workerId);
//            log.info("custId = "+custId);
//        }
        int rows = historyService.updateHistory(history,custId,workerId);
//        if (log.isInfoEnabled()) {
//            log.info("update history rows = "+rows);
//        }
        return "redirect:/history/listHistory";
    }

    @RequestMapping(value = "/insertHistory",method = RequestMethod.POST)
    public String insertHistory(History history,Integer custId,Integer workerId) {
//        if (log.isInfoEnabled()) {
//            log.info("updateHistory "+history);
//            log.info("workerId = "+workerId);
//            log.info("custId = "+custId);
//        }
        int rows = historyService.insertHistory(history,custId,workerId);
//        if (log.isInfoEnabled()) {
//            log.info("insert history rows = "+rows);
//        }
        return "redirect:/history/listHistory";
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
