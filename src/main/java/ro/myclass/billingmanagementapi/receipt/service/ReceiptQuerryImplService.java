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
public class ReceiptQuerryImplService implements ReceiptQuerryService{

    public ReceiptRepo receiptRepo;

    public ReceiptQuerryImplService(ReceiptRepo receiptRepo) {
        this.receiptRepo = receiptRepo;
    }

    public List<Receipt> getAllReceipts(){
        List<Receipt> receiptList = this.receiptRepo.getAllReceipt();

        if(receiptList.isEmpty()){
            throw new ListEmptyException();
        }else{
            return receiptList;
        }
    }



    public Receipt getReceiptById(long id){
        Optional<Receipt> receiptOptional = this.receiptRepo.getReceiptsById(id);

        if(receiptOptional.isEmpty()){
            throw new ReceiptWasFoundException();
        }else{
            return receiptOptional.get();
        }
    }

    public List<Receipt> getReceiptByType(String type){
        List<Receipt> receiptList = this.receiptRepo.getReceiptsByType(type);

        if(receiptList.isEmpty()){
            throw new ReceiptWasFoundException();
        }else{
            return receiptList;
        }
    }

    public List<Receipt> getReceiptByNumber(String number){
        List<Receipt> receiptList = this.receiptRepo.getReceiptsByNumber(number);

        if(receiptList.isEmpty()){
            throw new ReceiptWasFoundException();
        }else{
            return receiptList;
        }
    }

    public Receipt getReceiptByTypeAndNumberAndDescription(String type, String number, String description){
        Optional<Receipt> receiptOptional = this.receiptRepo.getReceiptByTypeAndNumberAndDescription(type,number,description);

        if(receiptOptional.isEmpty()){
            throw new ReceiptNotFoundException();
        }else{
            return receiptOptional.get();
        }
    }


}
