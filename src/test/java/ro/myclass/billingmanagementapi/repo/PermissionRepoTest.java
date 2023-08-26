package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.permission.models.Permission;
import ro.myclass.billingmanagementapi.permission.repo.PermissionRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class PermissionRepoTest {

    @Autowired
    PermissionRepo permissionRepo;

    @BeforeEach
    public void clean(){ this.permissionRepo.deleteAll();}

    @Test
    public void getAllPermission(){
        Permission permission = Permission.builder().id(1L).description("test").title("test").module("test").build();
        Permission permission2 = Permission.builder().id(2L).description("test2").title("test2").module("test2").build();
        Permission permission3 = Permission.builder().id(3L).description("test3").title("test3").module("test3").build();
        Permission permission4 = Permission.builder().id(4L).description("test4").title("test4").module("test4").build();

        permissionRepo.save(permission);
        permissionRepo.save(permission2);
        permissionRepo.save(permission3);
        permissionRepo.save(permission4);

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);
        permissionList.add(permission2);
        permissionList.add(permission3);
        permissionList.add(permission4);


        assertEquals(permissionList,this.permissionRepo.getAllPermission());

    }

    @Test
    public void getPermissionById(){

        Permission permission = Permission.builder().id(1L).description("test").title("test").module("test").build();

        permissionRepo.save(permission);



        assertEquals(permission,this.permissionRepo.getPermissionById(1L).get());

    }

    @Test
    public void getPermissionByTitle(){


        Permission permission = Permission.builder().id(1L).description("test").title("test").module("test").build();

        permissionRepo.save(permission);


        assertEquals(permission,this.permissionRepo.getPermissionByTitle("test").get());

    }

    @Test
    public void getPermissionByModule(){

        Permission permission = Permission.builder().id(1L).description("test").title("test1").module("test").build();
        Permission permission2 = Permission.builder().id(2L).description("test2").title("test2").module("test").build();
        Permission permission3 = Permission.builder().id(3L).description("test3").title("test3").module("test").build();
        Permission permission4 = Permission.builder().id(4L).description("test4").title("test4").module("test").build();

        permissionRepo.save(permission);
        permissionRepo.save(permission2);
        permissionRepo.save(permission3);
        permissionRepo.save(permission4);

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);
        permissionList.add(permission2);
        permissionList.add(permission3);
        permissionList.add(permission4);


        assertEquals(permissionList,this.permissionRepo.getPermissionByModule("test"));
    }
}