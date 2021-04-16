package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.CustomerModel;
import com.example.demo.shared.CustomerRequestModel;
import com.example.demo.shared.CustomerResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
public class CustomerServiceImpl implements CustomerService{
    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseModel createCustomer(CustomerRequestModel customerDetails) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerModel model=modelMapper.map(customerDetails,CustomerModel.class);
        model.setCustomerId(UUID.randomUUID().toString());
        CustomerModel response=customerRepository.save(model);
        return modelMapper.map(response,CustomerResponseModel.class);
    }

    @Override
    public CustomerRequestModel findByCustomerId(String customerId) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerModel model=customerRepository.findByCustomerId(customerId);
        CustomerRequestModel r=modelMapper.map(model, CustomerRequestModel.class);
        return r;

    }

    @Override
    public CustomerRequestModel findByAmount(int amount) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerModel model=customerRepository.findByAmount(amount);
        CustomerRequestModel r=modelMapper.map(model, CustomerRequestModel.class);
        return r;
    }

    @Override
    @Transactional
    public Integer deleteByCustomerId(String customerId) {
        return customerRepository.deleteByCustomerId(customerId);
    }
    @Override
    @Transactional
    public CustomerRequestModel updateCustomer(String customerId,CustomerRequestModel customerRequestModel)
    {
        CustomerModel customerModel=customerRepository.findByCustomerId(customerId);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerModel model=modelMapper.map(customerModel, CustomerModel.class);
        customerModel.setFirstName(model.getFirstName());
        customerModel.setLastName(model.getLastName());
        customerModel.setAmount(model.getAmount());
        customerRepository.save(model);
        CustomerRequestModel c=modelMapper.map(customerModel, CustomerRequestModel.class);
        return c;
    }

    @Override
    public List<CustomerModel> findByFirstName(String firstName) {
        List<CustomerModel> c=customerRepository.findByFirstName(firstName);
        return c;
    }
}
