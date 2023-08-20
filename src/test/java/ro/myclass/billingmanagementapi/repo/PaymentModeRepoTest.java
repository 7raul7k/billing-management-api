package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.models.PaymentMode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class PaymentModeRepoTest {

    @Autowired
    PaymentModeRepo paymentModeRepo;

    @BeforeEach
    public void clean(){ this.paymentModeRepo.deleteAll();}

    @Test
    public void getAllPaymentModes(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).description("test").name("test").type("test").build();
        PaymentMode paymentMode2 = PaymentMode.builder().id(2L).description("test2").name("test2").type("test2").build();
        PaymentMode paymentMode3 = PaymentMode.builder().id(3L).description("test3").name("test3").type("test3").build();
        PaymentMode paymentMode4 = PaymentMode.builder().id(4L).description("test4").name("test4").type("test4").build();

        paymentModeRepo.save(paymentMode);
        paymentModeRepo.save(paymentMode2);
        paymentModeRepo.save(paymentMode3);
        paymentModeRepo.save(paymentMode4);

        List<PaymentMode> paymentModeList = new ArrayList<>();
        paymentModeList.add(paymentMode);
        paymentModeList.add(paymentMode2);
        paymentModeList.add(paymentMode3);
        paymentModeList.add(paymentMode4);

        assertEquals(paymentModeList,this.paymentModeRepo.getAllPaymentMode());

    }

    @Test
    public void getPaymentModeById(){
        PaymentMode paymentMode = PaymentMode.builder().id(1L).description("test").name("test").type("test").build();

        paymentModeRepo.save(paymentMode);

        assertEquals(paymentMode,this.paymentModeRepo.getPaymentModeById(1L).get());
    }

    @Test
    public void getPaymentModeByName(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).description("test").name("test").type("test").build();

        paymentModeRepo.save(paymentMode);

        assertEquals(paymentMode,this.paymentModeRepo.getPaymentModeByName("test").get());

    }

    @Test
    public void getPaymentByType(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).description("test").name("test").type("test").build();

        paymentModeRepo.save(paymentMode);

        assertEquals(paymentMode,this.paymentModeRepo.getPaymentModeByType("test").get());

    }




}