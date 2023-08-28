package ro.myclass.billingmanagementapi.paymentmode.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PaymentModeNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.PaymentModeWasFoundException;
import ro.myclass.billingmanagementapi.paymentmode.dto.PaymentModeDTO;
import ro.myclass.billingmanagementapi.paymentmode.models.PaymentMode;
import ro.myclass.billingmanagementapi.paymentmode.repo.PaymentModeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentModeCommandImplService implements PaymentModeCommandService {

    public PaymentModeRepo paymentModeRepo;

    public PaymentModeCommandImplService(PaymentModeRepo paymentModeRepo) {
        this.paymentModeRepo = paymentModeRepo;
    }



    public void addPaymentMode(PaymentModeDTO paymentModeDTO){
        Optional<PaymentMode> paymentModeOptional = this.paymentModeRepo.getPaymentModeByName(paymentModeDTO.getName());

        if(paymentModeOptional.isEmpty()){
            PaymentMode paymentMode = PaymentMode.builder().name(paymentModeDTO.getName()).description(paymentModeDTO.getDescription()).type(paymentModeDTO.getType()).build();

            this.paymentModeRepo.save(paymentMode);
        }else{
        throw new PaymentModeWasFoundException();
        }
    }

    public void deletePaymentMode(String name){
        Optional<PaymentMode> paymentModeOptional = this.paymentModeRepo.getPaymentModeByName(name);

        if(paymentModeOptional.isEmpty()){
            throw new PaymentModeNotFoundException();
        }else{
            this.paymentModeRepo.delete(paymentModeOptional.get());
        }
    }



    public void updatePaymentMode(PaymentModeDTO paymentModeDTO){
        Optional<PaymentMode> paymentModeOptional = this.paymentModeRepo.getPaymentModeByName(paymentModeDTO.getName());

        if(paymentModeOptional.isEmpty()){
            throw new PaymentModeNotFoundException();
        }else{

            if(paymentModeDTO.getDescription()!=null){
                paymentModeOptional.get().setDescription(paymentModeDTO.getDescription());
            }if(paymentModeDTO.getName()!=null) {
                paymentModeOptional.get().setName(paymentModeDTO.getName());
            }if(paymentModeDTO.getType()!=null){
                paymentModeOptional.get().setType(paymentModeDTO.getType());
            }

            this.paymentModeRepo.saveAndFlush(paymentModeOptional.get());
        }
    }
}
