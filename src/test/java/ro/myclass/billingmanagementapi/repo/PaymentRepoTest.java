package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.payment.models.Payment;
import ro.myclass.billingmanagementapi.payment.repo.PaymentRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class PaymentRepoTest {

    @Autowired
    PaymentRepo paymentRepo;



    @BeforeEach
    public void clean(){this.paymentRepo.deleteAll();}

    @Test
    public void getAllPayements(){
        Payment payment = Payment.builder().id(1L).amount("100").date(LocalDate.now()).description("test").build();
        Payment payment2 = Payment.builder().id(2L).amount("200").date(LocalDate.now()).description("test2").build();
        Payment payment3 = Payment.builder().id(3L).amount("300").date(LocalDate.now()).description("test3").build();
        Payment payment4 = Payment.builder().id(4L).amount("400").date(LocalDate.now()).description("test4").build();

        paymentRepo.save(payment);
        paymentRepo.save(payment2);
        paymentRepo.save(payment3);
        paymentRepo.save(payment4);

        List<Payment> paymentList = new ArrayList<>();

        paymentList.add(payment);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);

        assertEquals(paymentList,this.paymentRepo.getAllPayment());

    }

    @Test
    public void getPaymentById(){

        Payment payment = Payment.builder().id(1L).amount("100").date(LocalDate.now()).description("test").build();

        paymentRepo.save(payment);

        assertEquals(payment,this.paymentRepo.getPaymentById(1L).get());

    }

    @Test
    public void getPaymentByAmount(){
        Payment payment = Payment.builder().id(1L).amount("100").date(LocalDate.now()).description("test").build();
        Payment payment2 = Payment.builder().id(2L).amount("100").date(LocalDate.now()).description("test2").build();
        Payment payment3 = Payment.builder().id(3L).amount("100").date(LocalDate.now()).description("test3").build();
        Payment payment4 = Payment.builder().id(4L).amount("100").date(LocalDate.now()).description("test4").build();

        paymentRepo.save(payment);
        paymentRepo.save(payment2);
        paymentRepo.save(payment3);
        paymentRepo.save(payment4);

        List<Payment> paymentList = new ArrayList<>();

        paymentList.add(payment);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);

        assertEquals(paymentList,this.paymentRepo.getPaymentByAmount("100"));
    }

    @Test
    public void getPaymentByAmountAndDescription(){

        Payment payment = Payment.builder().id(1L).amount("100").description("test").date(LocalDate.now()).build();

        paymentRepo.save(payment);

        assertEquals(payment,this.paymentRepo.getPaymentByAmountAndDescription("100","test").get());


    }




}