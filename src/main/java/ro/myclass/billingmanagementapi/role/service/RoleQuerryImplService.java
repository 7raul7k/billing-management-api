package ro.myclass.billingmanagementapi.role.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.RoleNotFoundException;
import ro.myclass.billingmanagementapi.role.dto.RoleDTO;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.role.repo.RoleRepo;

import java.util.List;
import java.util.Optional;

@Service

public class RoleQuerryImplService implements RoleQuerryService{

    private RoleRepo roleRepo;

    public RoleQuerryImplService(RoleRepo roleRepo) {
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



}
