package com.example.demo.rest;

import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.CustomerModel;
import com.example.demo.service.CustomerService;
import com.example.demo.shared.CustomerRequestModel;
import com.example.demo.shared.CustomerResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;
    private ModelMapper modelMapper;
    @Autowired
    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseModel> createCustomer(@RequestBody CustomerRequestModel customerRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequestModel));

    }
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerResponseModel> findByCustomerId(@PathVariable("customerId") String customerId) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerRequestModel r =customerService.findByCustomerId(customerId);
        CustomerResponseModel response=modelMapper.map(r,CustomerResponseModel.class);
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Integer> deleteCustomer(@PathVariable("customerId") String customerId) {

        Integer result = customerService.deleteByCustomerId(customerId);

        return ResponseEntity.ok(result);

    }

    @PutMapping("/customers/{customerid}")
    public ResponseEntity<CustomerResponseModel> updateCustomer(@PathVariable("customerid") String customerId,
                                                    @RequestBody CustomerRequestModel customerRequestModel)
    {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerRequestModel d= customerService.updateCustomer(customerId, customerRequestModel);
        CustomerResponseModel dto= modelMapper.map(customerRequestModel, CustomerResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



}
