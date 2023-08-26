package ro.myclass.billingmanagementapi.role.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.role.models.Role;

import java.util.List;

@Service
public interface RoleQuerryService {

    List<Role> getAllRoles();

    Role getRolebyId(long id);

    Role getRoleByTitle(String title);


}
