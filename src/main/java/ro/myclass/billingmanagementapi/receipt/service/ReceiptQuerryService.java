package ro.myclass.billingmanagementapi.receipt.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;

import java.util.List;

@Service
public interface ReceiptQuerryService {

    List<Receipt> getAllReceipts();

    Receipt getReceiptById(long id);

    List<Receipt> getReceiptByType(String type);

    List<Receipt> getReceiptByNumber(String number);

    Receipt getReceiptByTypeAndNumberAndDescription(String type, String number, String description);
}
