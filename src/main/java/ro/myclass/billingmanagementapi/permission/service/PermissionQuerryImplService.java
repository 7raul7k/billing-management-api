package ro.myclass.billingmanagementapi.permission.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PermissionWasFoundException;
import ro.myclass.billingmanagementapi.permission.dto.PermissionDTO;
import ro.myclass.billingmanagementapi.permission.models.Permission;
import ro.myclass.billingmanagementapi.permission.repo.PermissionRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionQuerryImplService implements PermissionQuerryService{

    private PermissionRepo permissionRepo;

    public PermissionQuerryImplService(PermissionRepo permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    public List<Permission> getAllPermissions(){
        List<Permission> permissionList = this.permissionRepo.getAllPermission();

        if(permissionList.isEmpty()){
            throw new ListEmptyException();
    }

        return permissionList;
    }



    public void deletePermission(String title){
        Optional<Permission> permissionOptional = this.permissionRepo.getPermissionByTitle(title);

        if(permissionOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
            this.permissionRepo.delete(permissionOptional.get());
        }
    }


    public List<Permission> getPermissionByModule(String module){
        List<Permission> permissionOptional = this.permissionRepo.getPermissionByModule(module);

        if(permissionOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
           return permissionOptional;
        }
    }

    public Permission getPermissionByTitle(String title){
        Optional<Permission> permissionOptional = this.permissionRepo.getPermissionByTitle(title);

        if(permissionOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
            return permissionOptional.get();
        }
    }

}
