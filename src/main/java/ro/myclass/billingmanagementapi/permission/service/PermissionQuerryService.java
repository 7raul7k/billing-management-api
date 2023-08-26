package ro.myclass.billingmanagementapi.permission.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.permission.models.Permission;

import java.util.List;

@Service
public interface PermissionQuerryService {

    List<Permission> getAllPermissions();

    Permission getPermissionById(long id);

    List<Permission> getPermissionByModule(String module);

    Permission getPermissionByTitle(String title);


}
