package ro.myclass.billingmanagementapi.bill.service;



import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.bill.dto.BillDTO;
import ro.myclass.billingmanagementapi.bill.repository.BillRepo;
import ro.myclass.billingmanagementapi.exceptions.BillNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.bill.models.Bill;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BillImplService implements BillQueryService,BillCommandService {

    private BillRepo billRepo;

    public BillImplService(BillRepo billRepo) {
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
            Bill bill = Bill.builder().number(billDTO.getNumber()).type(billDTO.getType()).description(billDTO.getDescription()).customer(billDTO.getCustomer()).build();

            this.billRepo.save(bill);
        } else {
            throw new BillNotFoundException();

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
