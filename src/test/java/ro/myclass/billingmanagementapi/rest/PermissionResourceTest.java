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
import ro.myclass.billingmanagementapi.dto.PermissionDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.models.Permission;
import ro.myclass.billingmanagementapi.service.PermissionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class PermissionResourceTest {

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private PermissionResource permissionResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(permissionResource).build();
    }

    @Test
    public void getAllPermissions() throws Exception {

        Faker faker = new Faker();

        List<Permission> permissionList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add permission with all properties
            permissionList.add(Permission.builder().id((long) i).title(faker.name().title()).build());
        }

        doReturn(permissionList).when(permissionService).getAllPermissions();

        restMockMvc.perform(get("/api/v1/permission/allPermissions"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(permissionList)));
    }



    @Test
    public void getAllPermissionsBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(permissionService).getAllPermissions();

        restMockMvc.perform(get("/api/v1/permission/allPermissions"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPermission() throws Exception {
        PermissionDTO permission = PermissionDTO.builder().title("title").module("module").build();

        restMockMvc.perform(post("/api/v1/permission/addPermission").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isOk());
    }

    @Test
    public void addPermissionBadRequest() throws Exception {
        PermissionDTO permission = PermissionDTO.builder().title("title").module("module").build();

        doThrow(ListEmptyException.class).when(permissionService).addPermission(permission);

        restMockMvc.perform(post("/api/v1/permission/addPermission").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPermissionById() throws Exception {
        Faker faker = new Faker();

        Permission permission = Permission.builder().id((long) 1).title(faker.name().title()).build();

        doReturn(permission).when(permissionService).getPermissionById(1);

        restMockMvc.perform(get("/api/v1/permission/getPermissionById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(permission)));
    }

    @Test
    public void getPermissionByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Permission permission = Permission.builder().id((long) 1).title(faker.name().title()).build();

        doThrow(ListEmptyException.class).when(permissionService).getPermissionById(1);

        restMockMvc.perform(get("/api/v1/permission/getPermissionById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPermissionByTitle() throws Exception {
        Faker faker = new Faker();

        Permission permission = Permission.builder().id((long) 1).title(faker.name().title()).build();

        doReturn(permission).when(permissionService).getPermissionByTitle("title");

        restMockMvc.perform(get("/api/v1/permission/getPermissionByTitle/title"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(permission)));
    }

    @Test
    public void getPermissionByTitleBadRequest() throws Exception {
        Faker faker = new Faker();

        Permission permission = Permission.builder().id((long) 1).title(faker.name().title()).build();

        doThrow(ListEmptyException.class).when(permissionService).getPermissionByTitle("title");

        restMockMvc.perform(get("/api/v1/permission/getPermissionByTitle/title"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPermissionByModule() throws Exception {
        Faker faker = new Faker();

        List<Permission> permissionList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add permission with all properties
            permissionList.add(Permission.builder().id((long) i).title(faker.name().title()).build());
        }

        doReturn(permissionList).when(permissionService).getPermissionByModule("module");

        restMockMvc.perform(get("/api/v1/permission/getPermissionByModule/module"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(permissionList)));
    }

    @Test
    public void getPermissionByModuleBadRequest() throws Exception {
        Faker faker = new Faker();

        List<Permission> permissionList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add permission with all properties
            permissionList.add(Permission.builder().id((long) i).title(faker.name().title()).build());
        }

        doThrow(ListEmptyException.class).when(permissionService).getPermissionByModule("module");

        restMockMvc.perform(get("/api/v1/permission/getPermissionByModule/module"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePermission() throws Exception {
        PermissionDTO permission = PermissionDTO.builder().title("title").module("module").build();

        restMockMvc.perform(put("/api/v1/permission/updatePermission").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePermissionBadRequest() throws Exception {
        PermissionDTO permission = PermissionDTO.builder().title("title").module("module").build();

        doThrow(ListEmptyException.class).when(permissionService).updatePermission(permission);

        restMockMvc.perform(put("/api/v1/permission/updatePermission").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isBadRequest());
    }

}