package ro.myclass.billingmanagementapi.customer.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.customer.dto.CustomerDTO;
import ro.myclass.billingmanagementapi.customer.repo.CustomerRepo;
import ro.myclass.billingmanagementapi.exceptions.CustomerNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.CustomerWasFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.customer.models.Customer;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerCommandImplService implements CustomerCommandService {

    private CustomerRepo customerRepo;

    public CustomerCommandImplService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public void addCustomer(CustomerDTO customerDTO) {

        Optional<Customer> customerOptional = this.customerRepo.getCustomerByUsername(customerDTO.getUsername());

        if (customerOptional.isEmpty()) {
            Customer customer = Customer.builder().name(customerDTO.getName()).mobile(customerDTO.getMobile()).email(customerDTO.getEmail()).address(customerDTO.getAddress()).username(customerDTO.getUsername()).password(customerDTO.getPassword()).payments(customerDTO.getPayments()).build();

            this.customerRepo.save(customer);
        } else {
            throw new CustomerWasFoundException();
        }
    }

    public void deleteCustomer(String username){
        Optional<Customer> customerOptional = this.customerRepo.getCustomerByUsername(username);

        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            this.customerRepo.delete(customerOptional.get());
        }
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = this.customerRepo.getCustomerByUsername(customerDTO.getUsername());

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException();
        }
            Customer customer = customerOptional.get();

            if (customerDTO.getName() != null) {
                customer.setName(customerDTO.getName());
            }
            if (customerDTO.getMobile() != null) {
                customer.setMobile(customerDTO.getMobile());
            }
            if (customerDTO.getEmail() != null) {
                customer.setEmail(customerDTO.getEmail());
            }
            if (customerDTO.getAddress() != null) {
                customer.setAddress(customerDTO.getAddress());
            }
            if (customerDTO.getUsername() != null) {
                customer.setUsername(customerDTO.getUsername());
            }
            if (customerDTO.getPassword() != null) {
                customer.setPassword(customerDTO.getPassword());
            }
            if (customerDTO.getPayments() != null) {
                customer.setPayments(customerDTO.getPayments());
            }
            if (customerDTO.getBills() != null) {
                customer.setBills(customerDTO.getBills());


            }

        this.customerRepo.saveAndFlush(customer);


    }
}
