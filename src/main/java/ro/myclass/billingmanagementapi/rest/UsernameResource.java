package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.UsernameDTO;
import ro.myclass.billingmanagementapi.models.Username;
import ro.myclass.billingmanagementapi.service.UsernameService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/username")
@Slf4j
public class UsernameResource {

    private UsernameService usernameService;

    public UsernameResource(UsernameService usernameService) {
        this.usernameService = usernameService;
    }

    @GetMapping("/allUsernames")
    public ResponseEntity<List<Username>> getAllUsernames() {
        List<Username> usernameList = this.usernameService.getAllUsernames();

        log.info("REST request to get all usernames {}", usernameList);

        return new ResponseEntity<>(usernameList, HttpStatus.OK);
    }

    @PostMapping("/addUsername")
    public ResponseEntity<String> addUsername(UsernameDTO username) {
        this.usernameService.addUsername(username);

        log.info("REST request to add a username {}", username);

        return new ResponseEntity<>("Username was added", HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteUsername/{username}")
    public ResponseEntity<String> deleteUsername(String username) {
        this.usernameService.deleteUsername(username);

        log.info("REST request to delete a username by username {}", username);

        return new ResponseEntity<>("Username was deleted", HttpStatus.OK);
    }
    
    @GetMapping("/getUsernameById/{id}")
    public ResponseEntity<Username> getUsernameById(long id) {
        Username username = this.usernameService.getUsernameById(id);

        log.info("REST request to get a username by id {}", id);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @GetMapping("/getUsernameByUsername/{username}")
    public ResponseEntity<Username> getUsernameByUsername(String username) {
        Username username1 = this.usernameService.getUsernameByUsername(username);

        log.info("REST request to get a username by username {}", username);

        return new ResponseEntity<>(username1, HttpStatus.OK);
    }

    @PutMapping("/updateUsername")
    public ResponseEntity<String> updateUsername(UsernameDTO usernameDTO) {
        this.usernameService.updateUsername(usernameDTO);

        log.info("REST request to update a username {}", usernameDTO);

        return new ResponseEntity<>("Username was updated", HttpStatus.OK);
    }


    public ResponseEntity<Username> getUsernameByEmail(String email) {
        Username username = this.usernameService.getUsernameByEmail(email);

        log.info("REST request to get a username by email {}", email);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }




}
