package ro.myclass.billingmanagementapi.receipt.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.receipt.dto.CancelReceiptRequest;
import ro.myclass.billingmanagementapi.receipt.dto.ReceiptDTO;

@Service
@Transactional
public interface ReceiptCommandService {

    void addReceipt(ReceiptDTO receiptDTO);

    void removeReceipt(CancelReceiptRequest cancelReceiptRequest);

    void updateReceipt(ReceiptDTO receiptDTO);
}
