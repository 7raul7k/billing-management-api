package ro.myclass.billingmanagementapi.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.billingmanagementapi.models.Permission;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission,Long> {


    @Query("select p from Permission p where p.id = ?1")
    Optional<Permission> getPermissionById(long id);

    @Query("select p from Permission p where p.title = ?1")
    Optional<Permission> getPermissionByTitle(String title);

    @Query("select p from Permission p where p.module = ?1")
    List<Permission> getPermissionByModule(String module);

    @Query("select p from Permission p")
    List<Permission> getAllPermission();
}
