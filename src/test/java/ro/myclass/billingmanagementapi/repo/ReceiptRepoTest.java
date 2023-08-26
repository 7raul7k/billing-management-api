package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.receipt.repo.ReceiptRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class ReceiptRepoTest {

    @Autowired
    ReceiptRepo receiptRepo;

    @BeforeEach
    public void clean(){this.receiptRepo.deleteAll();}

    @Test
    public void getAllReceipt(){
        Receipt receipt = Receipt.builder().id(1L).number("1234567890").type("test").date(LocalDate.now()).description("test").build();
        Receipt receipt2 = Receipt.builder().id(2L).number("1234567890").type("test2").date(LocalDate.now()).description("test2").build();
        Receipt receipt3 = Receipt.builder().id(3L).number("1234567890").type("test3").date(LocalDate.now()).description("test3").build();
        Receipt receipt4 = Receipt.builder().id(4L).number("1234567890").type("test4").date(LocalDate.now()).description("test4").build();

        receiptRepo.save(receipt);
        receiptRepo.save(receipt2);
        receiptRepo.save(receipt3);
        receiptRepo.save(receipt4);

        List<Receipt> receiptList = new ArrayList<>();

        receiptList.add(receipt);
        receiptList.add(receipt2);
        receiptList.add(receipt3);
        receiptList.add(receipt4);

        assertEquals(receiptList,receiptRepo.getAllReceipt());

    }

    @Test
    public void getReceiptById(){
        Receipt receipt = Receipt.builder().id(1L).number("1234567890").type("test").date(LocalDate.now()).description("test").build();

        receiptRepo.save(receipt);

        assertEquals(receipt,this.receiptRepo.getReceiptsById(1).get());
    }

    @Test
    public void getReceiptByType(){

        Receipt receipt = Receipt.builder().id(1L).number("1234567890").type("test").date(LocalDate.now()).description("test").build();
        Receipt receipt2 = Receipt.builder().id(2L).number("1234567890").type("test").date(LocalDate.now()).description("test2").build();
        Receipt receipt3 = Receipt.builder().id(3L).number("1234567890").type("test").date(LocalDate.now()).description("test3").build();
        Receipt receipt4 = Receipt.builder().id(4L).number("1234567890").type("test").date(LocalDate.now()).description("test4").build();

        receiptRepo.save(receipt);
        receiptRepo.save(receipt2);
        receiptRepo.save(receipt3);
        receiptRepo.save(receipt4);

        List<Receipt> receiptList = new ArrayList<>();

        receiptList.add(receipt);
        receiptList.add(receipt2);
        receiptList.add(receipt3);
        receiptList.add(receipt4);

        assertEquals(receiptList,this.receiptRepo.getReceiptsByType("test"));
    }

    @Test
    public void getReceiptByNumber(){
        Receipt receipt = Receipt.builder().id(1L).number("1234567890").type("test").date(LocalDate.now()).description("test").build();
        Receipt receipt2 = Receipt.builder().id(2L).number("1234567890").type("test2").date(LocalDate.now()).description("test2").build();
        Receipt receipt3 = Receipt.builder().id(3L).number("1234567890").type("test3").date(LocalDate.now()).description("test3").build();
        Receipt receipt4 = Receipt.builder().id(4L).number("1234567890").type("test4").date(LocalDate.now()).description("test4").build();

        receiptRepo.save(receipt);
        receiptRepo.save(receipt2);
        receiptRepo.save(receipt3);
        receiptRepo.save(receipt4);

        List<Receipt> receiptList = new ArrayList<>();

        receiptList.add(receipt);
        receiptList.add(receipt2);
        receiptList.add(receipt3);
        receiptList.add(receipt4);

        assertEquals(receiptList,this.receiptRepo.getReceiptsByNumber("1234567890"));
    }



}