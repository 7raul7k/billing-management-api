package ro.myclass.billingmanagementapi.bill.service;



import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.bill.dto.BillDTO;
import ro.myclass.billingmanagementapi.bill.repository.BillRepo;
import ro.myclass.billingmanagementapi.exceptions.BillNotFoundException;
import ro.myclass.billingmanagementapi.bill.models.Bill;

import java.util.Optional;

@Transactional
@Service
public class BillCommandImplService implements BillCommandService {

    private BillRepo billRepo;

    public BillCommandImplService(BillRepo billRepo) {
        this.billRepo = billRepo;
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
