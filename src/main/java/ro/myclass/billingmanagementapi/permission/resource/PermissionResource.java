package ro.myclass.billingmanagementapi.permission.resource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.billingmanagementapi.permission.dto.PermissionDTO;
import ro.myclass.billingmanagementapi.permission.models.Permission;
import ro.myclass.billingmanagementapi.permission.service.PermissionCommandService;
import ro.myclass.billingmanagementapi.permission.service.PermissionQuerryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permission")
@Slf4j
public class PermissionResource {

    private PermissionCommandService permissionService;

    private PermissionQuerryService permissionQuerryService;

    public PermissionResource(PermissionCommandService permissionService, PermissionQuerryService permissionQuerryService) {
        this.permissionService = permissionService;
        this.permissionQuerryService = permissionQuerryService;
    }

    @GetMapping("/allPermissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissionList = this.permissionQuerryService.getAllPermissions();

        log.info("REST request to get all permissions {}", permissionList);

        return new ResponseEntity<>(permissionList, HttpStatus.OK);
    }

    @PostMapping("/addPermission")
    public ResponseEntity<String> addPermission(@RequestBody PermissionDTO permission) {
        this.permissionService.addPermission(permission);

        log.info("REST request to add a permission {}", permission);

        return new ResponseEntity<>("Permission was added", HttpStatus.OK);
    }

    @GetMapping("/getPermissionById/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable long id) {
        Permission permission = this.permissionQuerryService.getPermissionById(id);

        log.info("REST request to get a permission by id {}", id);

        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping("/getPermissionByTitle/{title}")
    public ResponseEntity<Permission> getPermissionByTitle(@PathVariable String title) {
        Permission permission = this.permissionQuerryService.getPermissionByTitle(title);

        log.info("REST request to get a permission by title {}", title);

        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping("/getPermissionByModule/{module}")
    public ResponseEntity<List<Permission>> getPermissionByModule(@PathVariable String module) {

        List<Permission> permissions = this.permissionQuerryService.getPermissionByModule(module);

        log.info("REST request to get a permission by module {}", module);

        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PutMapping("/updatePermission")
    public ResponseEntity<String> updatePermission(@RequestBody PermissionDTO permissionDTO) {
        this.permissionService.updatePermission(permissionDTO);

        log.info("REST request to update a permission {}", permissionDTO);

        return new ResponseEntity<>("Permission was updated", HttpStatus.OK);
    }

}
