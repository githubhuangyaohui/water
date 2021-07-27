package com.whut.water.mapper;

import com.whut.water.entities.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO:Mapper用于在数据库表和Java实体类之间做映射，封装了数据库所有的增删改查操作，用于跟数据库交互
 *
 */
@Mapper
@Component
public interface CustomerMapper {
    /**
     获取所有客户信息
     */
    List<Customer> listCustomer();
    List<Customer> searchCustomer(@Param("customerName") String customerName);
    int updateCustomer(@Param("customer") Customer customer);
    int deleteCustomer(@Param("cid") Integer cid);
    int insertCustomer(@Param("customer") Customer customer);
}
