package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.customer.dto.CustomerDTO;
import ro.myclass.billingmanagementapi.customer.service.CustomerCommandService;
import ro.myclass.billingmanagementapi.customer.service.CustomerCommandImplService;
import ro.myclass.billingmanagementapi.customer.service.CustomerQuerryImplService;
import ro.myclass.billingmanagementapi.customer.service.CustomerQuerryService;
import ro.myclass.billingmanagementapi.exceptions.CustomerNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.CustomerWasFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.customer.repo.CustomerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerQuerryService customerQueryService = new CustomerQuerryImplService(customerRepo);

    @InjectMocks
    private CustomerCommandService customerCommandService = new CustomerCommandImplService(customerRepo);

    @Captor
    private ArgumentCaptor<Customer> argumentCaptor;

    @Test
    public void getAllCustomers(){


        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();
        Customer customer1 = Customer.builder().id(2L).name("test1").mobile("test1").email("test1").address("test1").username("test1").password("test1").build();
        Customer customer2 = Customer.builder().id(3L).name("test2").mobile("test2").email("test2").address("test2").username("test2").password("test2").build();
        Customer customer3 = Customer.builder().id(4L).name("test3").mobile("test3").email("test3").address("test3").username("test3").password("test3").build();

        customerRepo.save(customer);
        customerRepo.save(customer1);
        customerRepo.save(customer2);
        customerRepo.save(customer3);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        doReturn(customerList).when(customerRepo).getAllCustomer();

        assertEquals(customerList, customerQueryService.getAllCustomers());
    }


    @Test
    public void getAllCustomersException(){
        List<Customer> customerList = new ArrayList<>();

        doReturn(customerList).when(customerRepo).getAllCustomer();

        assertThrows(ListEmptyException.class, () -> customerQueryService.getAllCustomers());
    }

    @Test
    public void addCustomer(){
        CustomerDTO customerDTO = CustomerDTO.builder().name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        Customer customer = Customer.builder().name("test").mobile("test").email("test").address("test").username("test").password("test").build();


        doReturn(Optional.empty()).when(customerRepo).getCustomerByUsername(customerDTO.getUsername());

        customerCommandService.addCustomer(customerDTO);

        verify(customerRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(customer,argumentCaptor.getValue());

    }


    @Test
    public void addCustomerException(){
        CustomerDTO customerDTO = CustomerDTO.builder().name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(Customer.builder().build())).when(customerRepo).getCustomerByUsername(customerDTO.getUsername());

        assertThrows(CustomerWasFoundException.class,()-> customerCommandService.addCustomer(customerDTO));
    }

    @Test
    public void deleteCustomer(){
        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(customer)).when(customerRepo).getCustomerByUsername(customer.getUsername());

        customerCommandService.deleteCustomer(customer.getUsername());

        verify(customerRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(customer,argumentCaptor.getValue());
    }

    @Test
    public void deleteCustomerException(){

        doReturn(Optional.empty()).when(customerRepo).getCustomerByUsername("test");

        assertThrows(CustomerNotFoundException.class,()-> customerCommandService.deleteCustomer("test"));
    }

    @Test
    public void getCustomerByUsername(){
        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(customer)).when(customerRepo).getCustomerByUsername(customer.getUsername());

        assertEquals(customer, customerQueryService.getCustomerByUsername(customer.getUsername()));
    }

    @Test
    public void getCustomerByUsernameException(){

        doReturn(Optional.empty()).when(customerRepo).getCustomerByUsername("test");

        assertThrows(CustomerNotFoundException.class,()-> customerQueryService.getCustomerByUsername("test"));
    }

    @Test
    public void getCustomerById(){
        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(customer)).when(customerRepo).getCustomerById(customer.getId());

        assertEquals(customer, customerQueryService.getCustomerById(customer.getId()));
    }

    @Test
    public void getCustomerByIdException(){

        doReturn(Optional.empty()).when(customerRepo).getCustomerById(1L);

        assertThrows(CustomerNotFoundException.class,()-> customerQueryService.getCustomerById(1L));
    }

    @Test
    public void getCustomerByMobile(){
        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(customer)).when(customerRepo).getCustomerByMobile(customer.getMobile());

        assertEquals(customer, customerQueryService.getCustomerByMobile(customer.getMobile()));
    }

    @Test
    public void getCustomerByMobileException(){

        doReturn(Optional.empty()).when(customerRepo).getCustomerByMobile("test");

        assertThrows(CustomerNotFoundException.class,()-> customerQueryService.getCustomerByMobile("test"));
    }

    @Test
    public void getCustomerByEmail(){
        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(customer)).when(customerRepo).getCustomerByEmail(customer.getEmail());

        assertEquals(customer, customerQueryService.getCustomerByEmail(customer.getEmail()));
    }


    @Test
    public void getCustomerByEmailException(){

        doReturn(Optional.empty()).when(customerRepo).getCustomerByEmail("test");

        assertThrows(CustomerNotFoundException.class,()-> customerQueryService.getCustomerByEmail("test"));
    }

    @Test
    public void updateCustomer(){
        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        CustomerDTO customerDTO = CustomerDTO.builder().name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.of(customer)).when(customerRepo).getCustomerByUsername(customerDTO.getUsername());

        customerCommandService.updateCustomer(customerDTO);

        verify(customerRepo,times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(customer,argumentCaptor.getValue());
    }

    @Test
    public void updateCustomerException(){
        CustomerDTO customerDTO = CustomerDTO.builder().name("test").mobile("test").email("test").address("test").username("test").password("test").build();

        doReturn(Optional.empty()).when(customerRepo).getCustomerByUsername(customerDTO.getUsername());

        assertThrows(CustomerNotFoundException.class,()-> customerCommandService.updateCustomer(customerDTO));
    }

    @Test
    public void getCustomerByAddress(){
        //generate 4 customers with same address
        // save them in customerrepo and then  in a arraylist
        // doReturn the arraylist

        Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").build();
        Customer customer1 = Customer.builder().id(2L).name("test1").mobile("test1").email("test1").address("test1").username("test1").password("test1").build();
        Customer customer2 = Customer.builder().id(3L).name("test2").mobile("test2").email("test2").address("test2").username("test2").password("test2").build();
        Customer customer3 = Customer.builder().id(4L).name("test3").mobile("test3").email("test3").address("test3").username("test3").password("test3").build();

        customerRepo.save(customer);
        customerRepo.save(customer1);
        customerRepo.save(customer2);
        customerRepo.save(customer3);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        doReturn(customerList).when(customerRepo).getCustomerByAddress(customer.getAddress());

        assertEquals(customerList, customerQueryService.getCustomerByAddress(customer.getAddress()));


    }

    @Test
    public void getCustomerByAddressException(){
        List<Customer> customerList = new ArrayList<>();

        doReturn(customerList).when(customerRepo).getCustomerByAddress("test");

        assertThrows(ListEmptyException.class,()-> customerQueryService.getCustomerByAddress("test"));
    }
}