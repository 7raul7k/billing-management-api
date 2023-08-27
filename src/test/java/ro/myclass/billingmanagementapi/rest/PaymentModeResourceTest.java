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
import ro.myclass.billingmanagementapi.payment.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.payment.models.Payment;
import ro.myclass.billingmanagementapi.payment.service.PaymentCommandService;
import ro.myclass.billingmanagementapi.payment.service.PaymentQuerryService;
import ro.myclass.billingmanagementapi.payment.service.PaymentService;
import ro.myclass.billingmanagementapi.paymentmode.rest.PaymentModeResource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class PaymentModeResourceTest {

    @Mock
    private PaymentCommandService paymentCommandService;

    @Mock
    private PaymentQuerryService paymentQuerryService;



    @InjectMocks
    private PaymentModeResource paymentModeResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(paymentModeResource).build();
    }

    @Test
    public void getAllPayments() throws Exception {
        Faker faker = new Faker();

        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add payment with all properties
            paymentList.add(Payment.builder().id((long) i).amount(faker.numerify("####")).build());
        }

        doReturn(paymentList).when(paymentQuerryService).getAllPayments();

        restMockMvc.perform(get("/api/v1/paymentMode/allPayments"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paymentList)));

    }

    @Test
    public void getAllPaymentsBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(paymentQuerryService).getAllPayments();

        restMockMvc.perform(get("/api/v1/paymentMode/allPayments"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPayment() throws Exception {
        Faker faker = new Faker();

        PaymentDTO payment = PaymentDTO.builder().amount(faker.numerify("####")).build();

        doNothing().when(paymentCommandService).addPayment(payment);

        restMockMvc.perform(post("/api/v1/paymentMode/addPayment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk());
    }

    @Test
    public void addPaymentBadRequest() throws Exception {
        Faker faker = new Faker();

        PaymentDTO payment = PaymentDTO.builder().amount(faker.numerify("####")).build();

        doThrow(ListEmptyException.class).when(paymentCommandService).addPayment(payment);

        restMockMvc.perform(post("/api/v1/paymentMode/addPayment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPaymentById() throws Exception {
        Faker faker = new Faker();

        Payment payment = Payment.builder().id((long) 1).amount(faker.numerify("####")).build();

        doReturn(payment).when(paymentQuerryService).getPaymentById((long) 1);

        restMockMvc.perform(get("/api/v1/paymentMode/getPaymentById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(payment)));
    }

    @Test
    public void getPaymentByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Payment payment = Payment.builder().id((long) 1).amount(faker.numerify("####")).build();

        doThrow(ListEmptyException.class).when(paymentQuerryService).getPaymentById((long) 1);

        restMockMvc.perform(get("/api/v1/paymentMode/getPaymentById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPaymentByAmount() throws Exception {
        Faker faker = new Faker();

        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add payment with all properties
            paymentList.add(Payment.builder().id((long) i).amount(faker.numerify("####")).build());
        }

        doReturn(paymentList).when(paymentQuerryService).getPaymentByAmount("amount");

        restMockMvc.perform(get("/api/v1/paymentMode/getPaymentByAmount/amount"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paymentList)));
    }

    @Test
    public void getPaymentByAmountBadRequest() throws Exception {
        Faker faker = new Faker();

        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add payment with all properties
            paymentList.add(Payment.builder().id((long) i).amount(faker.numerify("####")).build());
        }

        doThrow(ListEmptyException.class).when(paymentQuerryService).getPaymentByAmount("amount");

        restMockMvc.perform(get("/api/v1/paymentMode/getPaymentByAmount/amount"))
                .andExpect(status().isBadRequest());
    }

    




}