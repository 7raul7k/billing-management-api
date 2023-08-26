package ro.myclass.billingmanagementapi.role.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.role.dto.RoleDTO;

@Service
public interface RoleCommandService {

   void addRole(RoleDTO roleDTO);

   void removeRole(String title);

   void updateRole(RoleDTO roleDTO);


}
