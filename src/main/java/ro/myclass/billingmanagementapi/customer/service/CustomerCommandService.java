package ro.myclass.billingmanagementapi.customer.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.customer.dto.CustomerDTO;

@Service
@Transactional
public interface CustomerCommandService {

    void addCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String username);

    void updateCustomer(CustomerDTO customerDTO);
}
