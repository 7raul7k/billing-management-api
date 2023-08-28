package ro.myclass.billingmanagementapi.bill.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.bill.repository.BillRepo;
import ro.myclass.billingmanagementapi.exceptions.BillNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;

import java.util.List;
import java.util.Optional;

@Service
public class BillImplQuerryService implements BillQueryService {

    private BillRepo billRepo;

    public BillImplQuerryService(BillRepo billRepo) {
        this.billRepo = billRepo;
    }

    public List<Bill> getAllBills() {
        List<Bill> bills = billRepo.getAllBill();

        if (bills.isEmpty()) {
            throw new ListEmptyException();
        } else {
            return bills;
        }
    }

    public Bill getBillByNumber(String number) {
        Optional<Bill> billOptional = billRepo.getBillByNumber(number);

        if (billOptional.isEmpty()) {
            throw new BillNotFoundException();
        } else {
            return billOptional.get();
        }
    }

    public List<Bill> getBillByType(String type) {
        List<Bill> billOptional = billRepo.getBillByType(type);

        if (billOptional.isEmpty()) {
            throw new BillNotFoundException();
        } else {
            return billOptional;
        }
    }


    public Bill getBillById(long id) {
        Optional<Bill> billOptional = billRepo.getBillById(id);

        if (billOptional.isEmpty()) {
            throw new BillNotFoundException();
        } else {
            return billOptional.get();
        }
    }
}
