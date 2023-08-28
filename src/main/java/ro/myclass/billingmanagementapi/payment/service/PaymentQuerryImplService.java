package ro.myclass.billingmanagementapi.payment.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.customer.repo.CustomerRepo;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PaymentNotFoundException;
import ro.myclass.billingmanagementapi.payment.models.Payment;
import ro.myclass.billingmanagementapi.payment.repo.PaymentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQuerryImplService  implements PaymentQuerryService {

    private PaymentRepo paymentRepo;

    private CustomerRepo customerRepo;

    public PaymentQuerryImplService(PaymentRepo paymentRepo, CustomerRepo customerRepo) {
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

    public Payment getPaymentByAmountAndDescription(String amount, String description) {
        Optional<Payment> paymentOptional = this.paymentRepo.getPaymentByAmountAndDescription(amount, description);

        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException();
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
