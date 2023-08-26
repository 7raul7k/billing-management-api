package ro.myclass.billingmanagementapi.customer.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.customer.dto.CustomerDTO;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.customer.service.CustomerImplService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerResource {

    private CustomerImplService customerImplService;

    public CustomerResource(CustomerImplService customerImplService) {
        this.customerImplService = customerImplService;
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = this.customerImplService.getAllCustomers();

        log.info("REST request to get all customers {}", customerList);

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer) {
        this.customerImplService.addCustomer(customer);

        log.info("REST request to add a customer {}", customer);

        return new ResponseEntity<>("Customer was added", HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{name}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String name) {
        this.customerImplService.deleteCustomer(name);

        log.info("REST request to delete a customer by name {}", name);

        return new ResponseEntity<>("Customer was deleted", HttpStatus.OK);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer customer = this.customerImplService.getCustomerById(id);

        log.info("REST request to get a customer by id {}", id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getCustomerByEmail/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = this.customerImplService.getCustomerByEmail(email);

        log.info("REST request to get a customer by email {}", email);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        this.customerImplService.updateCustomer(customerDTO);

        log.info("REST request to update a customer {}", customerDTO);

        return new ResponseEntity<>("Customer was updated", HttpStatus.OK);
    }

    @GetMapping("/getCustomerByAddress/{address}")
    public ResponseEntity<List<Customer>> getCustomerByAddress(@PathVariable String address) {

        List<Customer> customerList = this.customerImplService.getCustomerByAddress(address);

        log.info("REST request to get a customer by address {}", address);

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/getCustomerByMobile/{mobile}")
    public ResponseEntity<Customer> getCustomerByMobile(@PathVariable String mobile) {
        Customer customer = this.customerImplService.getCustomerByMobile(mobile);

        log.info("REST request to get a customer by mobile {}", mobile);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getCustomerByUsername/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
        Customer customer = this.customerImplService.getCustomerByUsername(username);

        log.info("REST request to get a customer by username {}", username);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }





}
