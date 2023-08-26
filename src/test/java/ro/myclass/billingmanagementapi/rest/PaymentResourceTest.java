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

import ro.myclass.billingmanagementapi.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.dto.UpdatePaymentRequest;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PaymentNotFoundException;
import ro.myclass.billingmanagementapi.models.Payment;
import ro.myclass.billingmanagementapi.service.PaymentService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PaymentResourceTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentResource paymentResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(paymentResource).build();
    }

    @Test
    public void getAllPayments() throws Exception {

        Faker faker = new Faker();

        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add payment with all properties
            paymentList.add(Payment.builder().id((long) i ).amount("100").description(faker.lorem().sentence()).build());
        }

        doReturn(paymentList).when(paymentService).getAllPayments();

        restMockMvc.perform(get("/api/v1/payment/allPayments"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paymentList)));
    }


    @Test
    public void getAllPaymentsBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(paymentService).getAllPayments();

        restMockMvc.perform(get("/api/v1/payment/allPayments"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPayment() throws Exception {
        Faker faker = new Faker();

        PaymentDTO payment = PaymentDTO.builder().amount("100").description(faker.lorem().sentence()).description(faker.lorem().sentence()).amount("100").build();


        doNothing().when(paymentService).addPayment(payment);

        restMockMvc.perform(post("/api/v1/payment/addPayment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk());
    }

    @Test
    public void addPaymentBadRequest() throws Exception {
        Faker faker = new Faker();

        PaymentDTO payment = PaymentDTO.builder().amount("100").description(faker.lorem().sentence()).description(faker.lorem().sentence()).amount("100").build();

        doThrow(ListEmptyException.class).when(paymentService).addPayment(payment);

        restMockMvc.perform(post("/api/v1/payment/addPayment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletePayment() throws Exception {
        Faker faker = new Faker();

        String amount = "100";
        String description = faker.lorem().sentence();

        doNothing().when(paymentService).deletePayment(amount,description);

        restMockMvc.perform(delete("/api/v1/payment/deletePayment").param("amount",amount).param("description",description))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePaymentBadRequest() throws Exception {
        Faker faker = new Faker();

        String amount = "100";
        String description = faker.lorem().sentence();

        doThrow(ListEmptyException.class).when(paymentService).deletePayment(amount,description);

        restMockMvc.perform(delete("/api/v1/payment/deletePayment").param("amount",amount).param("description",description))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePayment() throws Exception {
        Faker faker = new Faker();

        UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder().customerId(1).paymentDTO(PaymentDTO.builder().amount("100").description(faker.lorem().sentence()).description(faker.lorem().sentence()).amount("100").build()).build();

        doNothing().when(paymentService).updatePayment(updatePaymentRequest);

        restMockMvc.perform(put("/api/v1/payment/updatePayment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatePaymentRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePaymentBadRequest() throws Exception {
        Faker faker = new Faker();

        UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder().customerId(1).paymentDTO(PaymentDTO.builder().amount("100").description(faker.lorem().sentence()).description(faker.lorem().sentence()).amount("100").build()).build();

        doThrow(ListEmptyException.class).when(paymentService).updatePayment(updatePaymentRequest);

        restMockMvc.perform(put("/api/v1/payment/updatePayment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatePaymentRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPaymentById() throws Exception {
        Faker faker = new Faker();

        Payment payment = Payment.builder().id((long) 1).amount("100").description(faker.lorem().sentence()).build();

        doReturn(payment).when(paymentService).getPaymentById((long) 1);

        restMockMvc.perform(get("/api/v1/payment/getPaymentById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(payment)));
    }

    @Test
    public void getPaymentByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Payment payment = Payment.builder().id((long) 1).amount("100").description(faker.lorem().sentence()).build();

        doThrow(ListEmptyException.class).when(paymentService).getPaymentById((long) 1);

        restMockMvc.perform(get("/api/v1/payment/getPaymentById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPaymentByAmount() throws Exception {
        Faker faker = new Faker();

        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add payment with all properties
            paymentList.add(Payment.builder().id((long) i ).amount("100").description(faker.lorem().sentence()).build());
        }

        doReturn(paymentList).when(paymentService).getPaymentByAmount("100");

        restMockMvc.perform(get("/api/v1/payment/getPaymentByAmount/100"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paymentList)));
    }

    @Test
    public void getPaymentByAmountBadRequest() throws Exception {
        Faker faker = new Faker();

        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add payment with all properties
            paymentList.add(Payment.builder().id((long) i ).amount("100").description(faker.lorem().sentence()).build());
        }

        doThrow(ListEmptyException.class).when(paymentService).getPaymentByAmount("100");

        restMockMvc.perform(get("/api/v1/payment/getPaymentByAmount/100"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPaymentByAmountAndDescription() throws Exception {


        Payment payment = Payment.builder().id((long) 1).amount("100").description("description").build();
       doReturn(payment).when(paymentService).getPaymentByAmountAndDescription("100","description");

        restMockMvc.perform(get("/api/v1/payment/getPaymentByAmountAndDescription").param("amount","100").param("description","description"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(payment)));
    }

    @Test
    public void getPaymentByAmountAndDescriptionBadRequest() throws Exception {
        Faker faker = new Faker();

        doThrow(PaymentNotFoundException.class).when(paymentService).getPaymentByAmountAndDescription("100","description");

        restMockMvc.perform(get("/api/v1/payment/getPaymentByAmountAndDescription").param("amount","100").param("description","description"))
                .andExpect(status().isBadRequest());
    }





}