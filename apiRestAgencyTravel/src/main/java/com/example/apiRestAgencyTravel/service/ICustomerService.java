package com.example.apiRestAgencyTravel.service;

import com.example.apiRestAgencyTravel.model.Customer;

import java.util.List;

public interface ICustomerService {

    public Customer createCustomer(Customer c);
    public List<Customer> findAll();
    public Customer updateCustomer(Long id, Customer c);

    public Customer findById(Long id);
    public void removeCustomer(Long id);
}
