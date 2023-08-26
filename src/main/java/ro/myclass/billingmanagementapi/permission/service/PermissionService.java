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
public class PermissionService implements PermissionQuerryService, PermissionCommandService{

    private PermissionRepo permissionRepo;

    public PermissionService(PermissionRepo permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    public List<Permission> getAllPermissions(){
        List<Permission> permissionList = this.permissionRepo.getAllPermission();

        if(permissionList.isEmpty()){
            throw new ListEmptyException();
    }

        return permissionList;
    }

    public void addPermission(PermissionDTO permissionDTO){
       Optional<Permission> permissionOptional = this.permissionRepo.getPermissionByTitle(permissionDTO.getTitle());

        if(permissionOptional.isEmpty()){
            Permission permission = Permission.builder().title(permissionDTO.getTitle()).description(permissionDTO.getDescription()).module(permissionDTO.getModule()).build();

            this.permissionRepo.save(permission);
        }else{
            throw new PermissionWasFoundException();
        }
    }

    public void deletePermission(String title){
        Optional<Permission> permissionOptional = this.permissionRepo.getPermissionByTitle(title);

        if(permissionOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
            this.permissionRepo.delete(permissionOptional.get());
        }
    }

    public Permission getPermissionById(long id){
        Optional<Permission> permissionOptional = this.permissionRepo.getPermissionById(id);

        if(permissionOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
            return permissionOptional.get();
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

    public void updatePermission(PermissionDTO permissionDTO){
        Optional<Permission> permissionOptional = this.permissionRepo.getPermissionByTitle(permissionDTO.getTitle());

        if(permissionOptional.isEmpty()){
            throw new ListEmptyException();
        }else{
           if(permissionDTO.getDescription() != null){
               permissionOptional.get().setDescription(permissionDTO.getDescription());
           }if(permissionDTO.getTitle() != null){
               permissionOptional.get().setTitle(permissionDTO.getTitle());
           }if(permissionDTO.getModule() != null){
               permissionOptional.get().setModule(permissionDTO.getModule());
           }if(permissionDTO.getRole() != null){
               permissionOptional.get().setRole(permissionDTO.getRole());
           }

           this.permissionRepo.saveAndFlush(permissionOptional.get());
        }
    }
}
