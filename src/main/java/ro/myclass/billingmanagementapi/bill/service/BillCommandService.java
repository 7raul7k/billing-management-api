package ro.myclass.billingmanagementapi.bill.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.bill.dto.BillDTO;

@Service

public interface BillCommandService {
    void addBill(BillDTO billDTO);
    void deleteBill(String number);

    void updateBill(BillDTO billDTO);
}
