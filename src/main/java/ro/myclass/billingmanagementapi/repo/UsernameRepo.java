package ro.myclass.billingmanagementapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.Role;
import ro.myclass.billingmanagementapi.models.Username;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsernameRepo extends JpaRepository<Username,Long> {


    @Query("select u from Username u where u.address = ?1")
    List<Username> getUsernameByAddress(String address);

    @Query("select u from Username u where u.id = ?1")
    Optional<Username> getUsernameById(Long id);

    @Query("select u from Username u where u.email = ?1")
    Optional<Username> getUsernameByEmail(String email);

    @Query("select u from Username u where u.username = ?1")
    Optional<Username> getUsernameByUsername(String username);

    @Query("Select u from Username u")
    List<Username> getAllUsername();
}
