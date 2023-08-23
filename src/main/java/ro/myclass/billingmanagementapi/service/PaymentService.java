package ro.myclass.billingmanagementapi.service;

import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.dto.CancelPaymentRequest;
import ro.myclass.billingmanagementapi.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PaymentWasFoundException;
import ro.myclass.billingmanagementapi.models.Customer;
import ro.myclass.billingmanagementapi.models.Payment;
import ro.myclass.billingmanagementapi.repo.CustomerRepo;
import ro.myclass.billingmanagementapi.repo.PaymentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepo paymentRepo;

    private CustomerRepo customerRepo;

    public PaymentService(PaymentRepo paymentRepo, CustomerRepo customerRepo) {
        this.paymentRepo = paymentRepo;
        this.customerRepo = customerRepo;
    }

    public List<Payment> getAllPayments() {
        List<Payment> paymentList = this.paymentRepo.getAllPayment();

        if (paymentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return paymentList;

    }


    public void addPayment(PaymentDTO paymentDTO) {

        Optional<Payment> paymentOptional = this.paymentRepo.getPaymentByAmountAndDescription(paymentDTO.getAmount(), paymentDTO.getDescription());


        if (paymentOptional.isEmpty()) {
            Payment payment = Payment.builder().amount(paymentDTO.getAmount()).date(paymentDTO.getDate()).description(paymentDTO.getDescription()).build();

            this.paymentRepo.save(payment);
        } else {
            throw new PaymentWasFoundException();
        }


    }

    public void deletePayment(String amount,String description) {
        Optional<Payment> paymentOptional = this.paymentRepo.getPaymentByAmountAndDescription(amount,description);

        if (paymentOptional.isEmpty()) {
            throw new ListEmptyException();
        } else {
            this.paymentRepo.delete(paymentOptional.get());
        }

    }

    public void updatePayment(CancelPaymentRequest cancelPaymentRequest){

        Optional<Customer> customerOptional = this.customerRepo.getCustomerById( cancelPaymentRequest.getCustomerId());

        if (customerOptional.isEmpty()){
            throw new PaymentWasFoundException();
    }

        PaymentDTO paymentDTO = cancelPaymentRequest.getPaymentDTO();

        Optional<Payment> paymentOptional = this.paymentRepo.getPaymentByAmountAndDescription(paymentDTO.getAmount(), paymentDTO.getDescription());

        Payment payment = paymentOptional.get();
                if (paymentDTO.getAmount() != null) {
                    payment.setAmount(paymentDTO.getAmount());
                }
                if (paymentDTO.getDate() != null) {
                    payment.setDate(paymentDTO.getDate());
                }
                if (paymentDTO.getDescription() != null) {
                    payment.setDescription(paymentDTO.getDescription());
                }
                if (paymentDTO.getCustomer() != null) {
                    payment.setCustomer(paymentDTO.getCustomer());
                }

                this.paymentRepo.saveAndFlush(payment);


    }

    public Payment getPaymentByAmountAndDescription(String amount, String description) {
        Optional<Payment> paymentOptional = this.paymentRepo.getPaymentByAmountAndDescription(amount, description);

        if (paymentOptional.isEmpty()) {
            throw new ListEmptyException();
        } else {
            return paymentOptional.get();
        }
    }

    public Payment getPaymentById(long id){
        Optional<Payment> paymentOptional = this.paymentRepo.getPaymentById(id);

        if(paymentOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
            return paymentOptional.get();
        }
    }

    public List<Payment> getPaymentByAmount(String amount){
        List<Payment> paymentList = this.paymentRepo.getPaymentByAmount(amount);

        if(paymentList.isEmpty()){
            throw new ListEmptyException();
        }else{
            return paymentList;
        }
    }
}
