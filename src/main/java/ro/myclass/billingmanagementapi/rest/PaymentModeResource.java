package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.PaymentModeDTO;
import ro.myclass.billingmanagementapi.models.PaymentMode;
import ro.myclass.billingmanagementapi.service.PaymentModeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentMode")
@Slf4j
public class PaymentModeResource {

    private PaymentModeService paymentModeService;

    public PaymentModeResource(PaymentModeService paymentModeService) {
        this.paymentModeService = paymentModeService;
    }

    @GetMapping("/allPaymentModes")
    public ResponseEntity<List<PaymentMode>> getAllPaymentModes() {
        List<PaymentMode> paymentModeList = this.paymentModeService.getAllPaymentModes();

        log.info("REST request to get all payment modes {}", paymentModeList);

        return new ResponseEntity<>(paymentModeList, HttpStatus.OK);
    }

    @PostMapping("/addPaymentMode")
    public ResponseEntity<String> addPaymentMode(PaymentModeDTO paymentMode) {
        this.paymentModeService.addPaymentMode(paymentMode);

        log.info("REST request to add a payment mode {}", paymentMode);

        return new ResponseEntity<>("Payment mode was added", HttpStatus.OK);
    }

    @DeleteMapping("/deletePaymentMode/{name}")
    public ResponseEntity<String> deletePaymentMode(String name) {
        this.paymentModeService.deletePaymentMode(name);

        log.info("REST request to delete a payment mode by name {}", name);

        return new ResponseEntity<>("Payment mode was deleted", HttpStatus.OK);
    }

    @GetMapping("/getPaymentModeById/{id}")
    public ResponseEntity<PaymentMode> getPaymentModeById(long id) {
        PaymentMode paymentMode = this.paymentModeService.getPaymentModeById(id);

        log.info("REST request to get a payment mode by id {}", id);

        return new ResponseEntity<>(paymentMode, HttpStatus.OK);
    }

    @GetMapping("/getPaymentModeByName/{name}")
    public ResponseEntity<PaymentMode> getPaymentModeByName(String name){
        PaymentMode paymentMode = this.paymentModeService.getPaymentModeByName(name);

        log.info("REST request to get a payment mode by name {}", name);

        return new ResponseEntity<>(paymentMode, HttpStatus.OK);

    }

    @PutMapping("/updatePaymentMode")
    public ResponseEntity<String> updatePaymentMode(PaymentModeDTO paymentModeDTO) {
        this.paymentModeService.updatePaymentMode(paymentModeDTO);

        log.info("REST request to update a payment mode {}", paymentModeDTO);

        return new ResponseEntity<>("Payment mode was updated", HttpStatus.OK);
    }
    




}
