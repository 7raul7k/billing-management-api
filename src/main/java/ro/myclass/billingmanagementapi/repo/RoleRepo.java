package ro.myclass.billingmanagementapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {


    @Query("select r from Role r where r.id = ?1")
    Optional<Role> getRoleById(long id);

    @Query("select r from Role r where r.title = ?1")
    Optional<Role> getRoleByTitle(String title);

    @Query("select r from Role r ")
    List<Role> getAllRole();


}
