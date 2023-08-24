package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.BillDTO;
import ro.myclass.billingmanagementapi.models.Bill;
import ro.myclass.billingmanagementapi.service.BillService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@Slf4j
public class BillResource {

    private BillService billService;

    public BillResource(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/allBills")
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> billList = this.billService.getAllBills();

        log.info("REST request to get all bills {}", billList);

        return new ResponseEntity<>(billList, HttpStatus.OK);
    }

    @PostMapping("/addBill")
    public ResponseEntity<String> addBill(@RequestParam BillDTO bill) {
        this.billService.addBill(bill);

        log.info("REST request to add a bill {}", bill);

        return new ResponseEntity<>("Bill was added", HttpStatus.OK);
    }

    @GetMapping("/getBillById/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable long id) {
        Bill bill = this.billService.getBillById(id);

        log.info("REST request to get a bill by id {}", id);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping("/getBillByNumber/{number}")
    public ResponseEntity<Bill> getBillByNumber(@PathVariable String number) {
        Bill bill = this.billService.getBillByNumber(number);

        log.info("REST request to get a bill by number {}", number);

        return new ResponseEntity<>(bill, HttpStatus.OK);

    }

    @DeleteMapping("/deleteBill/{number}")
    public ResponseEntity<String> deleteBill(@PathVariable String number) {
        this.billService.deleteBill(number);

        log.info("REST request to delete a bill by number {}", number);

        return new ResponseEntity<>("Bill was deleted", HttpStatus.OK);
    }

    @PutMapping("/updateBill")
    public ResponseEntity<String> updateBill(@RequestParam BillDTO billDTO) {
        this.billService.updateBill(billDTO);

        log.info("REST request to update a bill {}", billDTO);

        return new ResponseEntity<>("Bill was updated", HttpStatus.OK);
    }
    
}
