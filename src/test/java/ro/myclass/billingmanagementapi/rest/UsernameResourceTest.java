package ro.myclass.billingmanagementapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.billingmanagementapi.username.dto.UsernameDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.UsernameNotFoundException;
import ro.myclass.billingmanagementapi.username.models.Username;
import ro.myclass.billingmanagementapi.username.rest.UsernameResource;
import ro.myclass.billingmanagementapi.username.service.UsernameCommandService;
import ro.myclass.billingmanagementapi.username.service.UsernameQuerryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsernameResourceTest {

    @Mock
    private UsernameCommandService usernameCommandService;

    @Mock
    private UsernameQuerryService usernameQuerryService;

    @InjectMocks
    private UsernameResource usernameResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(usernameResource).build();
    }

    @Test
    public void getAllUsernames() throws Exception {

        Faker faker = new Faker();

        List<Username> usernameList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add username with all properties
            usernameList.add(Username.builder().id((long) i).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build());
        }

        doReturn(usernameList).when(usernameQuerryService).getAllUsernames();

        restMockMvc.perform(get("/api/v1/username/allUsernames"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usernameList)));
    }

    @Test
    public void getAllUsernamesBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(usernameQuerryService).getAllUsernames();

        restMockMvc.perform(get("/api/v1/username/allUsernames"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addUsername() throws Exception {
        Faker faker = new Faker();

        //create usernamedto with all properties
        UsernameDTO username = UsernameDTO.builder().username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();
        doNothing().when(usernameCommandService).addUsername(username);

        restMockMvc.perform(post("/api/v1/username/addUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(username)))
                .andExpect(status().isOk());

    }

    @Test
    public void addUsernameBadRequest() throws Exception {
        Faker faker = new Faker();

        //create usernamedto with all properties
        UsernameDTO username = UsernameDTO.builder().username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();
        doThrow(ListEmptyException.class).when(usernameCommandService).addUsername(username);

        restMockMvc.perform(post("/api/v1/username/addUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(username)))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void deleteUsername() throws Exception {


        doNothing().when(usernameCommandService).deleteUsername("username");

        restMockMvc.perform(delete("/api/v1/username/deleteUsername/username"))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteUsernameBadRequest() throws Exception {
        doThrow(UsernameNotFoundException.class).when(usernameCommandService).deleteUsername("username");

        restMockMvc.perform(delete("/api/v1/username/deleteUsername/username"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUsernameById() throws Exception {
        Faker faker = new Faker();

        Username username = Username.builder().id((long) 1).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doReturn(username).when(usernameQuerryService).getUsernameById(1);

        restMockMvc.perform(get("/api/v1/username/getUsernameById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(username)));
    }

    @Test
    public void getUsernameByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Username username = Username.builder().id((long) 1).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doThrow(UsernameNotFoundException.class).when(usernameQuerryService).getUsernameById(1);

        restMockMvc.perform(get("/api/v1/username/getUsernameById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUsernameByUsername() throws Exception {
        Faker faker = new Faker();

        Username username = Username.builder().id((long) 1).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doReturn(username).when(usernameQuerryService).getUsernameByUsername("username");

        restMockMvc.perform(get("/api/v1/username/getUsernameByUsername/username"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(username)));
    }

    @Test
    public void getUsernameByUsernameBadRequest() throws Exception {
        Faker faker = new Faker();

        Username username = Username.builder().id((long) 1).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doThrow(UsernameNotFoundException.class).when(usernameQuerryService).getUsernameByUsername("username");

        restMockMvc.perform(get("/api/v1/username/getUsernameByUsername/username"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUsername() throws Exception {
        Faker faker = new Faker();

        UsernameDTO usernameDTO = UsernameDTO.builder().username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doNothing().when(usernameCommandService).updateUsername(usernameDTO);

        restMockMvc.perform(put("/api/v1/username/updateUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(usernameDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUsernameBadRequest() throws Exception {
        Faker faker = new Faker();

        UsernameDTO usernameDTO = UsernameDTO.builder().username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doThrow(UsernameNotFoundException.class).when(usernameCommandService).updateUsername(usernameDTO);

        restMockMvc.perform(put("/api/v1/username/updateUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(usernameDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUsernameByEmail() throws Exception {
        Faker faker = new Faker();

        Username username = Username.builder().id((long) 1).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doReturn(username).when(usernameQuerryService).getUsernameByEmail("email");

        restMockMvc.perform(get("/api/v1/username/getUsernameByEmail/email"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(username)));
    }

    @Test
    public void getUsernameByEmailBadRequest() throws Exception {
        Faker faker = new Faker();

        Username username = Username.builder().id((long) 1).username(faker.name().username()).email(faker.internet().emailAddress()).address(faker.address().fullAddress()).build();

        doThrow(UsernameNotFoundException.class).when(usernameQuerryService).getUsernameByEmail("email");

        restMockMvc.perform(get("/api/v1/username/getUsernameByEmail/email"))
                .andExpect(status().isBadRequest());
    }



}