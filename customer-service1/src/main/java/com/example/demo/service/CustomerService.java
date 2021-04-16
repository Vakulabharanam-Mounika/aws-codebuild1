package com.example.demo.service;

import com.example.demo.model.CustomerModel;
import com.example.demo.shared.CustomerRequestModel;
import com.example.demo.shared.CustomerResponseModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerService {

    public CustomerResponseModel createCustomer(CustomerRequestModel customerDetails);

    public CustomerRequestModel findByCustomerId(String customerId);

    public CustomerRequestModel findByAmount(int amount);

    public Integer deleteByCustomerId(String customerId);
    public CustomerRequestModel updateCustomer(String customerId,CustomerRequestModel customerRequestModel);

    public List<CustomerModel> findByFirstName(String firstName);
}
