package ro.myclass.billingmanagementapi.receipt.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.receipt.dto.CancelReceiptRequest;
import ro.myclass.billingmanagementapi.receipt.dto.ReceiptDTO;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptCommandService;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptQuerryService;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/receipt")
@Slf4j
public class ReceiptResource {

    private ReceiptCommandService receiptCommandService;

    private ReceiptQuerryService receiptQuerryService;


    public ReceiptResource(ReceiptCommandService receiptCommandService, ReceiptQuerryService receiptQuerryService) {
        this.receiptCommandService = receiptCommandService;
        this.receiptQuerryService = receiptQuerryService;
    }

    @GetMapping("/allReceipts")
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> receiptList = this.receiptQuerryService.getAllReceipts();

        log.info("REST request to get all receipts {}", receiptList);

        return ResponseEntity.ok(receiptList);
    }

    @PostMapping("/addReceipt")
    public ResponseEntity<String> addReceipt(@RequestBody ReceiptDTO receipt) {
        this.receiptCommandService.addReceipt(receipt);

        log.info("REST request to add a receipt {}", receipt);

        return new ResponseEntity<>("Receipt was added", HttpStatus.OK);
    }

    @DeleteMapping("/deleteReceipt")
    public ResponseEntity<String> deleteReceipt(@RequestBody CancelReceiptRequest cancelReceiptRequest) {
        this.receiptCommandService.removeReceipt(cancelReceiptRequest);

        log.info("REST request to delete a receipt by cancelReceiptRequest {}", cancelReceiptRequest);

        return new ResponseEntity<>("Receipt was deleted", HttpStatus.OK);
    }

    @GetMapping("/getReceiptById/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable long id) {
        Receipt receipt = this.receiptQuerryService.getReceiptById(id);

        log.info("REST request to get a receipt by id {}", id);

        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }



    @GetMapping("/getReceiptByType/{type}")
    public ResponseEntity<Receipt> getReceiptByType(@PathVariable String type) {
       List<Receipt> receiptList = this.receiptQuerryService.getReceiptByType(type);

        log.info("REST request to get a receipt by type {}", type);

        return new ResponseEntity(receiptList, HttpStatus.OK);
    }

    @PutMapping("/updateReceipt")
    public ResponseEntity<String> updateReceipt(@RequestBody ReceiptDTO receiptDTO) {
        this.receiptCommandService.updateReceipt(receiptDTO);

        log.info("REST request to update a receipt {}", receiptDTO);

        return new ResponseEntity<>("Receipt was updated", HttpStatus.OK);
    }
}
