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
import ro.myclass.billingmanagementapi.dto.RoleDTO;
import ro.myclass.billingmanagementapi.exceptions.RoleNotFoundException;
import ro.myclass.billingmanagementapi.models.Role;
import ro.myclass.billingmanagementapi.service.RoleService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RoleResourceTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleResource roleResource;

    public ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(roleResource).build();
    }


    @Test
    public void getAllRoles() throws Exception {

        Faker faker = new Faker();

        List<Role> roleList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add role with all properties
            roleList.add(Role.builder().id((long) i).title(faker.name().title()).build());
        }

        doReturn(roleList).when(roleService).getAllRoles();

        restMockMvc.perform(get("/api/v1/role/allRoles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getAllRolesBadRequest() throws Exception{
        doThrow(RoleNotFoundException.class).when(roleService).getAllRoles();

        restMockMvc.perform(get("/api/v1/role/allRoles"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRole() throws Exception {

        Faker faker = new Faker();

        RoleDTO roleDTO = RoleDTO.builder().title(faker.name().title()).build();

        doNothing().when(roleService).addRole(roleDTO);

        restMockMvc.perform(post("/api/v1/role/addRole").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(roleDTO)))
                .andExpect(status().isOk());


    }

    @Test
    public void addRoleBadRequest() throws Exception{
        Faker faker = new Faker();

        RoleDTO roleDTO = RoleDTO.builder().title(faker.name().title()).build();

        doThrow(RoleNotFoundException.class).when(roleService).addRole(roleDTO);

        restMockMvc.perform(post("/api/v1/role/addRole").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(roleDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteRole() throws Exception {

        Faker faker = new Faker();

        String title = faker.name().title();

        doNothing().when(roleService).removeRole(title);

        restMockMvc.perform(delete("/api/v1/role/deleteRole/{title}", title))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRoleBadRequest() throws Exception{
        Faker faker = new Faker();

        String title = faker.name().title();

        doThrow(RoleNotFoundException.class).when(roleService).removeRole(title);

        restMockMvc.perform(delete("/api/v1/role/deleteRole/{title}", title))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getRoleById() throws Exception {

        Faker faker = new Faker();

        Role role = Role.builder().id((long) 1).title(faker.name().title()).build();

        doReturn(role).when(roleService).getRolebyId(role.getId());

        restMockMvc.perform(get("/api/v1/role/getRoleById/{id}", role.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getRoleByIdBadRequest() throws Exception{
        Faker faker = new Faker();

        Role role = Role.builder().id((long) 1).title(faker.name().title()).build();

        doThrow(RoleNotFoundException.class).when(roleService).getRolebyId(role.getId());

        restMockMvc.perform(get("/api/v1/role/getRoleById/{id}", role.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateRole() throws Exception {

        Faker faker = new Faker();

        RoleDTO roleDTO = RoleDTO.builder().title(faker.name().title()).build();

        doNothing().when(roleService).updateRole(roleDTO);

        restMockMvc.perform(put("/api/v1/role/updateRole").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(roleDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRoleBadRequest() throws Exception{
        Faker faker = new Faker();

        RoleDTO roleDTO = RoleDTO.builder().title(faker.name().title()).build();

        doThrow(RoleNotFoundException.class).when(roleService).updateRole(roleDTO);

        restMockMvc.perform(put("/api/v1/role/updateRole").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(roleDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getRoleByTitle() throws Exception {

        Faker faker = new Faker();

        Role role = Role.builder().id((long) 1).title(faker.name().title()).build();

        doReturn(role).when(roleService).getRoleByTitle(role.getTitle());

        restMockMvc.perform(get("/api/v1/role/getRoleByTitle/{title}", role.getTitle()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getRoleByTitleBadRequest() throws Exception{
        Faker faker = new Faker();

        Role role = Role.builder().id((long) 1).title(faker.name().title()).build();

        doThrow(RoleNotFoundException.class).when(roleService).getRoleByTitle(role.getTitle());

        restMockMvc.perform(get("/api/v1/role/getRoleByTitle/{title}", role.getTitle()))
                .andExpect(status().isBadRequest());
    }




}