package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.role.repo.RoleRepo;
import ro.myclass.billingmanagementapi.role.models.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class RoleRepoTest {

    @Autowired
    RoleRepo roleRepo;

    @BeforeEach
    public void clean(){roleRepo.deleteAll();}

    @Test
    public void getAllRole(){
        Role role = Role.builder().id(1L).title("test").description("test").build();
        Role role2 = Role.builder().id(2L).title("test1").description("test1").build();
        Role role3 = Role.builder().id(3L).title("test2").description("test2").build();
        Role role4 = Role.builder().id(4L).title("test3").description("test3").build();

        roleRepo.save(role);
        roleRepo.save(role2);
        roleRepo.save(role3);
        roleRepo.save(role4);

        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        roleList.add(role2);
        roleList.add(role3);
        roleList.add(role4);

        assertEquals(roleList,this.roleRepo.getAllRole());

    }

    @Test
    public void getRoleById(){
        Role role = Role.builder().title("test").description("test").build();

        roleRepo.save(role);

        assertEquals(role,this.roleRepo.getRoleById(1L).get());

    }

    @Test
    public void getRoleByTitle(){

        Role role = Role.builder().title("test").description("test").build();

        roleRepo.save(role);


        assertEquals(role,this.roleRepo.getRoleByTitle("test").get());

    }


}