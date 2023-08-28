package ro.myclass.billingmanagementapi.paymentmode.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PaymentModeNotFoundException;
import ro.myclass.billingmanagementapi.paymentmode.models.PaymentMode;
import ro.myclass.billingmanagementapi.paymentmode.repo.PaymentModeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentModeQuerryImplService implements PaymentModeQuerryService {

    private PaymentModeRepo paymentModeRepo;

    public PaymentModeQuerryImplService(PaymentModeRepo paymentModeRepo) {
        this.paymentModeRepo = paymentModeRepo;
    }

    public List<PaymentMode> getAllPaymentModes(){
        List<PaymentMode> paymentModeList = this.paymentModeRepo.getAllPaymentMode();

        if(paymentModeList.isEmpty()){
            throw new ListEmptyException();
        }else{
            return paymentModeList;
        }
    }

    public PaymentMode getPaymentModeByName(String name){
        Optional<PaymentMode> paymentModeOptional = this.paymentModeRepo.getPaymentModeByName(name);

        if(paymentModeOptional.isEmpty()){
            throw new PaymentModeNotFoundException();
        }else{
            return paymentModeOptional.get();
        }


    }

    public PaymentMode getPaymentModeById(long id){
        Optional<PaymentMode> paymentModeOptional = this.paymentModeRepo.getPaymentModeById(id);

        if(paymentModeOptional.isEmpty()){
            throw new PaymentModeNotFoundException();
        }else{
            return paymentModeOptional.get();
        }
    }

}
