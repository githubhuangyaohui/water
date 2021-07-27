package com.whut.water.service;

import com.whut.water.entities.Customer;


import java.util.List;

public interface CustomerService {

    List<Customer> listCustomer();
    List<Customer> searchCustomer(String customerName);
    int deleteCustomer(Integer cid);
    int insertCustomer(Customer customer);
    int updateCustomer(Customer customer);
}
