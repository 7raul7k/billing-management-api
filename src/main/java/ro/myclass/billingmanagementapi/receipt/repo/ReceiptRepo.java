package ro.myclass.billingmanagementapi.receipt.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepo extends JpaRepository<Receipt,Long> {

    @Query("select r from Receipt r where r.id = ?1")
    Optional<Receipt> getReceiptsById(long id);

    @Query("select r from Receipt r where r.type = ?1")
    List<Receipt> getReceiptsByType(String type);

    @Query("select r from Receipt r where r.number = ?1")
    List<Receipt> getReceiptsByNumber(String number);

    @Query("select r from Receipt r")
    List<Receipt> getAllReceipt();


    @Query("select r from Receipt r where r.type = ?1 and r.number = ?2 and r.description = ?3")
    Optional<Receipt> getReceiptByTypeAndNumberAndDescription(String type, String number, String description);
}
