package ro.myclass.billingmanagementapi.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c where c.id = ?1")
    Optional<Customer> getCustomerById(long id);

    @Query("select c from Customer c where c.name = ?1")
    Optional<Customer> getCustomerByName(String name);

    @Query("select c from Customer c where c.mobile = ?1")
    Optional<Customer> getCustomerByMobile(String mobile);

    @Query("select c from Customer c where c.email = ?1")
    Optional<Customer> getCustomerByEmail(String email);

    @Query("select c from Customer c where c.address = ?1")
    List<Customer> getCustomerByAddress(String adress);

    @Query("select c from Customer c where c.username = ?1")
    Optional<Customer> getCustomerByUsername(String username);

    @Query("select c from Customer c")
    List<Customer> getAllCustomer();



}
