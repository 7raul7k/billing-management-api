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
public class UsernameService implements UsernameQuerryService, UsernameCommandService {

    private UsernameRepo usernameRepo;

    public UsernameService(UsernameRepo usernameRepo) {
        this.usernameRepo = usernameRepo;
    }

    public List<Username> getAllUsernames(){
        List<Username> usernameList = this.usernameRepo.getAllUsername();

        if(usernameList.isEmpty()){
        throw new ListEmptyException();
        }else{
            return usernameList;
        }
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

    public Username getUsernameById(long id){
        Optional<Username> usernameOptional = this.usernameRepo.getUsernameById(id);

        if(usernameOptional.isEmpty()){
            throw new UsernameNotFoundException();
        }else{
            return usernameOptional.get();
        }
    }

    public Username getUsernameByUsername(String username){
        Optional<Username> usernameOptional = this.usernameRepo.getUsernameByUsername(username);

        if(usernameOptional.isEmpty()){
            throw new UsernameNotFoundException();
        }else{
            return usernameOptional.get();
        }
    }

    public Username getUsernameByEmail(String email){
        Optional<Username> usernameOptional = this.usernameRepo.getUsernameByEmail(email);

        if(usernameOptional.isEmpty()){
            throw new UsernameNotFoundException();
        }else{
            return usernameOptional.get();
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
