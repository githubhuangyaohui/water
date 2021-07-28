package com.whut.water.controller;

import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Customer;
import com.whut.water.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/searchCustomer",method = RequestMethod.POST)
    public String searchCustomer(String searchName, Model model){
        List<Customer> customers = customerService.searchCustomer(searchName);
        model.addAttribute("customerList",customers);
        model.addAttribute("searchName",searchName);
        return "custList";
    }

    @RequestMapping(value = "/listCustomer",method = RequestMethod.GET)
    public String listCustomer( Model model){
        List<Customer> customers = customerService.listCustomer();
        model.addAttribute("customerList",customers);
        return "custList";
    }

    @RequestMapping("/customerListPage")
    public String listCustomerForPage(
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model) {
        // 调用业务逻辑层，获取分页数据
        PageInfo<Customer> pageInfo = customerService.listCustomerForPage(pageNum);
        // 获取当前页的客户列表
        List<Customer> custList = pageInfo.getList();
        // 客户列表、分页对象传入前端页面
        model.addAttribute("customerList",custList);
        model.addAttribute("pageInfo",pageInfo);
        // 表示普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","listData");
        return "customer";
    }

    /**
     * 搜索分页
     * 步骤：
     * 1 调  客户管理的搜索功能
     * 2 转
     *   将搜索的客户列表返回给前端(数据共享)
     *   跳转到客户列表页面
     * @param searchName
     * @param model
     * @return
     */
    @RequestMapping("/searchCustomerPage")
    public String searchCustomer(
            String searchName,
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            Model model) {
        if(log.isInfoEnabled()) {
            log.info("searchCustomer name = "+ searchName);
        }
        PageInfo<Customer> pageInfo = customerService.searchCustomer(pageNum,searchName);
        // 数据传入到前端
        model.addAttribute("customerList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        // 按条件搜索分页查询
        model.addAttribute("pageData","searchData");
        model.addAttribute("searchName",searchName);

        // 跳转到客户列表页面
        return "customer";
    }
    /**
     * 更新客户信息
     * @param customer
     * @return
     */
    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    public String updateCustomer(Customer customer){
        int i = customerService.updateCustomer(customer);
        return "redirect:/customer/listCustomer";
    }

    /**
     * 插入新客户
     * @param customer
     * @return
     */
    @RequestMapping(value = "/insertCustomer",method = RequestMethod.POST)
    public String insertCustomer(Customer customer){
        int i = customerService.insertCustomer(customer);
        return "redirect:/customer/listCustomer";
    }


    /**
     * 删除客户
      * @param cid
     * @return
     */
    @RequestMapping("/deleteCustomer/{cid}")
    public String deleteCustomer(@PathVariable("cid")Integer cid){
        int i = customerService.deleteCustomer(cid);
        return "redirect:/customer/listCustomer";
    }
}
