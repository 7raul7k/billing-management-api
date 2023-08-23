package ro.myclass.billingmanagementapi.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.dto.CancelReceiptRequest;
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

    public Receipt getReceiptByTypeAndNumberAndDescription(String type, String number, String description){
        Optional<Receipt> receiptOptional = this.receiptRepo.getReceiptByTypeAndNumberAndDescription(type,number,description);

        if(receiptOptional.isEmpty()){
            throw new ReceiptNotFoundException();
        }else{
            return receiptOptional.get();
        }
    }
}
