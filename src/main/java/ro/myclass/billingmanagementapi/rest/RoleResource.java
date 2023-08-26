package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.RoleDTO;
import ro.myclass.billingmanagementapi.models.Role;
import ro.myclass.billingmanagementapi.service.RoleService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@Slf4j
public class RoleResource {

    private RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roleList = this.roleService.getAllRoles();

        log.info("REST request to get all roles {}",roleList);

        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @PostMapping("/addRole")
    public ResponseEntity<String> addRole(@RequestBody RoleDTO role){
        this.roleService.addRole(role);

        log.info("REST request to add a role {}",role);


        return new ResponseEntity<>("Role was added",HttpStatus.OK);
    }

    @DeleteMapping("/deleteRole/{title}")
    public ResponseEntity<String> deleteRole(@PathVariable String title){
        this.roleService.removeRole(title);

        log.info("REST request to delete a role by title {}",title);


        return new ResponseEntity<>("Role was deleted",HttpStatus.OK);
    }

    @GetMapping("/getRoleById/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") long id) {
        Role role = this.roleService.getRolebyId(id);

        log.info("REST request to get a role by id {}", id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/updateRole")
    public ResponseEntity<String> updateRole(@RequestBody RoleDTO roleDTO) {
        this.roleService.updateRole(roleDTO);

        log.info("REST request to update a role {}", roleDTO);

        return new ResponseEntity<>("Role was updated", HttpStatus.OK);
    }

    @GetMapping("/getRoleByTitle/{title}")
    public ResponseEntity<Role> getRoleByTitle(@PathVariable("title") String title) {
        Role role = this.roleService.getRoleByTitle(title);

        log.info("REST request to get a role by title {}", title);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }



}
