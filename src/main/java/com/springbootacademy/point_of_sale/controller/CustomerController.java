package com.springbootacademy.point_of_sale.controller;

import com.springbootacademy.point_of_sale.dto.CustomerDTO;
import com.springbootacademy.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springbootacademy.point_of_sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path="save_customer")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){

        customerService.saveCustomer(customerDTO);
        return "Customer Saved";
    }

    @PutMapping(path="/update_customer")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){

        String message = customerService.updateCustomer(customerUpdateDTO);
        return message;
    }

    @GetMapping(
            path="/get_by_id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    @GetMapping(
            path = "/get_all_customers"
    )
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @DeleteMapping(
            path ="/delete_customer/{id}"
    )
    public String deleteCustomer(@PathVariable (value = "id") int customerID){
        String deleted = customerService.deleteCustomer(customerID);
        return deleted;
    }

    @GetMapping(
            path="/get_customers_by_state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByState(@PathVariable (value = "status") boolean activeState){

        List<CustomerDTO> allCustomers = customerService.getAllCustomersByState(activeState);
        return allCustomers;
    }


}
