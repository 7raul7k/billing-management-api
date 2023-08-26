package ro.myclass.billingmanagementapi.customer.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.customer.models.Customer;

import java.util.List;

@Service

public interface CustomerQuerryService {

    List<Customer> getAllCustomers();

    Customer getCustomerByUsername(String username);

    Customer getCustomerById(long id);

    Customer getCustomerByMobile(String mobile);

    Customer getCustomerByEmail(String email);

    List<Customer> getCustomerByAddress(String address);


}
