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

        List<Payment> paymentOptional = this.paymentRepo.getPaymentByAmount(paymentDTO.getAmount());

        for (Payment payment : paymentOptional) {
            if (payment.getAmount().equals(paymentDTO.getAmount()) && payment.getDate().equals(paymentDTO.getDate()) && payment.getDescription().equals(paymentDTO.getDescription())) {

                throw new PaymentWasFoundException();

            }
        }

        Payment payment = Payment.builder().amount(paymentDTO.getAmount()).date(paymentDTO.getDate()).description(paymentDTO.getDescription()).build();


        this.paymentRepo.save(payment);
    }

    public void deletePayment(String amount) {
        List<Payment> paymentList = this.paymentRepo.getPaymentByAmount(amount);

        for (Payment payment : paymentList) {
            if (payment.getAmount().equals(amount)) {
                this.paymentRepo.delete(payment);
            }
        }
    }

    public void updatePayment(CancelPaymentRequest cancelPaymentRequest){

        Optional<Customer> customerOptional = this.customerRepo.getCustomerById( cancelPaymentRequest.getCustomerId());

        if (customerOptional.isEmpty()){
            throw new PaymentWasFoundException();
    }

        PaymentDTO paymentDTO = cancelPaymentRequest.getPaymentDTO();
        List<Payment> paymentList = this.paymentRepo.getPaymentByAmount(cancelPaymentRequest.getPaymentDTO().getAmount());

        for (Payment payment : paymentList){
            if(payment.getAmount().equals(cancelPaymentRequest.getPaymentDTO().getAmount())&& payment.getCustomer().equals(customerOptional.get())) {
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
        }
    }
}
