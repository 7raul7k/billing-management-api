package ro.myclass.billingmanagementapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.dto.PermissionDTO;
import ro.myclass.billingmanagementapi.models.Permission;
import ro.myclass.billingmanagementapi.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permission")
@Slf4j
public class PermissionResource {

    private PermissionService permissionService;

    public PermissionResource(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/allPermissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissionList = this.permissionService.getAllPermissions();

        log.info("REST request to get all permissions {}", permissionList);

        return new ResponseEntity<>(permissionList, HttpStatus.OK);
    }

    @PostMapping("/addPermission")
    public ResponseEntity<String> addPermission(PermissionDTO permission) {
        this.permissionService.addPermission(permission);

        log.info("REST request to add a permission {}", permission);

        return new ResponseEntity<>("Permission was added", HttpStatus.OK);
    }

    @GetMapping("/getPermissionById/{id}")
    public ResponseEntity<Permission> getPermissionById(long id) {
        Permission permission = this.permissionService.getPermissionById(id);

        log.info("REST request to get a permission by id {}", id);

        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping("/getPermissionByTitle/{title}")
    public ResponseEntity<Permission> getPermissionByTitle(String title) {
        Permission permission = this.permissionService.getPermissionByTitle(title);

        log.info("REST request to get a permission by title {}", title);

        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping("/getPermissionByModule/{module}")
    public ResponseEntity<Permission> getPermissionByModule(String module) {
        Permission permissions = (Permission) this.permissionService.getPermissionByModule(module);

        log.info("REST request to get a permission by module {}", module);

        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PutMapping("/updatePermission")
    public ResponseEntity<String> updatePermission(PermissionDTO permissionDTO) {
        this.permissionService.updatePermission(permissionDTO);

        log.info("REST request to update a permission {}", permissionDTO);

        return new ResponseEntity<>("Permission was updated", HttpStatus.OK);
    }

}