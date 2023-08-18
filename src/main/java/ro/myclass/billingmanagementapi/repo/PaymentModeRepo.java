package ro.myclass.billingmanagementapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.PaymentMode;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentModeRepo extends JpaRepository<PaymentMode,Long> {

    @Query("select p from PaymentMode p where p.id = ?1")
    Optional<PaymentMode> getPaymentModeById(long id);

    @Query("select p from PaymentMode p where p.name = ?1")
    Optional<PaymentMode> getPaymentModeByName(String name);

    @Query("select p from PaymentMode p where p.type = ?1")
    List<PaymentMode> getPaymentModeByType(String type);

    @Query("select p from PaymentMode p")
    List<PaymentMode> getAllPaymentMode();
}
