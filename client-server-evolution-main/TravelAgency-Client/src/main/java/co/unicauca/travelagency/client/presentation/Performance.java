/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.travelagency.client.presentation;

import co.unicauca.travelagency.client.access.Factory;
import co.unicauca.travelagency.client.access.ICustomerAccess;
import co.unicauca.travelagency.client.domain.services.CustomerService;
import static co.unicauca.travelagency.client.infra.Messages.successMessage;
import co.unicauca.travelagency.commons.domain.Customer;
import java.util.Scanner;

/**
 *
 * @author ahurtado
 */
public class Performance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
        ICustomerAccess service = Factory.getInstance().getCustomerService();
        // Inyecta la dependencia
        CustomerService customerService = new CustomerService(service);
        try {
            Customer customer;
            for (int i=1; i<2 ; i++){
               customer = customerService.findCustomer("9800000"+i);
               System.out.println(customer.getName());
        }
            
           
        } catch (Exception ex) {
           
        }
        
    }
    
}
