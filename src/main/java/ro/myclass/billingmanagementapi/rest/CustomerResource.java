package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.CustomerDTO;
import ro.myclass.billingmanagementapi.models.Customer;
import ro.myclass.billingmanagementapi.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerResource {

    private CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = this.customerService.getAllCustomers();

        log.info("REST request to get all customers {}", customerList);

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(CustomerDTO customer) {
        this.customerService.addCustomer(customer);

        log.info("REST request to add a customer {}", customer);

        return new ResponseEntity<>("Customer was added", HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{name}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String name) {
        this.customerService.deleteCustomer(name);

        log.info("REST request to delete a customer by name {}", name);

        return new ResponseEntity<>("Customer was deleted", HttpStatus.OK);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(long id) {
        Customer customer = this.customerService.getCustomerById(id);

        log.info("REST request to get a customer by id {}", id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getCustomerByName/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(String email) {
        Customer customer = this.customerService.getCustomerByEmail(email);

        log.info("REST request to get a customer by email {}", email);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(CustomerDTO customerDTO) {
        this.customerService.updateCustomer(customerDTO);

        log.info("REST request to update a customer {}", customerDTO);

        return new ResponseEntity<>("Customer was updated", HttpStatus.OK);
    }

    @GetMapping("/getCustomerByAddress/{address}")
    public ResponseEntity<List<Customer>> getCustomerByAddress(String address) {

        List<Customer> customerList = this.customerService.getCustomerByAddress(address);

        log.info("REST request to get a customer by address {}", address);

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    

}
