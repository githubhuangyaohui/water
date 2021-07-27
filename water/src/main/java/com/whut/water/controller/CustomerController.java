package com.whut.water.controller;

import com.whut.water.entities.Customer;
import com.whut.water.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    public String updateCustomer(Customer customer){
        int i = customerService.updateCustomer(customer);
        return "redirect:/customer/listCustomer";
    }
    @RequestMapping(value = "/insertCustomer",method = RequestMethod.POST)
    public String insertCustomer(Customer customer){
        int i = customerService.insertCustomer(customer);
        return "redirect:/customer/listCustomer";
    }
    @RequestMapping("/deleteCustomer/{cid}")
    public String deleteCustomer(@PathVariable("cid")Integer cid){
        int i = customerService.deleteCustomer(cid);
        return "redirect:/customer/listCustomer";
    }
}
