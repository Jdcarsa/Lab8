package com.example.apiRestAgencyTravel.controller;

import com.example.apiRestAgencyTravel.model.Customer;
import com.example.apiRestAgencyTravel.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @PostMapping({"/customers/save"})
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer c){
        return new ResponseEntity<>(service.createCustomer(c), HttpStatus.CREATED);
    }

    @GetMapping({"/customers/findAll"})
    public List<Customer> findAll (){
        return  service.findAll();
    }

    @GetMapping({"/customer/findById/{id}"})
    public Customer findById (@PathVariable(name = "id") Long id){
        return  service.findById(id);
    }

    @PutMapping({"/customers/update/{id}"})
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c
                                    ,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.updateCustomer(id, c));
    }

    @DeleteMapping({"/customers/delete/{id}"})
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
        service.removeCustomer(id);
        return new ResponseEntity<>("DELETING CUSTOMER",HttpStatus.OK);
    }
}
