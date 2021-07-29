package com.whut.water.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Customer;
import com.whut.water.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> listCustomer() {
        return customerMapper.listCustomer();
    }

    @Override
    public List<Customer> searchCustomer(String customerName) {
        return customerMapper.searchCustomer(customerName);
    }

    @Override
    public Customer getCustomerByCid(Integer cid) {
        return customerMapper.getCustomerByCid(cid);
    }

    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public int deleteCustomer(Integer cid) {
        return customerMapper.deleteCustomer(cid);
    }

    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public int insertCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    public PageInfo<Customer> listCustomerForPage(Integer pageNum) {
        // 分页的核心：从第pageNum页开始，每页显示3条记录
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<Customer> list = this.listCustomer();
        // 分页Bean，封装了分页查询的数据，将查询结果注入到分页对象(Bean)
        PageInfo<Customer> pageInfo =  new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Customer> searchCustomer(Integer pageNum, String custName) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<Customer> custList = this.searchCustomer(custName);
        PageInfo<Customer> pageInfo = new PageInfo<>(custList);
        return pageInfo;
    }
}
