package ro.myclass.billingmanagementapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.billingmanagementapi.username.dto.UsernameDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.exceptions.UsernameNotFoundException;
import ro.myclass.billingmanagementapi.exceptions.UsernameWasFoundException;
import ro.myclass.billingmanagementapi.username.models.Username;
import ro.myclass.billingmanagementapi.username.repo.UsernameRepo;
import ro.myclass.billingmanagementapi.username.service.UsernameCommandService;
import ro.myclass.billingmanagementapi.username.service.UsernameQuerryImplService;
import ro.myclass.billingmanagementapi.username.service.UsernameQuerryService;
import ro.myclass.billingmanagementapi.username.service.UsernameCommandImplService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsernameImplServiceTest {

    @Mock
    private UsernameRepo usernameRepo;

    @InjectMocks
    private UsernameCommandService usernameService = new UsernameCommandImplService(usernameRepo);

    @InjectMocks
    private UsernameQuerryService usernameQuerryService = new UsernameQuerryImplService(usernameRepo);

    @Captor
    private ArgumentCaptor<Username> argumentCaptor;

    @Test
    public void getAllUsername() {
        //generate 4 usernames objects   with all properties
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

        doReturn(usernameList).when(usernameRepo).getAllUsername();


        assertEquals(usernameList, usernameQuerryService.getAllUsernames());

    }

    @Test

    public void getAllUsernameException() {
        doReturn(new ArrayList<>()).when(usernameRepo).getAllUsername();

        assertThrows(ListEmptyException.class, () -> usernameQuerryService.getAllUsernames());
    }

    @Test
    public void addUsername() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        UsernameDTO usernameDTO = UsernameDTO.builder().username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.empty()).when(usernameRepo).getUsernameByUsername(usernameDTO.getUsername());

        usernameService.addUsername(usernameDTO);

        verify(usernameRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(username,argumentCaptor.getValue());

    }


    @Test
    public void addUsernameException() {
        UsernameDTO usernameDTO = UsernameDTO.builder().username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.of(new Username())).when(usernameRepo).getUsernameByUsername(usernameDTO.getUsername());

        assertThrows(UsernameWasFoundException.class, () -> usernameService.addUsername(usernameDTO));
    }

    @Test
    public void deleteUsername() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.of(username)).when(usernameRepo).getUsernameByUsername(username.getUsername());

        usernameService.deleteUsername(username.getUsername());

        verify(usernameRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(username,argumentCaptor.getValue());
    }

    @Test
    public void deleteUsernameException() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.empty()).when(usernameRepo).getUsernameByUsername(username.getUsername());

        assertThrows(UsernameNotFoundException.class, () -> usernameService.deleteUsername(username.getUsername()));
    }

    @Test
    public void getUsernameById() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.of(username)).when(usernameRepo).getUsernameById(username.getId());

        assertEquals(username,usernameQuerryService.getUsernameById(username.getId()));
    }

    @Test
    public void getUsernameByIdException() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.empty()).when(usernameRepo).getUsernameById(username.getId());

        assertThrows(UsernameNotFoundException.class,()->usernameQuerryService.getUsernameById(username.getId()));
    }

    @Test
    public void getUsernameByUsername() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.of(username)).when(usernameRepo).getUsernameByUsername(username.getUsername());

        assertEquals(username,usernameQuerryService.getUsernameByUsername(username.getUsername()));

    }

    @Test
    public void getUsernameByUsernameException() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.empty()).when(usernameRepo).getUsernameByUsername(username.getUsername());

        assertThrows(UsernameNotFoundException.class,()->usernameQuerryService.getUsernameByUsername(username.getUsername()));    }

    @Test
    public void getUsernameByEmail() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.of(username)).when(usernameRepo).getUsernameByEmail(username.getEmail());

        assertEquals(username, usernameQuerryService.getUsernameByEmail(username.getEmail()));
    }

    @Test
    public void getUsernameByEmailException() {
        Username username = Username.builder().id(1L).username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.empty()).when(usernameRepo).getUsernameByEmail(username.getEmail());

        assertThrows(UsernameNotFoundException.class,()->usernameQuerryService.getUsernameByEmail(username.getEmail()));

    }

    @Test
    public void updateUsername() {
        UsernameDTO usernameDTO = UsernameDTO.builder().username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        Username username = Username.builder().username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.of(username)).when(usernameRepo).getUsernameByUsername(usernameDTO.getUsername());

        usernameService.updateUsername(usernameDTO);

        verify(usernameRepo,times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(username,argumentCaptor.getValue());
    }

    @Test
    public void updateUsernameException() {
        UsernameDTO usernameDTO = UsernameDTO.builder().username("test").address("test").email("test@gmail.com").dob(LocalDate.now()).build();

        doReturn(Optional.empty()).when(usernameRepo).getUsernameByUsername(usernameDTO.getUsername());

        assertThrows(UsernameNotFoundException.class,()->usernameService.updateUsername(usernameDTO));
    }
}