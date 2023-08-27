package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.role.dto.RoleDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.RoleNotFoundException;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.role.repo.RoleRepo;
import ro.myclass.billingmanagementapi.role.service.RoleCommandService;
import ro.myclass.billingmanagementapi.role.service.RoleQuerryService;
import ro.myclass.billingmanagementapi.role.service.RoleService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private RoleCommandService roleService = new RoleService(roleRepo);

    @InjectMocks
    private RoleQuerryService roleQuerryService = new RoleService(roleRepo);

    @Captor
    private ArgumentCaptor<Role> argumentCaptor;

    @Test
    public void getAllRole(){

        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();
        Role role1 = Role.builder().title("title1").description("description1").permissions(new ArrayList<>()).build();
        Role role2 = Role.builder().title("title2").description("description2").permissions(new ArrayList<>()).build();
        Role role3 = Role.builder().title("title3").description("description3").permissions(new ArrayList<>()).build();
        Role role4 = Role.builder().title("title4").description("description4").permissions(new ArrayList<>()).build();


        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        roleList.add(role1);
        roleList.add(role2);
        roleList.add(role3);
        roleList.add(role4);

        doReturn(roleList).when(roleRepo).getAllRole();

        assertEquals(roleList,roleQuerryService.getAllRoles());
    }

    @Test

    public void getAllRoleException(){
        doReturn(new ArrayList<>()).when(roleRepo).getAllRole();

        assertThrows(ListEmptyException.class,()->roleQuerryService.getAllRoles());
    }
    @Test
    public void addRole(){

        RoleDTO roleDTO = RoleDTO.builder().title("title").description("description").permissions(new ArrayList<>()).build();
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.empty()).when(roleRepo).getRoleByTitle(roleDTO.getTitle());

        roleService.addRole(roleDTO);

        verify(roleRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(role,argumentCaptor.getValue());

    }

    //create addRoleException
    @Test
    public void addRoleException(){
        RoleDTO roleDTO = RoleDTO.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.of(new Role())).when(roleRepo).getRoleByTitle(roleDTO.getTitle());

        assertThrows(RoleNotFoundException.class,()->roleService.addRole(roleDTO));
    }


    @Test
    public void removeRole(){
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.of(role)).when(roleRepo).getRoleByTitle(role.getTitle());

        roleService.removeRole(role.getTitle());

        verify(roleRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(role,argumentCaptor.getValue());
    }

    // create removeRoleException without create a role
    @Test
    public void removeRoleException(){
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.empty()).when(roleRepo).getRoleByTitle(role.getTitle());

        assertThrows(RoleNotFoundException.class,()->roleService.removeRole(role.getTitle()));
    }


    @Test
    public void getRoleById(){
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.of(role)).when(roleRepo).getRoleById(role.getId());

        assertEquals(role,roleQuerryService.getRolebyId(role.getId()));
    }


    @Test
    public void getRoleByIdException(){
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.empty()).when(roleRepo).getRoleById(role.getId());

        assertThrows(RoleNotFoundException.class,()->roleQuerryService.getRolebyId(role.getId()));
    }


    @Test
    public void getRoleByTitle(){
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.of(role)).when(roleRepo).getRoleByTitle(role.getTitle());

        assertEquals(role,roleQuerryService.getRoleByTitle(role.getTitle()));
    }

    @Test
    public void getRoleByTitleException(){
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.empty()).when(roleRepo).getRoleByTitle(role.getTitle());

        assertThrows(RoleNotFoundException.class,()->roleQuerryService.getRoleByTitle(role.getTitle()));
    }

    @Test
    public void updateRole(){
        RoleDTO roleDTO = RoleDTO.builder().title("title").description("description").permissions(new ArrayList<>()).build();
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.of(role)).when(roleRepo).getRoleByTitle(roleDTO.getTitle());

        roleService.updateRole(roleDTO);

        verify(roleRepo,times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(role,argumentCaptor.getValue());
    }

    @Test
    public void updateRoleException(){
        RoleDTO roleDTO = RoleDTO.builder().title("title").description("description").permissions(new ArrayList<>()).build();
        Role role = Role.builder().title("title").description("description").permissions(new ArrayList<>()).build();

        doReturn(Optional.empty()).when(roleRepo).getRoleByTitle(roleDTO.getTitle());

        assertThrows(RoleNotFoundException.class,()->roleService.updateRole(roleDTO));
    }



}