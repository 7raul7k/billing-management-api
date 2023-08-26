package ro.myclass.billingmanagementapi.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.Payment;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {


    @Query("select p from Payment p where p.id = ?1")
    Optional<Payment> getPaymentById(long id);

    @Query("select p from Payment p where p.amount = ?1")
    List<Payment> getPaymentByAmount(String amount);

    @Query("select p from Payment p where p.amount = ?1 and p.description = ?2")
    Optional<Payment> getPaymentByAmountAndDescription(String amount, String description);

    @Query("select p from Payment p")
    List<Payment> getAllPayment();

}
