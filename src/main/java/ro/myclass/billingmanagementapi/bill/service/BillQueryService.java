package ro.myclass.billingmanagementapi.bill.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.bill.models.Bill;

import java.util.List;

@Service
public interface BillQueryService {

    List<Bill> getAllBills();

    Bill getBillByNumber(String number);

    List<Bill> getBillByType(String type);

    Bill getBillById(long id);

}
