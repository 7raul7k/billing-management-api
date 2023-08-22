package ro.myclass.billingmanagementapi.service;



import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.dto.BillDTO;
import ro.myclass.billingmanagementapi.exceptions.BillNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.models.Bill;
import ro.myclass.billingmanagementapi.repo.BillRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private BillRepo billRepo;

    public BillService(BillRepo billRepo) {
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


    public void addBill(BillDTO billDTO) {
        Optional<Bill> billOptional = billRepo.getBillByNumber(billDTO.getNumber());

        if (billOptional.isEmpty()) {
            throw new BillNotFoundException();
        } else {
            this.billRepo.save(billOptional.get());
        }
    }

    public void deleteBill(String number) {
        Optional<Bill> billOptional = billRepo.getBillByNumber(number);

        if (billOptional.isEmpty()) {
            throw new BillNotFoundException();
        } else {
            this.billRepo.delete(billOptional.get());
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

      public void updateBill(BillDTO billDTO) {

          Optional<Bill> billOptional = billRepo.getBillByNumber(billDTO.getNumber());

          if (billOptional.isEmpty()) {
              throw new BillNotFoundException();
          }
          if (billDTO.getType() != null) {
              billOptional.get().setType(billDTO.getType());
          }
          if (billDTO.getDescription() != null) {
              billOptional.get().setDescription(billDTO.getDescription());
          }
          if (billDTO.getNumber() != null) {
              billOptional.get().setNumber(billDTO.getNumber());
          }
          if (billDTO.getCustomer() != null) {
              billOptional.get().setCustomer(billDTO.getCustomer());
          }

          this.billRepo.saveAndFlush(billOptional.get());
      }
}
