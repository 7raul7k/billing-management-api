package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.payment.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.payment.models.Payment;
import ro.myclass.billingmanagementapi.payment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentMode")
@Slf4j
public class PaymentModeResource {

    private PaymentService paymentService;

    public PaymentModeResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/allPayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> paymentList = this.paymentService.getAllPayments();

        log.info("REST request to get all payments {}", paymentList);

        return new ResponseEntity<>(paymentList, HttpStatus.OK);
    }

    @PostMapping("/addPayment")
    public ResponseEntity<String> addPayment(@RequestBody PaymentDTO payment) {
        this.paymentService.addPayment(payment);

        log.info("REST request to add a payment {}", payment);

        return new ResponseEntity<>("Payment was added", HttpStatus.OK);
    }

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable long id) {
        Payment payment = this.paymentService.getPaymentById(id);

        log.info("REST request to get a payment by id {}", id);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/getPaymentByAmount/{amount}")
    public ResponseEntity<List<Payment>> getPaymentByAmount(@PathVariable String amount) {
        List<Payment> payment = this.paymentService.getPaymentByAmount(amount);

        log.info("REST request to get a payment by amount {}", amount);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }






}
