package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.models.Bill;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class BillRepoTest {

    @Autowired
    BillRepo billRepo;

    @BeforeEach
    public void clean(){this.billRepo.deleteAll();}

    @Test
    public void getAllBill(){
        Bill bill = Bill.builder().id(1L).description("test").number("1").type("test").build();
        Bill bill2 = Bill.builder().id(2L).description("test").number("2").type("test2").build();
        Bill bill3 = Bill.builder().id(3L).description("test").number("3").type("test3").build();
        Bill bill4 = Bill.builder().id(4L).description("test").number("4").type("test4").build();

        billRepo.save(bill);
        billRepo.save(bill2);
        billRepo.save(bill3);
        billRepo.save(bill4);

        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        billList.add(bill2);
        billList.add(bill3);
        billList.add(bill4);

        assertEquals(billList,this.billRepo.getAllBill());

    }

    @Test
    public void getBillById(){
        Bill bill = Bill.builder().id(1L).description("test").number("1").type("test").build();

        billRepo.save(bill);

        assertEquals(bill,this.billRepo.getBillById(1).get());

    }

    @Test
    public void getBillByType(){

        Bill bill = Bill.builder().id(1L).description("test").number("1").type("test").build();
        Bill bill2 = Bill.builder().id(2L).description("test").number("2").type("test").build();
        Bill bill3 = Bill.builder().id(3L).description("test").number("3").type("test").build();
        Bill bill4 = Bill.builder().id(4L).description("test").number("4").type("test").build();

        billRepo.save(bill);
        billRepo.save(bill2);
        billRepo.save(bill3);
        billRepo.save(bill4);

        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        billList.add(bill2);
        billList.add(bill3);
        billList.add(bill4);

        assertEquals(billList,this.billRepo.getBillByType("test`"));

    }

    @Test
    public void getBillByNumber(){
        Bill bill = Bill.builder().id(1L).description("test").number("1").type("test").build();

        billRepo.save(bill);

        assertEquals(bill,this.billRepo.getBillByNumber("1").get());


    }



}