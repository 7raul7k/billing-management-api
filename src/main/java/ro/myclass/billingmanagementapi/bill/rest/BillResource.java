package ro.myclass.billingmanagementapi.bill.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.bill.dto.BillDTO;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.bill.service.BillCommandService;
import ro.myclass.billingmanagementapi.bill.service.BillQueryService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@Slf4j
public class BillResource {

    private BillCommandService billCommandService;

    private BillQueryService billQueryService;

    public BillResource(BillCommandService billCommandService, BillQueryService billQueryService) {
        this.billCommandService = billCommandService;
        this.billQueryService = billQueryService;
    }

    @GetMapping("/allBills")
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> billList = this.billQueryService.getAllBills();

        log.info("REST request to get all bills {}", billList);

        return new ResponseEntity<>(billList, HttpStatus.OK);
    }

    @PostMapping("/addBill")
    public ResponseEntity<String> addBill(@RequestBody BillDTO bill) {
        this.billCommandService.addBill(bill);

        log.info("REST request to add a bill {}", bill);

        return new ResponseEntity<>("Bill was added", HttpStatus.OK);
    }

    @GetMapping("/getBillById/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable long id) {
        Bill bill = this.billQueryService.getBillById(id);

        log.info("REST request to get a bill by id {}", id);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping("/getBillByNumber/{number}")
    public ResponseEntity<Bill> getBillByNumber(@PathVariable String number) {
        Bill bill = this.billQueryService.getBillByNumber(number);

        log.info("REST request to get a bill by number {}", number);

        return new ResponseEntity<>(bill, HttpStatus.OK);

    }

    @DeleteMapping("/deleteBill/{number}")
    public ResponseEntity<String> deleteBill(@PathVariable String number) {
        this.billCommandService.deleteBill(number);

        log.info("REST request to delete a bill by number {}", number);

        return new ResponseEntity<>("Bill was deleted", HttpStatus.OK);
    }

    @PutMapping("/updateBill")
    public ResponseEntity<String> updateBill(@RequestBody BillDTO billDTO) {
        this.billCommandService.updateBill(billDTO);

        log.info("REST request to update a bill {}", billDTO);

        return new ResponseEntity<>("Bill was updated", HttpStatus.OK);
    }


    
}
