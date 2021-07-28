package com.whut.water.service;

import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Customer;


import java.util.List;

public interface CustomerService {

    List<Customer> listCustomer();
    List<Customer> searchCustomer(String customerName);
    int deleteCustomer(Integer cid);
    int insertCustomer(Customer customer);
    int updateCustomer(Customer customer);

    /**
     * 每页数量
     */
    public final static int PAGE_SiZE = 10;

    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<Customer> listCustomerForPage(Integer pageNum );

    /**
     * 表单搜索分页
     * @param pageNum 当前页码
     * @param custName 客户名称
     * @return 分页对象
     */
    PageInfo<Customer> searchCustomer(Integer pageNum, String custName);

}
