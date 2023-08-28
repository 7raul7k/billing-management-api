package ro.myclass.billingmanagementapi.username.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.username.service.UsernameCommandService;
import ro.myclass.billingmanagementapi.username.service.UsernameQuerryService;
import ro.myclass.billingmanagementapi.username.dto.UsernameDTO;
import ro.myclass.billingmanagementapi.username.models.Username;

import java.util.List;

@RestController
@RequestMapping("/api/v1/username")
@Slf4j
public class UsernameResource {

    private UsernameCommandService usernameCommandService;

    private UsernameQuerryService usernameQuerryService;

    public UsernameResource(UsernameCommandService usernameCommandService, UsernameQuerryService usernameQuerryService) {
        this.usernameCommandService = usernameCommandService;
        this.usernameQuerryService = usernameQuerryService;
    }

    @GetMapping("/allUsernames")
    public ResponseEntity<List<Username>> getAllUsernames() {
        List<Username> usernameList = this.usernameQuerryService.getAllUsernames();

        log.info("REST request to get all usernames {}", usernameList);

        return new ResponseEntity<>(usernameList, HttpStatus.OK);
    }

    @PostMapping("/addUsername")
    public ResponseEntity<String> addUsername(@RequestBody UsernameDTO username) {
        this.usernameCommandService.addUsername(username);

        log.info("REST request to add a username {}", username);

        return new ResponseEntity<>("Username was added", HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteUsername/{username}")
    public ResponseEntity<String> deleteUsername(@PathVariable String username) {
        this.usernameCommandService.deleteUsername(username);

        log.info("REST request to delete a username by username {}", username);

        return new ResponseEntity<>("Username was deleted", HttpStatus.OK);
    }
    
    @GetMapping("/getUsernameById/{id}")
    public ResponseEntity<Username> getUsernameById(@PathVariable long id) {
        Username username = this.usernameQuerryService.getUsernameById(id);

        log.info("REST request to get a username by id {}", id);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @GetMapping("/getUsernameByUsername/{username}")
    public ResponseEntity<Username> getUsernameByUsername(@PathVariable String username) {
        Username username1 = this.usernameQuerryService.getUsernameByUsername(username);

        log.info("REST request to get a username by username {}", username);

        return new ResponseEntity<>(username1, HttpStatus.OK);
    }

    @PutMapping("/updateUsername")
    public ResponseEntity<String> updateUsername(@RequestBody UsernameDTO usernameDTO) {
        this.usernameCommandService.updateUsername(usernameDTO);

        log.info("REST request to update a username {}", usernameDTO);

        return new ResponseEntity<>("Username was updated", HttpStatus.OK);
    }


    @GetMapping("/getUsernameByEmail/{email}")
    public ResponseEntity<Username> getUsernameByEmail(@PathVariable String email) {
        Username username = this.usernameQuerryService.getUsernameByEmail(email);

        log.info("REST request to get a username by email {}", email);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }




}
