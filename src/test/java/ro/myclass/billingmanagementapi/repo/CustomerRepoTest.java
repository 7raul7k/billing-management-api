package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.models.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @BeforeEach
    public void clean(){this.customerRepo.deleteAll();}

    @Test
    public void getAllCustomer(){
        Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();
        Customer customer2 = Customer.builder().id(2L).email("test2@gmail.com").mobile("1234567890").name("test2").address("test2").password("test2test2").username("test2").build();
        Customer customer3 = Customer.builder().id(3L).email("test3@gmail.com").mobile("1234567890").name("test3").address("test3").password("test3test3").username("test3").build();
        Customer customer4 = Customer.builder().id(4L).email("test4@gmail.com").mobile("1234567890").name("test4").address("test4").password("test4test4z").username("test4").build();

        customerRepo.save(customer);
        customerRepo.save(customer2);
        customerRepo.save(customer3);
        customerRepo.save(customer4);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);

        assertEquals(customerList,customerRepo.getAllCustomer());
    }

    @Test
    public void getCustomerById(){
        Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();

        customerRepo.save(customer);

        assertEquals(customer,this.customerRepo.getCustomerById(1).get());
    }

    @Test
    public void getCustomerByName(){
            Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();

            customerRepo.save(customer);

        assertEquals(customer,this.customerRepo.getCustomerByName("test").get());

    }

    @Test
    public void getCustomerByMobile(){

        Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();

        customerRepo.save(customer);

        assertEquals(customer,this.customerRepo.getCustomerByMobile("1234567890").get());

    }


    @Test
    public void getCustomerByEmail(){
        Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();

        customerRepo.save(customer);

        assertEquals(customer,this.customerRepo.getCustomerByEmail("test@gmail.com").get());
    }

    @Test
    public void getCustomerByUsername(){

        Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();

        customerRepo.save(customer);

        assertEquals(customer,this.customerRepo.getCustomerByUsername("test1").get());

    }

    @Test
    public void getCustomerByAdress(){
        Customer customer = Customer.builder().id(1L).email("test@gmail.com").mobile("1234567890").name("test").address("test").password("testtest").username("test1").build();
        Customer customer2 = Customer.builder().id(2L).email("test2@gmail.com").mobile("1234567890").name("test2").address("test").password("test2test2").username("test2").build();
        Customer customer3 = Customer.builder().id(3L).email("test3@gmail.com").mobile("1234567890").name("test3").address("test").password("test3test3").username("test3").build();
        Customer customer4 = Customer.builder().id(4L).email("test4@gmail.com").mobile("1234567890").name("test4").address("test").password("test4test4z").username("test4").build();

        customerRepo.save(customer);
        customerRepo.save(customer2);
        customerRepo.save(customer3);
        customerRepo.save(customer4);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);

        assertEquals(customerList,this.customerRepo.getCustomerByAddress("test"));
    }

}