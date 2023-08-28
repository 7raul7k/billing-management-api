package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.receipt.dto.CancelReceiptRequest;
import ro.myclass.billingmanagementapi.receipt.dto.ReceiptDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.ReceiptNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ReceiptWasFoundException;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.receipt.repo.ReceiptRepo;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptCommandService;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptQuerryImplService;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptQuerryService;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptCommandImplService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReceiptImplServiceTest {

    @Mock
    private ReceiptRepo receiptRepo;

    @InjectMocks
    private ReceiptCommandService receiptService = new ReceiptCommandImplService(receiptRepo);

    @InjectMocks
    private ReceiptQuerryService receiptQuerryService = new ReceiptQuerryImplService(receiptRepo);

    @Captor
    private ArgumentCaptor<Receipt> argumentCaptor;

    @Test
    public void getAllReceipts(){


        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();
        Receipt receipt1 = Receipt.builder().id(2L).description("test1").type("test1").number("test1").date(LocalDate.now()).build();
        Receipt receipt2 = Receipt.builder().id(3L).description("test2").type("test2").number("test2").date(LocalDate.now()).build();
        Receipt receipt3 = Receipt.builder().id(4L).description("test3").type("test3").number("test3").date(LocalDate.now()).build();

        receiptRepo.save(receipt);
        receiptRepo.save(receipt1);
        receiptRepo.save(receipt2);
        receiptRepo.save(receipt3);

        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(receipt);
        receiptList.add(receipt1);
        receiptList.add(receipt2);
        receiptList.add(receipt3);

        doReturn(receiptList).when(receiptRepo).getAllReceipt();

        assertEquals(receiptList,receiptQuerryService.getAllReceipts());

    }

    @Test
    public void getAllReceiptsException(){
        doReturn(new ArrayList<>()).when(receiptRepo).getAllReceipt();

        assertThrows(ListEmptyException.class,()->receiptQuerryService.getAllReceipts());
    }

    @Test
    public void addReceipt(){



        ReceiptDTO receiptDTO = ReceiptDTO.builder().description("test").type("test").number("test").date(LocalDate.now()).build();

        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

       doReturn(Optional.empty()).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receiptDTO.getType(),receiptDTO.getNumber(),receiptDTO.getDescription());

        receiptService.addReceipt(receiptDTO);

        verify(receiptRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(receipt,argumentCaptor.getValue());

    }

    @Test
    public void addReceiptException(){
        ReceiptDTO receiptDTO = ReceiptDTO.builder().description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.of(new Receipt())).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receiptDTO.getType(),receiptDTO.getNumber(),receiptDTO.getDescription());

        assertThrows(ReceiptWasFoundException.class,()->receiptService.addReceipt(receiptDTO));
    }

    @Test
    public void removeReceipt(){
        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.of(receipt)).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receipt.getType(),receipt.getNumber(),receipt.getDescription());



        CancelReceiptRequest cancelReceiptRequest = CancelReceiptRequest.builder().description("test").type("test").number("test").build();

        receiptService.removeReceipt(cancelReceiptRequest);

        verify(receiptRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(receipt,argumentCaptor.getValue());
    }

    @Test
    public void removeReceiptException(){
        CancelReceiptRequest cancelReceiptRequest = CancelReceiptRequest.builder().description("test").type("test").number("test").build();

         doReturn(Optional.empty()).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(cancelReceiptRequest.getType(),cancelReceiptRequest.getNumber(),cancelReceiptRequest.getDescription());

        assertThrows(ReceiptWasFoundException.class,()->receiptService.removeReceipt(cancelReceiptRequest));
    }

    @Test
    public void getReceiptById(){

        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.of(receipt)).when(receiptRepo).getReceiptsById(receipt.getId());

        assertEquals(receipt,receiptQuerryService.getReceiptById(receipt.getId()));
    }

    @Test
    public void getReceiptByIdException(){

        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.empty()).when(receiptRepo).getReceiptsById(receipt.getId());

        assertThrows(ReceiptWasFoundException.class,()->receiptQuerryService.getReceiptById(receipt.getId()));
    }

    @Test
    public void getReceiptByType(){


        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();
        Receipt receipt1 = Receipt.builder().id(2L).description("test1").type("test").number("test1").date(LocalDate.now()).build();
        Receipt receipt2 = Receipt.builder().id(3L).description("test2").type("test").number("test2").date(LocalDate.now()).build();
        Receipt receipt3 = Receipt.builder().id(4L).description("test3").type("test").number("test3").date(LocalDate.now()).build();

        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(receipt);
        receiptList.add(receipt1);
        receiptList.add(receipt2);
        receiptList.add(receipt3);

        doReturn(receiptList).when(receiptRepo).getReceiptsByType(receipt.getType());

        assertEquals(receiptList,receiptQuerryService.getReceiptByType(receipt.getType()));
    }

    @Test
    public void getReceiptByTypeException(){
        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(new ArrayList<>()).when(receiptRepo).getReceiptsByType(receipt.getType());

        assertThrows(ReceiptWasFoundException.class,()->receiptQuerryService.getReceiptByType(receipt.getType()));
    }

    @Test
    public void getReceiptByNumber(){
        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();
        Receipt receipt1 = Receipt.builder().id(2L).description("test1").type("test1").number("test").date(LocalDate.now()).build();
        Receipt receipt2 = Receipt.builder().id(3L).description("test2").type("test2").number("test").date(LocalDate.now()).build();
        Receipt receipt3 = Receipt.builder().id(4L).description("test3").type("test3").number("test").date(LocalDate.now()).build();

        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(receipt);
        receiptList.add(receipt1);
        receiptList.add(receipt2);
        receiptList.add(receipt3);

        doReturn(receiptList).when(receiptRepo).getReceiptsByNumber(receipt.getNumber());

        assertEquals(receiptList,receiptQuerryService.getReceiptByNumber(receipt.getNumber()));
    }

    @Test
    public void getReceiptByNumberException(){
        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(new ArrayList<>()).when(receiptRepo).getReceiptsByNumber(receipt.getNumber());

        assertThrows(ReceiptWasFoundException.class,()->receiptQuerryService.getReceiptByNumber(receipt.getNumber()));
    }

    @Test
    public void updateReceipt(){
        ReceiptDTO receiptDTO = ReceiptDTO.builder().description("test").type("test").number("test").date(LocalDate.now()).build();

        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.of(receipt)).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receiptDTO.getType(),receiptDTO.getNumber(),receiptDTO.getDescription());

        receiptService.updateReceipt(receiptDTO);

        verify(receiptRepo,times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(receipt,argumentCaptor.getValue());
    }

    @Test
    public void updateReceiptException(){
        ReceiptDTO receiptDTO = ReceiptDTO.builder().description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.empty()).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receiptDTO.getType(),receiptDTO.getNumber(),receiptDTO.getDescription());

        assertThrows(ReceiptNotFoundException.class,()->receiptService.updateReceipt(receiptDTO));
    }

    @Test
    public void getReceiptByTypeNumberAndDescription(){

        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.of(receipt)).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receipt.getType(),receipt.getNumber(),receipt.getDescription());

        assertEquals(receipt,receiptQuerryService.getReceiptByTypeAndNumberAndDescription(receipt.getType(),receipt.getNumber(),receipt.getDescription()));
    }

    @Test
    public void getReceiptByTypeNumberAndDescriptionException(){

        Receipt receipt = Receipt.builder().id(1L).description("test").type("test").number("test").date(LocalDate.now()).build();

        doReturn(Optional.empty()).when(receiptRepo).getReceiptByTypeAndNumberAndDescription(receipt.getType(),receipt.getNumber(),receipt.getDescription());

        assertThrows(ReceiptNotFoundException.class,()->receiptQuerryService.getReceiptByTypeAndNumberAndDescription(receipt.getType(),receipt.getNumber(),receipt.getDescription()));
    }

}