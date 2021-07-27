package com.whut.water.service;

import com.whut.water.entities.Customer;
import com.whut.water.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int deleteCustomer(Integer cid) {
        return customerMapper.deleteCustomer(cid);
    }

    @Override
    public int insertCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }
}
