package ro.myclass.billingmanagementapi.role.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.role.dto.RoleDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.RoleNotFoundException;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.role.repo.RoleRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleCommandImplService implements RoleCommandService {

    private RoleRepo roleRepo;

    public RoleCommandImplService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }


    public void addRole(RoleDTO roleDTO){
        Optional<Role> roleOptional = this.roleRepo.getRoleByTitle(roleDTO.getTitle());

        if(roleOptional.isEmpty()){

            Role role = Role.builder().title(roleDTO.getTitle()).description(roleDTO.getDescription()).permissions(roleDTO.getPermissions()).build();

            this.roleRepo.save(role);
        }else{
            throw new RoleNotFoundException();
        }
    }

    public void removeRole(String title) {
        Optional<Role> roleOptional = this.roleRepo.getRoleByTitle(title);

        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException();
        } else {
            this.roleRepo.delete(roleOptional.get());
        }

    }


    public void updateRole(RoleDTO roleDTO){
        Optional<Role> roleOptional = this.roleRepo.getRoleByTitle(roleDTO.getTitle());

        if(roleOptional.isEmpty()){
            throw new RoleNotFoundException();
        }else{
            Role role = roleOptional.get();
         if(roleDTO.getTitle()!=null){
             role.setTitle(roleDTO.getTitle());
        }if(roleDTO.getDescription()!=null){
             role.setDescription(roleDTO.getDescription());
         }if(roleDTO.getPermissions()!=null){
             role.setPermissions(roleDTO.getPermissions());
         }

            this.roleRepo.saveAndFlush(role);
        }
    }
}
