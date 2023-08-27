package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.paymentmode.dto.PaymentModeDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PaymentModeNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.PaymentModeWasFoundException;
import ro.myclass.billingmanagementapi.paymentmode.models.PaymentMode;
import ro.myclass.billingmanagementapi.paymentmode.repo.PaymentModeRepo;
import ro.myclass.billingmanagementapi.paymentmode.service.PaymentModeCommandService;
import ro.myclass.billingmanagementapi.paymentmode.service.PaymentModeQuerryService;
import ro.myclass.billingmanagementapi.paymentmode.service.PaymentModeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PaymentModeServiceTest {

    @Mock
    private PaymentModeRepo paymentModeRepo;

    @InjectMocks
    private PaymentModeCommandService paymentModeService = new PaymentModeService(paymentModeRepo);

    @InjectMocks
    private PaymentModeQuerryService paymentModeQuerryService = new PaymentModeService(paymentModeRepo);

    @Captor
    ArgumentCaptor<PaymentMode> argumentCaptor;

    @Test
    public void getAllPaymentsMode(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").build();
        PaymentMode paymentMode1 = PaymentMode.builder().id(2L).name("test1").description("test1").build();
        PaymentMode paymentMode2 = PaymentMode.builder().id(3L).name("test2").description("test2").build();
        PaymentMode paymentMode3 = PaymentMode.builder().id(4L).name("test3").description("test3").build();


        paymentModeRepo.save(paymentMode);
        paymentModeRepo.save(paymentMode1);
        paymentModeRepo.save(paymentMode2);
        paymentModeRepo.save(paymentMode3);



        List<PaymentMode> paymentModeList = new ArrayList<>();
        paymentModeList.add(paymentMode);
        paymentModeList.add(paymentMode1);
        paymentModeList.add(paymentMode2);
        paymentModeList.add(paymentMode3);

        doReturn(paymentModeList).when(paymentModeRepo).getAllPaymentMode();

        assertEquals(paymentModeList,paymentModeQuerryService.getAllPaymentModes());


    }

    @Test
    public void getAllPaymentsModeException(){
        doReturn(new ArrayList<>()).when(paymentModeRepo).getAllPaymentMode();

        assertThrows(ListEmptyException.class,()->paymentModeQuerryService.getAllPaymentModes());
    }

    @Test
    public void addPaymentMode(){
        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        PaymentModeDTO paymentModeDTO = PaymentModeDTO.builder().name("test").description("test").type("test").build();

        doReturn(Optional.empty()).when(paymentModeRepo).getPaymentModeByName(paymentModeDTO.getName());

        paymentModeService.addPaymentMode(paymentModeDTO);


        verify(paymentModeRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(paymentMode,argumentCaptor.getValue());
    }

    @Test
    public void addPaymentModeException(){
        PaymentModeDTO paymentModeDTO = PaymentModeDTO.builder().name("test").description("test").type("test").build();

        doReturn(Optional.of(new PaymentMode())).when(paymentModeRepo).getPaymentModeByName(paymentModeDTO.getName());

        assertThrows(PaymentModeWasFoundException.class,()->paymentModeService.addPaymentMode(paymentModeDTO));
    }

    @Test
    public void deletePaymentMode(){
        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        doReturn(Optional.of(paymentMode)).when(paymentModeRepo).getPaymentModeByName(paymentMode.getName());

        paymentModeService.deletePaymentMode(paymentMode.getName());

        verify(paymentModeRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(paymentMode,argumentCaptor.getValue());
    }

    @Test
    public void deletePaymentModeException(){
        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        doReturn(Optional.empty()).when(paymentModeRepo).getPaymentModeByName(paymentMode.getName());

        assertThrows(PaymentModeNotFoundException.class,()->paymentModeService.deletePaymentMode(paymentMode.getName()));
    }

    @Test
    public void getPaymentModeById(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        doReturn(Optional.of(paymentMode)).when(paymentModeRepo).getPaymentModeById(paymentMode.getId());

        assertEquals(paymentMode,paymentModeQuerryService.getPaymentModeById(paymentMode.getId()));
    }

    @Test
    public void getPaymentModeByIdException(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        doReturn(Optional.empty()).when(paymentModeRepo).getPaymentModeById(paymentMode.getId());

        assertThrows(PaymentModeNotFoundException.class,()->paymentModeQuerryService.getPaymentModeById(paymentMode.getId()));
    }

    @Test
    public void getPaymentModeByName(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        doReturn(Optional.of(paymentMode)).when(paymentModeRepo).getPaymentModeByName(paymentMode.getName());

        assertEquals(paymentMode,paymentModeQuerryService.getPaymentModeByName(paymentMode.getName()));
    }

    @Test
    public void getPaymentModeByNameException(){

        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        doReturn(Optional.empty()).when(paymentModeRepo).getPaymentModeByName(paymentMode.getName());

        assertThrows(PaymentModeNotFoundException.class,()->paymentModeQuerryService.getPaymentModeByName(paymentMode.getName()));
    }

    @Test
    public void updatePaymentMode(){
        PaymentMode paymentMode = PaymentMode.builder().id(1L).name("test").description("test").type("test").build();

        PaymentModeDTO paymentModeDTO = PaymentModeDTO.builder().name("test").description("test").type("test").build();

        doReturn(Optional.of(paymentMode)).when(paymentModeRepo).getPaymentModeByName(paymentModeDTO.getName());

        paymentModeService.updatePaymentMode(paymentModeDTO);

        verify(paymentModeRepo,times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(paymentMode,argumentCaptor.getValue());
    }

    @Test
    public void updatePaymentModeException(){
        PaymentModeDTO paymentModeDTO = PaymentModeDTO.builder().name("test").description("test").type("test").build();

        doReturn(Optional.empty()).when(paymentModeRepo).getPaymentModeByName(paymentModeDTO.getName());

        assertThrows(PaymentModeNotFoundException.class,()->paymentModeService.updatePaymentMode(paymentModeDTO));
    }


}