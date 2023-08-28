package ro.myclass.billingmanagementapi.username.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.UsernameNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.UsernameWasFoundException;
import ro.myclass.billingmanagementapi.username.dto.UsernameDTO;
import ro.myclass.billingmanagementapi.username.models.Username;
import ro.myclass.billingmanagementapi.username.repo.UsernameRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsernameCommandImplService implements UsernameCommandService {

    private UsernameRepo usernameRepo;

    public UsernameCommandImplService(UsernameRepo usernameRepo) {
        this.usernameRepo = usernameRepo;
    }



    @Transactional
    public void addUsername(UsernameDTO usernameDTO){
      Optional<Username> usernameOptional = this.usernameRepo.getUsernameByUsername(usernameDTO.getUsername());

        if(usernameOptional.isEmpty()){
            Username username = Username.builder().username(usernameDTO.getUsername()).dob(usernameDTO.getDob()).email(usernameDTO.getEmail()).address(usernameDTO.getAddress()).build();

            this.usernameRepo.save(username);
        }else{
            throw new UsernameWasFoundException();
        }
    }

    @Transactional
    public void deleteUsername(String username){
        Optional<Username> usernameOptional = this.usernameRepo.getUsernameByUsername(username);

        if(usernameOptional.isEmpty()){
            throw new UsernameNotFoundException();
        }else{
            this.usernameRepo.delete(usernameOptional.get());
        }
    }

    public void updateUsername(UsernameDTO usernameDTO){
        Optional<Username> usernameOptional = this.usernameRepo.getUsernameByUsername(usernameDTO.getUsername());

        if(usernameOptional.isEmpty()){
            throw new UsernameNotFoundException();
        }else{
           if(usernameDTO.getEmail()!=null){
               usernameOptional.get().setEmail(usernameDTO.getEmail());
           }if(usernameDTO.getAddress()!=null){
               usernameOptional.get().setAddress(usernameDTO.getAddress());
        }if(usernameDTO.getDob()!=null){
               usernameOptional.get().setDob(usernameDTO.getDob());
           }
        }if(usernameDTO.getRole()!=null){
            usernameOptional.get().setRole(usernameDTO.getRole());
        }

        this.usernameRepo.saveAndFlush(usernameOptional.get());
    }
}
