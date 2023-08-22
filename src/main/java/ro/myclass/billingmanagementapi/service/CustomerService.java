package ro.myclass.billingmanagementapi.service;

import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.dto.CustomerDTO;
import ro.myclass.billingmanagementapi.exceptions.CustomerNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.CustomerWasFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.models.Customer;
import ro.myclass.billingmanagementapi.repo.CustomerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customerList = this.customerRepo.getAllCustomer();

        if(customerList.isEmpty()){
            throw new ListEmptyException();
        }else{
            return customerList;
        }
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

    public Customer getCustomerByUsername(String username){
        Optional<Customer> customerOptional = this.customerRepo.getCustomerByUsername(username);

        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            return customerOptional.get();
        }
    }

    public Customer getCustomerById(long id){
        Optional<Customer> customerOptional = this.customerRepo.getCustomerById(id);

        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            return customerOptional.get();
        }
    }

    public Customer getCustomerByMobile(String mobile){
        Optional<Customer> customerOptional = this.customerRepo.getCustomerByMobile(mobile);

        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            return customerOptional.get();
        }
    }

    public Customer getCustomerByEmail(String email){
        Optional<Customer> customerOptional = this.customerRepo.getCustomerByEmail(email);

        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            return customerOptional.get();
        }
    }

    public List<Customer> getCustomerByAddress(String address){
        List<Customer> customerList = this.customerRepo.getCustomerByAddress(address);

        if(customerList.isEmpty()){
            throw new ListEmptyException();
        }else{
            return customerList;
        }
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = this.customerRepo.getCustomerByUsername(customerDTO.getUsername());

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
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

                this.customerRepo.save(customer);
            }
        }

    }
}
