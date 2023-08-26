package ro.myclass.billingmanagementapi.username.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.username.dto.UsernameDTO;

@Service
@Transactional
public interface UsernameCommandService {

    void addUsername(UsernameDTO usernameDTO);

    void deleteUsername(String username);

    void updateUsername(UsernameDTO usernameDTO);
}
