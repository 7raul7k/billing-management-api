package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.permission.dto.PermissionDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.PermissionWasFoundException;
import ro.myclass.billingmanagementapi.permission.models.Permission;
import ro.myclass.billingmanagementapi.permission.repo.PermissionRepo;
import ro.myclass.billingmanagementapi.permission.service.PermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Mock
    private PermissionRepo permissionRepo;

    @InjectMocks
    private PermissionService permissionService;

    @Captor
    private ArgumentCaptor<Permission> argumentCaptor;

    @Test
    public void getAllPermissions(){

        Permission permission = Permission.builder().id(1L).title("test").description("test").build();
        Permission permission1 = Permission.builder().id(2L).title("test1").description("test1").build();
        Permission permission2 = Permission.builder().id(3L).title("test2").description("test2").build();
        Permission permission3 = Permission.builder().id(4L).title("test3").description("test3").build();

        permissionRepo.save(permission);
        permissionRepo.save(permission1);
        permissionRepo.save(permission2);
        permissionRepo.save(permission3);

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);
        permissionList.add(permission1);
        permissionList.add(permission2);
        permissionList.add(permission3);

        doReturn(permissionList).when(permissionRepo).getAllPermission();

        assertEquals(permissionList, permissionService.getAllPermissions());
    }

    @Test
    public void getAllPermissionsEmptyList(){
        List<Permission> permissionList = new ArrayList<>();

        doReturn(permissionList).when(permissionRepo).getAllPermission();

        assertThrows(ListEmptyException.class, () -> permissionService.getAllPermissions());
    }

    @Test
    public void addPermission(){

        // create 1 permission with all properties

        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        PermissionDTO permissionDTO = PermissionDTO.builder().title("test").description("test").module("test").build();


        doReturn(Optional.empty()).when(permissionRepo).getPermissionByTitle(permissionDTO.getTitle());

        permissionService.addPermission(permissionDTO);

        verify(permissionRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(permission,argumentCaptor.getValue());
    }

    @Test
    public void addPermissionException(){

            PermissionDTO permissionDTO = PermissionDTO.builder().title("test").description("test").module("test").build();

            doReturn(Optional.of(Permission.builder().build())).when(permissionRepo).getPermissionByTitle(permissionDTO.getTitle());

            assertThrows(PermissionWasFoundException.class, () -> permissionService.addPermission(permissionDTO));
    }

    @Test
    public void deletePermission(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        PermissionDTO permissionDTO = PermissionDTO.builder().title("test").description("test").module("test").build();

        doReturn(Optional.of(permission)).when(permissionRepo).getPermissionByTitle(permissionDTO.getTitle());

        permissionService.deletePermission(permissionDTO.getTitle());

        verify(permissionRepo,times(1)).delete(permission);
    }

    @Test
    public void deletePermissionException(){
        PermissionDTO permissionDTO = PermissionDTO.builder().title("test").description("test").module("test").build();

        doReturn(Optional.empty()).when(permissionRepo).getPermissionByTitle(permissionDTO.getTitle());

        assertThrows(ListEmptyException.class, () -> permissionService.deletePermission(permissionDTO.getTitle()));
    }

    @Test
    public void getPermissionById(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        doReturn(Optional.of(permission)).when(permissionRepo).getPermissionById(permission.getId());

        assertEquals(permission,permissionService.getPermissionById(permission.getId()));
    }

    @Test
    public void getPermissionByIdException(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        doReturn(Optional.empty()).when(permissionRepo).getPermissionById(permission.getId());

        assertThrows(ListEmptyException.class, () -> permissionService.getPermissionById(permission.getId()));
    }

    @Test
    public void getPermissionByModule(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();
        Permission permission1 = Permission.builder().id(2L).title("test1").description("test1").module("test").build();
        Permission permission2 = Permission.builder().id(3L).title("test2").description("test2").module("test").build();
        Permission permission3 = Permission.builder().id(4L).title("test3").description("test3").module("test").build();

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);
        permissionList.add(permission1);
        permissionList.add(permission2);
        permissionList.add(permission3);

        doReturn(permissionList).when(permissionRepo).getPermissionByModule(permission.getModule());

        assertEquals(permissionList,permissionService.getPermissionByModule(permission.getModule()));
    }

    @Test
    public void getPermissionByModuleException(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        doReturn(new ArrayList<>()).when(permissionRepo).getPermissionByModule(permission.getModule());

        assertThrows(ListEmptyException.class, () -> permissionService.getPermissionByModule(permission.getModule()));
    }

    @Test
    public void getPermissionByTitle(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        doReturn(Optional.of(permission)).when(permissionRepo).getPermissionByTitle(permission.getTitle());

        assertEquals(permission,permissionService.getPermissionByTitle(permission.getTitle()));
    }

    @Test
    public void getPermissionByTitleException(){
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        doReturn(Optional.empty()).when(permissionRepo).getPermissionByTitle(permission.getTitle());

        assertThrows(ListEmptyException.class, () -> permissionService.getPermissionByTitle(permission.getTitle()));
    }

    @Test
    public void updatePermission() {
        Permission permission = Permission.builder().id(1L).title("test").description("test").module("test").build();

        PermissionDTO permissionDTO = PermissionDTO.builder().title("test").description("test").module("test").build();

        doReturn(Optional.of(permission)).when(permissionRepo).getPermissionByTitle(permissionDTO.getTitle());

        permissionService.updatePermission(permissionDTO);

        verify(permissionRepo, times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(permission, argumentCaptor.getValue());
    }

    @Test
    public void updatePermissionException(){
        PermissionDTO permissionDTO = PermissionDTO.builder().title("test").description("test").module("test").build();

        doReturn(Optional.empty()).when(permissionRepo).getPermissionByTitle(permissionDTO.getTitle());

        assertThrows(ListEmptyException.class, () -> permissionService.updatePermission(permissionDTO));
    }



}