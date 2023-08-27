package ro.myclass.billingmanagementapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.billingmanagementapi.customer.dto.CustomerDTO;
import ro.myclass.billingmanagementapi.customer.rest.CustomerResource;
import ro.myclass.billingmanagementapi.customer.service.CustomerCommandService;
import ro.myclass.billingmanagementapi.customer.service.CustomerQuerryService;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.customer.service.CustomerImplService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CustomerResourceTest {

    @Mock
    private CustomerCommandService customerCommandService;

    @Mock
    private CustomerQuerryService customerQuerryService;

    @InjectMocks
    private CustomerResource customerResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        Faker faker = new Faker();

        List<Customer> customerList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add customer with all properties
            customerList.add(Customer.builder().id((long) i).name(faker.name().name()).build());
        }

        doReturn(customerList).when(customerQuerryService).getAllCustomers();

        restMockMvc.perform(get("/api/v1/customer/allCustomers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customerList)));
    }

    @Test
    public void getAllCustomersBadRequest() throws Exception {
        doThrow(ListEmptyException.class).when(customerQuerryService).getAllCustomers();

        restMockMvc.perform(get("/api/v1/customer/allCustomers"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCustomer() throws Exception {
        Faker faker = new Faker();

        CustomerDTO customer = CustomerDTO.builder().name(faker.name().name()).build();

        doNothing().when(customerCommandService).addCustomer(customer);
        restMockMvc.perform(post("/api/v1/customer/addCustomer").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }

    @Test
    public void addCustomerBadRequest() throws Exception {
        Faker faker = new Faker();

        CustomerDTO customer = CustomerDTO.builder().name(faker.name().name()).build();

        doThrow(ListEmptyException.class).when(customerCommandService).addCustomer(customer);
        restMockMvc.perform(post("/api/v1/customer/addCustomer").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCustomer() throws Exception {
        Faker faker = new Faker();

        String name = faker.name().name();

        doNothing().when(customerCommandService).deleteCustomer(name);
        restMockMvc.perform(delete("/api/v1/customer/deleteCustomer/{name}", name))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCustomerBadRequest() throws Exception {
        Faker faker = new Faker();

        String name = faker.name().name();

        doThrow(ListEmptyException.class).when(customerCommandService).deleteCustomer(name);
        restMockMvc.perform(delete("/api/v1/customer/deleteCustomer/{name}", name))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomerById() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).build();

        doReturn(customer).when(customerQuerryService).getCustomerById((long) 1);

        restMockMvc.perform(get("/api/v1/customer/getCustomerById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));
    }

    @Test
    public void getCustomerByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).build();

        doThrow(ListEmptyException.class).when(customerQuerryService).getCustomerById((long) 1);

        restMockMvc.perform(get("/api/v1/customer/getCustomerById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomerByEmail() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).email("email").password("password").username("username").address("address").mobile("mobile").mobile("mobile").build();

        doReturn(customer).when(customerQuerryService).getCustomerByEmail("email");

        restMockMvc.perform(get("/api/v1/customer/getCustomerByEmail/email"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));

    }

    @Test
    public void getCustomerByEmailBadRequest() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).email("email").password("password").username("username").address("address").mobile("mobile").mobile("mobile").build();

        doThrow(ListEmptyException.class).when(customerQuerryService).getCustomerByEmail("email");

        restMockMvc.perform(get("/api/v1/customer/getCustomerByEmail/email"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomerByMobile() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).email("email").password("password").username("username").address("address").mobile("mobile").mobile("mobile").build();

        doReturn(customer).when(customerQuerryService).getCustomerByMobile("mobile");

        restMockMvc.perform(get("/api/v1/customer/getCustomerByMobile/mobile"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));
    }

    @Test
    public void getCustomerByMobileBadRequest() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).email("email").password("password").username("username").address("address").mobile("mobile").mobile("mobile").build();

        doThrow(ListEmptyException.class).when(customerQuerryService).getCustomerByMobile("mobile");

        restMockMvc.perform(get("/api/v1/customer/getCustomerByMobile/mobile"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomerByUsername() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).email("email").password("password").username("username").address("address").mobile("mobile").mobile("mobile").build();

        doReturn(customer).when(customerQuerryService).getCustomerByUsername("username");

        restMockMvc.perform(get("/api/v1/customer/getCustomerByUsername/username"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));
    }

    @Test
    public void getCustomerByUsernameBadRequest() throws Exception {
        Faker faker = new Faker();

        Customer customer = Customer.builder().id((long) 1).name(faker.name().name()).email("email").password("password").username("username").address("address").mobile("mobile").mobile("mobile").build();

        doThrow(ListEmptyException.class).when(customerQuerryService).getCustomerByUsername("username");

        restMockMvc.perform(get("/api/v1/customer/getCustomerByUsername/username"))
                .andExpect(status().isBadRequest());
    }



}