package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.bill.service.BillImplService;
import ro.myclass.billingmanagementapi.bill.dto.BillDTO;
import ro.myclass.billingmanagementapi.exceptions.BillNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.bill.repository.BillRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {

    @Mock
    BillRepo billRepo;

    @InjectMocks
    BillImplService billServiceImpl;

    @Captor
    ArgumentCaptor<Bill> billArgumentCaptor;

    @Test
    public void getAllBills() {
        //create 4 bills and save them in billRepo

        Bill bill = Bill.builder().id(1L).number("1").build();
        Bill bill2 = Bill.builder().id(2L).number("2").build();
        Bill bill3 = Bill.builder().id(3L).number("3").build();
        Bill bill4 = Bill.builder().id(4L).number("4").build();

        billRepo.save(bill);
        billRepo.save(bill2);
        billRepo.save(bill3);
        billRepo.save(bill4);

        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        billList.add(bill2);
        billList.add(bill3);
        billList.add(bill4);

        doReturn(billList).when(billRepo).getAllBill();

        assertEquals(billList, this.billServiceImpl.getAllBills());


    }

    // create getAllBillsException

    @Test
    public void getAllBillsException() {

        doReturn(new ArrayList<>()).when(billRepo).getAllBill();

        assertThrows(ListEmptyException.class, () -> this.billServiceImpl.getAllBills());
    }

    @Test
    public void addBill() {

        BillDTO billDTO = BillDTO.builder().number("1").description("test").type("test").build();
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.empty()).when(billRepo).getBillByNumber("1");

        this.billServiceImpl.addBill(billDTO);

        verify(billRepo).save(billArgumentCaptor.capture());

        assertEquals(bill, billArgumentCaptor.getValue());

    }

    @Test
    public void addBillException() {

        BillDTO billDTO = BillDTO.builder().number("1").description("test").type("test").build();
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.of(bill)).when(billRepo).getBillByNumber("1");

        assertThrows(BillNotFoundException.class, () -> this.billServiceImpl.addBill(billDTO));

    }

    @Test
    public void deleteBill() {
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.of(bill)).when(billRepo).getBillByNumber("1");

        this.billServiceImpl.deleteBill("1");

        verify(billRepo).delete(billArgumentCaptor.capture());

        assertEquals(bill, billArgumentCaptor.getValue());
    }

    @Test
    public void deleteBillException() {
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.empty()).when(billRepo).getBillByNumber("1");

        assertThrows(BillNotFoundException.class, () -> this.billServiceImpl.deleteBill("1"));
    }

    @Test
    public void getBillByNumber() {
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.of(bill)).when(billRepo).getBillByNumber("1");

        assertEquals(bill, this.billServiceImpl.getBillByNumber("1"));
    }

    @Test
    public void getBillByNumberException() {


        doReturn(Optional.empty()).when(billRepo).getBillByNumber("1");

        assertThrows(BillNotFoundException.class, () -> this.billServiceImpl.getBillByNumber("1"));
    }

    @Test
    public void getBillByType() {
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();
        Bill bill2 = Bill.builder().id(2L).number("2").description("test").type("test").build();
        Bill bill3 = Bill.builder().id(3L).number("3").description("test").type("test").build();
        Bill bill4 = Bill.builder().id(4L).number("4").description("test").type("test").build();

        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        billList.add(bill2);
        billList.add(bill3);
        billList.add(bill4);

        doReturn(billList).when(billRepo).getBillByType("test");

        assertEquals(billList, this.billServiceImpl.getBillByType("test"));
    }

    @Test
    public void getBillByTypeException() {
        doReturn(new ArrayList<>()).when(billRepo).getBillByType("test");

        assertThrows(BillNotFoundException.class, () -> this.billServiceImpl.getBillByType("test"));
    }

    @Test
    public void getBillById() {
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.of(bill)).when(billRepo).getBillById(1L);

        assertEquals(bill, this.billServiceImpl.getBillById(1L));
    }

    @Test
    public void getBillByIdException() {
        doReturn(Optional.empty()).when(billRepo).getBillById(1L);

        assertThrows(BillNotFoundException.class, () -> this.billServiceImpl.getBillById(1L));
    }

    @Test
    public void updateBill() {
        BillDTO billDTO = BillDTO.builder().number("1").description("test").type("test").build();
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.of(bill)).when(billRepo).getBillByNumber("1");

        this.billServiceImpl.updateBill(billDTO);

        verify(billRepo).saveAndFlush(billArgumentCaptor.capture());

        assertEquals(bill, billArgumentCaptor.getValue());
    }

    @Test
    public void updateBillException() {
        BillDTO billDTO = BillDTO.builder().number("1").description("test").type("test").build();
        Bill bill = Bill.builder().id(1L).number("1").description("test").type("test").build();

        doReturn(Optional.empty()).when(billRepo).getBillByNumber("1");

        assertThrows(BillNotFoundException.class, () -> this.billServiceImpl.updateBill(billDTO));
    }
}