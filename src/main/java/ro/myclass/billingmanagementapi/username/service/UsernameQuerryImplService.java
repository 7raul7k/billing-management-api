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
public class UsernameQuerryImplService implements UsernameQuerryService {

    private UsernameRepo usernameRepo;

    public UsernameQuerryImplService(UsernameRepo usernameRepo) {
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


}
