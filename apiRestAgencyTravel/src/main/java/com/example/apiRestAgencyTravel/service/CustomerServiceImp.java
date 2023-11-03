package com.example.apiRestAgencyTravel.service;

import com.example.apiRestAgencyTravel.exceptions.ResourceNotFoundException;
import com.example.apiRestAgencyTravel.model.Customer;
import com.example.apiRestAgencyTravel.repository.ICustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements  ICustomerService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICustomerRepository repository;

    @Override
    public Customer createCustomer(Customer c) {
        return repository.save(c);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = repository.findAll();
        return customers;
    }

    @Override
    public Customer updateCustomer(Long id, Customer c) {
        Customer updateC = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Customer","id",id));
        updateC.setAddress(c.getAddress());
        updateC.setEmail(c.getEmail());
        updateC.setName(c.getName());
        updateC.setLastName(c.getLastName());
        updateC.setPhone(c.getPhone());
        updateC.setSex(c.getSex());
        return repository.save(updateC);
    }

    @Override
    public void removeCustomer(Long id) {
        Customer c = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Customer","id",id));
        repository.delete(c);
    }

    @Override
    public Customer findById(Long id){
        Customer c = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Customer","id",id));
        return c;
    }
}
