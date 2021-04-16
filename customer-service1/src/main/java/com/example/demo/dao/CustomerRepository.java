package com.example.demo.dao;

import com.example.demo.model.CustomerModel;
import com.example.demo.shared.CustomerRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {

    @Query
    public CustomerModel findByCustomerId(String customerId);

    @Query
    public CustomerModel findByAmount(int amount);

    @Query
    public Integer deleteByCustomerId(String customerId);

    @Query
    public List<CustomerModel> findByFirstName(String firstName);
}


