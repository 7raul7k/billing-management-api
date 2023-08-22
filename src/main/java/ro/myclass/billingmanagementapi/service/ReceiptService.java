package ro.myclass.billingmanagementapi.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.dto.ReceiptDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.ReceiptNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ReceiptWasFoundException;
import ro.myclass.billingmanagementapi.models.Receipt;
import ro.myclass.billingmanagementapi.repo.ReceiptRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    public ReceiptRepo receiptRepo;

    public ReceiptService(ReceiptRepo receiptRepo) {
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

    public void addReceipt(ReceiptDTO receiptDTO) {

        List<Receipt> receiptOptional = this.receiptRepo.getReceiptsByNumber(receiptDTO.getNumber());

        for(Receipt receipt : receiptOptional){
            if(receipt.getNumber().equals(receiptDTO.getNumber()) && receipt.getType().equals(receiptDTO.getType())){
                throw new ReceiptWasFoundException();
            }
        }

        Receipt receipt = Receipt.builder().description(receiptDTO.getDescription()).type(receiptDTO.getType()).number(receiptDTO.getNumber()).date(receiptDTO.getDate()).billList(receiptDTO.getBills()).build();

        this.receiptRepo.save(receipt);
    }

    public void removeReceipt(String type,String number){
        List<Receipt> receiptList = this.receiptRepo.getReceiptsByNumber(number);

        for (Receipt receipt : receiptList){
            if(receipt.getType().equals(type) && receipt.getNumber().equals(number)){
                this.receiptRepo.delete(receipt);
            }
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

    public void updateReceipt(ReceiptDTO receiptDTO){
        List<Receipt> receiptOptional = this.receiptRepo.getReceiptsByNumber(receiptDTO.getNumber());

        if(receiptOptional.isEmpty()){
            throw new ReceiptNotFoundException();
        }else{

            for(Receipt receipt : receiptOptional) {
                if (receipt.getNumber().equals(receiptDTO.getNumber()) && receipt.getType().equals(receiptDTO.getType())) {

                    if (receiptDTO.getDescription() != null) {
                        receipt.setDescription(receiptDTO.getDescription());
                    }
                    if (receiptDTO.getType() != null) {
                        receipt.setType(receiptDTO.getType());
                    }
                    if (receiptDTO.getDate() != null) {
                        receipt.setDate(receiptDTO.getDate());
                    }
                    if (receiptDTO.getBills() != null) {
                        receipt.setBillList(receiptDTO.getBills());
                    }
                    if (receiptDTO.getNumber() != null) {
                        receipt.setNumber(receiptDTO.getNumber());
                    }
                    if (receiptDTO.getDate() != null) {
                        receipt.setDate(receiptDTO.getDate());
                    }
                    this.receiptRepo.saveAndFlush(receipt);
                }

            }

        }
    }
}
