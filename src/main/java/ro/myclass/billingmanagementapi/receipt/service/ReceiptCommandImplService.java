package ro.myclass.billingmanagementapi.receipt.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.ReceiptNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ReceiptWasFoundException;
import ro.myclass.billingmanagementapi.receipt.dto.CancelReceiptRequest;
import ro.myclass.billingmanagementapi.receipt.dto.ReceiptDTO;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.receipt.repo.ReceiptRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptCommandImplService implements ReceiptCommandService{

    public ReceiptRepo receiptRepo;

    public ReceiptCommandImplService(ReceiptRepo receiptRepo) {
        this.receiptRepo = receiptRepo;
    }



    public void addReceipt(ReceiptDTO receiptDTO) {
        Optional<Receipt> receiptOptional = this.receiptRepo.getReceiptByTypeAndNumberAndDescription(receiptDTO.getType(), receiptDTO.getNumber(), receiptDTO.getDescription());

        if(receiptOptional.isEmpty()){
            Receipt receipt = Receipt.builder().description(receiptDTO.getDescription()).type(receiptDTO.getType()).number(receiptDTO.getNumber()).date(receiptDTO.getDate()).billList(receiptDTO.getBills()).build();

            this.receiptRepo.save(receipt);
        }else{
            throw new ReceiptWasFoundException();
        }
    }

    public void removeReceipt(CancelReceiptRequest cancelReceiptRequest){
      Optional<Receipt> receiptOptional = this.receiptRepo.getReceiptByTypeAndNumberAndDescription(cancelReceiptRequest.getType(),cancelReceiptRequest.getNumber(),cancelReceiptRequest.getDescription());

        if(receiptOptional.isEmpty()){
            throw new ReceiptWasFoundException();
        }else{
            this.receiptRepo.delete(receiptOptional.get());
        }
    }


    public void updateReceipt(ReceiptDTO receiptDTO) {
        Optional<Receipt> receiptOptional = this.receiptRepo.getReceiptByTypeAndNumberAndDescription(receiptDTO.getType(), receiptDTO.getNumber(), receiptDTO.getDescription());

        if (receiptOptional.isEmpty()) {
            throw new ReceiptNotFoundException();
        } else {
            Receipt receipt = receiptOptional.get();

            if (receiptDTO.getDescription() != null) {
                receipt.setDescription(receiptDTO.getDescription());
            }
            if (receiptDTO.getType() != null) {
                receipt.setType(receiptDTO.getType());
            }
            if (receiptDTO.getNumber() != null) {
                receipt.setNumber(receiptDTO.getNumber());
            }
            if (receiptDTO.getDate() != null) {
                receipt.setDate(receiptDTO.getDate());
            }
            if (receiptDTO.getBills() != null) {
                receipt.setBillList(receiptDTO.getBills());
            }

            this.receiptRepo.saveAndFlush(receipt);

        }
    }



}
