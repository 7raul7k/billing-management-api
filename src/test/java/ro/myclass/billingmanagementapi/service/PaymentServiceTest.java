package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.dto.UpdatePaymentRequest;
import ro.myclass.billingmanagementapi.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.models.Customer;
import ro.myclass.billingmanagementapi.models.Payment;
import ro.myclass.billingmanagementapi.repo.CustomerRepo;
import ro.myclass.billingmanagementapi.repo.PaymentRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

        @Mock
        private PaymentRepo paymentRepo;

        @Mock
        private CustomerRepo customerRepo;
        @InjectMocks
        private PaymentService paymentService;

        @Captor
        private ArgumentCaptor<Payment> argumentCaptor;

        @Test
        public void getAllPayments(){


            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();
            Payment payment1 = Payment.builder().id(2L).amount("test1").date(LocalDate.now()).description("test1").build();
            Payment payment2 = Payment.builder().id(3L).amount("test2").date(LocalDate.now()).description("test2").build();
            Payment payment3 = Payment.builder().id(4L).amount("test3").date(LocalDate.now()).description("test3").build();


            paymentRepo.save(payment);
            paymentRepo.save(payment1);
            paymentRepo.save(payment2);
            paymentRepo.save(payment3);


            List<Payment> paymentList = new ArrayList<>();
            paymentList.add(payment);
            paymentList.add(payment1);
            paymentList.add(payment2);
            paymentList.add(payment3);

            doReturn(paymentList).when(paymentRepo).getAllPayment();

            assertEquals(paymentList, paymentService.getAllPayments());
        }


        @Test
        public void getAllPaymentsException(){
            List<Payment> paymentList = new ArrayList<>();

            doReturn(paymentList).when(paymentRepo).getAllPayment();

            assertThrows(ListEmptyException.class, () -> paymentService.getAllPayments());
        }

        @Test
        public void addPayment(){

            PaymentDTO paymentDTO = PaymentDTO.builder().amount("test").date(LocalDate.now()).description("test").build();


            doReturn(Optional.empty()).when(paymentRepo).getPaymentByAmountAndDescription(paymentDTO.getAmount(),paymentDTO.getDescription());


            paymentService.addPayment(paymentDTO);


            verify(paymentRepo,times(1)).save(argumentCaptor.capture());

            assertEquals(paymentDTO.getAmount(),argumentCaptor.getValue().getAmount());

        }


        @Test
        public void addPaymentException(){
            PaymentDTO paymentDTO = PaymentDTO.builder().amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.of(Payment.builder().amount("test").date(LocalDate.now()).description("test").build())).when(paymentRepo).getPaymentByAmountAndDescription(paymentDTO.getAmount(),paymentDTO.getDescription());

            assertThrows(Exception.class, () -> paymentService.addPayment(paymentDTO));
        }

        @Test
        public void deletePayment() {
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.of(payment)).when(paymentRepo).getPaymentByAmountAndDescription(payment.getAmount(),payment.getDescription());

            paymentService.deletePayment(payment.getAmount(),payment.getDescription());

            verify(paymentRepo,times(1)).delete(argumentCaptor.capture());

            assertEquals(payment,argumentCaptor.getValue());
        }

        @Test
        public void deletePaymentException(){
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.empty()).when(paymentRepo).getPaymentByAmountAndDescription(payment.getAmount(),payment.getDescription());

            assertThrows(ListEmptyException.class, () -> paymentService.deletePayment(payment.getAmount(),payment.getDescription()));
        }

        @Test
        public void updatePayment(){

            PaymentDTO paymentDTO = PaymentDTO.builder().amount("test").date(LocalDate.now()).description("test").build();
            UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder().customerId(1).paymentDTO(paymentDTO).build();
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").payments(new ArrayList<>()).build();

            doReturn(Optional.of(customer)).when(customerRepo).getCustomerById(updatePaymentRequest.getCustomerId());

            doReturn(Optional.of(payment)).when(paymentRepo).getPaymentByAmountAndDescription(paymentDTO.getAmount(),paymentDTO.getDescription());
            paymentService.updatePayment(updatePaymentRequest);

            verify(paymentRepo,times(1)).saveAndFlush(argumentCaptor.capture());

            assertEquals(payment,argumentCaptor.getValue());
        }

        @Test
        public void updatePaymentCustomerException(){
            PaymentDTO paymentDTO = PaymentDTO.builder().amount("test").date(LocalDate.now()).description("test").build();
            UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder().customerId(1).paymentDTO(paymentDTO).build();
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();


            doReturn(Optional.empty()).when(customerRepo).getCustomerById(updatePaymentRequest.getCustomerId());

            assertThrows(Exception.class, () -> paymentService.updatePayment(updatePaymentRequest));
        }

        @Test
        public void updatePaymentPaymentException(){
            PaymentDTO paymentDTO = PaymentDTO.builder().amount("test").date(LocalDate.now()).description("test").build();
            UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder().customerId(1).paymentDTO(paymentDTO).build();
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            Customer customer = Customer.builder().id(1L).name("test").mobile("test").email("test").address("test").username("test").password("test").payments(new ArrayList<>()).build();

            doReturn(Optional.of(customer)).when(customerRepo).getCustomerById(updatePaymentRequest.getCustomerId());

            doReturn(Optional.empty()).when(paymentRepo).getPaymentByAmountAndDescription(paymentDTO.getAmount(),paymentDTO.getDescription());
            assertThrows(Exception.class, () -> paymentService.updatePayment(updatePaymentRequest));
        }

        @Test
        public void getPaymentByAmountAndDescription(){
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.of(payment)).when(paymentRepo).getPaymentByAmountAndDescription(payment.getAmount(),payment.getDescription());

            assertEquals(payment,paymentService.getPaymentByAmountAndDescription(payment.getAmount(),payment.getDescription()));
        }

        @Test
        public void getPaymentByAmountAndDescriptionException(){
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.empty()).when(paymentRepo).getPaymentByAmountAndDescription(payment.getAmount(),payment.getDescription());

            assertThrows(Exception.class, () -> paymentService.getPaymentByAmountAndDescription(payment.getAmount(),payment.getDescription()));
        }

        @Test
        public void getPaymentById(){
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.of(payment)).when(paymentRepo).getPaymentById(payment.getId());

            assertEquals(payment,paymentService.getPaymentById(payment.getId()));
        }

        @Test
        public void getPaymentByIdException(){
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();

            doReturn(Optional.empty()).when(paymentRepo).getPaymentById(payment.getId());

            assertThrows(Exception.class, () -> paymentService.getPaymentById(payment.getId()));
        }

        @Test
        public void getPaymentByAmount(){
            Payment payment = Payment.builder().id(1L).amount("test").date(LocalDate.now()).description("test").build();
            Payment payment1 = Payment.builder().id(2L).amount("test1").date(LocalDate.now()).description("test1").build();
            Payment payment2 = Payment.builder().id(3L).amount("test2").date(LocalDate.now()).description("test2").build();
            Payment payment3 = Payment.builder().id(4L).amount("test3").date(LocalDate.now()).description("test3").build();

            List<Payment> paymentList = new ArrayList<>();
            paymentList.add(payment);
            paymentList.add(payment1);
            paymentList.add(payment2);
            paymentList.add(payment3);

            doReturn(paymentList).when(paymentRepo).getPaymentByAmount(payment.getAmount());

            assertEquals(paymentList,paymentService.getPaymentByAmount(payment.getAmount()));
        }

        @Test
        public void getPaymentByAmountException(){


            doReturn(new ArrayList<>()).when(paymentRepo).getPaymentByAmount("1");

            assertThrows(Exception.class, () -> paymentService.getPaymentByAmount("1"));
        }





}