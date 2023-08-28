package ro.myclass.billingmanagementapi.customer.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.customer.repo.CustomerRepo;
import ro.myclass.billingmanagementapi.exceptions.CustomerNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerQuerryImplService implements CustomerQuerryService {

    private CustomerRepo customerRepo;

    public CustomerQuerryImplService(CustomerRepo customerRepo) {
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

}
