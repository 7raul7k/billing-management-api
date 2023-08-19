package ro.myclass.billingmanagementapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.billingmanagementapi.BillingManagementApiApplication;
import ro.myclass.billingmanagementapi.models.Username;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BillingManagementApiApplication.class)
class UsernameRepoTest {

    @Autowired
    UsernameRepo usernameRepo;

    @BeforeEach
    public void clean(){ usernameRepo.deleteAll();}

    @Test
    public void getAllUsername(){

        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();
        Username username1 = Username.builder().id(2L).username("test1").address("test1").email("test1@gmail.com").dob(LocalDate.now()).build();
        Username username2 = Username.builder().id(3L).username("test2").address("test2").email("test2@gmail.com").dob(LocalDate.now()).build();
        Username username3 = Username.builder().id(4L).username("test3").address("test3").email("test3@gmail.com").dob(LocalDate.now()).build();

        usernameRepo.save(username);
        usernameRepo.save(username1);
        usernameRepo.save(username2);
        usernameRepo.save(username3);

        List<Username> usernameList = new ArrayList<>();

        usernameList.add(username);
        usernameList.add(username1);
        usernameList.add(username2);
        usernameList.add(username3);

        assertEquals(usernameList,this.usernameRepo.getAllUsername());

    }

    @Test
    public void getUsernameByAdress(){

        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();
        Username username1 = Username.builder().id(2L).username("test1").address("test").email("test1@gmail.com").dob(LocalDate.now()).build();
        Username username2 = Username.builder().id(3L).username("test2").address("test").email("test2@gmail.com").dob(LocalDate.now()).build();
        Username username3 = Username.builder().id(4L).username("test3").address("test").email("test3@gmail.com").dob(LocalDate.now()).build();

        usernameRepo.save(username);
        usernameRepo.save(username1);
        usernameRepo.save(username2);
        usernameRepo.save(username3);

        List<Username> usernameList = new ArrayList<>();

        usernameList.add(username);
        usernameList.add(username1);
        usernameList.add(username2);
        usernameList.add(username3);

        assertEquals(usernameList,this.usernameRepo.getUsernameByAddress("test"));

    }

    @Test
    public void getUsernameById(){
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        usernameRepo.save(username);

        assertEquals(username,this.usernameRepo.getUsernameById(1L).get());

    }

    @Test
    public void getUsernameByEmail(){

        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        usernameRepo.save(username);

        assertEquals(username,this.usernameRepo.getUsernameByEmail("test@gmail.com").get());

    }

    @Test
    public void getUsernameByUsername(){
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        usernameRepo.save(username);

        assertEquals(username,this.usernameRepo.getUsernameByUsername("test").get());

    }

}