package ro.myclass.billingmanagementapi.role.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.role.dto.RoleDTO;
import ro.myclass.billingmanagementapi.role.service.RoleCommandService;
import ro.myclass.billingmanagementapi.role.service.RoleQuerryService;
import ro.myclass.billingmanagementapi.role.service.RoleService;
import ro.myclass.billingmanagementapi.role.models.Role;


import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@Slf4j
public class RoleResource {

    private RoleCommandService roleCommandService;

    private RoleQuerryService roleQuerryService;

    public RoleResource(RoleCommandService roleCommandService, RoleQuerryService roleQuerryService) {
        this.roleCommandService = roleCommandService;
        this.roleQuerryService = roleQuerryService;
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roleList = this.roleQuerryService.getAllRoles();

        log.info("REST request to get all roles {}",roleList);

        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @PostMapping("/addRole")
    public ResponseEntity<String> addRole(@RequestBody RoleDTO role){
        this.roleCommandService.addRole(role);

        log.info("REST request to add a role {}",role);


        return new ResponseEntity<>("Role was added",HttpStatus.OK);
    }

    @DeleteMapping("/deleteRole/{title}")
    public ResponseEntity<String> deleteRole(@PathVariable String title){
        this.roleCommandService.removeRole(title);

        log.info("REST request to delete a role by title {}",title);


        return new ResponseEntity<>("Role was deleted",HttpStatus.OK);
    }

    @GetMapping("/getRoleById/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") long id) {
        Role role = this.roleQuerryService.getRolebyId(id);

        log.info("REST request to get a role by id {}", id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/updateRole")
    public ResponseEntity<String> updateRole(@RequestBody RoleDTO roleDTO) {
        this.roleCommandService.updateRole(roleDTO);

        log.info("REST request to update a role {}", roleDTO);

        return new ResponseEntity<>("Role was updated", HttpStatus.OK);
    }

    @GetMapping("/getRoleByTitle/{title}")
    public ResponseEntity<Role> getRoleByTitle(@PathVariable("title") String title) {
        Role role = this.roleQuerryService.getRoleByTitle(title);

        log.info("REST request to get a role by title {}", title);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }



}
