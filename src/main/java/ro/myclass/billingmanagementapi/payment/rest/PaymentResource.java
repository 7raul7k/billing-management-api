package ro.myclass.billingmanagementapi.payment.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.payment.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.payment.dto.UpdatePaymentRequest;
import ro.myclass.billingmanagementapi.payment.models.Payment;
import ro.myclass.billingmanagementapi.payment.service.PaymentCommandService;
import ro.myclass.billingmanagementapi.payment.service.PaymentQuerryService;
import ro.myclass.billingmanagementapi.payment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@Slf4j
public class PaymentResource {

    private PaymentCommandService paymentCommandService;

    private PaymentQuerryService paymentQuerryService;

    public PaymentResource(PaymentCommandService paymentCommandService, PaymentQuerryService paymentQuerryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQuerryService = paymentQuerryService;
    }

    @GetMapping("/allPayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> paymentList = this.paymentQuerryService.getAllPayments();

        log.info("REST request to get all payments {}", paymentList);

        return ResponseEntity.ok(paymentList);
    }

    @PostMapping("/addPayment")
    public ResponseEntity<String> addPayment(@RequestBody PaymentDTO payment) {
        this.paymentCommandService.addPayment(payment);

        log.info("REST request to add a payment {}", payment);

        return  new ResponseEntity<>("Payment was added", HttpStatus.OK);
    }

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable long id) {
        Payment payment = this.paymentQuerryService.getPaymentById(id);

        log.info("REST request to get a payment by id {}", id);

        return  new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/getPaymentByAmount/{amount}")
    public ResponseEntity<List<Payment>> getPaymentByAmount(@PathVariable String amount) {
        List<Payment> payment = this.paymentQuerryService.getPaymentByAmount(amount);

        log.info("REST request to get a payment by amount {}", amount);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @DeleteMapping("/deletePayment")
    public ResponseEntity<String> deletePayment(@RequestParam String amount,@RequestParam String description) {
        this.paymentCommandService.deletePayment(amount, description);

        log.info("REST request to delete a payment by amount and description {}", amount, description);

        return new ResponseEntity<>("Payment was deleted", HttpStatus.OK);
    }

    @PutMapping("/updatePayment")
    public ResponseEntity<String> updatePayment(@RequestBody UpdatePaymentRequest updatePaymentRequest) {

        this.paymentCommandService.updatePayment(updatePaymentRequest);

        log.info("REST request to update a payment by updatePaymentRequest {}", updatePaymentRequest);

        return new ResponseEntity<>("Payment was updated", HttpStatus.OK);

    }

    @GetMapping("/getPaymentByAmountAndDescription")
    public ResponseEntity<Payment> getPaymentByAmountAndDescription(@RequestParam String amount,@RequestParam String description) {
        Payment payment = this.paymentQuerryService.getPaymentByAmountAndDescription(amount, description);

        log.info("REST request to get a payment by amount and description {}", amount, description);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

}
