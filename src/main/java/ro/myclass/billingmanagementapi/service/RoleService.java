package ro.myclass.billingmanagementapi.service;

import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.dto.RoleDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.RoleNotFoundException;
import ro.myclass.billingmanagementapi.models.Role;
import ro.myclass.billingmanagementapi.repo.RoleRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }


    public List<Role> getAllRoles(){
        List<Role> roleList = this.roleRepo.getAllRole();

        if(roleList.isEmpty()){
        throw new ListEmptyException();
        }else{
            return roleList;
        }
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


    public Role getRolebyId(long id){
        Optional<Role> roleOptional = this.roleRepo.getRoleById(id);

        if(roleOptional.isEmpty()){
            throw new RoleNotFoundException();
        }else{
            return roleOptional.get();
        }
    }

    public Role getRoleByTitle(String title){
        Optional<Role> roleOptional = this.roleRepo.getRoleByTitle(title);

        if(roleOptional.isEmpty()){
            throw new RoleNotFoundException();
        }else{
            return roleOptional.get();
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
