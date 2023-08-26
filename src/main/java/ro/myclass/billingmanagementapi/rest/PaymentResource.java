package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.dto.UpdatePaymentRequest;
import ro.myclass.billingmanagementapi.models.Payment;
import ro.myclass.billingmanagementapi.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@Slf4j
public class PaymentResource {

    private PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/allPayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> paymentList = this.paymentService.getAllPayments();

        log.info("REST request to get all payments {}", paymentList);

        return ResponseEntity.ok(paymentList);
    }

    @PostMapping("/addPayment")
    public ResponseEntity<String> addPayment(@RequestBody PaymentDTO payment) {
        this.paymentService.addPayment(payment);

        log.info("REST request to add a payment {}", payment);

        return  new ResponseEntity<>("Payment was added", HttpStatus.OK);
    }

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable long id) {
        Payment payment = this.paymentService.getPaymentById(id);

        log.info("REST request to get a payment by id {}", id);

        return  new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/getPaymentByAmount/{amount}")
    public ResponseEntity<List<Payment>> getPaymentByAmount(@PathVariable String amount) {
        List<Payment> payment = this.paymentService.getPaymentByAmount(amount);

        log.info("REST request to get a payment by amount {}", amount);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @DeleteMapping("/deletePayment")
    public ResponseEntity<String> deletePayment(@RequestParam String amount,@RequestParam String description) {
        this.paymentService.deletePayment(amount, description);

        log.info("REST request to delete a payment by amount and description {}", amount, description);

        return new ResponseEntity<>("Payment was deleted", HttpStatus.OK);
    }

    @PutMapping("/updatePayment")
    public ResponseEntity<String> updatePayment(@RequestBody UpdatePaymentRequest updatePaymentRequest) {

        this.paymentService.updatePayment(updatePaymentRequest);

        log.info("REST request to update a payment by updatePaymentRequest {}", updatePaymentRequest);

        return new ResponseEntity<>("Payment was updated", HttpStatus.OK);

    }

    @GetMapping("/getPaymentByAmountAndDescription")
    public ResponseEntity<Payment> getPaymentByAmountAndDescription(@RequestParam String amount,@RequestParam String description) {
        Payment payment = this.paymentService.getPaymentByAmountAndDescription(amount, description);

        log.info("REST request to get a payment by amount and description {}", amount, description);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

}
