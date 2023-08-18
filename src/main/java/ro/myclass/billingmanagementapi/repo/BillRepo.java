package ro.myclass.billingmanagementapi.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.Bill;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {

    @Query("select b from Bill b where b.id = ?1")
    Optional<Bill> getBillById(long id);

    @Query("select b from Bill b where b.type = ?1")
    List<Bill> getBillByType(String type);

    @Query("select b from Bill b where b.number = ?1")
    Optional<Bill> getBillByNumber(String number);

    @Query("select b from Bill b where b.receipt = ?1")
    List<Bill> getBillByReceipt(String receipt);


    @Query("select b from Bill b")
    List<Bill> getAllBill();
}
